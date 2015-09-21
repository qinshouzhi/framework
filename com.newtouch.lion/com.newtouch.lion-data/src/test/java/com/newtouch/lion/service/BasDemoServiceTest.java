/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: BasDemoServiceTest.java 9552 2015年9月16日 下午2:32:39 WangLijun$
*/
package com.newtouch.lion.service; 

 
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.newtouch.lion.model.demo.BasDemo;

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
//整合
@RunWith(SpringJUnit4ClassRunner.class)
//加载配置
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class BasDemoServiceTest {
	
	@Autowired
	private BasDemoService basDemoService;
	
	@Test
	public void save(){
		BasDemo basDemo=new BasDemo();
		//basDemo.setId(1L);
		basDemo.setNameZh("MyBatis");
		basDemo.setParentId(0L);
		basDemo.setUpdatedById(1L);
		basDemo.setUpdatedDate(new Date());
		basDemo.setCreatedById(1L);
		basDemo.setCreatedDate(new Date());
		basDemoService.insert(basDemo);
	}
}

	