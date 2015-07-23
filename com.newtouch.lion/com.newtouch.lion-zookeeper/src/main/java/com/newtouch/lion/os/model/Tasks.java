/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: Tasks.java 9552 2015年5月28日 上午11:51:00 WangLijun$
*/
package com.newtouch.lion.os.model; 
/**
 * <p>
 * Title: 操作系统监控信息－进程
 * </p>
 * <p>
 * Description: 操作系统监控信息－进程
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
public class Tasks {
	/**进程总数*/
	private long total;
	/**正在运行的进程数*/
	private long running;
	/**睡眠的进程数*/
	private long sleeping; 
	/**停止的进程数*/
	private long stopped;
	/**僵尸进程数*/
	private  long zombie;
	/**默认构造函数*/
	public Tasks() {
		super();
	}
	/**
	 * @return the total
	 */
	public long getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(long total) {
		this.total = total;
	}
	/**
	 * @return the running
	 */
	public long getRunning() {
		return running;
	}
	/**
	 * @param running the running to set
	 */
	public void setRunning(long running) {
		this.running = running;
	}
	/**
	 * @return the sleeping
	 */
	public long getSleeping() {
		return sleeping;
	}
	/**
	 * @param sleeping the sleeping to set
	 */
	public void setSleeping(long sleeping) {
		this.sleeping = sleeping;
	}
	/**
	 * @return the stopped
	 */
	public long getStopped() {
		return stopped;
	}
	/**
	 * @param stopped the stopped to set
	 */
	public void setStopped(long stopped) {
		this.stopped = stopped;
	}
	/**
	 * @return the zombie
	 */
	public long getZombie() {
		return zombie;
	}
	/**
	 * @param zombie the zombie to set
	 */
	public void setZombie(long zombie) {
		this.zombie = zombie;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Tasks [total=" + total + ", running=" + running + ", sleeping="
				+ sleeping + ", stopped=" + stopped + ", zombie=" + zombie
				+ "]";
	}
	
	
}

	