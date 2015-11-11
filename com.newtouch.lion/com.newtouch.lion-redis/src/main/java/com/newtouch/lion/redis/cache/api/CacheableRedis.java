/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RedisCacheableMethodAdvice.java 9552 2015年4月11日 下午2:30:25 WangLijun$
*/
package com.newtouch.lion.redis.cache.api;

import java.lang.reflect.Method;

import com.newtouch.lion.redis.cache.Remove;

/**
 * Redis 通用处理缓存存储<br> 
 * 缓存存储
 *
 * @author wanglijun
 */
public interface CacheableRedis {
    
    /***
     * 
     * 功能描述:获取缓存处理 <br>
     * 〈功能详细描述〉
     *
     * @param key 缓存KEY
     * @param method 方法
     * @param value 返回类型
     * @return
     */
    public <T> T getCache(String key,Method method,Class<T> value);
    
    /** 
    * 功能描述:  获取缓存处理<br>
    * 〈功能详细描述〉
    *
    * @param key      KEY
    * @param Object   缓存的方法
    * @param method   缓存的方法
    */
    public void setCache(String key,Object object,Method method);
    /***
     * 
     * 功能描述: 生成缓存主键<br>
     * 〈功能详细描述〉
     *
     * @param target  代理目标对象
     * @param method  缓存的方法
     * @param args    请求参数
     */
    public String getKeyGenerator(Object target,Method method,Object[] args);
    /***
     * 
     * 功能描述: KEY移动主键<br>
     * 〈功能详细描述〉
     *
     * @param target 目标对象
     * @param method 缓存方法 
     * @param args   请求参数
     * @param remove  删除的KEY
     * @return
     */
    public String getKeyGenerator(Object target,Method method,Object[] args,Remove remove);
    
    /***
     * 
     * 功能描述: 删除缓存<br>
     * 可以根据缓存KEY的方式:单个KEY的删除或模糊删除
     * @param target
     * @param method
     * @param args
     */
    public void removeCache(Object target,Method method,Object[] args);
    
}

    