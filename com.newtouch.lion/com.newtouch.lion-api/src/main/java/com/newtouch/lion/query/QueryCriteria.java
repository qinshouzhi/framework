/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: QueryCriteria.java 9552 2013-1-12 下午9:06:21 WangLijun$
 */
package com.newtouch.lion.query;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Title: SQL Or HQL 查询条件
 * </p>
 * <p>
 * Description: 查询条件
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
public class QueryCriteria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8882975004924152099L;
	/** 升序 */
	public static final String ASC = "ASC";
	/** 降序 */
	public static final String DESC = "DESC";
	/** 首页索引，默认值为：0 */
	private int startIndex = 0;
	/** 页面显示记录数 默认为：10 */
	private int pageSize = 10;
	/** 排序字段 */
	private String orderField = null;
	/** 排序方面，默认为：升序 */
	private String orderDirection = QueryCriteria.ASC;
	/** 查询条件 */
	private Map<String, Object> queryCondition = new HashMap<String, Object>();

	/***
	 * 添加查询条件，key-value
	 * 
	 * @param key
	 *            String
	 * @param value
	 *            Value
	 */
	public void addQueryCondition(String key, Object value) {
		this.queryCondition.put(key, value);
	}

	/***
	 * 重置查询条件 将首页索引设置为：0 将PageSize设置 默认值：10 将排序字段设置为：null; 将排序方向设置为升序:ASC
	 */
	public void reset() {
		this.startIndex = 0;
		this.pageSize = 10;
		this.orderField = null;
		this.orderDirection = QueryCriteria.ASC;
	}

	/**
	 * @return the startIndex
	 */
	public int getStartIndex() {
		return startIndex;
	}

	/**
	 * @param startIndex
	 *            the startIndex to set
	 */
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the orderField
	 */
	public String getOrderField() {
		return orderField;
	}

	/**
	 * @param orderField
	 *            the orderField to set
	 */
	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	/**
	 * @return the orderDirection
	 */
	public String getOrderDirection() {
		return orderDirection;
	}

	/**
	 * @param orderDirection
	 *            the orderDirection to set
	 */
	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}

	/**
	 * @return the queryCondition
	 */
	public Map<String, Object> getQueryCondition() {
		return queryCondition;
	}

	/**
	 * @param queryCondition
	 *            the queryCondition to set
	 */
	public void setQueryCondition(Map<String, Object> queryCondition) {
		this.queryCondition = queryCondition;
	}

}
