/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: EventBusMessager.java 9552 2015年7月24日 下午6:00:07 WangLijun$
*/
package com.newtouch.lion.monitor.event; 

import com.google.common.eventbus.EventBus;
import com.newtouch.lion.message.sender.MessageListener;
import com.newtouch.lion.monitor.task.MonitorInfo;

/**
 * <p>
 * Title: EventBusManger
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
public class EventBusManager {
	/**初始化EventBus*/
	private static EventBus eventBus;
	
	static{
		eventBus=new EventBus(MonitorInfo.ZOOKEEPER_EVENT_BUS_ID);
		eventBus.register(new MessageListener());
	}
	
	
	private static void initEventBus(){
		if(eventBus==null){
			eventBus=new EventBus(MonitorInfo.ZOOKEEPER_EVENT_BUS_ID);
			eventBus.register(new MessageListener());
		}
	}
	
	/***
	 * 监控对象
	 * @param obj
	 */
	public static void post(Object obj){
		EventBusManager.initEventBus();
		eventBus.post(obj);
	}
}

	