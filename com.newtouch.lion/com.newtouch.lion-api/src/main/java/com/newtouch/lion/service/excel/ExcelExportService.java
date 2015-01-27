/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ExcelService.java 9552 2015年1月26日 下午4:32:27 WangLijun$
*/
package com.newtouch.lion.service.excel; 

import java.io.OutputStream;
import java.util.Collection;
import java.util.Map;

import com.newtouch.lion.model.datagrid.DataGrid;

/**
 * <p>
 * Title: Excel导出功能接口定义
 * </p>
 * <p>
 * Description: Excel导出功能接口定义
 * <br/>根据DataGrid导出数据
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
public interface ExcelExportService<T> {
	
	/****
	 * 根据dataGrid,数据集，输出流，IM转换、数据格式导出Excel
	 * @param dataGrid
	 * @param dataset
	 * @param out
	 * @param codeTypes
	 * @param dataFormats
	 */
	public void export(DataGrid dataGrid, Collection<T> data,OutputStream out, Map<String, Map<Object, Object>> codeTypes,Map<String, String> dataFormats);
}