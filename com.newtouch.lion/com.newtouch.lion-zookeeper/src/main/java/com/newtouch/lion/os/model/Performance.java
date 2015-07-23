/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: Performance.java 9552 2015年5月27日 上午11:21:18 WangLijun$
*/
package com.newtouch.lion.os.model; 

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 * Title:操作系统监控信息
 * </p>
 * <p>
 * Description: 操作系统监控信息-Linux操作平台
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
public class Performance implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -8036530792461501988L;
	/**IP*/
	private String ip;
	/**主机名:hostname*/
	private String hostname;
	/**系统平均负载*/
	private LoadAverage loadAverage;
	/**进行监控信息*/
	private Tasks tasks;
	/**CPU使用率*/
	private String cpuUsage;
	/**CPU详细信息*/
	private CpuInfo cpuInfo;

	/**内存使用详细情况*/
	private String memoryUsage;
	/**内存详细信息*/
	private Memory memory;
	/**Swap详细信息*/
	private Swap swap;
	/*** 硬盘使用率情况*/
	private Map< String/**挂载点*/, String/**使用百分比*/ > diskUsage;
	/***
	 * 默认函数
	 */
	public Performance() {
		 super();
	}
	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * @return the hostname
	 */
	public String getHostname() {
		return hostname;
	}
	/**
	 * @param hostname the hostname to set
	 */
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	 
	/**
	 * @return the cpuUsage
	 */
	public String getCpuUsage() {
		return cpuUsage;
	}
	/**
	 * @param cpuUsage the cpuUsage to set
	 */
	public void setCpuUsage(String cpuUsage) {
		this.cpuUsage = cpuUsage;
	}
	/**
	 * @return the loadAverage
	 */
	public LoadAverage getLoadAverage() {
		return loadAverage;
	}
	/**
	 * @param loadAverage the loadAverage to set
	 */
	public void setLoadAverage(LoadAverage loadAverage) {
		this.loadAverage = loadAverage;
	}
	/**
	 * @return the memoryUsage
	 */
	public String getMemoryUsage() {
		return memoryUsage;
	}
	/**
	 * @param memoryUsage the memoryUsage to set
	 */
	public void setMemoryUsage(String memoryUsage) {
		this.memoryUsage = memoryUsage;
	}
	/**
	 * @return the diskUsage
	 */
	public Map<String, String> getDiskUsage() {
		return diskUsage;
	}
	/**
	 * @param diskUsage the diskUsage to set
	 */
	public void setDiskUsage(Map<String, String> diskUsage) {
		this.diskUsage = diskUsage;
	}
	/**
	 * @return the memory
	 */
	public Memory getMemory() {
		return memory;
	}
	/**
	 * @param memory the memory to set
	 */
	public void setMemory(Memory memory) {
		this.memory = memory;
	}
	/**
	 * @return the swap
	 */
	public Swap getSwap() {
		return swap;
	}
	/**
	 * @param swap the swap to set
	 */
	public void setSwap(Swap swap) {
		this.swap = swap;
	}
	/**
	 * @return the tasks
	 */
	public Tasks getTasks() {
		return tasks;
	}
	/**
	 * @param tasks the tasks to set
	 */
	public void setTasks(Tasks tasks) {
		this.tasks = tasks;
	}
	/**
	 * @return the cpuInfo
	 */
	public CpuInfo getCpuInfo() {
		return cpuInfo;
	}
	/**
	 * @param cpuInfo the cpuInfo to set
	 */
	public void setCpuInfo(CpuInfo cpuInfo) {
		this.cpuInfo = cpuInfo;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Performance [ip=" + ip + ", hostname=" + hostname
				+ ", loadAverage=" + loadAverage.toString() + ", tasks=" + tasks.toString()
				+ ", cpuUsage=" + cpuUsage + ", cpuInfo=" + cpuInfo.toString()
				+ ", memoryUsage=" + memoryUsage + ", memory=" + memory.toString()
				+ ", swap=" + swap.toString() + ", diskUsage=" + diskUsage + "]";
	}
	
	
}

	