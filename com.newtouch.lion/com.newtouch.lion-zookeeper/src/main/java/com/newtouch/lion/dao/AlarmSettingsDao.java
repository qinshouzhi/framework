/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: AlarmSettingsDao.java 9552 2015年7月24日 下午10:09:04 WangLijun$
*/
package com.newtouch.lion.dao; 

import com.newtouch.lion.alarm.model.AlarmSettings;

/**
 * <p>
 * Title: 警告策略
 * </p>
 * <p>
 * Description: 警告策略
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
public interface AlarmSettingsDao {
	/***
	 * 根据Zookeeper ClusterId 查找警告策略信息
	 * @param clusterId 集群ID
	 * @return  AlarmSettings
	 */
	public AlarmSettings findByClusterId(String clusterId);
}

	