/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: DataSet.java 9552 2015年1月9日 下午3:29:32 WangLijun$
*/
package com.newtouch.lion.service.dataset; 

import java.util.List;

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
public class DataSet {
	
	private Integer total;
	
	private List rows;

	/**
	 * @return the total
	 */
	public Integer getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}

	/**
	 * @return the rows
	 */
	public List getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(List rows) {
		this.rows = rows;
	}
	
	
}

	