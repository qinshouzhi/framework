/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RedisCacheableMethodAdvice.java 9552 2015年4月11日 下午2:30:25 WangLijun$
*/
package com.newtouch.lion.redis.cache.api;

import java.lang.reflect.Method;

/**
 * 缓存KEY生成接口定义<br> 
 * 〈功能详细描述〉
 *
 * @author wanglijun
 */
public interface CacheKeyGenerator {    
    /***
     * 
     * 功能描述:生成缓存KEY
     * 〈功能详细描述〉 
     *@param 未解析之前 key值
     * @param target 代理对象
     * @param method 方法
     * @param args   方法参数
     * @return
     */
    public String  keyGenerator(String key,Object target,Method method,Object[] args);
}
