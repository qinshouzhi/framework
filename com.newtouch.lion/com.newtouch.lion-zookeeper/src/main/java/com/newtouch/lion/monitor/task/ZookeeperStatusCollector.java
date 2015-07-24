/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ZookeeperStatusCollector.java 9552 2015年7月24日 下午10:36:22 WangLijun$
*/
package com.newtouch.lion.monitor.task; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.newtouch.lion.alarm.model.AlarmSettings;
import com.newtouch.lion.util.ObjectUtil;
import com.newtouch.lion.zookeeper.model.ZooKeeperCluster;
import com.newtouch.lion.zookeeper.model.ZooKeeperStatus;

/**
 * <p>
 * Title: 采集zooKeeper上的状态信息
 * </p>
 * <p>
 * Description: 
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
public class ZookeeperStatusCollector implements Runnable {
	
	/**日志*/
	private static final Logger logger=LoggerFactory.getLogger(ZookeeperStatusCollector.class);
	/**IP*/
	private String ip;
	/**端口*/
	private String port;
	/**警告策略*/
	private AlarmSettings alarmSettings;
	/**ZK集群*/
	private ZooKeeperCluster zookeeperCluster;
	/**是否持久化*/
	private boolean needStore;
	
	

	/**
	 * @param ip
	 * @param port
	 * @param alarmSettings
	 * @param zookeeperCluster
	 */
	public ZookeeperStatusCollector(String ip, String port,
			AlarmSettings alarmSettings, ZooKeeperCluster zookeeperCluster) {
		this.ip = ip;
		this.port = port;
		this.alarmSettings = alarmSettings;
		this.zookeeperCluster = zookeeperCluster;
		this.needStore=true;
	}

	
	


	/**
	 * @param ip
	 * @param port
	 * @param alarmSettings
	 * @param zookeeperCluster
	 * @param needStore
	 */
	public ZookeeperStatusCollector(String ip, String port,AlarmSettings alarmSettings, ZooKeeperCluster zookeeperCluster,boolean needStore) {
		this(ip, port, alarmSettings, zookeeperCluster);
		this.needStore = needStore;
	}
 
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		//收集Zookeeper状态
		this.statusCollector();	
	}
	/**
	 *收集Zookeeper的状态信息
	 */
	private void statusCollector(){
		if(StringUtils.isEmpty(this.ip)||StringUtils.isEmpty(this.port)||ObjectUtil.isBlank(this.alarmSettings,this.zookeeperCluster)){
			return;
		}
		ZooKeeperStatus zooKeeperStatus=new ZooKeeperStatus();
	}
}

	