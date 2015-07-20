/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: Columns.java 9552 2013-3-27 下午4:48:20 WangLijun$
 */
package com.newtouch.lion.model.datagrid;

import com.newtouch.lion.model.VersionEntity;

/**
 * <p>
 * Title:DataColumn 定义
 * </p>
 * <p>
 * Description:DataColumn 定义
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
public class DataColumn extends VersionEntity<Long> {

	/**
		 * 
		 */
	private static final long serialVersionUID = 5425792065799995912L;

	private Long id;
	/** 当前显示顺序从1开始，用于控制列显示顺序 */
	private int showOrder = 1;
	/** 列映射字段名称 ，与Model绑定 */
	private Long dataGridId;
	private String field;
	/** 列名显示字段 */
	private String name;
	/** 对齐方式:'left','center','right' */
	private String align;
	/** 显示列宽度：eg width=30 */
	private Integer width;
	/** 数据 */
	private DataGrid dataGrid;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.model.BaseEntity#getId()
	 */

	public Long getId() {
		return this.id;
	}

	/**
	 * @return the showOrder
	 */
	public int getShowOrder() {
		return showOrder;
	}

	/**
	 * @return the dataGridId
	 */
	public Long getDataGridId() {
		return dataGridId;
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @return the align
	 */
	public String getAlign() {
		return align;
	}

	/**
	 * @return the dataGrid
	 */
	public DataGrid getDataGrid() {
		return dataGrid;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param showOrder
	 *            the showOrder to set
	 */
	public void setShowOrder(int showOrder) {
		this.showOrder = showOrder;
	}

	/**
	 * @param field
	 *            the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param align
	 *            the align to set
	 */
	public void setAlign(String align) {
		this.align = align;
	}

	/**
	 * @param dataGrid
	 *            the dataGrid to set
	 */
	public void setDataGrid(DataGrid dataGrid) {
		this.dataGrid = dataGrid;
	}

	/**
	 * @param dataGridId
	 *            the dataGridId to set
	 */
	public void setDataGridId(Long dataGridId) {
		this.dataGridId = dataGridId;
	}

	/**
	 * @return the width
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}
	
}
