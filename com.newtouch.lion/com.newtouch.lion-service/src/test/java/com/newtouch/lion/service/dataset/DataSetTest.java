/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: DataSetTest.java 9552 2015年1月9日 下午3:29:10 WangLijun$
*/
package com.newtouch.lion.service.dataset; 

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.newtouch.lion.json.JSONParser;

/**
 * <p>
 * Title: DataSet
 * </p>
 * <p>
 * Description:DataSet
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
public class DataSetTest {
	
	@Test
	public void test(){
		Integer total=20;
		List<String> rows=new ArrayList<String>();
		rows.add("even");
		rows.add("even1");
		rows.add("even2");
		rows.add("even3");
		
		DataSet dataSet=new DataSet();
		dataSet.setTotal(total);
		dataSet.setRows(rows);
		
		String str=JSONParser.toJSONString(dataSet);
		System.out.println(str);
	}
}

	