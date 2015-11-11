/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: CountryMapper.java 9552 2015年10月9日 下午4:58:21 WangLijun$
 */
package com.newtouch.lion.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

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
public interface CountryMapper {
	
	List<Country> selectAll();

	List<Country> selectAll(RowBounds rowBounds);
}
