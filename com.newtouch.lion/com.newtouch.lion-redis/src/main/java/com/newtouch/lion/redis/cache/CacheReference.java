/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RedisCacheableMethodAdvice.java 9552 2015年4月11日 下午2:30:25 WangLijun$
*/
package com.newtouch.lion.redis.cache;


import com.alibaba.fastjson.TypeReference;
import com.newtouch.lion.redis.cache.api.RedisTypeReference;

/**
 * 缓存引用类型转换<br> 
 * 〈功能详细描述〉
 *
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public enum CacheReference implements RedisTypeReference{
    /**默认为空*/
    NONE(null),
    /**用户分页列表*/
    
    ;
    
    @SuppressWarnings("rawtypes")
    private TypeReference typeReference;
    
    @SuppressWarnings("rawtypes")
    private CacheReference(TypeReference  typeReference){
        this.typeReference=typeReference;
    }
    
    @SuppressWarnings("rawtypes")
    @Override
    public TypeReference typeReference() {
        return this.typeReference;
    }
    
}
