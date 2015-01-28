package com.newtouch.lion.adpater.http;

import java.io.InputStream;

import org.apache.http.Header;

/**
 * HTTP 请求响应信息<br> 
 * 〈功能详细描述〉
 *
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class AdapterHttpResponse {
    /**
     * 返回中的Header信息
     */
    private Header[] responseHeaders;

    /**
     * String类型的result
     */
    private String stringResult;
    /**
     * 返回流结果
     */
    private InputStream streamResult;
    /**
     * 机密公钥
     */
    private String key;
    
    /**
     * btye类型的result
     */
    private byte[] byteResult;

    /**
     * @return the responseHeaders
     */
    public Header[] getResponseHeaders() {
        return responseHeaders;
    }

    /**
     * @param responseHeaders the responseHeaders to set
     */
    public void setResponseHeaders(Header[] responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    /**
     * @return the stringResult
     */
    public String getStringResult() {
        return stringResult;
    }

    /**
     * @param stringResult the stringResult to set
     */
    public void setStringResult(String stringResult) {
        this.stringResult = stringResult;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the byteResult
     */
    public byte[] getByteResult() {
        return byteResult;
    }

    /**
     * @param byteResult the byteResult to set
     */
    public void setByteResult(byte[] byteResult) {
        this.byteResult = byteResult;
    }

    /**
     * @return the streamResult
     */
    public InputStream getStreamResult() {
        return streamResult;
    }

    /**
     * @param streamResult the streamResult to set
     */
    public void setStreamResult(InputStream streamResult) {
        this.streamResult = streamResult;
    }
}
