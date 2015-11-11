/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: MongoDocumentTest.java 9552 2015年5月11日 下午3:54:21 WangLijun$
*/
package com.newtouch.lion.mongo; 

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * <p>
 * Title: Document 常用insert,query,update,remove
 * </p>
 * <p>
 * Description: Document 常用insert,query,update,remove
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
public class MongoDocumentTest extends  MongoAllTest{
	

	@Autowired
	private MongoManager mongoManager;
	
	private String collectionName= "wm_trace";
	
	/***
	 * 插入数据
	 */
	@Test
	public void insert(){
		Document doc = new Document("trace_id", "MongoDB")
        .append("service_name", "database")
        .append("service_id", 1)
        .append("project","dddd");
		
		MongoDatabase  database= mongoManager.getConnectionForDataBase();
	  	MongoCollection<Document>  collection=database.getCollection(collectionName);
	  	collection.insertOne(doc);
	}
	
	
	@Test
	public void insertMany(){
		List<Document> documents = new ArrayList<Document>();
		for (int i = 0; i < 100; i++) {
			Document doc = new Document("trace_id", "ti"+i)
	        .append("service_name", "database")
	        .append("service_id", 1)
	        .append("project","dddd");
			documents.add(doc);
		}
		
		
		MongoCollection<Document> collection=mongoManager.mongoCollection(collectionName);
		collection.insertMany(documents);
	}
	
	/**
	 * 统计查询
	 * */
	@Test
	public void count(){
		MongoCollection<Document> collection=mongoManager.mongoCollection(collectionName);
		long count=collection.count();
		logger.info("count:{}",count);
	}
	
	/***
	 * 查询第一条记录
	 */
	@Test
	public void queryFirst(){
		MongoCollection<Document> collection=mongoManager.mongoCollection(collectionName);
		Document myDoc = collection.find().first();
		logger.info("toJson:{}",myDoc.toJson());
	}
	 
	
}

	