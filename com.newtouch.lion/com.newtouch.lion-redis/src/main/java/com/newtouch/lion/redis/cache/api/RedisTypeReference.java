/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RedisCacheableMethodAdvice.java 9552 2015年4月11日 下午2:30:25 WangLijun$
*/
package com.newtouch.lion.redis.cache.api;

import com.alibaba.fastjson.TypeReference;




/**
 * Redis 引用类型<br> 
 * @author wanglijun
 */
public interface RedisTypeReference {
    @SuppressWarnings("rawtypes")
    public TypeReference typeReference();
}
