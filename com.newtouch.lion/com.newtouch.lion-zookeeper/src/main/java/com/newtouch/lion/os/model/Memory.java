/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: Memory.java 9552 2015年5月27日 上午11:34:55 WangLijun$
*/
package com.newtouch.lion.os.model; 
/**
 * <p>
 * Title: 操作系统监控信息-内存使用情况
 * </p>
 * <p>
 * Description: 操作系统监控信息-内存使用情况
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
public class Memory {
	/**内存总量*/
	private double total;
	/**内存剩余空间*/
	private double free;
	/**内存已使用量*/
	private double used;
	/**内存缓存量*/
	private double buffers;
	/**默认构造函数*/
	public Memory() {
		 super();
	}
	
	/**
	 * @param total
	 * @param free
	 * @param used
	 * @param buffers
	 */
	public Memory(double total, double free, double used, double buffers) {
		super();
		this.total = total;
		this.free = free;
		this.used = used;
		this.buffers = buffers;
	}



	/**
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(double total) {
		this.total = total;
	}
	/**
	 * @return the free
	 */
	public double getFree() {
		return free;
	}
	/**
	 * @param free the free to set
	 */
	public void setFree(double free) {
		this.free = free;
	}
	/**
	 * @return the used
	 */
	public double getUsed() {
		return used;
	}
	/**
	 * @param used the used to set
	 */
	public void setUsed(double used) {
		this.used = used;
	}

	/**
	 * @return the buffers
	 */
	public double getBuffers() {
		return buffers;
	}

	/**
	 * @param buffers the buffers to set
	 */
	public void setBuffers(double buffers) {
		this.buffers = buffers;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Memory [total=" + total + ", free=" + free + ", used=" + used
				+ ", buffers=" + buffers + "]";
	}
	
	
}

	