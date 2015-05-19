/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RedisCacheableMethodAdvice.java 9552 2015年4月11日 下午2:30:25 WangLijun$
*/
package com.newtouch.lion.redis.aop;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import com.newtouch.lion.redis.cache.Cacheable;
import com.newtouch.lion.redis.cache.Remove;
import com.newtouch.lion.redis.cache.api.CacheableRedis;

/**
 * Redis缓存AOP处理<br> 
 * 〈功能详细描述〉
 *
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class RedisCacheableMethodAdvice implements MethodBeforeAdvice,AfterReturningAdvice{
    
    /**日志 * */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /**缓存处理对象*/
    @Resource
    private  CacheableRedis cacheableRedis;
       
    /**
     * {@inheritDoc}
     */
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        //获取缓存放数据
        if(method.isAnnotationPresent(Cacheable.class)){
            Object result = null; //返回结果
            //获取缓存
            logger.info("invoke:{}.{},{}",target.getClass().getClass().getName(),method.getName(),Cacheable.class.getName());
            //生成KEY
            String key=this.cacheableRedis.getKeyGenerator(target, method, args);
            logger.info("key:{}",key);
            result=this.cacheableRedis.getCache(key, method, method.getReturnType());
            if(result==null){
                logger.info("未查询，缓存查询");
              //查询Service
               result=method.invoke(target,args);
              //存储缓存
               if(result!=null){
                   logger.info("将查询结果存放到缓存中");
                   this.cacheableRedis.setCache(key,result,method);
               }
              
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        //获取缓存放数据
        if(method.isAnnotationPresent(Cacheable.class)){
            
        }else if(method.isAnnotationPresent(Remove.class)){
            
        }
    }
    
}
