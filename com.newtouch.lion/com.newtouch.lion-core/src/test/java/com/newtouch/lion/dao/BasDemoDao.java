package com.newtouch.lion.dao;


import com.newtouch.lion.model.BasDemo;

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
	 
    int insert(BasDemo basDemo);
    
    BasDemo getById(Long id);
   
	int  delete(Long id);
}

	