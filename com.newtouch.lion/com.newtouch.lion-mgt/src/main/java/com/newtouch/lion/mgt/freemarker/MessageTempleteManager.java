/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: MessageTempleteManager.java 9552 2015年3月11日 上午10:23:58 WangLijun$
 */
package com.newtouch.lion.mgt.freemarker;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.lang.StringUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

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
public class MessageTempleteManager {

	public static final String POM_TEMPLETE_NAME = "/resources/pom_template.xml";

	private static MessageTempleteManager tplm = null;

	private Configuration cfg = null;

	private MessageTempleteManager() {
		cfg = new Configuration();
		try {

			cfg.setClassForTemplateLoading(this.getClass(), "/");
		} catch (Exception e) {

		}
	}

	private static Template getTemplate(String name) throws IOException {
		if (tplm == null) {
			tplm = new MessageTempleteManager();
		}
		if (StringUtils.isEmpty(name)) {
			name = POM_TEMPLETE_NAME;
		}
		return tplm.cfg.getTemplate(name);
	}

	public static String process(String templateName, Object dataModel)
			throws IOException, TemplateException {
		Template template = MessageTempleteManager.getTemplate(templateName);
		StringWriter sw =new StringWriter();
		template.process(dataModel, sw);
		return sw.toString();
	}
}
