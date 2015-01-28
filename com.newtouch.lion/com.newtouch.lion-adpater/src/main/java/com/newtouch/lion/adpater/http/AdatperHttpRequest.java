package com.newtouch.lion.adpater.http;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;

import com.newtouch.lion.adpater.constant.Charset;

/**
 * HttpRequest 请求对象<br>
 * HttpRequest 请求对象
 * 
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class AdatperHttpRequest {

    /**
     * 请求的URL地址
     */
    private String address;
    /**请求Header 类型*/
    private ContentType contentType;
    /**响应类型*/
    private  ResultType resultType=ResultType.STRING;
    /**
     * 默认的请求方式
     */
    private String method =HttpPost.METHOD_NAME;
    /**超时时间*/
    private int timeout = 30;
    /**连接超时间*/
    private int connectionTimeout =30;
    /**
     * Post方式请求时组装好的参数值对
     */
    private NameValuePair[] parameters;

    /**
     * Get方式请求时对应的参数
     */
    private String queryString ;

    /**
     * 默认的请求编码方式
     */
    private String charset =Charset.UFT8.code();

    /**
     * 请求发起方的ip地址
     */
    private String clientIP;
    /**默认构造函数*/
    public AdatperHttpRequest() {
        super();
    }
    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }
    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * @return the method
     */
    public String getMethod() {
        return method;
    }
    /**
     * @param method the method to set
     */
    public void setMethod(String method) {
        this.method = method;
    }
    /**
     * @return the timeout
     */
    public int getTimeout() {
        return timeout;
    }
    /**
     * @param timeout the timeout to set
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
    /**
     * @return the connectionTimeout
     */
    public int getConnectionTimeout() {
        return connectionTimeout;
    }
    /**
     * @param connectionTimeout the connectionTimeout to set
     */
    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }
    /**
     * @return the parameters
     */
    public NameValuePair[] getParameters() {
        return parameters;
    }
    /**
     * @param parameters the parameters to set
     */
    public void setParameters(NameValuePair[] parameters) {
        this.parameters = parameters;
    }
    /**
     * @return the queryString
     */
    public String getQueryString() {
        return queryString;
    }
    /**
     * @param queryString the queryString to set
     */
    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }
    /**
     * @return the charset
     */
    public String getCharset() {
        return charset;
    }
    /**
     * @param charset the charset to set
     */
    public void setCharset(String charset) {
        this.charset = charset;
    }
    /**
     * @return the clientIP
     */
    public String getClientIP() {
        return clientIP;
    }
    /**
     * @param clientIP the clientIP to set
     */
    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }
    /**
     * @return the contentType
     */
    public ContentType getContentType() {
        return contentType;
    }
    /**
     * @param contentType the contentType to set
     */
    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }
    /**
     * @return the resultType
     */
    public ResultType getResultType() {
        return resultType;
    }
    /**
     * @param resultType the resultType to set
     */
    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }
    
    
    
}
