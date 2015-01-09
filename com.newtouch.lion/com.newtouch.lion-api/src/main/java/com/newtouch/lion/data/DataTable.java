/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: DataTable.java 9552 2015年1月9日 下午3:36:35 WangLijun$
*/
package com.newtouch.lion.data; 

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Title: DataTable的表示数据
 * </p>
 * <p>
 * Description: DataTable的表示数据
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
public class DataTable<T> {
	
	/**总记录数*/
	private Long total;
	/** 当前页记录的集合对象 */
	private List<T> rows = new ArrayList<T>(0);
	/**
	 * 默认构造函数
	 * */
	public DataTable() {
		super();
	}
	
	/**
	 * @param total 总记录数
	 * @param row 集合对象
	 */
	public DataTable(Long total, List<T> rows) {
		super();
		this.total = total;
		this.rows= rows;
	}

	/**
	 * @return the total
	 */
	public Long getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Long total) {
		this.total = total;
	}

	/**
	 * @return the rows
	 */
	public List<T> getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}	

	