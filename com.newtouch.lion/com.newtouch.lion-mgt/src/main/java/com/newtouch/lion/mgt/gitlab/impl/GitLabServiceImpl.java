/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: GitLabServiceImpl.java 9552 2015年3月17日 下午1:38:12 WangLijun$
*/
package com.newtouch.lion.mgt.gitlab.impl; 

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.GitlabProject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newtouch.lion.mgt.freemarker.MessageTempleteManager;
import com.newtouch.lion.mgt.gitlab.GitLabService;
import com.newtouch.lion.mgt.http.HttpClient;
import com.newtouch.lion.mgt.model.PomModel;

import freemarker.template.TemplateException;

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
public class GitLabServiceImpl  implements GitLabService{
	
	private  final Logger logger=LoggerFactory.getLogger(GitLabServiceImpl.class);
	
	public static final ObjectMapper MAPPER = new ObjectMapper().configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	
	private GitlabAPI api;
	/***
	 * 私钥
	 */
	private String privateToken;
	/**URL*/
	private String gitLabUrl;
	
	/**
	 * @return the privateToken
	 */
	public String getPrivateToken() {
		return privateToken;
	}
	/**
	 * @param privateToken the privateToken to set
	 */
	public void setPrivateToken(String privateToken) {
		this.privateToken = privateToken;
	}
	/**
	 * @return the gitLabUrl
	 */
	public String getGitLabUrl() {
		return gitLabUrl;
	}
	/**
	 * @param gitLabUrl the gitLabUrl to set
	 */
	public void setGitLabUrl(String gitLabUrl) {
		this.gitLabUrl = gitLabUrl;
	}
	@Override
	public String createPomXML(PomModel pomModel) {
		String pomTemplate="pom_template.xml";
	    try {
			return MessageTempleteManager.process(pomTemplate, pomModel);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	    return StringUtils.EMPTY;
	}
	/* (non-Javadoc)
	 * @see com.newtouch.lion.mgt.gitlab.GitLabService#currentUserId()
	 */
	@Override
	public Integer currentUserId() throws IOException {
		api = GitlabAPI.connect(this.getGitLabUrl(), this.getPrivateToken());
		return api.getCurrentSession().getId();
	}
	
	
	public Integer createProject(Integer userId,String  projectName) throws IOException{
		StringBuffer  sb=new StringBuffer();
		sb.append(this.gitLabUrl);
		sb.append("/api/v3/projects/user/");
		sb.append(String.valueOf(this.currentUserId()));
		sb.append("?");
;		sb.append("private_token=");
		sb.append(this.getPrivateToken());
		System.out.println(sb.toString());
		// 创建参数队列
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		//params.add(new BasicNameValuePair("user_id",String.valueOf(userId)));
		params.add(new BasicNameValuePair("name",projectName));
		HttpResponse response=HttpClient.execute(sb.toString(),params);
		System.out.println(response.getStatusLine());
		if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
			
		}else{
			System.out.println(response.getStatusLine());
		}
		String data=EntityUtils.toString(response.getEntity(), "UTF-8");
		System.out.println(data);
		return  MAPPER.readValue(data,GitlabProject.class).getId();
	}
	
	
	public void addPomFile(Integer projectId,String pom) throws ClientProtocolException, IOException{
		StringBuffer  sb=new StringBuffer();
		sb.append("http://git.newtouchone.com");
		sb.append("/api/v3/projects/");
		sb.append(String.valueOf(projectId));
		sb.append("/repository/files");
		sb.append("?private_token=");
		sb.append("4rdgT6qMBeg5xzBWsjPA");		
		 
		// 创建参数队列
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("file_path","/pom.xml"));
		params.add(new BasicNameValuePair("branch_name","master"));
		params.add(new BasicNameValuePair("content",pom));
		params.add(new BasicNameValuePair("commit_message","init proejct"));
		params.add(new BasicNameValuePair("encoding","text"));
		HttpResponse response=HttpClient.execute(sb.toString(),params);
		String data=EntityUtils.toString(response.getEntity(), "UTF-8");
		logger.info("data:{}",data);
	}
}

	