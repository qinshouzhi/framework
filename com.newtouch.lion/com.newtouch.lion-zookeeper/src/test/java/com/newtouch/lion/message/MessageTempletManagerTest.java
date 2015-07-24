/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: MessageTempletManagerTest.java 9552 2015年7月24日 下午9:07:30 WangLijun$
*/
package com.newtouch.lion.message; 

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newtouch.lion.message.sender.MessageTempletManager;

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
public class MessageTempletManagerTest {
	/**日志*/
	private static final Logger logger=LoggerFactory.getLogger(MessageTempletManagerTest.class);
	
	
	@Test
	public void messageTemplet(){
		String content=MessageTempletManager.zookeeperFailContent("Zookeeper");
		System.out.println(content);
		
		String title=MessageTempletManager.zookeeperFailTitle("Zookeeper Cluster");
		System.out.println(title);
	}
}

	