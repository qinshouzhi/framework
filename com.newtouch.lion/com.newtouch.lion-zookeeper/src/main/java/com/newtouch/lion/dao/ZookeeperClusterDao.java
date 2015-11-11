/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ZookeeperCluster.java 9552 2015年7月24日 下午9:50:37 WangLijun$
*/
package com.newtouch.lion.dao; 

import java.util.List;

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
public interface ZookeeperClusterDao {
	
	public  List<ZooKeeperCluster> findAll();
}

	