/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: PerformanceCollector.java 9552 2015年5月27日 下午2:10:03 WangLijun$
*/
package com.newtouch.lion.os.collector; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newtouch.lion.alarm.model.AlarmSettings;
import com.newtouch.lion.zookeeper.model.ZooKeeperCluster;

/**
 * <p>
 * Title: Linux 操盘系统性能信息收集
 * </p>
 * <p>
 * Description: Linux 操盘系统性能信息收集 CPU、MEM、IO、DISK
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
public class PerformanceCollector  implements Runnable{
	
	/**日志*/
	private static  final  Logger logger=LoggerFactory.getLogger(PerformanceCollector.class);
	
	/**IP*/
	private String ip;
	/**预警信息*/
	private AlarmSettings alarmSettings;
	/**ZooKeeperCluster*/
	private ZooKeeperCluster zooKeeperCluster;

	@Override
	public void run() {
		logger.info("start collector os ip:{}",ip);
	}

	
	
	/**
	 * 默认构造
	 */
	public PerformanceCollector() {
		super();
	}	



	/**
	 * @param ip
	 * @param alarmSettings
	 * @param zooKeeperCluster
	 */
	public PerformanceCollector(String ip, AlarmSettings alarmSettings,
			ZooKeeperCluster zooKeeperCluster) {
		this.ip = ip;
		this.alarmSettings = alarmSettings;
		this.zooKeeperCluster = zooKeeperCluster;
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
	 * @return the alarmSettings
	 */
	public AlarmSettings getAlarmSettings() {
		return alarmSettings;
	}

	/**
	 * @param alarmSettings the alarmSettings to set
	 */
	public void setAlarmSettings(AlarmSettings alarmSettings) {
		this.alarmSettings = alarmSettings;
	}

	/**
	 * @return the zooKeeperCluster
	 */
	public ZooKeeperCluster getZooKeeperCluster() {
		return zooKeeperCluster;
	}

	/**
	 * @param zooKeeperCluster the zooKeeperCluster to set
	 */
	public void setZooKeeperCluster(ZooKeeperCluster zooKeeperCluster) {
		this.zooKeeperCluster = zooKeeperCluster;
	}
}

	