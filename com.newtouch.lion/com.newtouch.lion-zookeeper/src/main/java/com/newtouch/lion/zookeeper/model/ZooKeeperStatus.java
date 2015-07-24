/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ZooKeeperStatus.java 9552 2015年7月23日 下午8:12:40 WangLijun$
*/
package com.newtouch.lion.zookeeper.model; 

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: Zookeeper 状态信息
 * </p>
 * <p>
 * Description: : Zookeeper 状态信息
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
public class ZooKeeperStatus {
	private String ip;
	private List< String > clientConnectionList;
	private Map< String,Connection> connections;
	@SuppressWarnings("unused")
	private String connectionsContent;
	/** watch nums */
	private int watches;
	private int watchedPaths;
	private boolean isLeader;
	private String mode;
	private long nodeCount;
	private String statContent;
	/** 0:不确定 1:OK 2: ERROR*/
	private int statusType = 0;

	private String sent;
	private String Received;
	
	private Map<String/**session id*/, List<String> /** watched path list */ > watchedPathMap;
	private String watchedPathMapContent;
}

	