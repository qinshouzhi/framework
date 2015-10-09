/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: CountryDaoImpl.java 9552 2015年10月9日 下午5:08:30 WangLijun$
*/
package com.newtouch.lion.dao.impl; 

import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newtouch.lion.dao.CountryDao;
import com.newtouch.lion.mapper.CountryMapper;
import com.newtouch.lion.model.Country;

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
public class CountryDaoImpl extends MyBatisAbstractBaseDao<CountryMapper> implements CountryDao{

	@Override
	public PageInfo<Country> findPage() {
		  PageHelper.startPage(5, 20);
		  List<Country> list = this.mapper.selectAll();
          PageInfo<Country> page = new PageInfo<Country>(list);
          return page;
	}

}

	