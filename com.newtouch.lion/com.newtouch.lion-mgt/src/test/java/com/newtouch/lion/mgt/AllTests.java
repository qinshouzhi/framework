/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: AllTests.java 9552 2015年3月16日 下午3:00:08 WangLijun$
 */
package com.newtouch.lion.mgt;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
@ContextConfiguration(locations = "classpath:applicationContext-mgt.xml")
public class AllTests extends AbstractJUnit4SpringContextTests {
	
}
