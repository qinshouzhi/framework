/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ZookeeperTest.java 9552 2015年5月25日 下午4:57:49 WangLijun$
*/
package com.newtouch.watchman.zookeeper; 

import java.io.IOException;

import org.apache.curator.framework.CuratorFramework;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.newtouch.lion.zookeeper.client.ZookeeperClient;

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
public class ZookeeperTest {
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationContext-common.xml", "applicationContext-zookeeper.xml" });
		context.start();
		ZookeeperClient zookeeperClient=(ZookeeperClient) context.getBean("zookeeperClient");
		CuratorFramework client=zookeeperClient.builder();
		System.in.read();
	}
}

	