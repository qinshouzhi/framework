/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: MessageSendListener.java 9552 2015年7月24日 下午5:44:48 WangLijun$
*/
package com.newtouch.lion.message.sender; 

import com.google.common.eventbus.Subscribe;
import com.newtouch.lion.message.model.Message;
import com.newtouch.lion.message.sender.impl.MessageSenderImpl;
import com.newtouch.lion.monitor.ExecutorServiceManager;

/**
 * <p>
 * Title: 消息发送监听器
 * </p>
 * <p>
 * Description: 消息发送监听器
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
public class MessageListener {
	
	private Message message;
	
	@Subscribe
	public void sendListener(Message message){
		this.message=message;
		MessageSender sender=new MessageSenderImpl();
		ExecutorServiceManager.addJobMessageSendExecutor(sender);
	}

	/**
	 * @return the message
	 */
	public Message getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(Message message) {
		this.message = message;
	}
}

	