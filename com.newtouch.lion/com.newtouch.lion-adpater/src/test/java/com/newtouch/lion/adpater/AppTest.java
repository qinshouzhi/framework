package com.newtouch.lion.adpater;

import java.util.LinkedHashMap;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-adapter.xml")
public class AppTest extends AbstractJUnit4SpringContextTests {
    /**日志*/
    protected  final Logger  logger=LoggerFactory.getLogger(super.getClass());
    /**查询KEY*/
    protected String key=null;
    /**参数初化*/
    protected LinkedHashMap<Object,Object> params=new LinkedHashMap<Object,Object>();
    
}
