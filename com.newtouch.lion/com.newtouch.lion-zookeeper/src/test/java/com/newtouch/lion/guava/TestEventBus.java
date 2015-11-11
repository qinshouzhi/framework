/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: TestEventBus.java 9552 2015年7月24日 下午5:33:48 WangLijun$
 */
package com.newtouch.lion.guava;

import org.junit.Test;

import com.google.common.eventbus.EventBus;

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
public class TestEventBus {
	@Test
	public void testReceiveEvent() throws Exception {

		EventBus eventBus = new EventBus("test");
		EventListener listener = new EventListener();
		eventBus.register(listener);

		eventBus.post(new TestEvent(200));
		eventBus.post(new TestEvent(300));
		eventBus.post(new TestEvent(400));

		System.out.println("LastMessage:" + listener.getLastMessage());
	}
}
