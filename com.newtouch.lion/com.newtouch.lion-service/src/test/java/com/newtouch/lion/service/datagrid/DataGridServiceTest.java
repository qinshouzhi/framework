/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: DataGridServiceTest.java 9552 2015年1月9日 下午2:34:04 WangLijun$
*/
package com.newtouch.lion.service.datagrid; 

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.newtouch.lion.model.datagrid.DataGrid;
import com.newtouch.lion.service.AllServiceTest;

/**
 * <p>
 * Title: DataGridTest测试类
 * </p>
 * <p>
 * Description: DataGridTest测试类
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
public class DataGridServiceTest extends AllServiceTest {
	
	@Autowired
	private DataGridService dataGridService;
	
	@Test
	public void doFindByTableIdTest(){
		String tableId="sys_parameter_lists_tb";
		DataGrid dataGrid=dataGridService.doFindByTableId(tableId);
		logger.info("ID:{}",dataGrid.getId());
	}
}

	