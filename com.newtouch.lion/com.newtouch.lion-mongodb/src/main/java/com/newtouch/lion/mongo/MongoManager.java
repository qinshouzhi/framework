/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: MongoManager.java 9552 2015年4月22日 下午4:52:26 WangLijun$
 */
package com.newtouch.lion.mongo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bson.Document;
import org.springframework.beans.factory.InitializingBean;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.newtouch.lion.mongo.config.Config;

/**
 * <p>
 * Title: Mongo 连接池管理
 * </p>
 * <p>
 * Description: Mongo 连接池管理
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
public class MongoManager  implements InitializingBean{
	/** Mongo Client */
	private static MongoClient client;
	/** Mongo Config */
	private    Config config;

	public MongoManager() {
		 super();
	}
	
	

	/**
	 * @return the config
	 */
	public  Config getConfig() {
		return this.config;
	}

	/**
	 * @param config the config to set
	 */
	public  void setConfig(Config config) {
		this.config = config;
	}
	
	

	@SuppressWarnings("unused")
	@Override
	public void afterPropertiesSet() throws Exception {
		 if(client==null){
			 MongoClientURI clientURI=null;
			 ServerAddress serverAddress=null;
			 //连接 
			 if(StringUtils.isEmpty(this.config.getUrl())){			 
				 serverAddress = new ServerAddress(this.config.getHost(),this.config.getPort());
			 }else{
				 clientURI=new MongoClientURI(this.config.url);
			 }
			 MongoClientOptions.Builder  builder= new MongoClientOptions.Builder();
			 //是否保持长连接
			 builder.socketKeepAlive(config.isSocketKeepAlive());
			 //与目标数据库能够建立的最大connection数量为50
			 builder.connectionsPerHost(config.getPerHost());
			 //与数据库建立连接的timeout设置为1分钟
			 builder.connectTimeout(config.getTimeout());
			 //最长等待时间 默认为2分钟
			 builder.maxWaitTime(config.getMaxWaitTime());
			 //如果当前所有的connection都在使用中，则每个connection上可以有50个线程排队等待
			 builder.threadsAllowedToBlockForConnectionMultiplier(config.getThreadsAllowedToBlockForConnectionMultiplier());
			 //最近优先策略
			 builder.readPreference(ReadPreference.primary());
			 //Read数据超时时间
			 builder.socketTimeout(config.getSocketTimeout());
			 //判断是否添加认证信息
			 if(StringUtils.isNotEmpty(config.getUsername())&&StringUtils.isNotEmpty(config.getPassword())){
				 
				 MongoCredential credential= MongoCredential.createCredential(config.getUsername(), config.getDbname(), config.getPassword().toCharArray());
				 List<MongoCredential>  credentials=new ArrayList<MongoCredential>();
				 credentials.add(credential);				 
				 client=new MongoClient(serverAddress,credentials, builder.build());
				 
			 }else{
				 client=new MongoClient(serverAddress,builder.build());
			 }
		 }
	}
	
	/****
	 * 返回DB数据库
	 * @return DB
	 */
	@SuppressWarnings("deprecation")
	public DB  getConnection(){
		return client.getDB(this.config.getDbname());
	}
	
	public MongoDatabase getConnectionForDataBase(){
		return client.getDatabase(this.config.getDbname());
	}
	
	/***
	 * 获取DB collection
	 * @param collectionName  集合名称
	 * @return DBCollection 集合名称
	 */
	@SuppressWarnings({ "static-access", "deprecation" })
	public DBCollection  dbCollection(String collectionName){
		return  this.client.getDB(this.config.getDbname()).getCollection(collectionName);
	}
	

	@SuppressWarnings("static-access")
	public MongoCollection<Document> mongoCollection(String collectionName){
		return this.client.getDatabase(this.config.getDbname()).getCollection(collectionName);
	}  
	
	 
	
	/***
	 * 关闭连接
	 */
	@SuppressWarnings("static-access")
	public void close(){
		this.client.close();
	}
}
