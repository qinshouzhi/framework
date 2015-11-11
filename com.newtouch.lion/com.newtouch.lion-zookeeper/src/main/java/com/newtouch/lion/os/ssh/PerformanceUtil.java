/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: LinuxPerformanceUtil.java 9552 2015年5月27日 下午2:53:38 WangLijun$
 */
package com.newtouch.lion.os.ssh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newtouch.lion.os.constant.Command;
import com.newtouch.lion.os.constant.PerformanceKey;
import com.newtouch.lion.os.constant.Spliter;
import com.newtouch.lion.os.model.CpuInfo;
import com.newtouch.lion.os.model.LoadAverage;
import com.newtouch.lion.os.model.Memory;
import com.newtouch.lion.os.model.Performance;
import com.newtouch.lion.os.model.ReleaseInfo;
import com.newtouch.lion.os.model.Swap;
import com.newtouch.lion.os.model.Tasks;

/**
 * <p>
 * Title: Linux 性能分析
 * </p>
 * <p>
 * Description: Linux 性能分析
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class PerformanceUtil {

	/** 日志 */
	private static final Logger logger = LoggerFactory
			.getLogger(PerformanceUtil.class);

	/****
	 * @param config
	 *            配置信息
	 * @return Map<String,String>
	 */
	public static Map<String, String> getDiskUsage(JschConfig config) {
		String execResult = JschHelper.exec(config.getIp(),config.getUsername(), config.getPassword(), config.getPort(),Command.DF_LH);
		return parseDiskUsage(execResult);
	}

	/****
	 * 解析磁盘信息
	 * 
	 * @param execResult
	 *            配置信息
	 * @return Map<String,String>
	 */
	public static Map<String, String> parseDiskUsage(String execResult) {
		Map<String, String> diskUsage = new HashMap<String, String>();
		if (StringUtils.isEmpty(execResult)) {
			return diskUsage;
		}
		BufferedReader reader = new BufferedReader(new StringReader(execResult));
		String line = null;// 行数据
		boolean isFirstLine=true;
		try {
			while ((line = reader.readLine()) != null) {

				if ( isFirstLine ) {
					isFirstLine = false;
					continue;
				}
				if ( StringUtils.isBlank( line ) )
					continue;

				line = line.replaceAll( " {1,}", PerformanceKey.WORD_SEPARATOR );
				String[] lineArray = line.split( PerformanceKey.WORD_SEPARATOR );
				if ( 6 != lineArray.length ) {
					continue;
				}
				String diskUsageStr = lineArray[4];
				String mountedOnKey= lineArray[5];
				diskUsage.put( mountedOnKey, diskUsageStr );
			}
		} catch (IOException e) {
			logger.error("", e);
		} finally {
			IOUtils.closeQuietly(reader);
		}
		return diskUsage;
	}

	/***
	 * 服务器连接信息
	 * 
	 * @param config
	 *            SSH连接配置信息
	 * @return
	 */
	public static Performance getPerformance(JschConfig config) {
		// TOP执行结果O
		String execResult = JschHelper.exec(config.getIp(),
				config.getUsername(), config.getPassword(), config.getPort(),
				Command.TOP_HEAD);
		return parsePerformance(execResult);
	}

	/***
	 * 解析Top性能信息
	 * 
	 * @param execResult
	 * @param releaseInfo
	 * @return
	 */
	public static Performance parsePerformance(String execResult) {
		Performance performance = new Performance();
		if (StringUtils.isEmpty(execResult)) {
			return performance;
		}
		BufferedReader reader = new BufferedReader(new StringReader(execResult));
		String line = null;// 行数据
		int lineNum = 0;// 行计数器
		try {
			while ((line = reader.readLine()) != null) {
				logger.info("line:{}", line);
				lineNum++;
				switch (lineNum) {
				case 1:
					performance.setLoadAverage(parseLoadAverage(line));
					break;
				case 2:
					performance.setTasks(parseTasks(line));
					break;
				case 3:
					performance.setCpuInfo(parseCpuInfo(line));
					break;
				case 4:
					performance.setMemory(parseMemory(line));
					break;
				case 5:
					performance.setSwap(parseSwap(line));
					break;
				}
			}
		} catch (IOException e) {
			logger.error("", e);
		} finally {
			IOUtils.closeQuietly(reader);
		}
		return performance;
	}

	/***
	 * 解析Swap的信息
	 * 
	 * @param line
	 * @return
	 */
	public static Swap parseSwap(String line) {
		Swap swap = new Swap();
		// 内存
		line = StringUtils.split(line, Spliter.COLON)[1];
		String[] strs = line.split(Spliter.COMMA);
		if (strs.length == 4) {
			/** 内存总量 */
			String total = StringUtils
					.replace(strs[0], PerformanceKey.SWAP_TOTAL,
							StringUtils.EMPTY)
					.replace(PerformanceKey.KB, StringUtils.EMPTY).trim();

			/** 内存剩余空间 */
			String free = StringUtils
					.replace(strs[2], PerformanceKey.SWAP_FREE,
							StringUtils.EMPTY)
					.replace(PerformanceKey.KB, StringUtils.EMPTY).trim();
			;
			/** 内存已使用量 */
			String used = StringUtils
					.replace(strs[1], PerformanceKey.SWAP_USED,
							StringUtils.EMPTY)
					.replace(PerformanceKey.KB, StringUtils.EMPTY).trim();
			/** 内存缓存量 */
			String catched = StringUtils
					.replace(strs[3], PerformanceKey.SWAP_CACHED,
							StringUtils.EMPTY)
					.replace(PerformanceKey.KB, StringUtils.EMPTY).trim();
			swap.setTotal(NumberUtils.toLong(total));
			swap.setFree(NumberUtils.toLong(free));
			swap.setUsed(NumberUtils.toLong(used));
			swap.setAvailMem(NumberUtils.toLong(catched));
		} else {
			/** 内存总量 */
			String total = StringUtils
					.replace(strs[0], PerformanceKey.SWAP_TOTAL,
							StringUtils.EMPTY)
					.replace(PerformanceKey.KB, StringUtils.EMPTY).trim();
			/** 内存已使用量 */
			String free = StringUtils
					.replace(strs[1], PerformanceKey.SWAP_FREE,
							StringUtils.EMPTY)
					.replace(PerformanceKey.KB, StringUtils.EMPTY).trim();

			String[] tempStrs = strs[2].split(PerformanceKey.SWAP_USED);
			if (tempStrs.length == 2) {
				/** 内存剩余空间 */
				String used = StringUtils
						.replace(tempStrs[0], PerformanceKey.SWAP_USED,
								StringUtils.EMPTY)
						.replace(PerformanceKey.KB, StringUtils.EMPTY).trim();
				;
				/** 内存缓存量 */
				String catched = StringUtils
						.replace(tempStrs[1], PerformanceKey.SWAP_AVAIL_MEM,
								StringUtils.EMPTY)
						.replace(Spliter.PREIOD, StringUtils.EMPTY).trim();
				swap.setUsed(NumberUtils.toLong(used));
				swap.setAvailMem(NumberUtils.toLong(catched));
			}
			swap.setTotal(NumberUtils.toLong(total));
			swap.setFree(NumberUtils.toLong(free));

		}
		return swap;
	}

	/***
	 * 解析Memory的信息
	 * 
	 * @param line
	 *            内存信息 如格式：Mem: 1012352k total, 186740k used, 825612k free,
	 *            17588k buffers
	 * @return Memory
	 */
	public static Memory parseMemory(String line) {
		Memory memory = new Memory();
		// 内存
		line = StringUtils.split(line, Spliter.COLON)[1];
		String[] strs = line.split(Spliter.COMMA);
		if (strs.length == 4) {
			/** 内存总量 */
			String total = StringUtils
					.replace(strs[0], PerformanceKey.MEM_TOTAL,
							StringUtils.EMPTY)
					.replace(PerformanceKey.KB, StringUtils.EMPTY).trim();
			/** 内存已使用量 */
			String used = StringUtils
					.replace(strs[1], PerformanceKey.MEM_FREE,
							StringUtils.EMPTY)
					.replace(PerformanceKey.KB, StringUtils.EMPTY).trim();

			/** 内存剩余空间 */
			String free = StringUtils
					.replace(strs[2], PerformanceKey.MEM_USED,
							StringUtils.EMPTY)
					.replace(PerformanceKey.KB, StringUtils.EMPTY).trim();
			;
			/** 内存缓存量 */
			String buffers = StringUtils
					.replace(strs[3], PerformanceKey.MEM_BUFFERS,
							StringUtils.EMPTY)
					.replace(PerformanceKey.KB, StringUtils.EMPTY).trim();
			memory.setTotal(NumberUtils.toLong(total));
			memory.setFree(NumberUtils.toLong(free));
			memory.setUsed(NumberUtils.toLong(used));
			memory.setBuffers(NumberUtils.toLong(buffers));
		}

		return memory;
	}

	/***
	 * 解析CPU的信息
	 * 
	 * @param line
	 *            CPU信息字符串 Cpu(s): 4.5%us, 13.6%sy, 0.0%ni, 68.4%id, 13.5%wa,
	 *            0.1%hi, 0.0%si, 0.0%st
	 * @return CpuInfo
	 */
	public static CpuInfo parseCpuInfo(String line) {
		CpuInfo cpuInfo = new CpuInfo();
		// CPU
		line = StringUtils.split(line, Spliter.COLON)[1].replace("%",
				StringUtils.EMPTY);
		String[] strs = line.split(Spliter.COMMA);
		if (strs.length == 8) {
			/** 用户使用率 */
			String userUsed = StringUtils.replace(strs[0],
					PerformanceKey.CPU_US, StringUtils.EMPTY).trim();
			/** 系统占用离率 */
			String systemUsed = StringUtils.replace(strs[1],
					PerformanceKey.CPU_SY, StringUtils.EMPTY).trim();
			/** ni 改变过优先级的进程占用CPU的百分比 */
			String ni = StringUtils.replace(strs[2], PerformanceKey.CPU_NI,
					StringUtils.EMPTY).trim();
			/** id 空闲CPU百分比 */
			String id = StringUtils.replace(strs[3], PerformanceKey.CPU_ID,
					StringUtils.EMPTY).trim();
			/** wa IO等待占用CPU的百分比 */
			String wa = StringUtils.replace(strs[4], PerformanceKey.CPU_WA,
					StringUtils.EMPTY).trim();
			/** hi 硬中断（Hardware IRQ）占用CPU的百分比 */
			String hi = StringUtils.replace(strs[5], PerformanceKey.CPU_HI,
					StringUtils.EMPTY).trim();
			/** si 软中断（Software Interrupts）占用CPU的百分比 */
			String si = StringUtils.replace(strs[6], PerformanceKey.CPU_SI,
					StringUtils.EMPTY).trim();
			/** st 虚拟机偷取时间 */
			String st = StringUtils.replace(strs[7], PerformanceKey.CPU_ST,
					StringUtils.EMPTY).trim();

			cpuInfo.setUserUsed(NumberUtils.toDouble(userUsed));
			cpuInfo.setSystemUsed(NumberUtils.toDouble(systemUsed));
			cpuInfo.setNi(NumberUtils.toDouble(ni));
			cpuInfo.setId(NumberUtils.toDouble(id));
			cpuInfo.setWa(NumberUtils.toDouble(wa));
			cpuInfo.setHi(NumberUtils.toDouble(hi));
			cpuInfo.setSi(NumberUtils.toDouble(si));
			cpuInfo.setSt(NumberUtils.toDouble(st));
		}
		return cpuInfo;
	}

	/***
	 * 解析系统进程信息
	 * 
	 * @param line
	 *            监控字符串信息 如： Tasks: 97 total, 1 running, 96 sleeping, 0 stopped,
	 *            0 zombie
	 * @return Tasks
	 */
	public static Tasks parseTasks(String line) {
		Tasks tasks = new Tasks();
		// 获取选择时间
		line = StringUtils.removeStart(line, PerformanceKey.TASKS);
		String[] strs = line.split(Spliter.COMMA);
		if (strs.length == 5) {
			/** 进程总数 */
			String total = StringUtils.replace(strs[0],
					PerformanceKey.TASKS_TOTAL, StringUtils.EMPTY).trim();
			/** 正在运行的进程数 */
			String running = StringUtils.replace(strs[1],
					PerformanceKey.TASKS_RUNNING, StringUtils.EMPTY).trim();
			/** 睡眠的进程数 */
			String sleeping = StringUtils.replace(strs[2],
					PerformanceKey.TASKS_SLEEPING, StringUtils.EMPTY).trim();
			/** 停止的进程数 */
			String stopped = StringUtils.replace(strs[3],
					PerformanceKey.TASKS_STOPPED, StringUtils.EMPTY).trim();
			/** 僵尸进程数 */
			String zombie = StringUtils.replace(strs[4],
					PerformanceKey.TASKS_ZOMBIE, StringUtils.EMPTY).trim();

			tasks.setTotal(NumberUtils.toLong(total));
			tasks.setRunning(NumberUtils.toLong(running));
			tasks.setSleeping(NumberUtils.toLong(sleeping));
			tasks.setStopped(NumberUtils.toLong(stopped));
			tasks.setZombie(NumberUtils.toLong(zombie));
		}
		return tasks;
	}

	/***
	 * 解析系统负载信息
	 * 
	 * @param line
	 *            字符 如：top - 07:43:33 up 1 min, 1 user, load average: 0.29,
	 *            0.20, 0.08
	 * @return LoadAverage
	 */
	public static LoadAverage parseLoadAverage(String line) {
		LoadAverage loadAverage = new LoadAverage();
		// 获取选择时间
		line = StringUtils.removeStart(line, PerformanceKey.TOP_);
		int average = StringUtils.lastOrdinalIndexOf(line, Spliter.COLON, 1);
		String lineAverage = line.substring(average + 1, line.length());
		line = line.substring(0, average);

		String[] strs = line.split(Spliter.COMMA);
		if (strs.length == 3) {
			// 系统时间
			String[] dates = strs[0].split(PerformanceKey.TOP_UP);
			loadAverage.setDate(dates[0].trim());
			// 已运行时间
			loadAverage.setRuntime(dates[1].trim());
			String user = strs[1].replace(PerformanceKey.TOP_USER,
					StringUtils.EMPTY).trim();
			// 用户数
			loadAverage.setUsers(NumberUtils.toLong(user));
		} else if (strs.length == 4) {
			// 系统时间
			String[] dates = strs[0].split(PerformanceKey.TOP_UP);
			loadAverage.setDate(dates[0].trim());
			// 已运行时间
			loadAverage.setRuntime(dates[1] + PerformanceKey.WHITE_SPACE
					+ strs[1].trim());
			String user = strs[2].replace(PerformanceKey.TOP_USER,
					StringUtils.EMPTY).trim();
			// 用户数
			loadAverage.setUsers(NumberUtils.toLong(user));
		}
		// 负载信息统计

		String[] lineAverages = lineAverage.split(Spliter.COMMA);
		if (lineAverages.length == 3) {
			loadAverage
					.setOneMinute(StringUtils.isNumeric(lineAverages[0]) ? Double
							.parseDouble(lineAverages[0]) : 0d);
			loadAverage
					.setFiveMinutes(StringUtils.isNumeric(lineAverages[1]) ? Double
							.parseDouble(lineAverages[0]) : 0d);
			loadAverage
					.setQuarter(StringUtils.isNumeric(lineAverages[2]) ? Double
							.parseDouble(lineAverages[0]) : 0d);
		}

		return loadAverage;
	}

	/***
	 * 查询服务器发行版本信息,注要安装
	 * 
	 * @param config
	 *            连接配置信息
	 * @return ReleaseInfo
	 */
	public static ReleaseInfo getReleaseInfo(JschConfig config) {
		String execResult = JschHelper.exec(config, Command.LSB_RELEASE_A);
		return parseReleaseInfo(execResult);
	}

	/***
	 * 根据执行结果解析成对象
	 * 
	 * @param execResult
	 *            执行结果数据
	 * @return ReleaseInfo
	 */
	private static ReleaseInfo parseReleaseInfo(String execResult) {
		ReleaseInfo releaseInfo = new ReleaseInfo();
		if (StringUtils.isEmpty(execResult)) {
			return releaseInfo;
		}
		BufferedReader reader = new BufferedReader(new StringReader(execResult));

		String line = null;// 行数据
		int lineNum = 0;// 行计数器
		boolean includeVersion = true;
		try {
			while ((line = reader.readLine()) != null) {
				logger.info("line:{}", line);
				lineNum++;
				// 判断是否版本信息
				if (lineNum == 1 && !line.contains(PerformanceKey.VERSION)) {
					includeVersion = false;
				}
				// 包括版本信息解析方法
				if (includeVersion) {
					switch (lineNum) {
					case 1:
						releaseInfo.setVersion(replace(line,
								PerformanceKey.VERSION));
						break;
					case 2:
						releaseInfo.setDistributorId(splitString(line,
								Spliter.COLON, 1));
						break;
					case 3:
						releaseInfo.setDescription(splitString(line,
								Spliter.COLON, 1));
						break;
					case 4:
						releaseInfo.setRelease(splitString(line, Spliter.COLON,
								1));
						break;
					case 5:
						releaseInfo.setCodeName(splitString(line,
								Spliter.COLON, 1));
						break;
					}
				} else {
					// 不包版本信息解析方法
					switch (lineNum) {
					case 1:
						releaseInfo.setDistributorId(splitString(line,
								Spliter.COLON, 1));
						break;
					case 2:
						releaseInfo.setDescription(splitString(line,
								Spliter.COLON, 1));
						break;
					case 3:
						releaseInfo.setRelease(splitString(line, Spliter.COLON,
								1));
						break;
					case 4:
						releaseInfo.setCodeName(splitString(line,
								Spliter.COLON, 1));
						break;
					}
				}
			}
		} catch (IOException e) {
			logger.error("", e);
		} finally {
			IOUtils.closeQuietly(reader);
		}
		return releaseInfo;
	}

	/**
	 * 替换字符串
	 * 
	 * @param source
	 *            源字符串
	 * @param target
	 *            替换字符串
	 * @return 字符串
	 */
	public static String replace(String source, String target) {
		if (StringUtils.isNotEmpty(source)) {
			return StringUtils.replace(source, target, StringUtils.EMPTY)
					.trim();
		}
		return StringUtils.EMPTY;
	}

	/***
	 * 字符串分割
	 * 
	 * @param str
	 *            源字符串
	 * @param spliter
	 *            分割号
	 * @param index
	 *            获取索引值
	 * @return String
	 */
	public static String splitString(String str, String spliter, int index) {
		if (StringUtils.isNotEmpty(str)) {
			String[] strs = str.split(spliter);
			if (strs != null && strs.length > index) {
				return StringUtils.trim(strs[index]);
			}
		}
		return StringUtils.EMPTY;
	}

	public static void main(String[] args) {
		CpuInfo cpuInfo = PerformanceUtil
				.parseCpuInfo("Cpu(s):  4.5%us, 13.6%sy,  0.0%ni, 68.4%id, 13.5%wa,  0.1%hi,  0.0%si,  0.0%st");
		logger.info("LoadAverage:{}", cpuInfo.toString());
		Memory memory = PerformanceUtil
				.parseMemory("Mem:   1012352k total,   186740k used,   825612k free,    17588k buffers");
		logger.info("LoadAverage:{}", memory.toString());
	}
}
