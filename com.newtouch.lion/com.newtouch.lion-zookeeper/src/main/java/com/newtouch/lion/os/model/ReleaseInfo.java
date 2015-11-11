/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RelaseInfo.java 9552 2015年5月27日 下午1:47:14 WangLijun$
*/
package com.newtouch.lion.os.model; 
/**
 * <p>
 * Title: 操作系统版本信息
 * </p>
 * <p>
 * Description: 操作系统版本信息
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
public class ReleaseInfo {
	
	/**版本信息*/
	private String version;
	/**操作系统名称：如CentOS,Redhat,ubuntu*/
	private String distributorId;
	/**Description 描述*/
	private String description;
	/**Release 发行版本号*/
	private String release;
	/**Codename*/
	private String codeName;
	
	public ReleaseInfo() {
		 super();
	}
	
	

	/**
	 * @param version
	 * @param distributorId
	 * @param description
	 * @param release
	 * @param codeName
	 */
	public ReleaseInfo(String version, String distributorId, String description,
			String release, String codeName) {
		super();
		this.version = version;
		this.distributorId = distributorId;
		this.description = description;
		this.release = release;
		this.codeName = codeName;
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
	 * @return the distributorId
	 */
	public String getDistributorId() {
		return distributorId;
	}

	/**
	 * @param distributorId the distributorId to set
	 */
	public void setDistributorId(String distributorId) {
		this.distributorId = distributorId;
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
	 * @return the release
	 */
	public String getRelease() {
		return release;
	}

	/**
	 * @param release the release to set
	 */
	public void setRelease(String release) {
		this.release = release;
	}

	/**
	 * @return the codeName
	 */
	public String getCodeName() {
		return codeName;
	}

	/**
	 * @param codeName the codeName to set
	 */
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ReleaseInfo [version=" + version + ", distributorId="
				+ distributorId + ", description=" + description + ", release="
				+ release + ", codeName=" + codeName + "]";
	}
}

	