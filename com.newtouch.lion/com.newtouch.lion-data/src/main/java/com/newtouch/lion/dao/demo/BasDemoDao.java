/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: BasDemoDao.java 9552 2015年9月16日 上午11:18:01 WangLijun$
*/
package com.newtouch.lion.dao.demo; 

import java.util.List;

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
public interface BasDemoDao {
	
	public BasDemo save(BasDemo  basDemo);
	
	public BasDemo update(BasDemo  basDemo);
	
	public BasDemo delete(Long id);
	
	public BasDemo find(Long id);
	
	public List<BasDemo> findAll();
	
	
}

	