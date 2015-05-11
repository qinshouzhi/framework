/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: MongoException.java 9552 2015年5月8日 下午3:06:59 WangLijun$
 */
package com.newtouch.lion.mongo.exception;

import com.newtouch.lion.exception.BaseException;

/**
 * <p>
 * Title: MongoException连接信息
 * </p>
 * <p>
 * Description: MongoException连接信息
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
public class MongoException extends BaseException {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 370716014949990448L;

	/** 错误代码 */
	protected String code;

	public MongoException(String code) {
		super(code);
	}

	/***
	 * 
	 * @param code
	 *            错误代码
	 * @param msg
	 *            错误消息
	 */
	public MongoException(String code, String msg) {
		super(msg);
		this.code = code;
	}

	/**
	 * Construct a {@code NestedRuntimeException} with the specified detail
	 * message and nested exception.
	 * 
	 * @param msg
	 *            the detail message
	 * @param cause
	 *            the nested exception
	 */
	public MongoException(String msg, Throwable cause) {
		super(msg, cause);
	}

	/***
	 * 
	 * @param code
	 * @param msg
	 * @param cause
	 */
	public MongoException(String code, String msg, Throwable cause) {
		super(msg, cause);
		this.code = code;
	}

}
