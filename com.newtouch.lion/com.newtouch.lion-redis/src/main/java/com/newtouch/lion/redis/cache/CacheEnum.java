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
public interface CacheEnum {
    /**
     * 功能描述: <br>
     * 获取枚举类型编码.
     *
     * @return 类型编码
     */
    Integer code();
    
    /**
     * 功能描述: <br>
     * 获取枚举类型字符描述.
     *
     * @return 类型字符描述
     */
    String desc();
}
