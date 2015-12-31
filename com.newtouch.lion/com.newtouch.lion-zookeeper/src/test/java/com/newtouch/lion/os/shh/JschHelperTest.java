/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: JschHelper.java 9552 2015年5月27日 下午3:52:30 WangLijun$
*/
package com.newtouch.lion.os.shh; 

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newtouch.lion.os.constant.Command;
import com.newtouch.lion.os.ssh.JschConfig;
import com.newtouch.lion.os.ssh.JschHelper;

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
public class JschHelperTest {
	/** 日志 */
	private static final Logger logger = LoggerFactory.getLogger(JschHelper.class);
	
	//public static void main(String[] args) {
	//	JschConfig config=new JschConfig("192.168.205.36","root", "newtouch.cn",22);
	//	String result=JschHelper.exec(config, Command.TOP_HEAD);/
	//	logger.info("result:{}",result);
	//}
	
	
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch countDownLatch=new CountDownLatch(1);
		countDownLatch.countDown();
	    Thread.sleep(3000);
		long time=countDownLatch.getCount();
		System.out.println(time);
	}
}

	