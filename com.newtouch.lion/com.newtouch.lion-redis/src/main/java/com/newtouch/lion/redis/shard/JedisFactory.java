/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: JedisFactory.java 9552 2015年7月10日 下午9:58:30 WangLijun$
 */
package com.newtouch.lion.redis.shard;

import java.net.URI;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import redis.clients.jedis.BinaryJedis;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.InvalidURIException;
import redis.clients.util.JedisURIHelper;

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

class JedisFactory implements PooledObjectFactory<Jedis> {
	
	private final AtomicReference<HostAndPort> hostAndPort = new AtomicReference<HostAndPort>();
	
	private final int timeout;
	
	private final String password;
	
	private final int database;
	
	private final String clientName;

	public JedisFactory(final String host, final int port, final int timeout,
			final String password, final int database) {
		this(host, port, timeout, password, database, null);
	}

	public JedisFactory(final String host, final int port, final int timeout,
			final String password, final int database, final String clientName) {
		this.hostAndPort.set(new HostAndPort(host, port));
		this.timeout = timeout;
		this.password = password;
		this.database = database;
		this.clientName = clientName;
	}

	public JedisFactory(final URI uri, final int timeout,
			final String clientName) {
		if (!JedisURIHelper.isValid(uri)) {
			throw new InvalidURIException(String.format(
					"Cannot open Redis connection due invalid URI. %s",
					uri.toString()));
		}

		this.hostAndPort.set(new HostAndPort(uri.getHost(), uri.getPort()));
		this.timeout = timeout;
		this.password = JedisURIHelper.getPassword(uri);
		this.database = JedisURIHelper.getDBIndex(uri);
		this.clientName = clientName;
	}

	public void setHostAndPort(final HostAndPort hostAndPort) {
		this.hostAndPort.set(hostAndPort);
	}

	@Override
	public void activateObject(PooledObject<Jedis> pooledJedis)
			throws Exception {
		final BinaryJedis jedis = pooledJedis.getObject();
		if (jedis.getDB() != database) {
			jedis.select(database);
		}

	}

	@Override
	public void destroyObject(PooledObject<Jedis> pooledJedis) throws Exception {
		final BinaryJedis jedis = pooledJedis.getObject();
		if (jedis.isConnected()) {
			try {
				try {
					jedis.quit();
				} catch (Exception e) {
				}
				jedis.disconnect();
			} catch (Exception e) {

			}
		}

	}

	@Override
	public PooledObject<Jedis> makeObject() throws Exception {
		final HostAndPort hostAndPort = this.hostAndPort.get();
		final Jedis jedis = new Jedis(hostAndPort.getHost(),
				hostAndPort.getPort(), this.timeout);

		jedis.connect();
		if (StringUtils.isNotEmpty(this.password)) {
			jedis.auth(this.password);
		}
		if (database != 0) {
			jedis.select(database);
		}
		if (clientName != null) {
			jedis.clientSetname(clientName);
		}

		return new DefaultPooledObject<Jedis>(jedis);
	}

	@Override
	public void passivateObject(PooledObject<Jedis> pooledJedis)
			throws Exception {
		// TODO maybe should select db 0? Not sure right now.
	}

	@Override
	public boolean validateObject(PooledObject<Jedis> pooledJedis) {
		final BinaryJedis jedis = pooledJedis.getObject();
		try {
			HostAndPort hostAndPort = this.hostAndPort.get();

			String connectionHost = jedis.getClient().getHost();
			int connectionPort = jedis.getClient().getPort();

			return hostAndPort.getHost().equals(connectionHost)
					&& hostAndPort.getPort() == connectionPort
					&& jedis.isConnected() && jedis.ping().equals("PONG");
		} catch (final Exception e) {
			return false;
		}
	}
}
