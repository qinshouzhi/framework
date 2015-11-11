/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: GitLabService.java 9552 2015年3月17日 下午1:37:43 WangLijun$
*/
package com.newtouch.lion.mgt.gitlab; 

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.newtouch.lion.mgt.model.PomModel;

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
public interface GitLabService {
	
	public String createPomXML(PomModel pomModel);
	
	public Integer currentUserId() throws IOException;
	
	public Integer createProject(Integer userId,String  projectName) throws IOException;
	
	public void addPomFile(Integer projectId,String pom) throws ClientProtocolException, IOException;
}

	