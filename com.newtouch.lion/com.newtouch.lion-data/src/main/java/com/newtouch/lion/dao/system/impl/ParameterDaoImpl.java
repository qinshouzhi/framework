/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TsParameterDaoImpl.java 9552 2012-7-8 上午01:39:26 WangLijun$
 */
package com.newtouch.lion.dao.system.impl;

import org.springframework.stereotype.Repository;

import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.dao.system.ParameterDao;
import com.newtouch.lion.model.system.Parameter;

/**
 * <p>
 * Title:参数配置信息数据存储类
 * </p>
 * <p>
 * Description:用天处理参数配置信息，保存、更新、保存、删除等
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
@Repository("parameterDao")
public class ParameterDaoImpl extends BaseDaoImpl<Parameter,Long> implements ParameterDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1772480301507118710L;

}
