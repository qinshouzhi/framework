/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: Dependency.java 9552 2015年3月11日 上午9:53:54 WangLijun$
*/
package com.newtouch.lion.mgt.model; 


/**
 * <p>
 * Title:  Maven 依赖模型
 * </p>
 * <p>
 * Description:  Maven 依赖模型
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
public class Dependency {
	
	private String groupId;
	
	private String artifactId;
	
	private String version;
	
	private String scope;

	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the artifactId
	 */
	public String getArtifactId() {
		return artifactId;
	}

	/**
	 * @param artifactId the artifactId to set
	 */
	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the scope
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * @param scope the scope to set
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}

	
	
	
 }

	