/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ZookeeperCommandTest.java 9552 2015年7月24日 下午11:13:48 WangLijun$
*/
package com.newtouch.lion.zookeeper; 

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newtouch.lion.zookeeper.command.ZookeeperCommand;

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
public class ZookeeperCommandTest {
	/**
	 * 日志
	 */
	private static final Logger logger=LoggerFactory.getLogger(ZookeeperCommandTest.class);
	
	
	@Test
	public void command(){
		String command=ZookeeperCommand.format(ZookeeperCommand.COMMAND_STAT,"127.0.0.1","2181");
		logger.info("command:{}",command);
		
	}
}

	