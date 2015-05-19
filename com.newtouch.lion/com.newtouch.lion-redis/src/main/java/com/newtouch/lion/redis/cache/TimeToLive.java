/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: TimeToLive.java 9552 2013-4-7 上午11:01:56 WangLijun$
 */
package com.newtouch.lion.redis.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 有效时间设置 <br> 
 * 〈功能详细描述〉
 *
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public enum TimeToLive  implements CacheEnum{
    
    /** 默认. */
    DEFAULT(5*60, "默认为5分钟"),
    
    /**持久化－永久 24不时 */
    PERSISTENCE(-1, "持久化-永久"),
    /**半小时*/
    QUARTER(15*60,"一刻钟"),
    /** 一个小时 */
    HOUR(60*60, "一小时"),
    /**两小时*/
    THOUR(2*60*60,"两小时"),
    /**30秒*/
    HALFAMINUTE(30,"30秒"),
    /**24小时*/
    DAY(24*60*60,"24小时"),
    /**30天*/
    MONTH(30*24*60*60,"24小时")
    ;
    

    /**
     * 构造函数.
     *
     * @param code 枚举代码
     * @param display 枚举显示
     */
    private TimeToLive(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    /** 枚举代码. */
    private Integer code;
    
    /** 枚举显示. */
    private String desc;
    @Override
    public Integer code() {
        return this.code;
    }

    @Override
    public String desc() {
        return this.desc;
    }
    
    private static Map<String, TimeToLive> stringToEnum = new HashMap<String, TimeToLive>();
    static {
        // Initialize map from constant name to enum constant
        for (TimeToLive value : values()) {
            stringToEnum.put(String.valueOf(value.code()), value);
        }
    }
    
    public static TimeToLive fromCode(int code) {
        return stringToEnum.get(String.valueOf(code));
    }
}
