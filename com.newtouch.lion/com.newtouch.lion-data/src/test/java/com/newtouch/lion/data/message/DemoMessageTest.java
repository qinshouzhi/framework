/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: DemoMessageTest.java 9552 2015年1月25日 下午1:02:50 WangLijun$
*/
package com.newtouch.lion.data.message; 

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.newtouch.lion.common.resource.MessageResource;
import com.newtouch.lion.data.AllTest;

/**
 * <p>
 * Title: 消息体系测试类
 * </p>
 * <p>
 * Description: 消息体系测试类
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
public class DemoMessageTest extends AllTest {
	@Autowired
	private MessageResource resource = null;
	@Test
	public void doMessageResouceSample() {
		String message = this.resource.getMessage("SE0001");
		logger.info("message:"+message);
		
		String messageWithParam = this.resource.getMessage("SE0001",
				new Object[] {"1111","2222"});
		logger.info("message:"+messageWithParam);
	}
}

	