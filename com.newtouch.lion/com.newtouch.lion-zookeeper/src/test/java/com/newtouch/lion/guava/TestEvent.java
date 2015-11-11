/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: TestEvent.java 9552 2015年7月24日 下午5:31:17 WangLijun$
*/
package com.newtouch.lion.guava; 
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
public class TestEvent {
	private  int message;
	
	public TestEvent(int message) {
		this.message=message;
	}

	/**
	 * @return the message
	 */
	public int getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(int message) {
		this.message = message;
	}
	
}

	