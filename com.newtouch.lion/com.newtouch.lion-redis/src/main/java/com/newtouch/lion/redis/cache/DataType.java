/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RedisCacheableMethodAdvice.java 9552 2015年4月11日 下午2:30:25 WangLijun$
*/
package com.newtouch.lion.redis.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Redis 数据类型<br> 
 *  数据如下：
 *  ●String
 *  ●Lists (列表)
　　●Sets (集合)
　　●Sorted sets (有序集合)
　　●Hashes (哈希表)
 *
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public enum DataType implements CacheEnum{
    STRING(1,"字符串"),
    HASH(2,"哈希表"),
    LIST(3,"列表"),
    SET(4,"集合"),
    SORTEDSET(5,"有序集合");
    /** 枚举显示. */
    private String desc;
    /** 枚举代码. */
    private Integer code;
    /**
     * 默认
     * */
    private DataType() {
        
    }
    /**
     * 
     * @param code 值
     * @param desc 描述
     */
    private DataType(Integer code,String desc){
        this.code=code;
        this.desc=desc;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer code() {
        return this.code;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String desc() {
        return this.desc;
    }
    
    private static Map<String, DataType> stringToEnum = new HashMap<String, DataType>();
    static {
        // Initialize map from constant name to enum constant
        for (DataType value : values()) {
            stringToEnum.put(String.valueOf(value.code()), value);
        }
    }
    /***
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param code  值
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static DataType fromCode(Integer code) {
        return stringToEnum.get(String.valueOf(code));
    }
    
}
