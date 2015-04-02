
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: ReminderServiceImpl.java 9552 2015年3月31日 下午3:38:32 ZhongMengDie$
*/
package com.newtouch.lion.service.system.impl; 
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.lion.common.Assert;
import com.newtouch.lion.common.sql.HqlUtils;
import com.newtouch.lion.dao.system.ReminderBodyDao;

import com.newtouch.lion.model.system.ReminderBody;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.AbstractService;
import com.newtouch.lion.service.system.ReminderBodyService;


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
 * Company: NewTouch
 * </p>
 * 
 * @author ZhongMengDie
 * @version 1.0
 */
@Service("reminderService")
public class ReminderServiceImpl extends AbstractService implements ReminderBodyService{

	@Autowired
	private ReminderBodyDao reminderDao;

	@Override
	public ReminderBody doFindById(Long id) {
		// TODO Auto-generated method stub
		return this.reminderDao.findById(id);
	}

	//根据ID选择删除
	@Override
	public int doDeleteById(Long id) {
		String hql="delete from ReminderBody i where i.id=:id";
		Map<String,Object> params=new  HashMap<String, Object>();
		params.put("id",id);
		return this.reminderDao.updateHQL(hql, params);
	}
	//删除

	@Override
	public void doDelete(ReminderBody reminderBody) {
		// TODO Auto-generated method stub
		this.reminderDao.remove(reminderBody);
		
	}

	
	@Override
	public void doCreate(ReminderBody reminderBody) {
		Assert.notNull(reminderBody);
		reminderDao.save(reminderBody);
		
	}

	@Override
	public ReminderBody doUpdate(ReminderBody reminderBody) {
		// TODO Auto-generated method stub
		Assert.notNull(reminderBody);
		reminderDao.update(reminderBody);
		return reminderBody;
	}

	@Override
	public ReminderBody doFindTypeByReminderBodyClass(String reminderBodyClass) {
		// TODO Auto-generated method stub
		Assert.notNull(reminderBodyClass);
		String hql = "from ReminderBody where title=:reminderBodyClass";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", reminderBodyClass);
		java.util.List<ReminderBody> remidner = reminderDao.query(hql, params);
		if (remidner != null && remidner.size() > 0) {
			return remidner.get(0);
		}
		return null;

	}

	@Override
	public boolean doIsExistByReminderBodyClass(String title) {
		// TODO Auto-generated method stub
		Assert.notNull(title);
		ReminderBody reminder = this.doFindTypeByReminderBodyClass(title);
		if (reminder != null)
			return true;
		return false;
	}

	@Override
	public PageResult<ReminderBody> doFindByCriteria(QueryCriteria queryCriteria) {
		// TODO Auto-generated method stub
				String queryEntry = "from ReminderBody";
				
				String[] whereBodies = {"title like:title"," reminderType like:reminderType " };
			
				String fromJoinSubClause = "";
				System.out.println("");
				Map<String, Object> params = queryCriteria.getQueryCondition();
				
				String orderField = queryCriteria.getOrderField();
		
				String orderDirection = queryCriteria.getOrderDirection();
			
				String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause, whereBodies, orderField, orderDirection, params);

				int pageSize = queryCriteria.getPageSize();
				
				int startIndex = queryCriteria.getStartIndex();
				
				PageResult<ReminderBody> pageResult = this.reminderDao.query(hql, HqlUtils.generateCountHql(hql, null), params, startIndex, pageSize);
				
				return pageResult;
	}

}

	