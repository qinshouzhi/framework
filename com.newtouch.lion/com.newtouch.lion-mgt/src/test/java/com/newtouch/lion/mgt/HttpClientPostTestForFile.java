/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: HttpClientPostTest.java 9552 2015年3月16日 下午3:30:25 WangLijun$
 */
package com.newtouch.lion.mgt;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
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
public class HttpClientPostTestForFile {
	public static void main(String[] args) {

		// 创建HttpClientBuilder
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		// HttpClient
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		StringBuffer  sb=new StringBuffer();
		sb.append("http://git.newtouchone.com");
		sb.append("/api/v3/projects/11/repository/files");
		sb.append("?private_token=");
		sb.append("4rdgT6qMBeg5xzBWsjPA");
		HttpPost httpPost = new HttpPost(sb.toString());
		httpPost.setConfig(RequestConfig.DEFAULT);
		 
		// 创建参数队列
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("file_path","/pom.xml"));
		formparams.add(new BasicNameValuePair("branch_name","master"));
		formparams.add(new BasicNameValuePair("content","pom.xml11"));
		formparams.add(new BasicNameValuePair("commit_message","master"));
		formparams.add(new BasicNameValuePair("encoding","text"));		 

		UrlEncodedFormEntity entity;
		try {
			entity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httpPost.setEntity(entity);

			HttpResponse httpResponse;
			// post请求
			httpResponse = closeableHttpClient.execute(httpPost);

			// getEntity()
			HttpEntity httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {
				// 打印响应内容
				System.out.println("response:"+ EntityUtils.toString(httpEntity, "UTF-8"));
			}
			// 释放资源
			closeableHttpClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
