/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: MessageTemplet.java 9552 2015年7月24日 下午8:46:13 WangLijun$
*/
package com.newtouch.lion.message.sender; 

import java.text.MessageFormat;

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
public class MessageTempletManager {
	 
	/**检查Zookeeper 是否存活的标题模板*/
	public static final String ZK_CHECK_NO_AVLIE_TITLE="ZooKeeper所在机器存活性检测失败 {0} ";
	/**检查Zookeeper 是否存活的内容模板*/
	public static final String ZK_CHECK_NO_AVLIE_CONTENT="Zk node: {0} 存活性检测失败";
	 
	/**检查Zookeeper 是否存活的标题
	 *@param params
	 * */
	public static final String zookeeperFailTitle(Object ... params){
		return MessageFormat.format(ZK_CHECK_NO_AVLIE_TITLE,params);
	}
	/**检查Zookeeper 是否存活的内容
	 * @param params
	 * */
	public static final String zookeeperFailContent(Object params){
		return MessageFormat.format(ZK_CHECK_NO_AVLIE_CONTENT,params);
	}
	/***
	 *格式化字符串模板信息
	 * @param templetName
	 * @param params
	 * @return
	 */
	public static final String format(String templetName,Object params){
		return MessageFormat.format(templetName,params);
	}
	
	
	public static void main(String[] args) {
		String content=MessageTempletManager.zookeeperFailContent("Zookeeper");
		System.out.println(content);
		
		String title=MessageTempletManager.zookeeperFailTitle("Zookeeper Cluster");
		System.out.println(title);
	}
}

	