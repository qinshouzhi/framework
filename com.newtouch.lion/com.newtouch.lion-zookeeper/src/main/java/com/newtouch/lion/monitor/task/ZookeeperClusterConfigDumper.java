/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ZookeeperClusterConfigDumper.java 9552 2015年7月24日 下午10:44:19 WangLijun$
*/
package com.newtouch.lion.monitor.task; 

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.newtouch.lion.dao.ZookeeperClusterDao;
import com.newtouch.lion.monitor.GlobalInstance;
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
public class ZookeeperClusterConfigDumper implements Runnable{
	
	/**日志*/
	private static final Logger logger=LoggerFactory.getLogger(ZookeeperClusterConfigDumper.class);
	
	@Autowired
	private ZookeeperClusterDao zookeeperClusterDao;
	
	
	@Override
	public void run() {
		this.dumpZookeeperClusterToMemory();
	}
	
	private void dumpZookeeperClusterToMemory(){
		List< ZooKeeperCluster > zookeeperClusters = zookeeperClusterDao.findAll();
		if(CollectionUtils.isEmpty(zookeeperClusters)){
			logger.warn("No zookeeper Cluster");
			return;
		}
		GlobalInstance.clearZooKeeperCluster();
		
		for(ZooKeeperCluster cluster:zookeeperClusters){
			GlobalInstance.putZooKeeperCluster(cluster.getClusterId(), cluster);
		}
		logger.info("dump all cluster info from db:{}",GlobalInstance.getAllZooKeeperCluster());
	}
}

	