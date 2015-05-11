/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: Configuration.java 9552 2015年5月8日 下午2:23:14 WangLijun$
*/
package com.newtouch.lion.mongo.config; 

import javax.naming.RefAddr;
import javax.naming.Reference;

import com.mongodb.MongoClientOptions;

/**
 * <p>
 * Title: Mongo DB 连接配置
 * </p>
 * <p>
 * Description: Mongo DB 连接配置，包括用户名及密码、HOST、端口 、数据库
 * </p>Pool:minSize maxSize、maxWaitTime
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
public class Config {
	    // Server constants
		/**主机地址*/
		private static final String PROP_HOST = "host";
		/**端口*/
		private static final String PROP_PORT = "port";
		// Authentication constants
		/**数据库*/
		private static final String PROP_DB_NAME	= "dbname";
		/**用户名*/
		private static final String PROP_USERNAME	= "username";
		/**密码*/
		private static final String PROP_PASSWORD	= "password";
		// Pooling constants
		/**最小连接数*/
		private static final String PROP_MIN_POOL_SIZE	= "minPoolSize";
		/**最大连接数*/
		private static final String PROP_MAX_POOL_SIZE	= "maxPoolSize";
		/**最大等待时间*/
		private static final String PROP_MAX_WAIT_TIME	= "maxWaitTime";
		/**超时*/
		@SuppressWarnings("unused")
		private static final String PROP_TIMEOUT="timeout";
		/**是否自动重新连接*/
		@SuppressWarnings("unused")
		private static final String AUTO_CONNECT="autoconnect";
		
		/**
		 * MongoDB的连接URL
		 * */
		public String url;
		/**
		 * 与目标数据库能够建立的最大connection数量,默认为10 
		 * */
		private int perHost=10;

		// Server settings
		private String host	= "localhost";
		
		private int    port= 27017;
		// Authentication settings
		private String dbname= null;
		
		private String username= null;
		
		private String password= null;
		
		// Pooling settings
		private Integer minPoolSize	= 5;
		
		private Integer maxPoolSize	= 10;
		/** 
		 * 一个线程访问数据库的时候，在成功获取到一个可用数据库连接之前的最长等待时间为2分钟 
		 * 这里比较危险，如果超过maxWaitTime都没有获取到这个连接的话，该线程就会抛出Exception 
		 * 故这里设置的maxWaitTime应该足够大，以免由于排队线程过多造成的数据库访问失败 
		 * 默认设置为：2分钟
		 */ 
		private Integer maxWaitTime	= 1000*60*2;
		/**
		 * 与数据库建立连接的timeout默认设置为1分钟 
		 * */
		private Integer timeout=1000*60*1;
		
		/**是否自动连接*/
		private boolean  autoconnect=Boolean.TRUE;
		
		/**threadsAllowedToBlockForConnectionMultiplier 
		 * 如果当前所有的connection都在使用中，则每个connection上可以有50个线程排队等待
		 * */
		
		private int threadsAllowedToBlockForConnectionMultiplier=10;
		
		/**socketTimeout read数据超时时间 默认为：60000*/
		private  Integer socketTimeout=1000*60*1;
		/**
		 * // 是否保持长链接 默认等于true
		 * */
		private  boolean socketKeepAlive=Boolean.TRUE;
		
		public static Config loadFromJNDIReference(Reference reference) throws Exception {
			Config config = new Config();

			// Server settings
			
			// Optional DB host
			String host = getReferenceValue(reference, PROP_HOST);
			if (!isEmptyValue(host)) {
				config.host = host;
			}
			// Optional DB port
			Integer optionalIntValue = getOptionalReferenceValueAsInteger(reference, PROP_PORT);
			if (optionalIntValue != null) {
				config.port = optionalIntValue.intValue();
			}
			
			// Authentication settings
			
			// DB name
			config.dbname = getReferenceValue(reference, PROP_DB_NAME);
			if (isEmptyValue(config.dbname)) {
				throw new Exception("Missing value for mandatory property "+ PROP_DB_NAME);
			}
			// Optional user name
			config.username = getReferenceValue(reference, PROP_USERNAME);
			// Optional password (mandatory if user name is specified)
			config.password = getReferenceValue(reference, PROP_PASSWORD);
			if ((!isEmptyValue(config.username)) && (isEmptyValue(config.password)))
				throw new Exception("Missing password property: "+ PROP_PASSWORD);
			
			// Pooling settings
			
			// Optional minimum pool size
			optionalIntValue = getOptionalReferenceValueAsInteger(reference, PROP_MIN_POOL_SIZE);
			if (optionalIntValue != null)
				config.minPoolSize = optionalIntValue;
			// Optional maximum pool size
			optionalIntValue = getOptionalReferenceValueAsInteger(reference, PROP_MAX_POOL_SIZE);
			if (optionalIntValue != null) 
				config.maxPoolSize = optionalIntValue;
			// Optional maximum wait time to retrieved a connection from the pool
			optionalIntValue = getOptionalReferenceValueAsInteger(reference, PROP_MAX_WAIT_TIME);
			if (optionalIntValue != null)
				config.maxWaitTime = optionalIntValue;
			
			return config;
		}

		/**
		 * Assembles a MongoClientOptions object based on datasource configuraton
		 * @param datasourceName name of the datasource
		 * @return MongoClientOptions initialized with datasource configuration
		 */
		public MongoClientOptions getMongoClientOptions(String datasourceName) {
			MongoClientOptions.Builder optionBuilder = MongoClientOptions.builder();

			// Set DS name
			optionBuilder.description(datasourceName);
			
			// Set min pool size
			if (this.minPoolSize != null)
				optionBuilder.minConnectionsPerHost(this.minPoolSize.intValue());
			// Set max pool size
			if (this.maxPoolSize != null)
				optionBuilder.connectionsPerHost(this.maxPoolSize.intValue());
			// Set max wait time
			if (this.maxWaitTime != null)
				optionBuilder.maxWaitTime(this.maxWaitTime.intValue());
			
			return optionBuilder.build();
		}

		private static Integer getOptionalReferenceValueAsInteger(Reference reference, String key) throws Exception {
			String stringValue = getReferenceValue(reference, key);
			if (!isEmptyValue(stringValue)) {
				try {
					return Integer.valueOf(Integer.parseInt(stringValue));
				} catch (NumberFormatException e) {
					throw new Exception("Invalid " + key + " value " + stringValue, e);
				}
			}
			return null;
		}

		private static String getReferenceValue(Reference reference, String key) {
			RefAddr property = reference.get(key);
			return property == null ? null : (String) property.getContent();
		}

		private static boolean isEmptyValue(String value) {
			return (value == null) || (value.trim().isEmpty());
		}

		public String toString() {
			StringBuffer output = new StringBuffer("Configuration {");
			// Server settings
			output.append(PROP_HOST).append("=").append(this.host).append(", ");
			output.append(PROP_PORT).append("=").append(this.port).append(", ");
			// Authentication settings
			output.append(PROP_DB_NAME).append("=").append(this.dbname).append(", ");
			output.append(PROP_USERNAME).append("=").append(this.username).append(", ");
			// Pooling settings
			output.append(PROP_MIN_POOL_SIZE).append("=").append(this.minPoolSize).append(", ");
			output.append(PROP_MAX_POOL_SIZE).append("=").append(this.maxPoolSize).append(", ");
			output.append(PROP_MAX_WAIT_TIME).append("=").append(this.maxWaitTime);
			output.append("}");
			return output.toString();
		}

		/**
		 * @return the host
		 */
		public String getHost() {
			return host;
		}

		/**
		 * @param host the host to set
		 */
		public void setHost(String host) {
			this.host = host;
		}

		/**
		 * @return the port
		 */
		public int getPort() {
			return port;
		}

		/**
		 * @param port the port to set
		 */
		public void setPort(int port) {
			this.port = port;
		}
 

		/**
		 * @return the dbname
		 */
		public String getDbname() {
			return dbname;
		}

		/**
		 * @param dbname the dbname to set
		 */
		public void setDbname(String dbname) {
			this.dbname = dbname;
		}

		/**
		 * @return the username
		 */
		public String getUsername() {
			return username;
		}

		/**
		 * @param username the username to set
		 */
		public void setUsername(String username) {
			this.username = username;
		}

		/**
		 * @return the password
		 */
		public String getPassword() {
			return password;
		}

		/**
		 * @param password the password to set
		 */
		public void setPassword(String password) {
			this.password = password;
		}

		/**
		 * @return the minPoolSize
		 */
		public Integer getMinPoolSize() {
			return minPoolSize;
		}

		/**
		 * @param minPoolSize the minPoolSize to set
		 */
		public void setMinPoolSize(Integer minPoolSize) {
			this.minPoolSize = minPoolSize;
		}

		/**
		 * @return the maxPoolSize
		 */
		public Integer getMaxPoolSize() {
			return maxPoolSize;
		}

		/**
		 * @param maxPoolSize the maxPoolSize to set
		 */
		public void setMaxPoolSize(Integer maxPoolSize) {
			this.maxPoolSize = maxPoolSize;
		}

		/**
		 * @return the maxWaitTime
		 */
		public Integer getMaxWaitTime() {
			return maxWaitTime;
		}

		/**
		 * @param maxWaitTime the maxWaitTime to set
		 */
		public void setMaxWaitTime(Integer maxWaitTime) {
			this.maxWaitTime = maxWaitTime;
		}

		/**
		 * @return the timeout
		 */
		public Integer getTimeout() {
			return timeout;
		}

		/**
		 * @param timeout the timeout to set
		 */
		public void setTimeout(Integer timeout) {
			this.timeout = timeout;
		}

		/**
		 * @return the perHost
		 */
		public int getPerHost() {
			return perHost;
		}

		/**
		 * @param perHost the perHost to set
		 */
		public void setPerHost(int perHost) {
			this.perHost = perHost;
		}

		/**
		 * @return the autoconnect
		 */
		public boolean isAutoconnect() {
			return autoconnect;
		}

		/**
		 * @param autoconnect the autoconnect to set
		 */
		public void setAutoconnect(boolean autoconnect) {
			this.autoconnect = autoconnect;
		}

		/**
		 * @return the threadsAllowedToBlockForConnectionMultiplier
		 */
		public int getThreadsAllowedToBlockForConnectionMultiplier() {
			return threadsAllowedToBlockForConnectionMultiplier;
		}

		/**
		 * @param threadsAllowedToBlockForConnectionMultiplier the threadsAllowedToBlockForConnectionMultiplier to set
		 */
		public void setThreadsAllowedToBlockForConnectionMultiplier(
				int threadsAllowedToBlockForConnectionMultiplier) {
			this.threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;
		}

		/**
		 * @return the socketTimeout
		 */
		public int getSocketTimeout() {
			return socketTimeout;
		}

		/**
		 * @param socketTimeout the socketTimeout to set
		 */
		public void setSocketTimeout(int socketTimeout) {
			this.socketTimeout = socketTimeout;
		}

		/**
		 * @return the socketKeepAlive
		 */
		public boolean isSocketKeepAlive() {
			return socketKeepAlive;
		}

		/**
		 * @param socketKeepAlive the socketKeepAlive to set
		 */
		public void setSocketKeepAlive(boolean socketKeepAlive) {
			this.socketKeepAlive = socketKeepAlive;
		}

		/**
		 * @return the url
		 */
		public String getUrl() {
			return url;
		}

		/**
		 * @param url the url to set
		 */
		public void setUrl(String url) {
			this.url = url;
		}
		
		
		
}

	