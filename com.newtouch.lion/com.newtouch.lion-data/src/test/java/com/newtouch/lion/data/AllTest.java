/**
 * 
 */
package com.newtouch.lion.data;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wanglijun
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = false)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
public abstract class AllTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	/**日志*/
	protected final Logger logger = LoggerFactory.getLogger(super.getClass());
	
	
}
