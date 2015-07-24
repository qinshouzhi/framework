/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: MessageSenderImpl.java 9552 2015年7月24日 下午4:40:39 WangLijun$
*/
package com.newtouch.lion.message.sender.impl; 

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newtouch.lion.message.model.Message;
import com.newtouch.lion.message.model.Result;
import com.newtouch.lion.message.sender.MessageSender;

/**
 * <p>
 * Title: 消息实现
 * </p>
 * <p>
 * Description: 消息实现
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
public class MessageSenderImpl  implements MessageSender {
	/**消息*/
	private static final Logger logger = LoggerFactory.getLogger( MessageSenderImpl.class );
	/**消息列表*/
	private Message[] messages;
	
	
	
	/**
	 * @param messages
	 */
	public MessageSenderImpl(Message ... messages) {
		this.messages = messages;
	}

	@Override
	public void run() {
		this.sendMessages();
		logger.info("send message ....");
	}

	public void sendMessages(){
		List<Message> lists=Arrays.asList(this.messages);
		if(org.springframework.util.CollectionUtils.isEmpty(lists)){
			return;
		}
		//发送消息
		for(Message message:lists){
			this.send(message.getTargetAddresses(), message.getSubject(), message.getContent(),String.valueOf(message.getType()));
		}
	}
	
	public Result send(String targetAddresses, String subject, String content, String channel){
		if(StringUtils.isEmpty(targetAddresses)||StringUtils.isEmpty(channel)){
			return null;
		}
		return null;
		//将消息发送Redis 队列中,由最终呈端展示信息
		
		//将消息发送直接发送给用户;
	}

}

	