/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ConnectionStateListener.java 9552 2015年5月25日 下午4:09:32 WangLijun$
*/
package com.newtouch.lion.zookeeper.listener; 

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Title: Zookeeper 连接监听器
 * </p>
 * <p>
 * Description: Zookeeper 连接监听器
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
public class ZookeeperConnectionStateListener  implements ConnectionStateListener{
	/***
	 * 日志
	 */
	private static final Logger logger=LoggerFactory.getLogger(ZookeeperConnectionStateListener.class);
	
	/***
	 * 重写监控器
	 */
	@Override
	public void stateChanged(CuratorFramework client, ConnectionState newState) {
		logger.info("--------------------------------------");
		logger.info(newState.name());
	}
		
}

	