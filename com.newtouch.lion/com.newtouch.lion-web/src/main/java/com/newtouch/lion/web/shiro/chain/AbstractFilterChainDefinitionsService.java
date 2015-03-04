/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: AbstractFilterChainDefinitionsService.java 9552 2015年3月4日 下午3:23:10 WangLijun$
*/
package com.newtouch.lion.web.shiro.chain; 

import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

 

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
public class AbstractFilterChainDefinitionsService implements FilterChainDefinitionsService {
	/***
	 * 日志
	 */
	private static final Logger logger=LoggerFactory.getLogger(AbstractFilterChainDefinitionsService.class);
	
	/** shiro默认的链接定义***/
	private String filterChainDefinitions;
	
	//@Autowired  
	private ShiroFilterFactoryBean shiroFilterFactoryBean;
	 
	 

	/* (non-Javadoc)
	 * @see com.newtouch.lion.web.shiro.chain.FilterChainDefinitionsService#intiPermission()
	 */
	 //@PostConstruct  
	@Override
	public void intiPermission() {
		 //shiroFilterFactoryBean.setFilterChainDefinitionMap(obtainPermission());  
	    // Logger("initialize shiro permission success...");  
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.web.shiro.chain.FilterChainDefinitionsService#updatePermission()
	 */
	@Override
	public void updatePermission() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.web.shiro.chain.FilterChainDefinitionsService#initOtherPermission()
	 */
	@Override
	public Map<String, String> initOtherPermission() {
		// TODO Auto-generated method stub
		return null;
	}

}

	