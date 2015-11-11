/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ExecutorServiceManager.java 9552 2015年6月3日 上午11:24:10 WangLijun$
*/
package com.newtouch.lion.monitor; 

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Title: 线程池管理
 * </p>
 * <p>
 * Description: 线程池管理
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
public class ExecutorServiceManager {
	
	/**日志*/
	private static final Logger logger=LoggerFactory.getLogger(ExecutorServiceManager.class);
	/**Zookeeper状态监控线程池大小*/
	public static final  int ZKNODEALIVECHECK_EXECUTOR_SIZE=5;
	/**消息发送线程池大小*/
	public static final  int MESSAGESEND_EXECUTOR_SIZE=5;
	/**Zookeeper状态监控*/
	public static final int ZKSERVERSTATUS_COLLECTOR_EXECUTOR_SIZE=3;
	/**Zookeeper服务器信息收到线程大小*/
	public static final int ZKSERVERPERFORMAN_CECOLLECTOR_EXECUTOR_SIZE=3;
	/**Dump zk cluster config info to memeory*/
	public static final int ZKCLUSTERCONFIG_DUMPER_EXECUTOR_SIZE=2;
	/** 节点自检 线程池 */
	private static ExecutorService zooKeeperNodeAliveCheckExecutor;
	/** 消息发送 线程池 */
	private static ExecutorService messageSendExecutor;
	/** 收集ZKServer状态信息 线程池 */
	private static ExecutorService zkServerStatusCollectorExecutor;
	/** 收集ZKServer机器信息 线程池 */
	private static ExecutorService zkServerPerformanceCollectorExecutor;
	/** Dump zk cluster config info to memeory*/
	private static ExecutorService zkClusterConfigDumperExecutor;
	/**实始化线程池*/
	private static  void init(){
		if(null==zooKeeperNodeAliveCheckExecutor){
			logger.info( "Start init ThreadPoolManager..." );
			zooKeeperNodeAliveCheckExecutor=Executors.newFixedThreadPool(ZKNODEALIVECHECK_EXECUTOR_SIZE);
			messageSendExecutor=Executors.newFixedThreadPool(MESSAGESEND_EXECUTOR_SIZE);
			zkServerStatusCollectorExecutor=Executors.newFixedThreadPool(ZKSERVERSTATUS_COLLECTOR_EXECUTOR_SIZE);
			zkServerPerformanceCollectorExecutor=Executors.newFixedThreadPool(ZKSERVERPERFORMAN_CECOLLECTOR_EXECUTOR_SIZE);
			zkClusterConfigDumperExecutor=Executors.newFixedThreadPool(ZKCLUSTERCONFIG_DUMPER_EXECUTOR_SIZE);
		}
	}
	/***
	 * 添加Zookeeper是否存活的检测
	 * @param command
	 */
	public static  void addJobZooKeeperNodeAliveCheckExecutor(Runnable  command){
		ExecutorServiceManager.init();
		ExecutorServiceManager.zooKeeperNodeAliveCheckExecutor.execute(command);
	}
	
	
	public static  void addJobMessageSendExecutor(Runnable  command){
		ExecutorServiceManager.init();
		ExecutorServiceManager.messageSendExecutor.execute(command);
	}
	
	public static void addJobZookeeperServerStatusCollectorExecutor(Runnable command){
		ExecutorServiceManager.init();
		ExecutorServiceManager.zkServerStatusCollectorExecutor.execute(command);
	}
	
	public static void addJobServerPerformanceCollectorExecutor(Runnable command){
		ExecutorServiceManager.init();
		ExecutorServiceManager.zkServerPerformanceCollectorExecutor.execute(command);
	}
	
	public static void addJobZookeeperClusterConfigDumperExecutor(Runnable command){
		ExecutorServiceManager.init();
		ExecutorServiceManager.zkClusterConfigDumperExecutor.execute(command);
	}
	
	
}

	