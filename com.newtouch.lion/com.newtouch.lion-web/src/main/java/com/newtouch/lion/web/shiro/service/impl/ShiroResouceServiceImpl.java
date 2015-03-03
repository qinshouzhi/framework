/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: ShiroResouceServiceImpl.java 9552 2015年3月3日 下午6:21:42 WangLijun$
 */
package com.newtouch.lion.web.shiro.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.newtouch.lion.model.system.Resource;
import com.newtouch.lion.model.system.Role;
import com.newtouch.lion.service.AbstractService;
import com.newtouch.lion.service.system.ResourceService;
import com.newtouch.lion.web.shiro.mgt.ShiroFilterChainManager;
import com.newtouch.lion.web.shiro.model.AuthorityModel;
import com.newtouch.lion.web.shiro.service.ShiroResouceService;

/**
 * <p>
 * Title: Shiro角色权限资源管理
 * </p>
 * <p>
 * Description: Shiro角色权限资源管理
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
public class ShiroResouceServiceImpl extends AbstractService implements
		ShiroResouceService {
	/** 资源读取类 */
	@Autowired
	private ResourceService resourceService;

	@Autowired
	private ShiroFilterChainManager shiroFilerChainManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.newtouch.lion.web.shiro.service.ShiroResouceService#findAll()
	 */
	@Override
	public List<AuthorityModel> findAll() {
		List<Resource> resources = resourceService.doFindAll();
		List<AuthorityModel> authorityModels = new ArrayList<AuthorityModel>();
		for (Resource resource : resources) {
			AuthorityModel authorityModel = new AuthorityModel();
			authorityModel.setId(resource.getId());
			authorityModel.setUrl(resource.getPath());
			// 添加角色
			for (Role role : resource.getRoles()) {
				authorityModel.addRole(role.getNameEn());
			}
			// 添加权限模型

			authorityModels.add(authorityModel);
		}
		return authorityModels;
	}

	@PostConstruct
	public void initFilterChain() {
		shiroFilerChainManager.initFilterChains(findAll());
	}

}
