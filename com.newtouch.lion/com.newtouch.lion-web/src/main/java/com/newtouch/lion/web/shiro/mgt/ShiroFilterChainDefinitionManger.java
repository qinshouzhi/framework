/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: ShiroFilterChainDefinitionManger.java 9552 2015年3月4日 上午1:04:20 WangLijun$
 */
package com.newtouch.lion.web.shiro.mgt;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.newtouch.lion.web.shiro.model.AuthorityModel;
import com.newtouch.lion.web.shiro.service.ShiroResouceService;

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
public class ShiroFilterChainDefinitionManger implements FactoryBean<Section> {
	
	/**日志*/
	private static final Logger logger=LoggerFactory.getLogger(ShiroFilterChainDefinitionManger.class);
	/***
	 * 资源权限读取
	 */
	@Autowired
	private ShiroResouceService shiroResouceService;

	// shiro默认的链接定义
	private String filterChainDefinitions;

	/**
	 * 通过filterChainDefinitions对默认的链接过滤定义
	 * 
	 * @param filterChainDefinitions
	 *            默认的接过滤定义
	 */
	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}

	@Override
	public Section getObject() throws Exception {
		Ini ini = new Ini();
		// 加载默认的url
		ini.load(filterChainDefinitions);
		Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
		if (CollectionUtils.isEmpty(section)) {
			section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		}
		 List<AuthorityModel> resources =shiroResouceService.doFindAll();
		// 循环数据库资源的url
		for (AuthorityModel authorityModel:resources) {
			if(StringUtils.isNotEmpty(authorityModel.getPermissions())){
				section.put(authorityModel.getUrl(),"authc,perms[\""+authorityModel.getPermissions()+"\"]");
				logger.info("url{} = {}",authorityModel.getUrl(),"authc,perms[\""+authorityModel.getPermissions()+"\"]");
			}
			
		}
		return section;
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return Section.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
	
	public static void main(String[] args) {
		Set<String> perms=new HashSet<String>();
	    System.out.println(perms.toString());
	}

}
