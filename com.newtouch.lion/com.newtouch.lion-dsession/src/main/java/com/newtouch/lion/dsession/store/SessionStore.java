/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: SessionStore.java 9552 2015年5月21日 下午2:23:16 WangLijun$
*/
package com.newtouch.lion.dsession.store; 

import com.newtouch.lion.session.context.SessionRequestContext;

/**
 * <p>
 * Title: Session存取操作
 * </p>
 * <p>
 * Description: Session存取操作
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
public interface SessionStore {
	 
	public void invalidate(SessionRequestContext requestContext);	
}

	