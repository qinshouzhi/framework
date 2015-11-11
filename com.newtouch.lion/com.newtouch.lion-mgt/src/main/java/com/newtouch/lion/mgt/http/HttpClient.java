/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: HttpClient.java 9552 2015年3月17日 下午2:08:33 WangLijun$
*/
package com.newtouch.lion.mgt.http; 

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * <p>
 * Title: 
 * </p>
 * <p>
 * Description: 
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class HttpClient {
	
	public static HttpResponse execute(String url,List<NameValuePair> params) throws ClientProtocolException, IOException{
		//创建HttpClientBuilder
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		//HttpClient
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(RequestConfig.DEFAULT);
		
		UrlEncodedFormEntity entity= new UrlEncodedFormEntity(params, "UTF-8");
		httpPost.setEntity(entity);
	
		HttpResponse httpResponse= closeableHttpClient.execute(httpPost);
		return httpResponse;
	}
	
}

	