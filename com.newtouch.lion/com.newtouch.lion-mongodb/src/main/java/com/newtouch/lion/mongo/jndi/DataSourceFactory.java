/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: DataSourceFactory.java 9552 2015年5月8日 下午2:56:38 WangLijun$
 */
package com.newtouch.lion.mongo.jndi;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.Reference;
import javax.naming.spi.ObjectFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.newtouch.lion.mongo.config.Config;
import com.newtouch.lion.mongo.exception.ErrorCode;
import com.newtouch.lion.mongo.exception.MongoException;

/**
 * <p>
 * Title: A MongoDB JDNI pooled datasource that can be used in a Java server
 * </p>
 * <p>
 * Description: A MongoDB JDNI pooled datasource that can be used in a Java
 * server
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
public class DataSourceFactory implements ObjectFactory {

	/** 日志 */
	private static final Logger logger = LoggerFactory
			.getLogger(DataSourceFactory.class);
	/** 连接地址信息 */
	private static final Map<String, DataSource> datasources = new ConcurrentHashMap<String, DataSource>();

	@Override
	public Object getObjectInstance(Object obj, Name name, Context nameCtx,
			Hashtable<?, ?> environment) throws Exception {
		try {
			final DataSource datasource = getDataSource(obj, name.toString());
			return datasource.getConnection();
		} catch (Exception e) {
			throw new MongoException(ErrorCode.DATA_SOURCE.code(),e);
		}
	}

	private static synchronized DataSource getDataSource(
			Object referenceObject, String dsName) {
		DataSource datasource = (DataSource) datasources.get(dsName);

		if (datasource == null) {
			logger.info("[DS {} ] Initializing datasource for first use...",
					dsName);

			// Check datasource reference
			if ((referenceObject == null)|| (!(referenceObject instanceof Reference)))
				throw new MongoException(ErrorCode.DATA_SOURCE.code(),"[DS " + dsName+ "] Invalid JNDI object reference");
			final Reference reference = (Reference) referenceObject;

			// Load datasource
			try {
				datasource = loadDatasource(dsName, reference);
			} catch (Exception e) {
				throw new MongoException(ErrorCode.DATA_SOURCE.code(),"Failed to load configuration: "
						+ e.getMessage(), e);
			}

			// Test connection for first use
			try {
				final DB db = datasource.getConnection();

				try {
					db.getCollectionNames();
				} finally {
				}
			} catch (Exception e) {
				throw new MongoException(ErrorCode.DATA_SOURCE.code(),"Initial connection test failed: "
						+ e.getMessage(), e);
			}

			// Add datasource to cache
			logger.info("[DS " + dsName
					+ "] Datasource initialized and added to cache");

		}
		logger.info("[DS " + dsName + "] Using cached datasource");

		return datasource;
	}

	private static DataSource loadDatasource(final String dsName,
			final Reference reference) throws Exception {
		// Load datasource configuration
		final Config config = Config.loadFromJNDIReference(reference);
		logger.info("[DS  {} ] Configuration loaded: {}", dsName, config);

		// Prepare Mongo client configuration
		final MongoClientOptions options = config.getMongoClientOptions(dsName);
		final ServerAddress serverAddress = new ServerAddress(config.getHost(),
				config.getPort());

		// Create Mongo client instance
		final MongoClient mongoClient;
		if (config.getUsername() != null && !config.getUsername().isEmpty()) {

			logger.info(
					"[DS {} ] Attempting to create authenticated connection...",
					dsName);
			final MongoCredential credential = MongoCredential
					.createMongoCRCredential(config.getUsername(), config
							.getDbname(), config.getPassword().toCharArray());
			mongoClient = new MongoClient(serverAddress,
					Arrays.asList(new MongoCredential[] { credential }),
					options);
		} else {

			logger.info(
					"[DS  {}] Attempting to create anonymous connection...",
					dsName);
			mongoClient = new MongoClient(serverAddress, options);
		}

		return new DataSource(mongoClient, config);
	}

}
