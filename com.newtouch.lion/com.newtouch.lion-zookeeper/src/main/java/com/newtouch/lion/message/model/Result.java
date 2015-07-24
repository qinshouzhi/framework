/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: Result.java 9552 2015年7月24日 下午5:04:55 WangLijun$
*/
package com.newtouch.lion.message.model; 

import java.io.Serializable;

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
public class Result  implements Serializable{

	/**
	 *序列化
	 */
	private static final long serialVersionUID = -3844397276391818904L;
	/****
	 * 是否成功标记
	 */
	private  boolean success;
	/**消息*/
	private String  msg;
	/**重试次数*/
	private int reTryTimes=0;
	
	public Result() {
		super();
	}

	/**
	 * @param success
	 * @param msg
	 * @param reTryTimes
	 */
	public Result(boolean success, String msg, int reTryTimes) {
		this.success = success;
		this.msg = msg;
		this.reTryTimes = reTryTimes;
	}

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the reTryTimes
	 */
	public int getReTryTimes() {
		return reTryTimes;
	}

	/**
	 * @param reTryTimes the reTryTimes to set
	 */
	public void setReTryTimes(int reTryTimes) {
		this.reTryTimes = reTryTimes;
	}

	
	
}

	