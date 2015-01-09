/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TsCodeTypeDao.java 9552 2012-12-31 下午7:14:53 WangLijun$
 */
package com.newtouch.lion.dao.system.impl;

import org.springframework.stereotype.Repository;

import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.dao.system.CodeTypeDao;
import com.newtouch.lion.model.system.CodeType;

/**
 * <p>
 * Title: 通用编码类型Dao实现类
 * </p>
 * <p>
 * Description: 通用编码类型Dao实现类
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
@Repository("codeTypeDao")
public class CodeTypeDaoImpl extends BaseDaoImpl<CodeType,Long> implements CodeTypeDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7307456949562991641L;

}
