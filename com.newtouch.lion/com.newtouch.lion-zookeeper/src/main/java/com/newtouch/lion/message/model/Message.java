/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: Message.java 9552 2015年7月24日 下午4:37:04 WangLijun$
 */
package com.newtouch.lion.message.model;

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
public class Message {

	/**
	 * 邮件
	 */
	public static final String MAIL_TYPE = "1";

	/**
	 * 短信
	 */
	public static final String SMS_TYPE = "2";

	/**
	 * 站内信
	 */
	public static final String SITE_TYPE = "3";

	/**
	 * 消息类型
	 */
	public enum MessageType {
		SMS(SMS_TYPE), EMAIL(MAIL_TYPE),SITE(SITE_TYPE);

		private String type;

		private MessageType(String type) {
			this.type = type;
		}

		public String toString() {
			return this.type;
		}
	}

	private String targetAddresses;
	private String subject;
	private String content;
	private Message.MessageType type;

	public Message(String targetAddresses, String subject, String content,
			Message.MessageType type) {
		this.targetAddresses = targetAddresses;
		this.subject = subject;
		this.content = content;
		this.type = type;
	}

	@Override
	public String toString() {
		return "[targetAddresses: " + this.targetAddresses + ", subject: "
				+ this.subject + ", content: " + this.content + ", type: "
				+ this.type + "]";
	}

	public String getTargetAddresses() {
		return targetAddresses;
	}

	public void setTargetAddresses(String targetAddresses) {
		this.targetAddresses = targetAddresses;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Message.MessageType getType() {
		return type;
	}

	public void setType(Message.MessageType type) {
		this.type = type;
	}

}
