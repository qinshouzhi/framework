/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RedisCacheableMethodAdvice.java 9552 2015年4月11日 下午2:30:25 WangLijun$
*/
package com.newtouch.lion.redis.cache;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Redis 缓存注解<br> 
 * 〈功能详细描述〉
 *
 * @author wanglijun
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Cacheable {
    /**缓存KEY*/
    String key();
    /**默认为5分钟*/
    TimeToLive timeToLive() default TimeToLive.DEFAULT;
    /**数据类型*/
    DataType dataType() default DataType.STRING;
    /**引用类型*/
    CacheReference cacheReference() default CacheReference.NONE; 
}
