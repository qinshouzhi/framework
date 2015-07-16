/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RedisCacheableMethodAdvice.java 9552 2015年4月11日 下午2:30:25 WangLijun$
*/
package com.newtouch.lion.redis.cache.impl;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.newtouch.lion.redis.cache.Cacheable;
import com.newtouch.lion.redis.cache.Constant;
import com.newtouch.lion.redis.cache.DataType;
import com.newtouch.lion.redis.cache.Remove;
import com.newtouch.lion.redis.cache.TimeToLive;
import com.newtouch.lion.redis.cache.api.CacheKeyGenerator;
import com.newtouch.lion.redis.cache.api.CacheableRedis;
import com.newtouch.lion.redis.client.IBinaryRedisClient;
import com.newtouch.lion.redis.client.IRedisClient;
import com.newtouch.lion.redis.exception.CacheErrorCode;
import com.newtouch.lion.redis.exception.CacheException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author wanglijun
 */
public class CacheableRedisImpl implements CacheableRedis {
    
    /**日志*/
    protected final Logger logger=LoggerFactory.getLogger(super.getClass());
   
    /**缓存KEY*/
    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    /**判断是否有参数正则表达式*/
    protected final Pattern paramPattern=Pattern.compile(Constant.KEY_PARESE_PATTERN);
    /**判断KEY中是否包含模糊匹配的字符 用于删除还是*/
    protected final Pattern patternLike=Pattern.compile(Constant.KEY_LIKE_PATTERN);
    
    /**  二进制的redis. */
    
    private IBinaryRedisClient binaryRedisClient;
    /** 缓存接口. */
 
    private IRedisClient redisClient;
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCache(String key, Method method,Class<T> value) {
        Cacheable annotation=method.getAnnotation(Cacheable.class);
        if(annotation.dataType()==DataType.LIST){
            if(annotation.cacheReference().typeReference()==null){
                throw new CacheException(CacheErrorCode.CACHE_REFERENCE_NULL);
            }
            Object object=binaryRedisClient.get(key,annotation.cacheReference().typeReference());
            return (T) object;
        }else  if(annotation.dataType()==DataType.STRING){
            return binaryRedisClient.get(key,value);
        }
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setCache(String key,Object object, Method method) {
       
        Cacheable annotation=method.getAnnotation(Cacheable.class);
        //缓存有效时间
        Integer timeToLive=annotation.timeToLive().code();
        if(annotation.timeToLive()==TimeToLive.PERSISTENCE){
            this.binaryRedisClient.set(key,object); 
        }else{
            this.binaryRedisClient.setex(key,object, timeToLive);
        }
       
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getKeyGenerator(Object target, Method method, Object[] args){
        Cacheable annotation=method.getAnnotation(Cacheable.class);
        String key=annotation.key();
        //如果缓存的key,则抛出异常
        if(StringUtils.isEmpty(key)){
            throw new CacheException(CacheErrorCode.KEY_IS_NULL);
        }
        //调用cache生成方法
        key=this.cacheKeyGenerator.keyGenerator(key, target, method, args);
        //如果缓存的key,则抛出异常
        if(StringUtils.isEmpty(key)){
            throw new CacheException(CacheErrorCode.KEY_IS_NULL);
        }
        return key;
    }
    
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getKeyGenerator(Object target, Method method, Object[] args,Remove remove) {
        String key=remove.key();
        //如果缓存的key,则抛出异常
        if(StringUtils.isEmpty(key)){
            throw new CacheException(CacheErrorCode.KEY_IS_NULL);
        }
        //调用cache生成方法
        key=this.cacheKeyGenerator.keyGenerator(key, target, method, args);
        //如果缓存的key,则抛出异常
        if(StringUtils.isEmpty(key)){
            throw new CacheException(CacheErrorCode.KEY_IS_NULL);
        }
        return key;
    }
    
    
    

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeCache(Object target, Method method, Object[] args) {
        Remove annotation=method.getAnnotation(Remove.class);
        String key=this.getKeyGenerator(target, method, args, annotation);
        logger.info("removeCache Key:{}",key);
        //是否包含模糊匹配删除
        if(containRemoveLikeStr(key)){
            logger.info("模糊匹配删除RedisCache");
            this.clearCacheByPattern(key);
        }else{
            logger.info("单个键值删除RedisCache");
            this.redisClient.del(key);
        }
    }
    /***
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param key
     * @return
     */
    protected Boolean  containRemoveLikeStr(String key){
        Matcher matcher = patternLike.matcher(key);
        return matcher.find();
    }

    /**
     * 功能描述:模糊匹配清除缓存 <br>
     * 清理符合指定正则表达式的缓存.
     *
     * @param pattern the pattern KEY
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    protected void clearCacheByPattern(String pattern) {
        Set<String> keys =this.redisClient.keys(pattern);
        for (String key : keys) {
            redisClient.del(key);
        }
    }
    
    /***
     * 
     * 功能描述: 判断KEY是否转参数，如果传参数则按参数解析参数<br>
     * 〈功能详细描述〉
     *
     * @param key 缓存键
     * @return 是否包含参数值 如果包含返回:TRUE,否则返回FALSE
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    protected Boolean containParamemter(String key){
       Matcher matcher = paramPattern.matcher(key);
       return matcher.find();
    }

	/**
	 * @return the cacheKeyGenerator
	 */
	public CacheKeyGenerator getCacheKeyGenerator() {
		return cacheKeyGenerator;
	}

	/**
	 * @param cacheKeyGenerator the cacheKeyGenerator to set
	 */
	public void setCacheKeyGenerator(CacheKeyGenerator cacheKeyGenerator) {
		this.cacheKeyGenerator = cacheKeyGenerator;
	}

	/**
	 * @return the binaryRedisClient
	 */
	public IBinaryRedisClient getBinaryRedisClient() {
		return binaryRedisClient;
	}

	/**
	 * @param binaryRedisClient the binaryRedisClient to set
	 */
	public void setBinaryRedisClient(IBinaryRedisClient binaryRedisClient) {
		this.binaryRedisClient = binaryRedisClient;
	}

	/**
	 * @return the redisClient
	 */
	public IRedisClient getRedisClient() {
		return redisClient;
	}

	/**
	 * @param redisClient the redisClient to set
	 */
	public void setRedisClient(IRedisClient redisClient) {
		this.redisClient = redisClient;
	}
	
	
}