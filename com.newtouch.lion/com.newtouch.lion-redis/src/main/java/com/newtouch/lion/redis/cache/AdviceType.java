/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RedisCacheableMethodAdvice.java 9552 2015年4月11日 下午2:30:25 WangLijun$
*/
package com.newtouch.lion.redis.cache;


/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author wanglijun
 */
public enum AdviceType {
    /**
     * No advice
     */
    NONE,
    /**
     * {@link Cacheable} advice
     */
    CACHE,
    /**UPDATE Advice*/
    UPDATE, 
    /**
     * {@link TriggersRemove} advice
     */
    REMOVE;
}
