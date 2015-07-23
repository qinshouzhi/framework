/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ZooKeeperCluster.java 9552 2015年5月27日 下午2:13:55 WangLijun$
*/
package com.newtouch.lion.zookeeper.model; 

import java.util.List;

/**
 * <p>
 * Title:Zookeeper Cluster集群信息
 * </p>
 * <p>
 * Description: Zookeeper Cluster集群信息
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
public class ZooKeeperCluster {
	
	/**集群ID*/
	private String clusterId;
	/**集群名称*/
	private String clusterName;
	/**集群描述*/
	private String description;
	/**集群地址信息 ip:prot */
	private List< String > serverList;
	/***
	 * 默认地址信息
	 */
	public ZooKeeperCluster() {
		super();
	}
	
	
	
	/**
	 * @param clusterId
	 * @param clusterName
	 * @param description
	 * @param serverList
	 */
	public ZooKeeperCluster(String clusterId, String clusterName,
			String description, List<String> serverList) {
		super();
		this.clusterId = clusterId;
		this.clusterName = clusterName;
		this.description = description;
		this.serverList = serverList;
	}



	/**
	 * @return the clusterId
	 */
	public String getClusterId() {
		return clusterId;
	}
	/**
	 * @param clusterId the clusterId to set
	 */
	public void setClusterId(String clusterId) {
		this.clusterId = clusterId;
	}
	/**
	 * @return the clusterName
	 */
	public String getClusterName() {
		return clusterName;
	}
	/**
	 * @param clusterName the clusterName to set
	 */
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the serverList
	 */
	public List<String> getServerList() {
		return serverList;
	}
	/**
	 * @param serverList the serverList to set
	 */
	public void setServerList(List<String> serverList) {
		this.serverList = serverList;
	}
	
	
}

	