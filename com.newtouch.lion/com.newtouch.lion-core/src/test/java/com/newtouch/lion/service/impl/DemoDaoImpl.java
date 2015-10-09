/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: DemoDaoImpl.java 9552 2015年10月9日 上午10:58:07 WangLijun$
*/
package com.newtouch.lion.service.impl; 

import org.springframework.stereotype.Repository;

import com.newtouch.lion.dao.BasDemoDao;
import com.newtouch.lion.dao.impl.MyBatisBaseDaoImpl;
import com.newtouch.lion.model.BasDemo;
import com.newtouch.lion.service.DemoDao;

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
@Repository
public class DemoDaoImpl extends MyBatisBaseDaoImpl<BasDemoDao> implements DemoDao {

	/* (non-Javadoc)
	 * @see com.newtouch.lion.dao.DemoDao#getById()
	 */
	@Override
	public BasDemo getById(Long id) {
		return this.mapper.getById(id);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.DemoDao#delete(java.lang.Long)
	 */
	@Override
	public int delete(Long id) {
	 
		 return  this.mapper.delete(id);
	}
	
	
}	

	