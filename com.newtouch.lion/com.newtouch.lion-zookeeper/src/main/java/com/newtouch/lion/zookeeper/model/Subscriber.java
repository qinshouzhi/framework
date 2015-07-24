/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: Subscriber.java 9552 2015年7月23日 下午8:54:22 WangLijun$
*/
package com.newtouch.lion.zookeeper.model; 

import org.apache.commons.lang.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newtouch.lion.zookeeper.client.ZookeeperClient;
import com.newtouch.lion.zookeeper.config.ZookeeperConfig;

/**
 * <p>
 * Title: Zookeeper　订阅消息
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
public class Subscriber {
	
	/**日志*/
	private static final Logger logger=LoggerFactory.getLogger(Subscriber.class);
	
	private int okTimes = 0;
	/**Zookeeper服务器地址*/
	private String serverList;
	/**路径*/
	private String path;
	/**命名空间*/
	private String nameSpace;
	
	private CuratorFramework client= null;
	
	private int maxDelaySecsForNotify=1;
	
	private  String lastedUpdateToServer =StringUtils.EMPTY;
	
	private NodeCache cache;
	
	private int checkAliveTime=3;

	/***
	 * 
	 * @param serverList
	 * @param path 路径
	 * @param nameSpace　命名空间
	 * @param maxDelaySecsForNotify
	 */
	public Subscriber(String serverList, String path,String nameSpace,int maxDelaySecsForNotify,int checkAliveTime) {
		this.serverList = serverList;
		this.path = path;
		this.maxDelaySecsForNotify=maxDelaySecsForNotify;
		this.nameSpace=nameSpace;
		this.checkAliveTime=checkAliveTime;
		//创建Zookeeper Client连接
		ZookeeperConfig config=new ZookeeperConfig(this.serverList,500,500,false,500,1,this.nameSpace);
		this.client=new ZookeeperClient(config).builder();
		this.monitorData(path);
	}
	
	//监控数据变化
	private void monitorData(String path){		
		cache=new NodeCache(client,this.path,false);
		try {
				cache.start();
				cache.getListenable().addListener(new NodeCacheListener() {				
				@Override
				public void nodeChanged() throws Exception {
					String data=new String(cache.getCurrentData().getData());
					if(lastedUpdateToServer.equals(data)){
						okTimes++;
						return;
					}
				}
			});
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		
	}
	//检查是否存活着
	public boolean checkAlive(){
		this.createNodeNotExist(this.path);
		for(int i=0;i<this.checkAliveTime;i++){
			this.lastedUpdateToServer=this.serverList+System.currentTimeMillis();
			this.updateNodeData(this.lastedUpdateToServer);
			try {
				Thread.sleep(1000*this.maxDelaySecsForNotify);
			} catch (InterruptedException e) {
				logger.error(e.getMessage(),e);
				return false;
			}
		}
		
		return this.okTimes==3;
	}
	
	//关闭
	public void close(){
		CloseableUtils.closeQuietly(this.cache);
		CloseableUtils.closeQuietly(this.client);
	}
	
	//更新节点数据
	private void updateNodeData(String newData){
		try {
			this.client.setData().forPath(this.path,newData.getBytes());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	//更新节点数据
	private void createNodeNotExist(String defaultdata){
		try {
			Stat  stat=this.client.checkExists().forPath(this.path);
			if(stat==null){
				this.client.create().withMode(CreateMode.PERSISTENT).forPath(this.path,(this.serverList+System.currentTimeMillis()).getBytes());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}

	