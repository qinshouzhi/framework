/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: BasDemoSevice.java 9552 2015��9��16�� ����4:25:07 WangLijun$
*/
package  com.newtouch.lion.service;

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
public interface BasDemoService {
	/***
	 * 
	 * @param basDemo
	 */
	public void insert(BasDemo basDemo);
	
	/***
	 * 
	 * @param id
	 */
	public BasDemo findById(Long id);
	
	/**
	 * 删除
	 * @param id
	 */
	public int deleteById(Long id);
}

	