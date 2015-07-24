/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ZookeeperCommand.java 9552 2015年7月24日 下午11:09:53 WangLijun$
*/
package com.newtouch.lion.zookeeper.command; 

import java.text.MessageFormat;

/**
 * <p>
 * Title: Zookeeper的命令
 * </p>
 * <p>
 * Description: Zookeeper的命令
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
public class ZookeeperCommand {
	
	public final static String COMMAND_CONS = "echo cons | nc {0} {1}";
	
	public final static String COMMAND_STAT = "echo stat | nc {0} {1}";
	
	public final static String COMMAND_WCHS = "echo wchs | nc {0} {1}";
	
	public final static String COMMAND_WCHC = "echo wchc | nc {0} {1}";
	
	
	public  static String format(String command,Object ... params){
		 return MessageFormat.format(command, params);
	}
}

	