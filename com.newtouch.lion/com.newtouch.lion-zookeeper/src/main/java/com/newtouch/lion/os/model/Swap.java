/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: Swap.java 9552 2015年5月27日 上午11:40:38 WangLijun$
*/
package com.newtouch.lion.os.model; 
/**
 * <p>
 * Title: 操作系统监控信息－Swap交换分区
 * </p>
 * <p>
 * Description: 操作系统监控信息－Swap交换分区
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
public class Swap {
	/**内存总量*/
	private double total;
	/**内存剩余空间*/
	private double free;
	/**内存已使用量*/
	private double used;
	/**avail Mem */
	private double availMem;
	/**默认构造函数*/
	public Swap() {
		super();
	}
	/**
	 * @param total
	 * @param free
	 * @param used
	 * @param availMem
	 */
	public Swap(double total, double free, double used, double availMem) {
		super();
		this.total = total;
		this.free = free;
		this.used = used;
		this.availMem = availMem;
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
	 * @return the availMem
	 */
	public double getAvailMem() {
		return availMem;
	}
	/**
	 * @param availMem the availMem to set
	 */
	public void setAvailMem(double availMem) {
		this.availMem = availMem;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Swap [total=" + total + ", free=" + free + ", used=" + used
				+ ", availMem=" + availMem + "]";
	}
	
	
	
}

	