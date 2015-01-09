/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: DepartmentVo.java 9552 2014-4-4 下午05:18:16 WangLijun$
 */
package com.newtouch.lion.admin.web.model.system.department;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: NewTouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class DepartmentVo {
	/*** 部门ID */
	private Long id;
	/** 上一级部门ID */
	private Long parentDepartmentId;
	/** 部门编号 */
	private String departmentNO;
	/** 部门中文名称 */
	private String nameZh;
	/** 部门英文名称 */
	private String nameEn;
	/** 部门描述 */
	private String description;
	/** 是否可编辑 */
	private boolean editable;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the parentDepartmentId
	 */
	public Long getParentDepartmentId() {
		return parentDepartmentId;
	}

	/**
	 * @param parentDepartmentId
	 *            the parentDepartmentId to set
	 */
	public void setParentDepartmentId(Long parentDepartmentId) {
		this.parentDepartmentId = parentDepartmentId;
	}

	/**
	 * @return the departmentNO
	 */
	public String getDepartmentNO() {
		return departmentNO;
	}

	/**
	 * @param departmentNO
	 *            the departmentNO to set
	 */
	public void setDepartmentNO(String departmentNO) {
		this.departmentNO = departmentNO;
	}

	/**
	 * @return the nameZh
	 */
	public String getNameZh() {
		return nameZh;
	}

	/**
	 * @param nameZh
	 *            the nameZh to set
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
	 * @param nameEn
	 *            the nameEn to set
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
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the editable
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * @param editable
	 *            the editable to set
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public DepartmentVo() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DepartmentVo [id=" + id + ", parentDepartmentId="
				+ parentDepartmentId + ", departmentNO=" + departmentNO
				+ ", nameZh=" + nameZh + ", nameEn=" + nameEn
				+ ", description=" + description + ", editable=" + editable
				+ "]";
	}
}
