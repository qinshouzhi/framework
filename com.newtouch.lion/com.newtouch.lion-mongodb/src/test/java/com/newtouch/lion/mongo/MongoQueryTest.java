/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: MongoQueryTest.java 9552 2015年5月11日 下午1:55:56 WangLijun$
 */
package com.newtouch.lion.mongo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryOperators;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;

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
public class MongoQueryTest extends MongoAllTest {

	@Autowired
	private MongoManager mongoManager;
	
	/**
	 * 插入
	 * */
	@Test
	public void insert() {
		String collection = "wm_trace";
		DB db = mongoManager.getConnection();
		DBCollection dbcollection = db.getCollection(collection);
		DBObject dbObject = new BasicDBObject();
		dbObject.put("trace_id", "1234567810");
		dbObject.put("service_name", "wanglijun");
		dbObject.put("project", "application_a");
		WriteResult insert = dbcollection.insert(dbObject,WriteConcern.FSYNC_SAFE);
		logger.info("insert:{}",insert.getN());
	}

	
	/**
	 * 基本查询
	 * */
	@Test
	public void query() {
		String collectionName = "wm_trace";
		DBCollection dbollection = mongoManager.getConnection().getCollection(
				collectionName);
		BasicDBObject query = new BasicDBObject("trace_id", "1234567810");
		DBCursor dbCursor = dbollection.find(query);
		for (DBObject dbObject : dbCursor) {
			logger.info("dbObject:trace_id:{},serviceName:{},project:{}",
					dbObject.get("trace_id"), dbObject.get("service_name"),
					dbObject.get("project"));
		}

	}
	/***
	 * 高级查询
	 */
	@Test
	public void advancedQuery(){
		String collectionName = "wm_trace";
		DBCollection dbollection = mongoManager.getConnection().getCollection(
				collectionName);
		BasicDBObject query = new BasicDBObject("trace_id", new BasicDBObject(QueryOperators.GTE, "1234567810"));
		DBCursor dbCursor = dbollection.find(query);
		for (DBObject dbObject : dbCursor) {
			logger.info("dbObject:trace_id:{},serviceName:{},project:{}",
					dbObject.get("trace_id"), dbObject.get("service_name"),
					dbObject.get("project"));
		}
	}
	/***
	 * 全局数据更新 
	 */
	@Test
	public void update(){
		String collectionName = "wm_trace";
		DBCollection dbollection = mongoManager.getConnection().getCollection(
				collectionName);
		BasicDBObject query = new BasicDBObject("trace_id","1234567811");
		BasicDBObject newValue = new BasicDBObject("trace_id","1234567811");
		newValue.put("service_name", "wanglijun");
		newValue.put("project", "application_a");
		WriteResult update = dbollection.update(query, newValue);
		logger.info("update:{}",update.getN()); // update.getN()返回的是影响的行数
	}
	
	/***
	 *字段级数据更新 
	 */
	@Test
	public void updateFields(){
		String collectionName = "wm_trace";
		DBCollection dbollection = mongoManager.getConnection().getCollection(
				collectionName);
		BasicDBObject query = new BasicDBObject("trace_id","1234567811");
		BasicDBObject newValue = new BasicDBObject("trace_id","123");
		BasicDBObject setValue= new BasicDBObject("$set",newValue);
		//newValue.put("service_name", "wanglijun");
		//newValue.put("project", "application_a");
		WriteResult update = dbollection.update(query, setValue);
		logger.info("update:{}",update.getN()); // update.getN()返回的是影响的行数
	}
	
	/***
	 * 数据更新
	 */
	@Test
	public void remove(){
		String collectionName = "wm_trace";
		DBCollection dbollection = mongoManager.getConnection().getCollection(
				collectionName);
		 
		BasicDBObject removeValue = new BasicDBObject("trace_id","123");	 
		 
		WriteResult update = dbollection.remove(removeValue,WriteConcern.FSYNC_SAFE);
		logger.info("update:{}",update.getN()); // update.getN()返回的是影响的行数
	}
}
