/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: ResourceServiceImpl.java 9552 2012-12-31 下午9:56:07 WangLijun$
 */
package com.newtouch.lion.service.system.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.lion.common.Assert;
import com.newtouch.lion.common.codelist.CodeListConstant;
import com.newtouch.lion.dao.system.ResourceDao;
import com.newtouch.lion.json.JSONParser;
import com.newtouch.lion.model.system.Resource;
import com.newtouch.lion.service.AbstractService;
import com.newtouch.lion.service.datagrid.DataColumnService;
import com.newtouch.lion.service.system.ResourceService;
import com.newtouch.lion.tree.Tree;

/**
 * <p>
 * Title: 资源管理Service实现类
 * </p>
 * <p>
 * Description: 资源管理Service实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
@Service("resourceService")
public class ResourceServiceImpl extends AbstractService implements
		ResourceService {
 

	@Autowired
	private ResourceDao resourceDao;
	
	@Autowired
	private DataColumnService dataColumnService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.newtouch.lion.service.system.ResourceService#idSaveTsResource
	 * (com.lion.framework.model.system.Resource)
	 */
	@Override
	public void doCreateResource(Resource resource) {
		if(resource.getParentResourceId()!=null&&resource.getType().equals(CodeListConstant.RESTYPE_MODULE_CATEGORY_ITEM)){
			Resource  parentResource=this.doFindById(resource.getParentResourceId());
			parentResource.setIsLeaf(Boolean.FALSE);
			parentResource.setType(CodeListConstant.RESTYPE_MODULE_MENU_CATEGORY);
			resource.setResource(parentResource);
		}
		resourceDao.save(resource);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.newtouch.lion.service.system.ResourceService#doDelete(java.
	 * util.Collection)
	 */
	@Override
	public void doDelete(Collection<Long> resourceIds) {
		for (Long resourceId : resourceIds)
			this.doDelete(resourceId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.newtouch.lion.service.system.ResourceService#doDelete(java.
	 * lang.Long)
	 */
	@Override
	public Resource doDelete(Long resourceId) {
		Resource resource = this.doFindById(resourceId);
		Resource parentResource = resource.getResource();

		if (parentResource != null) {
			boolean empty = parentResource.getResources().size() <= 1;
			parentResource.setIsLeaf(Boolean.valueOf(empty));
			
			if(parentResource.getType().equals(CodeListConstant.RESTYPE_MODULE_MENU_CATEGORY)){
				parentResource.setType(CodeListConstant.RESTYPE_MODULE_CATEGORY_ITEM);
			}
			this.resourceDao.save(parentResource);
		}

		this.resourceDao.remove(resource);
		return resource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.newtouch.lion.service.system.ResourceService#doFindById(java
	 * .lang.Long)
	 */
	@Override
	public Resource doFindById(Long resourceId) {
		return this.resourceDao.findById(resourceId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.newtouch.lion.service.system.ResourceService#doFindAll()
	 */
	@Override
	public List<Resource> doFindAll() {
		String hql = "from Resource";
		return this.resourceDao.query(hql, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.newtouch.lion.service.system.ResourceService#doFindByParentId
	 * (java.lang.Long)
	 */
	@Override
	public List<Resource> doFindByParentId(Long parentResourceId) {
		Assert.notNull(parentResourceId);
		String   hql="from Resource r where r.resource.id=:parentResourceId  order by r.seqNum";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("parentResourceId", parentResourceId);
		return this.resourceDao.query(hql, params);
	}	

	
	
	@Override
	public List<Tree> doFindByResourceId(Long resourceId) {
		Assert.notNull(resourceId);
		String   hql="from Resource r where r.resource.id=:parentResourceId  order by r.seqNum";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("parentResourceId", resourceId);
		List<Resource>  resources=this.resourceDao.query(hql, params);
		List<Tree> list=new ArrayList<Tree>();
			for(Resource resource:resources){
				Tree tree = new Tree();
				tree.setChecked(false);
				if (resource.getResource() != null) {
					tree.setPid(resource.getResource().getId().toString());
				}
				tree.setText(resource.getNameZh());
				//tree.setIconCls(resource.getIcon());
				Map<String, Object> attr = new HashMap<String, Object>();
				attr.put("url",resource.getAttributes().getPath());
				tree.setAttributes(attr);
				list.add(tree);
			}

			return  list;	
		}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.ResourceService#doFindFirstLevel()
	 */
	@Override
	public List<Resource> doFindFirstLevel() {
		
		String hql = "from Resource where  parentResourceId is null order by seqNum asc";

		Map<String, Object> params = new HashMap<String, Object>();

		List<Resource> resources = resourceDao.query(hql, params);

		return resources;
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.ResourceService#doFindByType(java.lang.String[])
	 */
	@Override
	public List<Resource> doFindByType(String[] resTypes) {
		String hql = "from Resource where type in(:resTypes) order by seqNum asc";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("resTypes", resTypes);
		List<Resource> resources = resourceDao.query(hql, params);

		return resources;
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.ResourceService#doGetById(java.lang.Long)
	 */
	@Override
	public Resource doGetById(Long id) {
		return this.resourceDao.getById(id);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.ResourceService#doUpdate(com.lion.framework.model.system.Resource)
	 */
	@Override
	public Resource doUpdate(Resource resource) {
		this.resourceDao.update(resource);
		return resource;
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.ResourceService#doFindAllToTree(java.lang.String)
	 */
	@Override
	public String doFindAllToTree(String tableId) {
		Set<String> properties=this.dataColumnService.doFindColumnsByTableId(tableId);
		List<Resource> resources=this.doFindAll();
		String jsonStr=JSONParser.toJSONDataGridString(resources, properties);
		return jsonStr.replace("parentResourceId", "_parentId");
	}

 
	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.ResourceService#doFindByUserIdAndType(java.lang.Long, java.lang.String[])
	 */
	@Override
	public List<Resource> doFindByUserIdAndType(Long userId,String[] resourceType) {
		Assert.notNull(userId);
	    Assert.notNull(resourceType);
	    String hql = "select distinct r from User user join user.roles role join role.resources r  where user.id=:userId and r.type in (:resourceType) order by r.seqNum asc,r.parentResourceId asc";
	    Map<String,Object> params = new HashMap<String,Object>();
	    params.put("userId", userId);
	    params.put("resourceType", Arrays.asList(resourceType));
		return this.resourceDao.query(hql, params);
	}
	
	 

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.ResourceService#doFindByUserIdAndType(java.lang.Long, java.lang.Long, java.lang.String[])
	 */
	@Override
	public List<Resource> doFindByUserIdAndType(Long userId,
			Long parentResourceId, String[] resourceType) {
		Assert.notNull(userId);
		Assert.notNull(parentResourceId);
	    Assert.notNull(resourceType);
	    String hql = "select distinct r from User user join user.roles role join role.resources r  where (r.id in(select distinct r.parentResourceId from User user join user.roles role join role.resources r  where user.id=:childrenUserId and r.type in (:childrenResourceType)) and r.type in(:parentResourceType)  and r.parentResourceId=:parentResourceId ) or (r.parentResourceId=:parentResourceId and  user.id=:userId and r.type in (:resourceType)) order by r.seqNum asc,r.parentResourceId asc";
	    Map<String,Object> params = new HashMap<String,Object>();
	    params.put("userId", userId);
	    params.put("childrenUserId", userId);
	    params.put("parentResourceType", CodeListConstant.RESTYPE_MODULE_MENU_CATEGORY);	    
	    params.put("childrenResourceType",CodeListConstant.RESTYPE_MODULE_CATEGORY_ITEM);
	    params.put("parentResourceId", parentResourceId);
	    params.put("resourceType", resourceType);
		return this.resourceDao.query(hql, params);
	}

	
	
	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.ResourceService#doFindByUserIdAndType(java.lang.Long, java.lang.Long, java.lang.String[], java.lang.String[])
	 */
	@Override
	public List<Resource> doFindByUserIdAndType(Long userId,
			Long parentResourceId, String[] parentResourceType,
			String[] childrenResourceType) {
		Assert.notNull(userId);
		Assert.notNull(parentResourceId);
		Assert.notNull(parentResourceType);
	    Assert.notNull(childrenResourceType);
	    String hql = "select distinct r from User user join user.roles role join role.resources r  " +
	    		"   where user.id=:userId and r.type in (:childrenResourceType)  and r.parentResourceId in(select distinct r.id from User user join user.roles role join role.resources r  " +
	    		"   where   r.type in (:parentResourceType) and r.parentResourceId=:parentResourceId )   order by r.seqNum asc,r.parentResourceId asc";
	    Map<String,Object> params = new HashMap<String,Object>();
	    params.put("userId", userId);
	    params.put("parentResourceType",parentResourceType);	    
	    params.put("childrenResourceType",childrenResourceType);
	    params.put("parentResourceId", parentResourceId);	 
		return this.resourceDao.query(hql, params);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.ResourceService#doFindByUserIdAndType(java.lang.Long, java.lang.String[], java.lang.String[])
	 */
	@Override
	public List<Resource> doFindByUserIdAndType(Long userId,
			String[] parentResourceType, String[] resourceType) {
		 String hql = "select distinct pr from  Resource pr where pr.type in(:parentResourceType) and  pr.id in (select distinct  r.parentResourceId from User user join user.roles role join role.resources r " +
		 		" where user.id=:userId and r.type in (:resourceType) )";
		 Map<String,Object> params = new HashMap<String,Object>();
		 params.put("userId", userId);
		 params.put("resourceType", resourceType);
		 params.put("parentResourceType", parentResourceType);
	     return this.resourceDao.query(hql, params);
	}
	
	/* (non-Javadoc)
	 * @see ccom.newtouch.lion.service.system.ResourceService#doFindByUserId(java.lang.Long)
	 */
	@Override
	public List<Resource> doFindByUserId(Long userId) {
		Assert.notNull(userId);
	    String hql = "select distinct r from User user join user.roles role join role.resources r  where user.id=:userId";
	    Map<String,Object> params = new HashMap<String,Object>();
	    params.put("userId", userId);
		return this.resourceDao.query(hql, params);
	}
	
}
