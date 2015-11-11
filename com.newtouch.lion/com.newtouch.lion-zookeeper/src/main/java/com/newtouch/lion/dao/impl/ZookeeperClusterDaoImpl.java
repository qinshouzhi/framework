/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ZookeeperClusterDaoImpl.java 9552 2015年7月24日 下午9:51:08 WangLijun$
*/
package com.newtouch.lion.dao.impl; 

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.newtouch.lion.dao.ZookeeperClusterDao;
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
@Repository
public class ZookeeperClusterDaoImpl implements ZookeeperClusterDao{

	
	
	@Override
	public List<ZooKeeperCluster> findAll() {	 
		String serverListStr="192.168.205.36:2181,192.168.205.37:2181,192.168.205.38:2181";
		String[] serverListArray = serverListStr.split(",");
		List<String>  serverList =Arrays.asList(serverListArray);		 
		List<ZooKeeperCluster> keeperClusters=new ArrayList<ZooKeeperCluster>();
		keeperClusters.add(new ZooKeeperCluster(1,"lion", "Zookeeper",serverList));
		return keeperClusters;
	}
	
}

	