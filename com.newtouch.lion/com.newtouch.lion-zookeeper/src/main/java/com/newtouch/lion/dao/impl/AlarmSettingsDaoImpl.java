/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: AlarmSettingsDaoImpl.java 9552 2015年7月24日 下午10:09:24 WangLijun$
*/
package com.newtouch.lion.dao.impl; 

import org.springframework.stereotype.Repository;

import com.newtouch.lion.alarm.model.AlarmSettings;
import com.newtouch.lion.dao.AlarmSettingsDao;

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
public class AlarmSettingsDaoImpl implements AlarmSettingsDao{

	@Override
	public AlarmSettings findByClusterId(String clusterId) {
		AlarmSettings alarmSettings=new AlarmSettings(1,"3","80","80","80","", "","scwanglijun@gmail.com", "10", "50", "100","100", "80","");
		return alarmSettings;
	}
}

	