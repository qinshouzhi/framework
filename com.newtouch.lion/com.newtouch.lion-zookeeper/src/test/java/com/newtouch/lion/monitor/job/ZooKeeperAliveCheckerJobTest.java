/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ZooKeeperAliveCheckerJobTest.java 9552 2015年7月24日 下午10:28:05 WangLijun$
*/
package com.newtouch.lion.monitor.job; 

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.newtouch.lion.zookeeper.ZookeeperAllTest;

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
public class ZooKeeperAliveCheckerJobTest extends ZookeeperAllTest{
	
	@Autowired
	public ZooKeeperAliveCheckerJob zooKeeperAliveCheckerJob;
	
	@Test
	public void run(){
		zooKeeperAliveCheckerJob.run();
	}
}

	