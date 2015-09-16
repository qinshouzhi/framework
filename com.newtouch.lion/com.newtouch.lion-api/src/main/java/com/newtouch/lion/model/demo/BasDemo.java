/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: BasDemo.java 9552 2015年9月16日 上午11:12:58 WangLijun$
*/
package com.newtouch.lion.model.demo; 

import com.newtouch.lion.model.AuditEntity;

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
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class BasDemo  extends AuditEntity<Long>{
	
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -529025771708601459L;

	private Long id;
	
	private Long parentId;
	
	private String nameZh;
	
	@Override
	public Long getId() {
		return this.id;
	}

	/**
	 * @return the parentId
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
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
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BasDemo [id=" + id + ", parentId=" + parentId + ", nameZh="
				+ nameZh + "]";
	}
	
	
}

	