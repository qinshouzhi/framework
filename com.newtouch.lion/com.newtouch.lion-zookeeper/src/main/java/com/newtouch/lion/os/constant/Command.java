/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: Command.java 9552 2015年5月27日 下午1:19:57 WangLijun$
*/
package com.newtouch.lion.os.constant; 
/**
 * <p>
 * Title: Linux常用监控命令
 * </p>
 * <p>
 * Description: Linux常用监控命令
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
public class Command {	
	/**执行*/
	public static final String EXEC="exec";
	/**CPU、MEM、Load监控命令参数：op -b -n 1 | head -5*/
	public static final String  TOP_HEAD="top -b -n 1 | head -5";
	/**查询系统版本信息：lsb_release -a*/
	public static final String LSB_RELEASE_A="lsb_release -a";
	/**系统内核信息查询 uname -a*/
	public static final String UNAME_A="uname -a";
	/**系统内核信息查询*/
	public static final String PROC_VERSION="cat  /proc/version";
	/**磁盘：DF_LH = "df -lh";*/
	public static final String DF_LH ="df -lh";
	
}

	