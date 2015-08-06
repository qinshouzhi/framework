/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: IPUtil.java 9552 2015年7月30日 下午2:40:13 WangLijun$
*/
package com.newtouch.lion.util; 

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * Title:IP工具类 
 * </p>
 * <p>
 * Description: IP工具类 
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
public class IPUtil {
	/** 包含IP */
	public static final String REG_EXP_OF_CONTAINS_IP = "((?s).*?)([1-9]|[1-9]\\d|1\\d{2}|2[0-1]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}((?s).*?)";
	
	public static final Pattern PATTERN_OF_CONTAINS_IP = Pattern.compile( REG_EXP_OF_CONTAINS_IP );
		
	/**
	 * Check if string contains ip,此方法不能完全判断ip是否合法
	 * 
	 * @return
	 */
	public static boolean containsIPAddress( String originalStr ) {

		if ( StringUtils.isBlank( originalStr ) )
			return false;

		Matcher match = PATTERN_OF_CONTAINS_IP.matcher( originalStr );
		return match.matches();
	}
	
	public static void main(String[] args) {
		String str="127.0.0.1";
		boolean result=IPUtil.containsIPAddress(str);
		System.out.println(result);
	}
}

	