/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: LoadAverage.java 9552 2015年5月27日 下午1:28:44 WangLijun$
 */
package com.newtouch.lion.os.model;

/**
 * <p>
 * Title: 系统负载，即任务队列的平均长度
 * </p>
 * <p>
 * Description: 系统负载，即任务队列的平均长度
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
public class LoadAverage {
	/** 系统当前时间 */
	private String date;
	/** 系统已运行时间 */
	private String runtime;
	/** 系统使用用户 */
	private long users;
	/** 15分钟前到现在的平均值 */
	private double quarter;
	/** 5分钟前到现在的平均值 */
	private double fiveMinutes;
	/** 1分钟前到现在的平均值 */
	private double oneMinute;

	public LoadAverage() {
		super();
	}

	/**
	 * @return the quarter
	 */
	public double getQuarter() {
		return quarter;
	}

	/**
	 * @param quarter
	 *            the quarter to set
	 */
	public void setQuarter(double quarter) {
		this.quarter = quarter;
	}

	/**
	 * @return the fiveMinutes
	 */
	public double getFiveMinutes() {
		return fiveMinutes;
	}

	/**
	 * @param fiveMinutes
	 *            the fiveMinutes to set
	 */
	public void setFiveMinutes(double fiveMinutes) {
		this.fiveMinutes = fiveMinutes;
	}

	/**
	 * @return the oneMinute
	 */
	public double getOneMinute() {
		return oneMinute;
	}

	/**
	 * @param oneMinute
	 *            the oneMinute to set
	 */
	public void setOneMinute(double oneMinute) {
		this.oneMinute = oneMinute;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the runtime
	 */
	public String getRuntime() {
		return runtime;
	}

	/**
	 * @param runtime
	 *            the runtime to set
	 */
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	/**
	 * @return the users
	 */
	public long getUsers() {
		return users;
	}

	/**
	 * @param users
	 *            the users to set
	 */
	public void setUsers(long users) {
		this.users = users;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LoadAverage [date=" + date + ", runtime=" + runtime
				+ ", users=" + users + ", quarter=" + quarter
				+ ", fiveMinutes=" + fiveMinutes + ", oneMinute=" + oneMinute
				+ "]";
	}
}
