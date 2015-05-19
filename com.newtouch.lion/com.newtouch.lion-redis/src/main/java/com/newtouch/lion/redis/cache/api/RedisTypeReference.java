/*
 * Copyright (C), 2013-2014, 上海汽车集团股份有限公司
 * FileName: TypeReference.java
 * Author:   wanglijun
 * Date:     2014年8月4日 下午6:13:31
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.newtouch.lion.redis.cache.api;




/**
 * Redis 引用类型<br> 
 * 〈功能详细描述〉
 *
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface RedisTypeReference {
    @SuppressWarnings("rawtypes")
    public com.alibaba.fastjson.TypeReference typeReference();
}
