/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: GitLabTest.java 9552 2015年3月11日 下午3:48:45 WangLijun$
 */
package com.newtouch.lion.mgt;

import java.io.IOException;
import java.net.ConnectException;
import java.util.List;
import java.util.UUID;

import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.GitlabProject;
import org.gitlab.api.models.GitlabSession;
import org.gitlab.api.models.GitlabUser;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class GitLabTest {
	
	 public static final Logger logger=LoggerFactory.getLogger(GitLabTest.class);
	
	 GitlabAPI api;

	private static final String TEST_URL = System.getProperty("TEST_URL",
			"http://git.newtouchone.com/");
	private static final String TEST_TOKEN = System.getProperty("TEST_TOKEN","rizspvNxMjmJU6xT7Xe1");

	String rand = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
	
	 
	
	@Test
	public void createProject(){
		
		try {
			 List<GitlabProject>  projects=GitlabAPI.connect(TEST_URL, TEST_TOKEN).getAllProjects();
			 for(GitlabProject  gitlabProject:projects){
				 System.out.println(gitlabProject.getSshUrl());
			 }
		 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   logger.info("------------");
	}
}
