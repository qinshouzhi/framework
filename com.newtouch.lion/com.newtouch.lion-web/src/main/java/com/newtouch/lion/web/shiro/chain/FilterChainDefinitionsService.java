/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: FilterChainDefinitionsService.java 9552 2015年3月4日 下午3:14:41 WangLijun$
*/
package com.newtouch.lion.web.shiro.chain; 

import java.util.Map;

/**
 * <p>
 * Title: Shiro权限动态更新
 * </p>
 * <p>
 * Description: Shiro权限动态更新
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
public interface FilterChainDefinitionsService {
	 /** 初始化框架权限资源配置 */  
    public abstract void intiPermission();
    
    /** 重新加载框架权限资源配置 (强制线程同步) */  
    public abstract void updatePermission();
    
    /**始化第三方权限资源配置 */  
    public abstract Map<String, String> initOtherPermission();
}

	