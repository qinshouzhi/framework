 
package com.newtouch.lion.adpater.http.impl;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.newtouch.lion.adpater.constant.Charset;
import com.newtouch.lion.adpater.exception.AdapterException;
import com.newtouch.lion.adpater.exception.ErrorCode;
import com.newtouch.lion.adpater.http.AdapterHttpHandler;
import com.newtouch.lion.adpater.http.AdapterHttpResponse;
import com.newtouch.lion.adpater.http.AdatperHttpRequest;
import com.newtouch.lion.adpater.http.ResultType;

/**
 * Http Client连接实类<br>
 * Http Client连接实类
 * 
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class AdapterHttpHandlerImpl implements AdapterHttpHandler {

    /** 连接超时时间，由bean factory设置，缺省为8秒钟 */
    private int connectionTimeout = 8000;
    /** 回应超时时间, 由bean factory设置，缺省为30秒钟 */
    private int socketTimeOut = 30000;
    /** 请求连接超时 默认为：5秒 */
    private int connectionRequestTimeout = 5 * 1000;

    /** 最大连接数 */
    private int maxTotal = 80;

    /** 每个路由最大连接数 */
    private int maxRouteConnection = 80;
    /**连接空闲的时间 默认为：300秒 */
    private int idleTimeout=300;

    /** 连接池 */
    private static PoolingHttpClientConnectionManager clientConnectionManager;

    /**默认构造函数*/
    private AdapterHttpHandlerImpl() {
        clientConnectionManager = new PoolingHttpClientConnectionManager();
        clientConnectionManager.setMaxTotal(maxTotal);
        clientConnectionManager.setDefaultMaxPerRoute(maxRouteConnection);
        clientConnectionManager.closeIdleConnections(idleTimeout,TimeUnit.SECONDS);
    }

    @Override
    public AdapterHttpResponse execute(AdatperHttpRequest adatperHttpRequest) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create().setConnectionManager(clientConnectionManager);
        CloseableHttpClient httpClient = httpClientBuilder.build();
        
        HttpPost httpPost = new HttpPost(adatperHttpRequest.getAddress());
        httpPost.setConfig(this.requestConfig());
        httpPost.setHeader(HTTP.CONTENT_TYPE, adatperHttpRequest.getContentType().code());
        HttpEntity httpEntity = new StringEntity(adatperHttpRequest.getQueryString(), Charset.UFT8.code());
        httpPost.setEntity(httpEntity);
        AdapterHttpResponse adapterHttpResponse=null;
        try {
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                adapterHttpResponse=new AdapterHttpResponse();
                //返回字符串
                if (adatperHttpRequest.getResultType().equals(ResultType.STRING)) {
                    adapterHttpResponse.setStringResult(EntityUtils.toString(response.getEntity()));
                } else if (adatperHttpRequest.getResultType().equals(ResultType.BYTES)) {
                    //返回btye[] 数组
                    adapterHttpResponse.setByteResult(EntityUtils.toByteArray(response.getEntity()));
                } else if (adatperHttpRequest.getResultType().equals(ResultType.STREAM)){
                   //返回文件流
                    adapterHttpResponse.setStreamResult(response.getEntity().getContent());
                }
            }
        } catch (ClientProtocolException e) {
            throw new AdapterException(ErrorCode.HTTP_PROTOCOL,e);
        } catch (IOException e) {
            throw new AdapterException(ErrorCode.HTTP_IO,e);
        } finally {
            httpPost.releaseConnection();
        }
        return adapterHttpResponse;
    }
    
    /***
     * 
     * 功能描述:请求参数配置 <br>
     * 〈功能详细描述〉
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    protected RequestConfig requestConfig() {
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeOut)
                .setConnectTimeout(this.connectionTimeout).setConnectionRequestTimeout(connectionRequestTimeout).build();
        return requestConfig;
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
     * @return the socketTimeOut
     */
    public int getSocketTimeOut() {
        return socketTimeOut;
    }

    /**
     * @param socketTimeOut the socketTimeOut to set
     */
    public void setSocketTimeOut(int socketTimeOut) {
        this.socketTimeOut = socketTimeOut;
    }

    
    /**
     * @return the connectionRequestTimeout
     */
    public int getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    /**
     * @param connectionRequestTimeout the connectionRequestTimeout to set
     */
    public void setConnectionRequestTimeout(int connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    /**
     * @return the maxTotal
     */
    public int getMaxTotal() {
        return maxTotal;
    }

    /**
     * @param maxTotal the maxTotal to set
     */
    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    /**
     * @return the maxRouteConnection
     */
    public int getMaxRouteConnection() {
        return maxRouteConnection;
    }

    /**
     * @param maxRouteConnection the maxRouteConnection to set
     */
    public void setMaxRouteConnection(int maxRouteConnection) {
        this.maxRouteConnection = maxRouteConnection;
    }

    /**
     * @return the idleTimeout
     */
    public int getIdleTimeout() {
        return idleTimeout;
    }

    /**
     * @param idleTimeout the idleTimeout to set
     */
    public void setIdleTimeout(int idleTimeout) {
        this.idleTimeout = idleTimeout;
    }
    
    

}
