/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RedisCacheableMethodAdvice.java 9552 2015年4月11日 下午2:30:25 WangLijun$
*/
package com.newtouch.lion.redis.cache.interceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import com.newtouch.lion.redis.cache.Cacheable;
import com.newtouch.lion.redis.cache.Remove;
import com.newtouch.lion.redis.cache.api.CacheableRedis;

/**
 * 缓存注解解析<br>
 * 缓存注解解析
 * 
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
//@Component("redisCacheInterceptor")
public class RedisCacheInterceptor implements MethodInterceptor {
    /**
     * 日志
     * */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**代理类分割符*/
    private final String PROXY_CGLIB_SPLIT="$$";
    /**缓存处理对象*/
    @Resource
    private  CacheableRedis cacheableRedis;
    /**代理对象列表*/
    private static Map<String,Object>  targets=new HashMap<String,Object>();
 
    /**
     * 默认构造函数
     * */
    public RedisCacheInterceptor() {
        super();
    } 
        
    /**
     * {@inheritDoc}
     */
    @Override
    public Object intercept(Object target, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        logger.debug("in intercept...");
        Object result = null; //返回结果
        logger.info("invoke:{}",method.getName());
        String className=this.getTargetClassName(target);
        //获取缓存放数据
        if(method.isAnnotationPresent(Cacheable.class)){
            //获取缓存
            logger.info("invoke:{},{}",method.getName(),Cacheable.class.getName());
            //生成KEY
            String key=this.cacheableRedis.getKeyGenerator(this.getTarget(className), method, args);
            logger.info("key:{}",key);
            result=this.cacheableRedis.getCache(key, method, method.getReturnType());
            if(result==null){
                logger.info("未查询，缓存查询");
              //查询Service
               result=method.invoke(this.getTarget(className),args);
              //存储缓存
               if(result!=null){
                   logger.info("将查询结果存放到缓存中");
                   this.cacheableRedis.setCache(key,result,method);
               }
              
            }
        }else if(method.isAnnotationPresent(Remove.class)){
            //清除缓存
            logger.info("invoke:{},{}",method.getName(),Remove.class.getName());
            this.cacheableRedis.removeCache(this.getTarget(className), method, args);
            result = method.invoke(this.getTarget(className),args);
        }else{
            result = method.invoke(this.getTarget(className),args);
        }
        logger.debug("out intercept...");
        return result;
    }
    /***
     * 
     * 功能描述:根据className名称获取代理对象 <br>
     * 〈功能详细描述〉
     *
     * @param className
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    protected Object getTarget(String className){
        return targets.get(className);
    }
    
    /***
     * 
     * 功能描述: 获取代理类的名称<br>
     * 〈功能详细描述〉
     *
     * @param target
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    protected String getTargetClassName(Object target){
        String classProxyName=target.getClass().getName();
        return classProxyName.substring(0,classProxyName.indexOf(PROXY_CGLIB_SPLIT));
    }
    
    
    /**
     * @param target 代理对象
     */
    public void setTarget(Object target) {
        targets.put(target.getClass().getName(),target);
    }
    
    
}
