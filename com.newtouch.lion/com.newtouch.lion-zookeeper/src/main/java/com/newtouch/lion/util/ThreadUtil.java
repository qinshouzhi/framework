/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ThreadUtil.java 9552 2015年5月29日 下午9:57:40 WangLijun$
*/
package com.newtouch.lion.util; 
/**
 * <p>
 * Title: 线程工具类
 * </p>
 * <p>
 * Description: 线程工具类
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
public class ThreadUtil {
	/**
	 * 重复开启 threadNum 个线程来执行 runnable
	 * @param runnable 可执行任务
	 * @param threadNum 重复开启的线程个数
	 * @param sleepTime 启动完所有线程后，休息 ms
	 */
	public static void startThread( Runnable runnable, int threadNum, long sleepTime ) {

		for ( int i = 0; i < threadNum; i++ ) {
			Thread thread = new Thread( runnable, "Thread#" + i );
			thread.start();
		}
		try {
			Thread.sleep( sleepTime );
		} catch ( InterruptedException e ) {
		}
	}

	/**
	 * 开启 1 个线程来执行 runnable
	 * @param runnable 可执行任务
	 */
	public static void startThread( Runnable runnable ) {
		startThread( runnable, 1, 0 );
	}

	/**
	 * 重复开启 threadNum 个线程来执行 runnable
	 * @param runnable 可执行任务
	 * @param threadNum 重复开启的线程个数
	 */
	public static void startThread( Runnable runnable, long sleepTime ) {
		startThread( runnable, 1, sleepTime );
	}
}

	