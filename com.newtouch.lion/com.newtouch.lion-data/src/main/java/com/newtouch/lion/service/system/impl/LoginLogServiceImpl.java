/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: LoginLogServiceImpl.java 9552 2013-1-12 下午8:37:13 WangLijun$
 */
package com.newtouch.lion.service.system.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.lion.common.sql.HqlUtils;
import com.newtouch.lion.dao.system.LoginLogDao;
import com.newtouch.lion.model.system.LoginLog;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.AbstractService;
import com.newtouch.lion.service.system.LoginLogService;

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
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
@Service("loginLogService")
public class LoginLogServiceImpl extends AbstractService implements
		LoginLogService {
	@Autowired
	private LoginLogDao loginLogDao;
	@Override
	public PageResult<LoginLog> doFindByCriteria(QueryCriteria queryCriteria) {
		// TODO Auto-generated method stub
		String queryEntry = "select  loginlog";

		String[] whereBodies = { " loginlog.userId=:userId "};

		String fromJoinSubClause = "from LoginLog loginlog";

		Map<String, Object> params = queryCriteria.getQueryCondition();

		String orderField = queryCriteria.getOrderField();

		String orderDirection = queryCriteria.getOrderDirection();

		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause,
				whereBodies, orderField, orderDirection, params);

		int pageSize = queryCriteria.getPageSize();

		int startIndex = queryCriteria.getStartIndex();

		PageResult<LoginLog> result = this.loginLogDao.query(hql,
				HqlUtils.generateCountHql(hql, null), params, startIndex,
				pageSize);

		return result;
	}

}
