/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TsCodeListDaoImpl.java 9552 2012-12-31 下午7:11:17 WangLijun$
 */
package com.newtouch.lion.dao.system.impl;

import org.springframework.stereotype.Repository;

import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.dao.system.CodeListDao;
import com.newtouch.lion.model.system.CodeList;

/**
 * <p>
 * Title: 通用编码Dao实现类
 * </p>
 * <p>
 * Description: 通用编码Dao实现类
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
@Repository("codeListDao")
public class CodeListDaoImpl extends BaseDaoImpl<CodeList, Long> implements
		CodeListDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5326942517929124113L;

}
