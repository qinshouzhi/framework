/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: IconServiceTest.java 9552 2015年3月30日 下午2:33:24 WangLijun$
*/
package com.newtouch.lion.data.service; 

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.newtouch.lion.AllTest;
import com.newtouch.lion.model.system.Icon;
import com.newtouch.lion.service.system.IconService;

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
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class IconServiceTest   extends AllTest {
	
	@Autowired
	IconService iconService;
	
	
	
	@Test
	public void query(){
		String iconType="RESOURCE_ICON";
		 List<Icon>  list=iconService.doFindByType(iconType);
		 System.out.println(list.size());
	}
}

	