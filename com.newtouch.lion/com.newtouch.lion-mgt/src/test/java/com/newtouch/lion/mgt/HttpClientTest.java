/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: HttpClientTest.java 9552 2015年3月16日 下午3:21:11 WangLijun$
 */
package com.newtouch.lion.mgt;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

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
public class HttpClientTest {
	public static void main(String args[]) {
		
		StringBuffer  sb=new StringBuffer();
		sb.append("http://git.newtouchone.com");
		sb.append("/api/v3/projects?");
		sb.append("private_token=");
		sb.append("4rdgT6qMBeg5xzBWsjPA");
		// 创建HttpClientBuilder
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		// HttpClient
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

		HttpGet httpGet = new HttpGet(sb.toString());
		System.out.println(httpGet.getRequestLine());
		try {
			// 执行get请求
			HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
			// 获取响应消息实体
			HttpEntity entity = httpResponse.getEntity();
			// 响应状态
			System.out.println("status:" + httpResponse.getStatusLine());
			// 判断响应实体是否为空
			if (entity != null) {
				System.out.println("contentEncoding:"
						+ entity.getContentEncoding());
				System.out.println("response content:"
						+ EntityUtils.toString(entity));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}
}
