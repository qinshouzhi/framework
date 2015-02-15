
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: GroupRole.java 9552 Feb 15, 2015 1:27:21 PM MaoJiaWei$
*/
package com.newtouch.lion.model.system; 

import com.newtouch.lion.model.VersionEntity;

/**
 * <p>
 * Title: 
 * </p>
 * <p>
 * Description: 
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: NewTouch
 * </p>
 * 
 * @author MaoJiaWei
 * @version 1.0
 */
public class GroupRole extends VersionEntity<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -775741526557914478L;
	/** 用户组ID*/
	private Long id;
	/** 用户组名称－中文 */
	private String nameZh;
	/** 用户组名称－英文 */
	private String nameEn;
	/** 用户组描述 */
	private String description;
	/** 角色描述Id*/
	private Long roleId;
	
	/**
	 * 默认构造器
	 */
	public GroupRole() {
		super();
	}
	/**
	 * @param id
	 * @param nameZh
	 * @param nameEn
	 * @param description
	 * @param roleId
	 */
	public GroupRole(Long id, String nameZh, String nameEn, String description,
			Long roleId) {
		super();
		this.id = id;
		this.nameZh = nameZh;
		this.nameEn = nameEn;
		this.description = description;
		this.roleId = roleId;
	}
	/* (non-Javadoc)
	 * @see com.newtouch.lion.model.BaseEntity#getId()
	 */
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}
	/**
	 * @return the nameZh
	 */
	public String getNameZh() {
		return nameZh;
	}
	/**
	 * @param nameZh the nameZh to set
	 */
	public void setNameZh(String nameZh) {
		this.nameZh = nameZh;
	}
	/**
	 * @return the nameEn
	 */
	public String getNameEn() {
		return nameEn;
	}
	/**
	 * @param nameEn the nameEn to set
	 */
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
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
	 * @return the roleId
	 */
	public Long getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
}

	