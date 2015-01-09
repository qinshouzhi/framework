/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: DataGridService.java 9552 2013-3-27 下午6:16:54 WangLijun$
 */
package com.newtouch.lion.service.datagrid;

import java.util.List;

import com.newtouch.lion.model.datagrid.DataGrid;
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
public interface DataGridService {

	public void doCreateDataGrid(DataGrid dataGrid);

	public DataGrid doFindByTableId(String tableId);

	public List<DataGrid> doFindAll();

	public List<DataGrid> doFindByType(String type);

	public DataGrid doGetById(Long id);

	public DataGrid doUpdate(DataGrid dataGrid);

	public void doDelete(DataGrid dataGrid);

	public int doDeleteById(Long id);

	public PageResult<DataGrid> doFindByCriteria(QueryCriteria queryCriteria);

	public String doFindByCriteria(QueryCriteria queryCriteria, String tableId);

	public String doFindAllForCombox();

	public String doFindComboxByType(String type);
}
