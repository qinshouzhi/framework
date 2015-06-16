/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RedisCacheableMethodAdvice.java 9552 2015年4月11日 下午2:30:25 WangLijun$
*/
package com.newtouch.lion.redis.cache.api;

/**
 * 方法信息模型<br> 
 * 〈功能详细描述〉
 *
 * @author wanglijun
 */
public class MethodParameter {
    /**参数名称*/
    private String name;
    /**参数值*/
    private Object value;
    /**参数类型*/
    private Class<?>  type;
    /**default*/
    public MethodParameter(){
        super();
    }
    

    /**
     * @param name  参数名称
     * @param value 参数值
     * @param type  参数类型
     */
    public MethodParameter(String name, Object value, Class<?> type) {
        super();
        this.name = name;
        this.value = value;
        this.type = type;
    }


    /**
     * @return the *参数名称
     */
    public String getName() {
        return name;
    }


    /**
     * @param name *参数名称
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return 参数值
     */
    public Object getValue() {
        return value;
    }


    /**
     * @param value 参数值
     */
    public void setValue(Object value) {
        this.value = value;
    }


    /**
     * @return 参数类型
     */
    public Class<?> getType() {
        return type;
    }


    /**
     * @param 参数类型
     */
    public void setType(Class<?> type) {
        this.type = type;
    }
    
    

}
