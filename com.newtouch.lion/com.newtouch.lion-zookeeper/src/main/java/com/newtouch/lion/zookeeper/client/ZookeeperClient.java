/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ZookeeperClient.java 9552 2015年5月25日 下午2:48:39 WangLijun$
*/
package com.newtouch.lion.zookeeper.client; 

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.CuratorFrameworkFactory.Builder;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import com.newtouch.lion.zookeeper.config.ZookeeperConfig;
import com.newtouch.lion.zookeeper.listener.ZookeeperConnectionStateListener;

/**
 * <p>
 * Title: Zookeeper 连接创建
 * </p>
 * <p>
 * Description: Zookeeper 连接创建
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
public class ZookeeperClient {
	
	/**Zookeeper连接配置信息*/
	private ZookeeperConfig config;
	/**连接客户端*/
	private CuratorFramework client;
	/**连接监听器*/
	private ZookeeperConnectionStateListener  connectionStateListener;
	
	
	public ZookeeperClient() {
		super();
		//this.initBuilder();
	}
	/***
	 * Zookeeper 客户端
	 * @return
	 */
	public CuratorFramework builder(){
		if(this.client==null){
			this.initBuilder();
		}
		//判断是否已经启动
		if(!this.client.getState().equals(CuratorFrameworkState.STARTED)){
			this.client.start();
		}
		return this.client;
	}
	/***
	 * 初始化信息
	 */
	private void initBuilder(){
		//重试机制 重试指定的次数, 且每一次重试之间停顿的时间逐渐增加.
		RetryPolicy retryPolicy=new ExponentialBackoffRetry(config.getBaseSleepTime(), config.getMaxRetries());
		//连接创建
		Builder builder=CuratorFrameworkFactory.builder();
		//是否只读
		builder.canBeReadOnly(config.getReadOnly());
		//命名空间
		builder.namespace(config.getNameSpace());
		//重试机制
		builder.retryPolicy(retryPolicy);
		//会话超时时间
		builder.sessionTimeoutMs(config.getSessionTimeout());
		//连接超时时间
		builder.connectionTimeoutMs(config.getConnectionTimeout());
		//连接服务器地址
		builder.connectString(config.getConnectString());		 
		//连接
		this.client=builder.build();
		//添加连接监听器
		if(this.connectionStateListener!=null){
			this.client.getConnectionStateListenable().addListener(this.connectionStateListener);
		}
	}
	
	/**
	 * 设置监听器
	 * @param connectionStateListener the connectionStateListener to set
	 */
	public void setConnectionStateListener(ZookeeperConnectionStateListener connectionStateListener) {
		this.connectionStateListener = connectionStateListener;
	}
	/***
	 * 关闭Zookeeper客户端
	 * @param client 连接
	 */
	public void close(CuratorFramework client){
		CloseableUtils.closeQuietly(client);
	}
	/**
	 * @return the config
	 */
	public ZookeeperConfig getConfig() {
		return config;
	}
	/**
	 * @param config the config to set
	 */
	public void setConfig(ZookeeperConfig config) {
		this.config = config;
	}
}

	