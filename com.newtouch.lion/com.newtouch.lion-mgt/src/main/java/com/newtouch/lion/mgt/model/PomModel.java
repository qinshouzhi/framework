/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: PomModel.java 9552 2015年3月11日 上午9:55:27 WangLijun$
 */
package com.newtouch.lion.mgt.model;

import java.util.List;

/**
 * <p>
 * Title: pom.xml 模型
 * </p>
 * <p>
 * Description: pom.xml 依赖模型
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
public class PomModel {

	private String groupId;

	private String artifactId;

	private String version;

	private String name;

	private String url;
	
	private Dependency parent;
	
	private List<Dependency> dependencies;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the parent
	 */
	public Dependency getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Dependency parent) {
		this.parent = parent;
	}

	/**
	 * @return the dependencies
	 */
	public List<Dependency> getDependencies() {
		return dependencies;
	}

	/**
	 * @param dependencies the dependencies to set
	 */
	public void setDependencies(List<Dependency> dependencies) {
		this.dependencies = dependencies;
	}
	
	
	
}
