/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: Subscriber.java 9552 2015年7月23日 下午8:54:22 WangLijun$
*/
package com.newtouch.lion.zookeeper.model; 

import java.util.ArrayList;
import java.util.List;

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
	
	private String serverList;
	
	private String path;
	
	private CuratorFramework client= null;
	
	private int maxDelaySecsForNotify=1;
	
	private  String lastedUpdateToServer =StringUtils.EMPTY;
	
	private NodeCache cache;

	/**
	 * @param serverList
	 * @param path
	 * @param maxDelaySecsForNotify
	 */
	public Subscriber(String serverList, String path, int maxDelaySecsForNotify) {
		this.serverList = serverList;
		this.path = path;
		this.maxDelaySecsForNotify=maxDelaySecsForNotify;
		//创建Zookeeper Client连接
		ZookeeperConfig config=new ZookeeperConfig(this.serverList,500,500,false,10,1,"zookeeper");
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
		for(int i=0;i<3;i++){
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
	
	
	public static void main(String[] args) throws InterruptedException {
		List<String> serverList = new ArrayList< String >();
		serverList.add( "127.0.0.1:2181" );
		serverList.add( "10.232.37.128:2181");
		serverList.add( "10.232.37.126:2181");
	 
			for( String server : serverList ){
				
				Subscriber sub = null;
				try {
					sub = new Subscriber( server, "/wanglijun/test", 1 );
					 
					if( !sub.checkAlive() ){			 
						logger.info( server + " error" );
					 
					}else{
						logger.info( server + " alive" );
					}
					Thread.sleep( 1000 * 5 );
				} catch ( Exception e ) {
					logger.error( server + "" + e.getMessage() );
				}finally{
					if( null != sub )
						sub.close();
				}
			}
			Thread.sleep( 5000 );
		}
	 
	
}

	