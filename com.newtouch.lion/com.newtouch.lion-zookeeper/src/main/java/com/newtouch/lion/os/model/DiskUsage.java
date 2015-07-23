/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: DiskUsage.java 9552 2015年6月3日 上午11:03:46 WangLijun$
*/
package com.newtouch.lion.os.model; 

import java.io.Serializable;

/**
 * <p>
 * Title: 磁盘监控信息
 * </p>
 * <p>
 * Description: 硬盘监控
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
public class DiskUsage implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1995530607925197068L;
	/**分区文件*/
	private String filesystem;
	/**硬盘空间大小*/
	private  long size;
	/**已使用空间*/
	private  long used;
	/**可用空间*/
	private long available;
	/**已使用空间%*/
	private String usedPercent;
	/**挂载目录*/
	private String mounted;
	
	public DiskUsage() {
		 super();
	}

	/**
	 * @return the filesystem
	 */
	public String getFilesystem() {
		return filesystem;
	}

	/**
	 * @param filesystem the filesystem to set
	 */
	public void setFilesystem(String filesystem) {
		this.filesystem = filesystem;
	}

	/**
	 * @return the size
	 */
	public long getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(long size) {
		this.size = size;
	}

	/**
	 * @return the used
	 */
	public long getUsed() {
		return used;
	}

	/**
	 * @param used the used to set
	 */
	public void setUsed(long used) {
		this.used = used;
	}

	/**
	 * @return the available
	 */
	public long getAvailable() {
		return available;
	}

	/**
	 * @param available the available to set
	 */
	public void setAvailable(long available) {
		this.available = available;
	}

	/**
	 * @return the usedPercent
	 */
	public String getUsedPercent() {
		return usedPercent;
	}

	/**
	 * @param usedPercent the usedPercent to set
	 */
	public void setUsedPercent(String usedPercent) {
		this.usedPercent = usedPercent;
	}

	/**
	 * @return the mounted
	 */
	public String getMounted() {
		return mounted;
	}

	/**
	 * @param mounted the mounted to set
	 */
	public void setMounted(String mounted) {
		this.mounted = mounted;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DiskUsage [filesystem=" + filesystem + ", size=" + size
				+ ", used=" + used + ", available=" + available
				+ ", usedPercent=" + usedPercent + ", mounted=" + mounted + "]";
	}
	
	
	
}

	