/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ZooKeeperAliveCheckerJob.java 9552 2015年7月24日 下午9:41:17 WangLijun$
*/
package com.newtouch.lion.monitor.job; 

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.newtouch.lion.alarm.model.AlarmSettings;
import com.newtouch.lion.dao.AlarmSettingsDao;
import com.newtouch.lion.dao.ZookeeperClusterDao;
import com.newtouch.lion.monitor.ExecutorServiceManager;
import com.newtouch.lion.monitor.GlobalInstance;
import com.newtouch.lion.monitor.MonitorInfo;
import com.newtouch.lion.monitor.task.ZeekeeperServerAliveChecking;
import com.newtouch.lion.zookeeper.model.ZooKeeperCluster;

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
@Component
public class ZooKeeperAliveCheckerJob  implements Runnable{
	/***
	 * 日志
	 */
	private static final Logger logger=LoggerFactory.getLogger(ZooKeeperAliveCheckerJob.class);
	/**Zookeeper集群*/
	private  List<ZooKeeperCluster>  zooKeeperClusters;
	/**Zookeeper集群*/
	private Map<Integer,ZooKeeperCluster> zooKeeperClusterMap;
	
	@Autowired
	private ZookeeperClusterDao  zookeeperClusterDao;
	
	@Autowired
	private AlarmSettingsDao  alarmSettingsDao;

	@Override
	public void run() {
		this.checkZookeeperCluster();
	}
	
	public void initData(){
		
		zooKeeperClusterMap=GlobalInstance.getAllZooKeeperCluster();
		
		if(CollectionUtils.isEmpty(this.zooKeeperClusterMap)){
			zooKeeperClusters=zookeeperClusterDao.findAll();
		}else{
			zooKeeperClusters=new ArrayList<ZooKeeperCluster>();
			zooKeeperClusters.addAll(zooKeeperClusterMap.values());
		}
	}
	
	public void checkZookeeperCluster(){
		//初始化数据
		while(true){
			this.initData();
			if(CollectionUtils.isEmpty(this.zooKeeperClusters)){
				logger.warn("No zookeeper Cluster...");
				return;
			}
			
			for(ZooKeeperCluster zooKeeper:zooKeeperClusters){
				//判断Zookeeper是已在检测
				if(!GlobalInstance.addToAllCheckingCluster(zooKeeper.getClusterId()+StringUtils.EMPTY)){
					logger.info("Zookeeper Cluster checking");
					continue;
				}
				
				if(null!=zooKeeper&&!CollectionUtils.isEmpty(zooKeeper.getServerList())){
					 AlarmSettings alarmSettings=alarmSettingsDao.findByClusterId(zooKeeper.getClusterId()+StringUtils.EMPTY); 
					 if(null==alarmSettings){
						 GlobalInstance.removeFromAllCheckingCluster(zooKeeper.getClusterId()+StringUtils.EMPTY);
						 continue;
					 }
					 ExecutorServiceManager.addJobZooKeeperNodeAliveCheckExecutor(new  ZeekeeperServerAliveChecking(zooKeeper,alarmSettings));
				}
			}
			// 每1分钟收集一次检测
			try {
				Thread.sleep( 1000 * 60 * MonitorInfo.ALIVE_CHECK_ZOOKEEPER_PER_TIME);
			} catch (InterruptedException e) {
				logger.error(StringUtils.EMPTY,e);
			}
		}
		
	}	
}