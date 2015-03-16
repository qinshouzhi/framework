
package com.newtouch.lion.adpater.connector;

import com.newtouch.lion.adpater.exception.AdapterException;

/**
 *  接口连接声明类 <br> 
 *  接口连接声明类
 *
 * @author wanglijun

 * @since [产品/模块版本] （可选）
 */
public interface Connector {
    /***
     * 
     * 功能描述: 发送请求并收响应对象
     * 〈功能详细描述〉
     *
     * @param object 请求对象
     * @return   响应对象 
     * @throws AdapterException 接口适器异常

     * @since [产品/模块版本](可选)
     */
    public  Object sendRequestResponse(Object object) throws AdapterException;
}
