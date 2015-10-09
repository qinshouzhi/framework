 
package com.newtouch.lion.test; 

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.newtouch.lion.model.BasDemo;
import com.newtouch.lion.service.BasDemoService;

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
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class BasDemoServiceTest {
	
	protected static  final Logger logger=LoggerFactory.getLogger(BasDemoServiceTest.class);
	
	@Autowired
	private BasDemoService  basDemoService;
	
	@Test
	public void insert(){
		BasDemo basDemo=new BasDemo();
		basDemo.setNameZh("MyBatis");
		basDemo.setParentId(0L);
		basDemo.setUpdatedById(1L);
		basDemo.setUpdatedDate(new Date());
		basDemo.setCreatedById(1L);
		basDemo.setCreatedDate(new Date());
		basDemoService.insert(basDemo);
		
		
	}
	
	@Test
	public void findById(){
		Long id=2L;
		BasDemo basDemo=this.basDemoService.findById(id);
		logger.info("{}",basDemo.toString());
	}
	
	
	@Test
	public void deleteById(){
		Long id=1L;
		int result=this.basDemoService.deleteById(id);
		logger.info("result:{}",result);
	}
}

	