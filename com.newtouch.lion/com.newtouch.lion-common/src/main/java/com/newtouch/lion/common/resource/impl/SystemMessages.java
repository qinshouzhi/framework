/*
 * Copyright (c)  2013, lion
 * All rights reserved. 
 *
 * $id: SystemMessages.java 9552 2013-4-7 上午11:32:39 WangLijun$
 */
package com.newtouch.lion.common.resource.impl;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Repository;

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
@Repository("frameworkMessages")
public class SystemMessages extends ResourceBundleMessageSource implements
		MessageResource {

	private static final String BUNDLE_NAME = MessageResource.class.getPackage().getName() + ".messages";

	private static MessageSourceAccessor accessor = null;

	public SystemMessages() {
		this(BUNDLE_NAME);
	}

	public SystemMessages(String bundleName) {
		this.setBasename(bundleName);
		this.setUseCodeAsDefaultMessage(true);
	}

	private MessageSourceAccessor getAccessor() {
		if (accessor == null) {
			accessor = new MessageSourceAccessor(this);
		}
		return accessor;
	}

	public String getMessage(String code) {
		return getAccessor().getMessage(code);
	}

	public String getMessage(String code, Object[] args) {
		return getAccessor().getMessage(code, args);
	}
}
