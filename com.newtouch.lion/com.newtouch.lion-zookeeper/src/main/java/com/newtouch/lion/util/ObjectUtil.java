/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ObjectUtil.java 9552 2015年5月29日 下午10:00:41 WangLijun$
*/
package com.newtouch.lion.util; 


/**
 * <p>
 * Title: 判断是否为空
 * </p>
 * <p>
 * Description: 判断是否为空
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
public class ObjectUtil {
	
	/***
	 * 判断对象是否为空
	 * @param object
	 * @return　如为空则返回true,否则返回false
	 */
	public static boolean isBlank( Object object ){
		return null == object;
	}
	
	/***
	 * 判断一组对象是否为空，
	 * @param objects　对象组
	 * @return　如有一个对象为空，则返回true,否则返回false
	 */
	public static boolean isBlank( Object... objects ) {

		if ( null == objects || 0 == objects.length )
			return true;
		for ( int i = 0; i < objects.length; i++ ) {
			if ( isBlank( objects[i] ) )
				return true;
		}
		return false;
	}
}

	