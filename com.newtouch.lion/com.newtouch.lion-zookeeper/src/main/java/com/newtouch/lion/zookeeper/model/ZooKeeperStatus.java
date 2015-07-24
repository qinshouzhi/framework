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
	
	
	public ZooKeeperStatus() {
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
	 * @return the clientConnectionList
	 */
	public List<String> getClientConnectionList() {
		return clientConnectionList;
	}


	/**
	 * @param clientConnectionList the clientConnectionList to set
	 */
	public void setClientConnectionList(List<String> clientConnectionList) {
		this.clientConnectionList = clientConnectionList;
	}


	/**
	 * @return the connections
	 */
	public Map<String, Connection> getConnections() {
		return connections;
	}


	/**
	 * @param connections the connections to set
	 */
	public void setConnections(Map<String, Connection> connections) {
		this.connections = connections;
	}


	/**
	 * @return the connectionsContent
	 */
	public String getConnectionsContent() {
		return connectionsContent;
	}


	/**
	 * @param connectionsContent the connectionsContent to set
	 */
	public void setConnectionsContent(String connectionsContent) {
		this.connectionsContent = connectionsContent;
	}


	/**
	 * @return the watches
	 */
	public int getWatches() {
		return watches;
	}


	/**
	 * @param watches the watches to set
	 */
	public void setWatches(int watches) {
		this.watches = watches;
	}


	/**
	 * @return the watchedPaths
	 */
	public int getWatchedPaths() {
		return watchedPaths;
	}


	/**
	 * @param watchedPaths the watchedPaths to set
	 */
	public void setWatchedPaths(int watchedPaths) {
		this.watchedPaths = watchedPaths;
	}


	/**
	 * @return the isLeader
	 */
	public boolean isLeader() {
		return isLeader;
	}


	/**
	 * @param isLeader the isLeader to set
	 */
	public void setLeader(boolean isLeader) {
		this.isLeader = isLeader;
	}


	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}


	/**
	 * @param mode the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}


	/**
	 * @return the nodeCount
	 */
	public long getNodeCount() {
		return nodeCount;
	}


	/**
	 * @param nodeCount the nodeCount to set
	 */
	public void setNodeCount(long nodeCount) {
		this.nodeCount = nodeCount;
	}


	/**
	 * @return the statContent
	 */
	public String getStatContent() {
		return statContent;
	}


	/**
	 * @param statContent the statContent to set
	 */
	public void setStatContent(String statContent) {
		this.statContent = statContent;
	}


	/**
	 * @return the statusType
	 */
	public int getStatusType() {
		return statusType;
	}


	/**
	 * @param statusType the statusType to set
	 */
	public void setStatusType(int statusType) {
		this.statusType = statusType;
	}


	/**
	 * @return the sent
	 */
	public String getSent() {
		return sent;
	}


	/**
	 * @param sent the sent to set
	 */
	public void setSent(String sent) {
		this.sent = sent;
	}


	/**
	 * @return the received
	 */
	public String getReceived() {
		return Received;
	}


	/**
	 * @param received the received to set
	 */
	public void setReceived(String received) {
		Received = received;
	}


	/**
	 * @return the watchedPathMap
	 */
	public Map<String, List<String>> getWatchedPathMap() {
		return watchedPathMap;
	}


	/**
	 * @param watchedPathMap the watchedPathMap to set
	 */
	public void setWatchedPathMap(Map<String, List<String>> watchedPathMap) {
		this.watchedPathMap = watchedPathMap;
	}


	/**
	 * @return the watchedPathMapContent
	 */
	public String getWatchedPathMapContent() {
		return watchedPathMapContent;
	}


	/**
	 * @param watchedPathMapContent the watchedPathMapContent to set
	 */
	public void setWatchedPathMapContent(String watchedPathMapContent) {
		this.watchedPathMapContent = watchedPathMapContent;
	}
	
	
}

	