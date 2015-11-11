/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: EventListener.java 9552 2015年7月24日 下午5:33:08 WangLijun$
 */
package com.newtouch.lion.guava;

import com.google.common.eventbus.Subscribe;

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
public class EventListener {
	public int lastMessage = 0;

	@Subscribe
	public void listen(TestEvent event) {
		lastMessage = event.getMessage();
		System.out.println("Message:" + lastMessage);
	}

	public int getLastMessage() {
		return lastMessage;
	}
}
