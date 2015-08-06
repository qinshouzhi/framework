/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ZookeeperStatusCollector.java 9552 2015年7月24日 下午10:36:22 WangLijun$
*/
package com.newtouch.lion.monitor.task; 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newtouch.lion.alarm.model.AlarmSettings;
import com.newtouch.lion.monitor.MonitorInfo;
import com.newtouch.lion.os.ssh.JschConfig;
import com.newtouch.lion.os.ssh.JschHelper;
import com.newtouch.lion.util.IPUtil;
import com.newtouch.lion.util.ObjectUtil;
import com.newtouch.lion.zookeeper.command.ZookeeperCommand;
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
	/**MODE_FOLLOWER:Mode: follower*/
	public static final String MODE_FOLLOWER = "Mode: follower";
	/**Mode: leader*/
	public static final String MODE_LEADERER = "Mode: leader";
	/**Mode: standalone*/
	public static final String MODE_STANDALONE = "Mode: standalone";
	/**Node count:*/
	public static final String NODE_COUNT = "Node count:";
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
	
	/***
	 * 
	 * @param ip 服务器IP
	 * @param port  服务器端口
	 * @param zooKeeperStatus Zookeeper的状态数据
	 */
	public void zookeeperHandleStat(String ip,String port,ZooKeeperStatus zooKeeperStatus){
		//操作连接信息
		JschConfig  config=new JschConfig(ip,MonitorInfo.OS_USERNAME,MonitorInfo.OS_PASSWORD,Integer.parseInt(port));
		String command=ZookeeperCommand.format(ZookeeperCommand.COMMAND_STAT, new Object[]{ip,port});
		logger.info("command:{}",command);
		//执行命令
		String result=JschHelper.exec(config,command);
		logger.debug("result:{}",result);
		/**
		 * 
		 * 通常的内容是这样： Zookeeper version: 3.3.3-1073969, built on 02/23/2011
		 * 22:27 GMT Clients:
		 * /1.2.37.111:43681[1](queued=0,recved=434,sent=434)
		 * /10.13.44.47:54811[0](queued=0,recved=1,sent=0)
		 * 
		 * Latency min/avg/max: 0/1/227 Received: 2349 Sent: 2641
		 * Outstanding: 0 Zxid: 0xc00000243 Mode: follower Node count: 8
		 */
		zooKeeperStatus.setIp(ip);
		if(!StringUtils.isEmpty(result)){
			String line=null;
			BufferedReader reader=new BufferedReader(new StringReader(result));
			List< String > clientConnectionList = new ArrayList< String >();
			try {
				while((line=reader.readLine())!=null){
					if(this.analyseLineIfClientConnection(line)){
						clientConnectionList.add(line);
					}else if(line.contains(MODE_FOLLOWER)){
						zooKeeperStatus.setMode(MonitorInfo.ZK_MODE_FOLLOWER);
					}else if(line.contains(MODE_STANDALONE)){
						zooKeeperStatus.setMode(MonitorInfo.ZK_MODE_STANDALONE);
					}else if(line.contains(MonitorInfo.ZK_MODE_LEADER)){
						zooKeeperStatus.setMode(MonitorInfo.ZK_MODE_LEADER);
					}
					//TODO
				}
			} catch (IOException e) {
				logger.error(StringUtils.EMPTY,e);
			}finally{
				IOUtils.closeQuietly(reader);
			}
		}
	}
	/** 分析一行内容, 判断是否为客户端连接 */
	private boolean analyseLineIfClientConnection( String line ) {
		if ( StringUtils.isBlank(line) ) {
			return false;
		}
		// 标准的一行客户端连接是这样的
		// /1.2.37.111:43681[1](queued=0,recved=434,sent=434)
		line = StringUtils.trimToEmpty( line );
		if ( line.startsWith( "/" ) &&IPUtil.containsIPAddress(line ) ) {
			return true;
		}
		return false;
	}
}

	