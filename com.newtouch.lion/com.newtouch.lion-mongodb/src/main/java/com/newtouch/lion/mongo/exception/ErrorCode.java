/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ErrorCode.java 9552 2015年1月27日 下午9:07:25 WangLijun$
*/
package com.newtouch.lion.mongo.exception; 
/**
 * <p>
 * Title: 错误代码定义
 * </p>
 * <p>
 * Description: 错误代码定义
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
public enum ErrorCode {
	DATA_SOURCE("MD00001"),
	EXCEL_PROPERTY_READ("MD00002"),
	EXCEL_FILE_NOTFOUND("MD00003");
	
	/***
	 * 错误代码
	 */
	private String code;
	/***
	 * 错误代码
	 * @param code
	 */
	private ErrorCode(String code) {
		this.code=code;
	}

	/**
	 * @return the code获取Code代码
	 */
	public String code() {
		return code;
	}
	
	
}

	