package com.newtouch.lion.adpater.http;

/**
 *  HTTPClient连接适配器  <br> 
 * 〈功能详细描述〉
 *
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface AdapterHttpHandler {
 
    /***
    * 
    * 功能描述: HTTPRequest请求
    * 〈功能详细描述〉
    *
    * @param request
    * @return
    * @see [相关类/方法](可选)
    * @since [产品/模块版本](可选)
    */
    public  AdapterHttpResponse   execute(AdatperHttpRequest request); 
}
