/*
 * Copyright (c)  2013, lion
 * All rights reserved. 
 *
 * $id: ResourceManagerSample.java 9552 2013-4-7 上午11:57:04 WangLijun$
 */
package com.newtouch.lion.common.resource.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service("resourceManagerSample")
public class ResourceManagerSample{
	
   private final Logger  log=LoggerFactory.getLogger(super.getClass());
	
	@Autowired
	private MessageResource frameworkMessages = null;

	public void doMessageResouceSample(String param) {
		String message = this.frameworkMessages.getMessage("SI0001");
		log.info("message:"+message);
		String messageWithParam = this.frameworkMessages.getMessage("SI0001",
				new Object[] { param });
		log.info("message:"+messageWithParam);
	}
}
