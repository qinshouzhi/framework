/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TsParameterServiceImpl.java 9552 2012-7-8 下午09:26:37 WangLijun$
 */
package com.newtouch.lion.service.system.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.lion.common.Assert;
import com.newtouch.lion.common.bean.BeanUtils;
import com.newtouch.lion.common.sql.HqlUtils;
import com.newtouch.lion.dao.system.ParameterDao;
import com.newtouch.lion.model.system.CodeList;
import com.newtouch.lion.model.system.Parameter;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.AbstractService;
import com.newtouch.lion.service.system.CodeListService;
import com.newtouch.lion.service.system.ParameterService;

/**
 * <p>
 * Title:系统参数Service层实现
 * </p>
 * <p>
 * Description:系统参数Service层实现
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: lion
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
@Service("parameterService")
public class ParameterServiceImpl extends AbstractService implements
		ParameterService {
	@Autowired
	private ParameterDao parameterDao;
	@Autowired
	private CodeListService codeListService;

	/*
	 * <p>Title: doFindById</p> <p>Description: </p>
	 * 
	 * @param id
	 * 
	 * @return
	 * 
	 * @see
	 * com.lion.framework.service.system.TsParameterService#doFindById(long)
	 */

	public Parameter doFindById(long id) {
		Assert.notNull(id);
		return parameterDao.findById(id);
	}

	/*
	 * <p>Title: idoCreate</p> <p>Description: </p>
	 * 
	 * @param tsParameter
	 * 
	 * @see com.lion.framework.service.system.TsParameterService#idoCreate(com
	 * .lion.framework.model.system.)
	 */

	public void doCreate(Parameter parameter) {
		Assert.notNull(parameter);
		parameterDao.save(parameter);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.ParameterService#doIsExistByNameEn(
	 * java.lang.String)
	 */
	@Override
	public boolean doIsExistByNameEn(String nameEn) {
		Assert.notNull(nameEn);
		Parameter parameter = this.doFindTypeByNameEn(nameEn);
		if (parameter != null)
			return true;
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.service.system.TsParameterService#doFindType(java
	 * .lang.String, java.lang.String)
	 */
	@Override
	public Parameter doFindTypeByNameEn(String nameEn) {
		Assert.notNull(nameEn);
		String hql = "from Parameter  where nameEn=:nameEn";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nameEn", nameEn);
		java.util.List<Parameter> parameters = parameterDao.query(hql, params);
		if (parameters != null && parameters.size() > 0) {
			return parameters.get(0);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.service.system.ParameterService#doDelele(com.lion
	 * .framework.model.system.Parameter)
	 */
	@Override
	public void doDelete(Parameter parameter) {
		this.parameterDao.remove(parameter);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.ParameterService#doUpdate(com.lion.
	 * framework.model.system.Parameter)
	 */
	@Override
	public Parameter doUpdate(Parameter parameter) {
		this.parameterDao.update(parameter);
		return parameter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.ParameterService#doDeleteById(java.
	 * lang.Long)
	 */
	@Override
	public int doDeleteById(Long id) {
		String hql = "delete from Parameter p where p.id=:id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return this.parameterDao.updateHQL(hql, params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.service.system.ParameterService#doFindAll()
	 */
	@Override
	public List<Parameter> doFindAll() {
		return this.parameterDao.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.service.system.ParameterService#doFindByCriteria
	 * (com.lion.framework.common.QueryCriteria)
	 */
	@Override
	public PageResult<Parameter> doFindByCriteria(QueryCriteria queryCriteria) {

		String queryEntry = "select parameter ";

		String[] whereBodies = { " parameter.type=:type " };

		String fromJoinSubClause = "from Parameter parameter";

		Map<String, Object> params = queryCriteria.getQueryCondition();

		String orderField = queryCriteria.getOrderField();

		String orderDirection = queryCriteria.getOrderDirection();

		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause,
				whereBodies, orderField, orderDirection, params);

		int pageSize = queryCriteria.getPageSize();

		int startIndex = queryCriteria.getStartIndex();

		PageResult<Parameter> result = this.parameterDao.query(hql,
				HqlUtils.generateCountHql(hql, null), params, startIndex,
				pageSize);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.ParameterService#doUpdate(java.lang
	 * .Long, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void doUpdate(Long id, String type, String nameEn, String nameZh,
			String value, String description, Boolean editable) {
		Parameter parameter = this.doFindById(id);
		parameter.setType(type);
		parameter.setNameEn(nameEn);
		parameter.setNameZh(nameZh);
		parameter.setValue(value);
		parameter.setDescription(description);
		parameter.setEditable(editable);
		this.parameterDao.save(parameter);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.ParameterService#doExportExcel(com.
	 * lion.framework.common.QueryCriteria)
	 */
	public void doExportExcel(QueryCriteria queryCriteria, String dataGridId) {
		// TODO Auto-generated method stub

		// 数据查询
		String queryEntry = "select parameter ";

		String[] whereBodies = { " parameter.type=:type " };

		String fromJoinSubClause = "from Parameter parameter";

		Map<String, Object> params = queryCriteria.getQueryCondition();

		String orderField = queryCriteria.getOrderField();

		String orderDirection = queryCriteria.getOrderDirection();

		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause,
				whereBodies, orderField, orderDirection, params);

		int pageSize = queryCriteria.getPageSize();

		int startIndex = queryCriteria.getStartIndex();

		PageResult<Parameter> result = this.parameterDao.query(hql,HqlUtils.generateCountHql(hql, null), params, startIndex,pageSize);

	}

	/***
	 * 解析为List对象
	 * 
	 * @param result
	 *            查询结果
	 * @return
	 */
	private List<Map<String, Object>> parserData(PageResult<Parameter> result) {
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

		for (Parameter parameter : result.getContent()) {
			data.add(BeanUtils.convertBeanToMap(parameter));
		}
		return data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.ParameterService#doFindCodeListByNameEn
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public List<CodeList> doFindCodeListByNameEn(String codeTypeNameEn,
			String selectedValue) {
		List<CodeList> codeLists = codeListService.doFindCodeListByCodeTypeNameEn(codeTypeNameEn);
		List<CodeList> returnCodeLists = null;
		if (StringUtils.isNotEmpty(selectedValue)) {
			returnCodeLists = new ArrayList<CodeList>();
			for (CodeList codeList : codeLists) {
				if (codeList.getNameEn().equals(selectedValue)) {
					codeList.setSelected(Boolean.TRUE);
				} else {
					codeList.setSelected(Boolean.FALSE);
				}
				returnCodeLists.add(codeList);
			}
		} else {
			returnCodeLists = codeLists;
		}
		return returnCodeLists;
	}

}
