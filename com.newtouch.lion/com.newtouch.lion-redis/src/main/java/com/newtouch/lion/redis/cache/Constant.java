/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RedisCacheableMethodAdvice.java 9552 2015年4月11日 下午2:30:25 WangLijun$
*/
package com.newtouch.lion.redis.cache;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  Redis缓存常量类<br> 
 * Redis缓存常量类
 *
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public final class Constant {
    /**参数值为空时,替换为:"_"*/
    public static final String PARAM_EMPTY="_";
    /**KEY参数解析正则表达式是否包含参数，如  userId_:${#userId} 解析结果为:#userId*/
    public static final String KEY_PARESE_PATTERN="\\{|\\}";
    /**KEY是否包含模糊匹配"*"*/
    public static final String KEY_LIKE_PATTERN="\\*";
    /**KEY正则表达式， 获取缓存key中的参数值*/
    public static final String KEY_PARESE_PARAM_PATTERN="(?<=\\{)[^\\}]+";
    
    public static void main(String[] args) {
        Pattern pattern=Pattern.compile(Constant.KEY_LIKE_PATTERN);
        String  source="DOP:USER:*";
        Matcher matcher =pattern.matcher(source);
        if(matcher.find()){
            System.out.println("DDD");
        }else{
            System.out.println("DDD1");
        }
    }
    
}
