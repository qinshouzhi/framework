/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RedisCacheableMethodAdvice.java 9552 2015年4月11日 下午2:30:25 WangLijun$
*/
package com.newtouch.lion.redis.cache.interceptor;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.newtouch.lion.redis.cache.Cacheable;
import com.newtouch.lion.redis.cache.Remove;
import com.newtouch.lion.redis.cache.api.CacheableRedis;

/**
 * Redis 缓存代码<br>
 * 〈功能详细描述〉
 * 
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class RedisMethodCacheInterceptor implements MethodInterceptor,InitializingBean{

    /** 日志 * */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    /** 缓存处理对象 */
    @Resource
    private CacheableRedis cacheableRedis;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object target = invocation.getThis();
        Method method = invocation.getMethod();
        Object[] args = invocation.getArguments();
        Object returnValue = null;
        if (invocation.getThis().getClass().getCanonicalName().contains("$Proxy")) {
            logger.warn(
                    "The object has been proxyed and method cache interceptor can't get the target,so the method result can't be cached which name is:{}",
                    method.getName());
            return invocation.proceed();
        }
        // 获取缓存放数据
        if (method.isAnnotationPresent(Cacheable.class)) {
            // 获取缓存
            logger.info("invoke:{}.{},{}", target.getClass().getClass().getName(), method.getName(),
                    Cacheable.class.getName());
            // 生成KEY
            String key = this.cacheableRedis.getKeyGenerator(target, method, args);
            logger.info("key:{}",key);
            returnValue = this.cacheableRedis.getCache(key, method, method.getReturnType());
            if (returnValue == null) {
                returnValue = invocation.proceed();
            }else{
                return returnValue;
            }
            // 存储缓存
            if (returnValue != null) {
                logger.info("将查询结果存放到缓存中");
                this.cacheableRedis.setCache(key, returnValue, method);
            }
        } else if (method.isAnnotationPresent(Remove.class)) {
            this.cacheableRedis.removeCache(target, method, args);
            returnValue = invocation.proceed();
        } else {
            returnValue = invocation.proceed();
        }
        return returnValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(cacheableRedis, "Need a cache. Please use setCache(Cache) create it.");
    }

    
    
}
