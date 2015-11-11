/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RedisCacheableMethodAdvice.java 9552 2015年4月11日 下午2:30:25 WangLijun$
*/
package com.newtouch.lion.redis.cache.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;

import com.newtouch.lion.redis.cache.Cache;
import com.newtouch.lion.redis.cache.interceptor.RedisCacheInterceptor;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author wanglijun
 */
//@Component
public class RedisPostBeanProcessor implements BeanPostProcessor {
    /**日志*/
    private  final Logger logger=LoggerFactory.getLogger(super.getClass());
    /**缓存方法拦截器*/
    @Autowired
    private RedisCacheInterceptor cacheInterceptor;
    /**对象列表*/
    

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Cache.class)) {
            // 如果是缓存代理的类
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(bean.getClass());
            logger.info("bean.getClass():{}",bean.getClass());
            cacheInterceptor.setTarget(bean);
            enhancer.setCallback(cacheInterceptor);
            return enhancer.create();
        } else {
            return bean;
        }
    }

}
