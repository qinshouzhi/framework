/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: DataColumnService.java 9552 2013-3-27 下午6:17:11 WangLijun$
 */
package com.newtouch.lion.service.datagrid;

import java.util.List;
import java.util.Set;

import com.newtouch.lion.model.datagrid.DataColumn;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;

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
public interface DataColumnService {

	public void doCreateDataColumn(DataColumn dataColumn);

	public DataColumn doGetById(Long id);

	public List<DataColumn> doFindByTableId(String tableId);

	public DataColumn doUpdate(DataColumn dataColumn);

	public void doDeleteObj(DataColumn dataColumn);

	public int doDeleteById(Long id);

	public List<DataColumn> doFindByDataGridId(Long dataGridId);

	public Set<String> doFindColumnsByTableId(String tableId);

	public PageResult<DataColumn> doFindByCriteria(QueryCriteria criteria);

	/***
	 * 根据查询条件和表格列表名称并进行分页
	 * 
	 * @param queryCriteria
	 *            查询条件
	 * @param tableId
	 *            表格列表名称
	 * @return String JSON字符串
	 */
	public String doFindByCriteria(QueryCriteria queryCriteria, String tableId);
}
