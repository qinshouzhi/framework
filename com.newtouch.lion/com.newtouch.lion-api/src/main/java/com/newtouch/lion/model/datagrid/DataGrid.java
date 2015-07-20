/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: DataGrid.java 9552 2013-3-27 下午4:51:03 WangLijun$
 */
package com.newtouch.lion.model.datagrid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.newtouch.lion.model.VersionEntity;

/**
 * <p>
 * Title:DataGrid Model
 * </p>
 * <p>
 * Description:DataGrid Model 定义
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class DataGrid extends VersionEntity<Long> {
	/** 序列化 */
	private static final long serialVersionUID = -2196344082980302505L;
	/** ID 为DataGrid Id */
	private Long id;
	/** 表格类型 */
	private String type;
	/** 表格ID */
	private String tableId;
	/** 表格标题 */
	private String title;
	/** 显示排序列的记录 */
	private List<DataColumn> sortColumns = new ArrayList<DataColumn>();
	/** 显示集合列 */
	private Set<DataColumn> columns = new HashSet<DataColumn>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.model.BaseEntity#getId()
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the tableId
	 */
	public String getTableId() {
		return tableId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the columns
	 */

	public Set<DataColumn> getColumns() {
		return columns;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param tableId
	 *            the tableId to set
	 */
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}


	/**
	 * @param columns
	 *            the columns to set
	 */
	public void setColumns(Set<DataColumn> columns) {
		this.columns = columns;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public List<DataColumn> getSortColumns() {
		return sortColumns;
	}

	public void setSortColumns(List<DataColumn> sortColumns) {
		this.sortColumns = sortColumns;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
}
