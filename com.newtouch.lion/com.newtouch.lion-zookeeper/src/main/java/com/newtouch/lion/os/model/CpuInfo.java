/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: CpuInfo.java 9552 2015年5月27日 下午1:24:42 WangLijun$
*/
package com.newtouch.lion.os.model; 

import java.io.Serializable;

/**
 * <p>
 * Title: Linux cpu监控信息
 * </p>
 * <p>
 * Description:Linux cpu监控信息
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
public class CpuInfo  implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 6376443903710811561L;	
	/**用户使用率*/
	private double userUsed;
	/**系统占用离率*/
	private double systemUsed;
	/** ni 改变过优先级的进程占用CPU的百分比*/
	private double ni;
	/**id 空闲CPU百分比*/
	private double id;
	/**wa IO等待占用CPU的百分比*/
	private double wa;
	/**hi 硬中断（Hardware IRQ）占用CPU的百分比*/
	private double hi;
	/**si 软中断（Software Interrupts）占用CPU的百分比*/
	private double si;
	/**st 虚拟机偷取时间*/
	private double st;
	
	public CpuInfo() {
		super();
	}
	/**
	 * @param userUsed
	 * @param systemUsed
	 * @param ni
	 * @param id
	 * @param wa
	 * @param hi
	 */
	public CpuInfo(double userUsed, double systemUsed, double ni, double id,
			double wa, double hi) {
		super();
		this.userUsed = userUsed;
		this.systemUsed = systemUsed;
		this.ni = ni;
		this.id = id;
		this.wa = wa;
		this.hi = hi;
	}
	/**
	 * @return the userUsed
	 */
	public double getUserUsed() {
		return userUsed;
	}
	/**
	 * @param userUsed the userUsed to set
	 */
	public void setUserUsed(double userUsed) {
		this.userUsed = userUsed;
	}
	/**
	 * @return the systemUsed
	 */
	public double getSystemUsed() {
		return systemUsed;
	}
	/**
	 * @param systemUsed the systemUsed to set
	 */
	public void setSystemUsed(double systemUsed) {
		this.systemUsed = systemUsed;
	}
	/**
	 * @return the ni
	 */
	public double getNi() {
		return ni;
	}
	/**
	 * @param ni the ni to set
	 */
	public void setNi(double ni) {
		this.ni = ni;
	}
	/**
	 * @return the id
	 */
	public double getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(double id) {
		this.id = id;
	}
	/**
	 * @return the wa
	 */
	public double getWa() {
		return wa;
	}
	/**
	 * @param wa the wa to set
	 */
	public void setWa(double wa) {
		this.wa = wa;
	}
	/**
	 * @return the hi
	 */
	public double getHi() {
		return hi;
	}
	/**
	 * @param hi the hi to set
	 */
	public void setHi(double hi) {
		this.hi = hi;
	}
	/**
	 * @return the si
	 */
	public double getSi() {
		return si;
	}
	/**
	 * @param si the si to set
	 */
	public void setSi(double si) {
		this.si = si;
	}
	/**
	 * @return the st
	 */
	public double getSt() {
		return st;
	}
	/**
	 * @param st the st to set
	 */
	public void setSt(double st) {
		this.st = st;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CpuInfo [userUsed=" + userUsed + ", systemUsed=" + systemUsed
				+ ", ni=" + ni + ", id=" + id + ", wa=" + wa + ", hi=" + hi
				+ ", si=" + si + ", st=" + st + "]";
	}
	 
	
	
	
	
}

	