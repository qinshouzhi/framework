/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: Datasource.java 9552 2015年5月8日 下午2:46:11 WangLijun$
 */
package com.newtouch.lion.mongo.jndi;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.newtouch.lion.mongo.config.Config;

/**
 * <p>
 * Title:MongoDB 数据源
 * </p>
 * <p>
 * Description: MongoDB 数据源
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
public class DataSource {
	/** Mongo Client */
	private final MongoClient client;
	/** Mongo Config */
	private final Config config;

	/***
	 * 
	 * @param client
	 *            Mongo客户端
	 * @param config
	 *            连接配置信息
	 */
	public DataSource(MongoClient client, Config config) {
		this.client = client;
		this.config = config;
	}

	/***
	 * 获取数据库连接
	 * 
	 * @return DB object representing a MongoDB connection
	 */
	public DB getConnection() {
		return this.client.getDB(this.config.getDbname());
	}

	/**
	 * 获取数据库连接
	 * 
	 * @return MongoDatabase
	 * */
	public MongoDatabase getDatabaseConnection() {
		return this.client.getDatabase(this.config.getDbname());
	}

	/**
	 * Closes all datasource connections
	 */
	public void close() {
		client.close();
	}
}
