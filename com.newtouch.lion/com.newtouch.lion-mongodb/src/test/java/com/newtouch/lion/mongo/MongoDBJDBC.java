/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: MongoDBJDBC.java 9552 2015年4月22日 下午4:46:48 WangLijun$
 */
package com.newtouch.lion.mongo;

import com.mongodb.DB;
import com.mongodb.MongoClient;

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
public class MongoDBJDBC {
	public static void main(String args[]) {
		try {
			// 连接到 mongodb 服务
			MongoClient mongoClient = new MongoClient("192.168.202.136", 27017);
			// 连接到数据库
			DB db = mongoClient.getDB("test");
			System.out.println("Connect to database successfully");
			//boolean auth = db.authenticate(myUserName, myPassword);
			//System.out.println("Authentication: " + auth);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
}
