/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: MonitorParameter.java 9552 2015年7月24日 下午2:44:11 WangLijun$
*/
package com.newtouch.lion.monitor; 
/**
 * <p>
 * Title: 监控常用属性定义
 * </p>
 * <p>
 * Description: 监控常用属性定义
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
public class MonitorInfo {
	/**监控Zookeeper命名空间*/
	public static final String ZOOKEEPER_MONITOR_NAMESPACE="zookeeper.monitor";
	/**监控Zookeeper 路径*/
	public static final String ZOOKEEPER_MONITOR_PATH="/alive.check";
	/**Zookeeper EventBus I*/
	public static final String ZOOKEEPER_EVENT_BUS_ID="zookeeper.monitor.eventbus";
	/**两次存活性检查间隔*/
	public final static int   ALIVE_CHECK_ZOOKEEPER_PER_TIME = 1; 
	/**用户名*/
	public final static String OS_USERNAME="root";
	/**默认密码*/
	public final static String OS_PASSWORD="newtouch.cn";
	/**ZK MODE follower*/
	public final static String ZK_MODE_FOLLOWER="F";
	/**ZK MODE follower*/
	public final static String ZK_MODE_STANDALONE="S";
	/**ZK MODE leader*/
	public final static String ZK_MODE_LEADER="L";
	
	
	
	
}

	