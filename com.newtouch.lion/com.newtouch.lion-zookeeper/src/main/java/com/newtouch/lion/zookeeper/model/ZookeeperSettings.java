/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ZookeeperZSettings.java 9552 2015年7月23日 下午8:36:09 WangLijun$
*/
package com.newtouch.lion.zookeeper.model; 

import java.io.Serializable;

/**
 * <p>
 * Title: ZookeeperSettings
 * </p>
 * <p>
 * Description:  ZookeeperSettings
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
public class ZookeeperSettings  implements Serializable{
	
	
	
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -4767090831406461775L;

	private int 	settingsId;
	
	private String  envName;
	
	private int 	maxThreadsOfZooKeeperCheck;
	
	private String 	description;

	
	public ZookeeperSettings() {}
	
	
	
	/**
	 * @param settingsId
	 * @param envName
	 * @param maxThreadsOfZooKeeperCheck
	 * @param description
	 */
	public ZookeeperSettings(int settingsId, String envName,
			int maxThreadsOfZooKeeperCheck, String description) {
		this.settingsId = settingsId;
		this.envName = envName;
		this.maxThreadsOfZooKeeperCheck = maxThreadsOfZooKeeperCheck;
		this.description = description;
	}



	/**
	 * @return the settingsId
	 */
	public int getSettingsId() {
		return settingsId;
	}

	/**
	 * @param settingsId the settingsId to set
	 */
	public void setSettingsId(int settingsId) {
		this.settingsId = settingsId;
	}

	/**
	 * @return the envName
	 */
	public String getEnvName() {
		return envName;
	}

	/**
	 * @param envName the envName to set
	 */
	public void setEnvName(String envName) {
		this.envName = envName;
	}

	/**
	 * @return the maxThreadsOfZooKeeperCheck
	 */
	public int getMaxThreadsOfZooKeeperCheck() {
		return maxThreadsOfZooKeeperCheck;
	}

	/**
	 * @param maxThreadsOfZooKeeperCheck the maxThreadsOfZooKeeperCheck to set
	 */
	public void setMaxThreadsOfZooKeeperCheck(int maxThreadsOfZooKeeperCheck) {
		this.maxThreadsOfZooKeeperCheck = maxThreadsOfZooKeeperCheck;
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
	
	
	
}

	