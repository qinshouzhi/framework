 
package com.newtouch.lion.adpater.service;

import java.util.LinkedHashMap;

import com.newtouch.lion.adpater.command.AdCommand;
import com.newtouch.lion.adpater.command.AdapterCommand;
import com.newtouch.lion.adpater.exception.AdapterException;

/**
 * 接口适配器服务定义 <br> 
 * 〈功能详细描述〉
 *
 * @author wanglijun

 * @since 接口/模块版本] （可选）
 */
public interface AdapterService {
    /***
     * 
     * 功能描述:根据交易号、参数对象请求服务器
     * 〈功能详细描述〉
     *
     * @param stransno 请求交易号
     * @param params  请求参数
     * @return AdapterBaseCommand 响应对象
     * @throws AdapterException 适配器异常 

     * @since [产品/模块版本](可选)
     */
    public AdapterCommand sendCommand(String stransno,
            LinkedHashMap<Object, Object> params) throws AdapterException;
    
    /***
     * 
     * 功能描述: 根据交易号，请求对象调用接口<br>
     * 〈功能详细描述〉
     *
     * @param stransno
     * @param command
     * @return
     * @throws AdapterException

     * @since [产品/模块版本](可选)
     */
    public AdapterCommand sendCommand(String stransno, AdCommand command)
            throws AdapterException;
    
    //从网站获取相关数据
    public <T> T getDataFromAczWeb(Class<T> clazz);
}
