/*
 * Copyright (c)  2013, lion
 * All rights reserved. 
 *
 * $id: ResourceManagerSample.java 9552 2013-4-7 上午11:57:04 WangLijun$
 */
package com.newtouch.lion.common.resource.sample;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.newtouch.lion.common.CommonAllTest;
import com.newtouch.lion.common.resource.MessageResource;

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
 
public class ResourceManagerSampleTest extends CommonAllTest{
 
	
	@Autowired
	private MessageResource frameworkMessages = null;
	@Test
	public void doMessageResouceSample() {
		String param="error";
		String message = this.frameworkMessages.getMessage("SI0001");
		logger.info("message:"+message);
		String messageWithParam = this.frameworkMessages.getMessage("SI0001",
				new Object[] { param });
		logger.info("message:"+messageWithParam);
	}
}
