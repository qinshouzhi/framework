
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: IconServiceImpl.java 9552 Mar 4, 2015 11:47:35 AM MaoJiaWei$
*/
package com.newtouch.lion.service.system.impl; 

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.lion.common.Assert;
import com.newtouch.lion.common.sql.HqlUtils;
import com.newtouch.lion.dao.system.IconDao;
import com.newtouch.lion.model.system.Group;
import com.newtouch.lion.model.system.Icon;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.AbstractService;
import com.newtouch.lion.service.system.IconService;

/**
 * <p>
 * Title: 图标Icon的Service实现层
 * </p>
 * <p>
 * Description: 图标Icon的Service实现层
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: NewTouch
 * </p>
 * 
 * @author MaoJiaWei
 * @version 1.0
 */
@Service("iconService")
public class IconServiceImpl extends AbstractService implements IconService {
	
	@Autowired
	private IconDao iconDao;
	
	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.IconService#doFindById(long)
	 */
	@Override
	public Icon doFindById(long id) {
		// TODO Auto-generated method stub
		return this.iconDao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.IconService#doDeleteById(java.lang.Long)
	 */
	@Override
	public int doDeleteById(Long id) {
		// TODO Auto-generated method stub
		String hql="delete from Icon i where i.id=:id";
		Map<String,Object> params=new  HashMap<String, Object>();
		params.put("id",id);
		return this.iconDao.updateHQL(hql, params);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.IconService#doDelete(com.newtouch.lion.model.system.Icon)
	 */
	@Override
	public void doDelete(Icon icon) {
		// TODO Auto-generated method stub
		this.iconDao.remove(icon);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.IconService#doFindByCriteria(com.newtouch.lion.query.QueryCriteria)
	 */
	@Override
	public PageResult<Icon> doFindByCriteria(QueryCriteria queryCriteria) {
		// TODO Auto-generated method stub
		String queryEntry = " from Icon";

		String[] whereBodies = { "iconClass like :iconClass","iconType =:iconType"};

		String fromJoinSubClause = "";
		
		Map<String, Object> params = queryCriteria.getQueryCondition();
		
		String orderField = queryCriteria.getOrderField();
		
		String orderDirection = queryCriteria.getOrderDirection();
		
		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause, whereBodies, orderField, orderDirection, params);
		
		int pageSize = queryCriteria.getPageSize();
		
		int startIndex = queryCriteria.getStartIndex();

		PageResult<Icon> pageResult = this.iconDao.query(hql, HqlUtils.generateCountHql(hql, null), params, startIndex, pageSize);
		return pageResult;
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.IconService#doCreate(com.newtouch.lion.model.system.Icon)
	 */
	@Override
	public void doCreate(Icon icon) {
		// TODO Auto-generated method stub
		Assert.notNull(icon);
		iconDao.save(icon);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.IconService#doUpdate(com.newtouch.lion.model.system.Icon)
	 */
	@Override
	public Icon doUpdate(Icon icon) {
		// TODO Auto-generated method stub
		Assert.notNull(icon);
		iconDao.update(icon);
		return icon;
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.IconService#doFindTypeByNameEn(java.lang.String)
	 */
	@Override
	public Icon doFindTypeByNameEn(String nameEn) {
		// TODO Auto-generated method stub
		return null;
	}

}

	