
package com.newtouch.lion.adpater.exception;

/**
 * 接口异常代码定义<br> 
 * 定义接口连接错误、连接成功、
 *
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public enum ErrorCode implements Code<String, String>{
    TIMEOUT("A000001","接口连接超时"),
    HTTP_PROTOCOL("A000002","接口连接HTTP Client 连接协议出错"),
    HTTP_IO("A000003","接口连接 HTTP Client IO失败"),
    CLASS_NOTFOUND("A000004","接口请求初化失败"),
    CLASS_NOTFOUND_FORAMTTER("A000005","接口请解析类初化失败"),
    XML_REDER("A000006","xml文件读取出错")
    ;
    /**异常CODE*/
    private String code;
    /**异常描述*/
    private String  message;
    /***
     * 默认构造函数
     * @param code 代码
     * @param message  描述
     */
    private ErrorCode(String code,String message){
        this.code=code;
        this.message=message;
    }
    
    /**
     * {@inheritDoc}
     */
    public String code() {
        return this.code;
    }

    /**
     * {@inheritDoc}
     */
    public String message() {
        return this.message;
    }
    
   
    
}
