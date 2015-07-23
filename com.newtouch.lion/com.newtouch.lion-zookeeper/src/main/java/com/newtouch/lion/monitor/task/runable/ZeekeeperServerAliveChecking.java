/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ZeekeeperServerAliveChecking.java 9552 2015年5月29日 下午10:16:15 WangLijun$
*/
package com.newtouch.lion.monitor.task.runable; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Title: Zookeeper节点服务器自检查
 * </p>
 * <p>
 * Description: Zookeeper自检查节点服务器是指对集群中每个IP所在ZK节点上的PATH: /WATCHMAN.MONITOR.ALIVE.CHECKING 定期进行三次如下流程 :<br>
 *  节点连接 - 数据发布 - 修改通知 - 获取数据 - 数据对比, 三次流程均成功视为该节点处于正常状态。<br>
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
public class ZeekeeperServerAliveChecking implements Runnable{
	/***
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger( ZeekeeperServerAliveChecking.class );

	@Override
	public void run() {
		
	}
	
}

	