/*
 * Copyright (c)  2013, lion
 * All rights reserved. 
 *
 * $id: MessageResource.java 9552 2013-4-7 上午11:28:50 WangLijun$
 */
package com.newtouch.lion.common.resource;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: lion
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public interface MessageResource {
	
	public  String getMessage(String param);
	
	public  String getMessage(String param, Object[] objs);
}
