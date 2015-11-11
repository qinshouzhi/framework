/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ZookeeperConfig.java 9552 2015年5月25日 下午2:19:03 WangLijun$
*/
package com.newtouch.lion.zookeeper.config; 


/**
 * <p>
 * Title: Zookeeper 连接配置信息
 * </p>
 * <p>
 * Description: 包括连接地址，会话超时、连接超时、是否只读、重试最大次数及重试等待时间
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
public class ZookeeperConfig {
	/***
	 * Zookeeper连接地址信息
	 */
	private String connectString="127.0.0.1:2181";
	/**会话超时间 默认为30秒*/
	private Integer  sessionTimeout=30000;
	/**连接超时 30秒*/
	private Integer  connectionTimeout=30000;
	/**是否只读:默认为false*/
	private Boolean readOnly=Boolean.FALSE;
	/**** 重试机制－等待时间 默认为1000 */
	private Integer baseSleepTime=1000;
	/**** 重试机制－重试最大次数 默认为：3*/
	private Integer maxRetries=3;
	/**命名空间,默认为：zoo*/
	private String nameSpace="zoo";
	/**
	 * 默认构造 函数
	 */
	public ZookeeperConfig() {
		 super();
	}
	
	
	
	
	 
	
	


	/**
	 * @param connectString
	 */
	public ZookeeperConfig(String connectString) {
		this.connectString = connectString;
	}

 



	/**
	 * @param connectString
	 * @param sessionTimeout
	 * @param connectionTimeout
	 * @param readOnly
	 * @param baseSleepTime
	 * @param maxRetries
	 * @param nameSpace
	 */
	public ZookeeperConfig(String connectString, Integer sessionTimeout,
			Integer connectionTimeout, Boolean readOnly, Integer baseSleepTime,
			Integer maxRetries, String nameSpace) {
		super();
		this.connectString = connectString;
		this.sessionTimeout = sessionTimeout;
		this.connectionTimeout = connectionTimeout;
		this.readOnly = readOnly;
		this.baseSleepTime = baseSleepTime;
		this.maxRetries = maxRetries;
		this.nameSpace = nameSpace;
	}



	/**
	 * @return the connectString
	 */
	public String getConnectString() {
		return connectString;
	}
	/**
	 * @param connectString the connectString to set
	 */
	public void setConnectString(String connectString) {
		this.connectString = connectString;
	}
	/**
	 * @return the sessionTimeout
	 */
	public Integer getSessionTimeout() {
		return sessionTimeout;
	}
	/**
	 * @param sessionTimeout the sessionTimeout to set
	 */
	public void setSessionTimeout(Integer sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}
	/**
	 * @return the connectionTimeout
	 */
	public Integer getConnectionTimeout() {
		return connectionTimeout;
	}
	/**
	 * @param connectionTimeout the connectionTimeout to set
	 */
	public void setConnectionTimeout(Integer connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}
	/**
	 * @return the readOnly
	 */
	public Boolean getReadOnly() {
		return readOnly;
	}
	/**
	 * @param readOnly the readOnly to set
	 */
	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}
	/**
	 * @return the baseSleepTime
	 */
	public Integer getBaseSleepTime() {
		return baseSleepTime;
	}
	/**
	 * @param baseSleepTime the baseSleepTime to set
	 */
	public void setBaseSleepTime(Integer baseSleepTime) {
		this.baseSleepTime = baseSleepTime;
	}
	/**
	 * @return the maxRetries
	 */
	public Integer getMaxRetries() {
		return maxRetries;
	}
	/**
	 * @param maxRetries the maxRetries to set
	 */
	public void setMaxRetries(Integer maxRetries) {
		this.maxRetries = maxRetries;
	}
	/**
	 * @return the nameSpace
	 */
	public String getNameSpace() {
		return nameSpace;
	}
	/**
	 * @param nameSpace the nameSpace to set
	 */
	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}
	
	
}

	