/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: CodeListServiceImpl.java 9552 2013-1-12 下午8:35:09 WangLijun$
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
import com.newtouch.lion.common.sql.HqlUtils;
import com.newtouch.lion.dao.system.CodeListDao;
import com.newtouch.lion.model.system.CodeList;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.AbstractService;
import com.newtouch.lion.service.system.CodeListService;

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
@Service("codeListService")
public class CodeListServiceImpl extends AbstractService implements
		CodeListService {
 

	@Autowired
	private CodeListDao codeListDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.CodeListService#doCreateObj(com.
	 * lion.framework.model.system.CodeList)
	 */
	@Override
	public void doCreateObj(CodeList obj) {
		this.codeListDao.save(obj);
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.lion.framework.service.system.CodeListService#doFindById(java.lang.Long)
	 */
	@Override
	public CodeList doFindById(Long id) {
		return this.codeListDao.findById(id);
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.CodeListService#doDeleteById(java
	 * .lang.Long)
	 */
	@Override
	public int doDeleteById(Long id) {
		String hql="delete from CodeList c where c.id=:id";
		Map<String,Object> params=new  HashMap<String, Object>();
		params.put("id",id);
		return this.codeListDao.updateHQL(hql, params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.CodeListService#doDeleteByObj(com
	 * .lion.framework.model.system.CodeList)
	 */
	@Override
	public void doDeleteByObj(CodeList obj) {
		this.codeListDao.remove(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.CodeListService#doUpdateByParams
	 * (java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, int,
	 * java.lang.Boolean)
	 */
	@Override
	public void doUpdateByParams(Long id, Long codeTypeId,String codeValue,String nameEn,
			String nameZh, int sortNo, Boolean editable) {
		//TODO
	}
	
	
	

	/* (non-Javadoc)
	 * @see com.lion.framework.service.system.CodeListService#doUpdateObj(com.lion.framework.model.system.CodeList)
	 */
	@Override
	public CodeList doUpdateObj(CodeList obj) {
		CodeList codeList=this.codeListDao.getById(obj.getId());
		codeList.setCodeType(obj.getCodeType());
		codeList.setCodeValue(obj.getCodeValue());
		codeList.setNameEn(obj.getNameEn());
		codeList.setNameZh(obj.getNameZh());
		codeList.setSortNo(obj.getSortNo());
		codeList.setEditable(obj.getEditable());
		codeList.setSelected(obj.getSelected());
		this.codeListDao.save(codeList);
		return codeList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.CodeListService#doFindByCriteria
	 * (com.lion.framework.common.QueryCriteria)
	 */
	@Override
	public PageResult<CodeList> doFindByCriteria(QueryCriteria criteria) {
		String queryEntry = " select cl from CodeList as cl left join cl.codeType as ct ";

		String[] whereBodies = {"ct.id =:codeTypeId"};

		String fromJoinSubClause = "";

		Map<String, Object> params = criteria.getQueryCondition();

		String orderField ="cl."+criteria.getOrderField();

		String orderDirection = criteria.getOrderDirection();

		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause,whereBodies, orderField, orderDirection, params);

		int pageSize = criteria.getPageSize();

		int startIndex = criteria.getStartIndex();

		PageResult<CodeList> pageResult = this.codeListDao.query(hql,HqlUtils.generateCountHql(hql, null), params, startIndex,pageSize);
		
		return pageResult;
	}

	/* (non-Javadoc)
	 * @see com.lion.framework.service.system.CodeListService#doFindCodeListByCodeTypeNameEn(java.lang.String)
	 */
	@Override
	public List<CodeList> doFindCodeListByCodeTypeNameEn(String codeTypeNameEn) {
		
		String hql="select cl from CodeList as cl left join cl.codeType as ct where ct.nameEn=:nameEn order by cl.sortNo ASC";
		Map<String,Object>  params=new HashMap<String, Object>();
		params.put("nameEn",codeTypeNameEn);
		return codeListDao.query(hql, params);
	}

	/* (non-Javadoc)
	 * @see com.lion.framework.service.system.CodeListService#doFindCodeListByNameEn(java.lang.String, java.lang.String)
	 */
	@Override
	public List<CodeList> doFindCodeListByNameEn(String codeTypeNameEn,
			String selectedValue) {
		List<CodeList> codeLists=this.doFindCodeListByCodeTypeNameEn(codeTypeNameEn);
		List<CodeList> returnCodeLists=null;
		if(StringUtils.isNotEmpty(selectedValue)){
			returnCodeLists=new ArrayList<CodeList>();
			for(CodeList codeList:codeLists){
				if(codeList.getNameEn().equals(selectedValue)){
					codeList.setSelected(Boolean.TRUE);
				}else{
					codeList.setSelected(Boolean.FALSE);
				}
				returnCodeLists.add(codeList);
			}
		}else{
			returnCodeLists=codeLists;
		}
		return returnCodeLists;
	}
	
	public CodeList doFindCodeListByNameEn(String nameEn) {
		Assert.notNull(nameEn);
		String hql = "from CodeList  where nameEn=:nameEn";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nameEn",nameEn);
		java.util.List<CodeList> codeList = codeListDao
				.query(hql, params);
		if (codeList != null&&codeList.size()>0) {
			return codeList.get(0);
		}
		return null;
	}


	@Override
	public boolean doIsExistByNameEn(String nameEn) {
		Assert.notNull(nameEn);
			CodeList  codelist=this.doFindCodeListByNameEn(nameEn);
		if(codelist!=null)
			return true;
		return false;
	}
	
	
}
