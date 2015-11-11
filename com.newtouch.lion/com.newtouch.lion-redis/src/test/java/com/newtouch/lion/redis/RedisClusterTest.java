/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: RedisClusterTest.java 9552 2015年7月10日 下午2:17:40 WangLijun$
 */
package com.newtouch.lion.redis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

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
public class RedisClusterTest {
	private static JedisCluster jedisCluster;
	
	

	static {
		// 只给集群里一个实例就可以
		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
		/*jedisClusterNodes.add(new HostAndPort("192.168.205.36", 6379));
		jedisClusterNodes.add(new HostAndPort("192.168.205.37", 6379));
		jedisClusterNodes.add(new HostAndPort("192.168.205.38", 6379));
		jedisClusterNodes.add(new HostAndPort("192.168.205.36", 7379));
		jedisClusterNodes.add(new HostAndPort("192.168.205.37", 7379));
		jedisClusterNodes.add(new HostAndPort("192.168.205.38", 7379));*/
		
		jedisClusterNodes.add(new HostAndPort("192.168.1.59", 6379));
		jedisClusterNodes.add(new HostAndPort("192.168.1.60", 6379));
		jedisClusterNodes.add(new HostAndPort("192.168.1.61", 6379));
		jedisClusterNodes.add(new HostAndPort("192.168.1.60", 7379));
		jedisClusterNodes.add(new HostAndPort("192.168.1.61", 7379));
		jedisClusterNodes.add(new HostAndPort("192.168.1.62", 7379));
		jedisCluster = new JedisCluster(jedisClusterNodes);
	
	}

	@Test
	public void testBenchRedisSet() throws Exception {
		
		for (int i = 0; i < 1000; i++) {
			String key = "key:" + i;
			jedisCluster.set("watchmen:"+i,key);
		}
	 
	}
	
	
	@Test
	public void query() throws Exception {
	 
	 
		for (int i = 0; i < 1000; i++) {
			String key = "key:" + i;
			String value=jedisCluster.get("watchmen:"+i);
			System.out.println("value:"+value);
		}
	 
	}
}
