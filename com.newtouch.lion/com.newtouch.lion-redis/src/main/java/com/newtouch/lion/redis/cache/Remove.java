/*
 * Copyright (C), 2013-2014, 上海汽车集团股份有限公司
 * FileName: Cleared.java
 * Author:   wanglijun
 * Date:     2014年7月29日 下午6:34:30
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.newtouch.lion.redis.cache;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 移除缓存信息<br> 
 * 根据缓存KEY信息,移除KEY信息
 * DOP:*表示模糊匹配
 * DOP:USER_ID_{#userId}
 * DOP:ST
 *
 * @author wanglijun
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Remove {
    /**缓存KEY*/
    String key();
}
