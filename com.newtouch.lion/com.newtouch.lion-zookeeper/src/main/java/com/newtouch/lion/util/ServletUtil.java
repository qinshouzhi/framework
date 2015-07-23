package com.newtouch.lion.util;

import org.apache.commons.lang.StringUtils;

/**
 * 类说明: Servlet相关工具类<br>
 * 请注意本类的一些概念：<br>
 * requestURI:  /taokeeper/monitor.do?method=alarm&clusterId=1 <br>
 * requestSimpleURL:  http://ops.jm.taobao.net/taokeeper/monitor.do <br>
 * requestFullURL:  http://ops.jm.taobao.net/taokeeper/monitor.do?method=alarm&clusterId=1 <br>
 * contextPath: /taokeeper <br>
 * requestServer: ops.jm.taobao.net:80 
 */
public class ServletUtil {


	
	/**
	 * 从一个 server （127.0.0.1:8080）中解析出 [127.0.0.1,8080] return ip: stirng[0]
	 * port: string[1] 放心，不会出现null pointer, 数组长度一定是2
	 */
	public static String[] paraseIpAndPortFromServer( String server ) {
		if (StringUtils.isEmpty(server))
			return new String[] { StringUtils.EMPTY, StringUtils.EMPTY};
		String[] serverArray = server.split(":");
		if ( 0 == serverArray.length )
			return new String[] { StringUtils.EMPTY, StringUtils.EMPTY };
		else if ( 1 == serverArray.length )
			return new String[] { serverArray[0], StringUtils.EMPTY };
		else if ( 2 == serverArray.length )
			return serverArray;
		return new String[] { StringUtils.EMPTY, StringUtils.EMPTY };
	}
	
	
	
	
	 

}