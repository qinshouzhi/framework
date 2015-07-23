/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ReleaseKey.java 9552 2015年5月27日 下午4:17:25 WangLijun$
*/
package com.newtouch.lion.os.constant; 
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
public class PerformanceKey {
	/**
	 * WORD_SEPARATOR  ( char )2
	 */
	public static final String WORD_SEPARATOR = Character.toString( ( char )2 );
	/***
	 * 空格
	 */
	public static final String WHITE_SPACE=" ";
	/***
	 * 单位KB
	 */
	public static final String KB="k";
	/***
	 * 操作系统名称：如CentOS,Redhat,ubuntu
	 * 此ubuntu 系统有可能为空;
	 */
	public static  final String VERSION="LSB Version:";
	/**top - */
	public static final String TOP_="top -";
	/**TOP up */
	public static final String TOP_UP="up";
	/**TOP user */
	public static final String TOP_USER="user";
	/**load average*/
	public static final String TOP_LOAD_AVERAGE="load average:";
	/**TOP days*/
	public static final String TOP_DAYS="days";
	/***
	 * 进行信息
	 */
	public static final String TASKS="Tasks:";
	/**进程总数*/
	public static final String TASKS_TOTAL="total";
	/**正在运行的进程数*/
	public static final String TASKS_RUNNING="running";
	/**睡眠的进程数*/
	public static final String TASKS_SLEEPING="sleeping"; 
	/**停止的进程数*/
	public static final String TASKS_STOPPED="stopped";
	/**僵尸进程数*/
	public static final String TASKS_ZOMBIE="zombie";
	/**CPU的信息*/
	public static final String CPU="Cpu(s):";
	/**CPU 用户使用率*/
	public static final String CPU_US="us";
	/**CPU 系统占用离率*/
	public static final String CPU_SY="sy";
	/**CPU  ni 改变过优先级的进程占用CPU的百分比*/
	public static final String CPU_NI="ni";
	/**CPU id 空闲CPU百分比*/
	public static final String CPU_ID="id";
	/**CPU wa IO等待占用CPU的百分比*/
	public static final String CPU_WA="wa";
	/**CPU hi 硬中断（Hardware IRQ）占用CPU的百分比*/
	public static final String CPU_HI="hi";
	/**CPU si 软中断（Software Interrupts）占用CPU的百分比*/
	public static final String CPU_SI="si";
	/**CPU st 虚拟机偷取时间*/
	public static final String CPU_ST="st";
	/**内存信息*/
	public static final String MEM="Mem:";
	/**内存总量*/
	public static final String MEM_TOTAL="total";
	/**内存剩余空间*/
	public static final String MEM_FREE="free";
	/**内存已使用量*/
	public static final String MEM_USED="used";
	/**内存缓存量*/
	public static final String MEM_BUFFERS="buffers";
	
	
	/**内存信息*/
	public static final String SWAP="Swap:";
	/**内存总量*/
	public static final String SWAP_TOTAL="total";
	/**内存剩余空间*/
	public static final String SWAP_FREE="free";
	/**内存已使用量*/
	public static final String SWAP_USED="used";
	/**内存缓存量*/
	public static final String SWAP_CACHED="cached";
	/**CentOS7　Unbutu*/
	public static final String SWAP_AVAIL_MEM="avail Mem";
}

	