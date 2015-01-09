/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: MenuServiceTest.java 9552 2015年1月5日 上午10:30:22 WangLijun$
*/
package com.newtouch.lion.service.menu; 

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.newtouch.lion.model.menu.Menu;
import com.newtouch.lion.service.AllServiceTest;

/**
 * <p>
 * Title: 菜单测试类
 * </p>
 * <p>
 * Description: 菜单测试类
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
public class MenuServiceTest extends AllServiceTest{
	
	@Autowired
	private MenuService menuService;
	
	@Test
	public void doFindByUserId(){
		Long userId=1L;
		List<Menu>  list=menuService.doFindByUserId(userId);

		
		logger.info("list.size:{}",list.size());
		for(Menu menu:list){
			logger.info("一级菜单:{},{},{}",menu.getNameZh(),menu.getId(),menu.getParentId());
			for(Menu menu1:menu.getMenus()){
				logger.info("二级菜单:{},{},{}",menu1.getNameZh(),menu1.getId(),menu1.getParentId());
				for(Menu menu2:menu.getMenus()){
					logger.info("三级菜单:{},{},{}",menu2.getNameZh(),menu2.getId(),menu2.getParentId());
				}
			}
		}
		logger.info("list.size:{}",list.size());

	}
	
}

	
