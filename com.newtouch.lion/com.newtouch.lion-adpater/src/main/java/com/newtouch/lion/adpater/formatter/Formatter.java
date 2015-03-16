
package com.newtouch.lion.adpater.formatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newtouch.lion.adpater.command.AdCommand;
import com.newtouch.lion.adpater.command.AdapterCommand;
import com.newtouch.lion.adpater.exception.AdapterException;

/**
 * 请求体和响应体解析<br> 
 * 〈功能详细描述〉
 *
 * @author wanglijun
 */
public abstract class Formatter {
	
    protected  final Logger logger=LoggerFactory.getLogger(super.getClass());
    /**返回对象*/
    protected AdapterCommand  adapterCommand;
    /***
     * 默认构建函数
     */
    public Formatter() {
        super();
        this.adapterCommand = new AdapterCommand();
    }
    /***
     * 
     * 功能描述: 构建请求报文
     * 〈功能详细描述〉
     *
     * @param command
     * @return Object
     * @throws AdapterException
     */
    public abstract Object formateRequest(AdCommand command) throws AdapterException;
    /***
     * 
     * 功能描述: 解析响应报文
     * 〈功能详细描述〉
     *
     * @param receiveObj
     * @return AdapterCommand
     * @throws AdapterException
     */
    public abstract AdapterCommand formateResponse(Object receiveObj) throws AdapterException;

    
}
