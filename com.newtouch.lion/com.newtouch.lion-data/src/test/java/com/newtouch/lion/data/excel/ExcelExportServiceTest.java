/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ExcelExportServiceTest.java 9552 2015年1月27日 下午9:26:41 WangLijun$
*/
package com.newtouch.lion.data.excel; 

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.newtouch.lion.common.date.DateUtil;
import com.newtouch.lion.data.AllTest;
import com.newtouch.lion.model.datagrid.DataColumn;
import com.newtouch.lion.model.datagrid.DataGrid;
import com.newtouch.lion.model.system.CodeList;
import com.newtouch.lion.model.system.CodeType;
import com.newtouch.lion.model.system.Parameter;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.datagrid.DataGridService;
import com.newtouch.lion.service.excel.ExcelExportService;
import com.newtouch.lion.service.excel.impl.ExcelExportServiceImpl;
import com.newtouch.lion.service.system.CodeTypeService;
import com.newtouch.lion.service.system.ParameterService;

/**
 * <p>
 * Title: Excel导出服务测试
 * </p>
 * <p>
 * Description: Excel导出服务测试
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
public class ExcelExportServiceTest extends AllTest {
	
	@Autowired
	private DataGridService dataGridService;
	
	@Autowired
	private ParameterService parameterService;
	
	@Autowired
	private CodeTypeService codeTypeService;
	
	@Test
	public void export(){
		String tableId="sys_parameter_lists_tb";
		String type="SystemParamter";
		DataGrid dataGrid=dataGridService.doFindByTableId(tableId);
		List<DataColumn> dataColumns = new ArrayList<DataColumn>(dataGrid.getColumns());
		Collections.sort(dataColumns, new DataColumnComparator());
		dataGrid.setSortColumns(dataColumns);
		QueryCriteria queryCriteria=new QueryCriteria();
		queryCriteria.setPageSize(99999);
		PageResult<Parameter> result=parameterService.doFindByCriteria(queryCriteria);
		ExcelExportService<Parameter> excelExportService=new ExcelExportServiceImpl<Parameter>();
		
		CodeType codeType=codeTypeService.doFindTypeByNameEn(type);
		 Map<Object, Object>  codeTypeMap=new HashMap<Object, Object>();
		 for(CodeList codeList:codeType.getCodeLists()){
			 codeTypeMap.put(codeList.getCodeValue(),codeList);
		 }
		
		Map<String, Map<Object, Object>> fieldCodeTypes = new HashMap<String, Map<Object, Object>>();
		fieldCodeTypes.put("type",codeTypeMap);

		Map<String, String> dataFormats = new HashMap<String, String>();
		dataFormats.put("birthday", DateUtil.FORMAT_DATE_YYYY_MM_DD);
		
		OutputStream out=null;
		try {
			out = new FileOutputStream("D:/app/excel/parameter1.xls");
			excelExportService.export(dataGrid, result.getContent(), out, fieldCodeTypes, dataFormats);
			out.close();
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}finally{
			if(out!=null){
				IOUtils.closeQuietly(out);
			}
		}
	}
}

	