package com.newtouch.lion.adpater.http;

import com.newtouch.lion.adpater.constant.Code;

/**
 * HTTP 请求头信息<br> 
 * 〈功能详细描述〉
 *
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public enum ContentType implements Code<String> {
    SOAP_XML_UTF8("application/soap+xml;charset=UTF-8"),
    DEFAULT_FORM("application/x-www-form-urlencoded"),
    SOAP_XML_GBK("application/soap+xml;charset=GBK");
    ;
    /**请求头类型*/
    private String code;
    
    private ContentType(String code){
        this.code=code;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String code() {
        return this.code;
    }
}
