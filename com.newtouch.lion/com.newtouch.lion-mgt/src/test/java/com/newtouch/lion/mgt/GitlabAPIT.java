/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: GitlabAPIT.java 9552 2015年3月12日 上午10:50:13 WangLijun$
 */
package com.newtouch.lion.mgt;

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

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.ConnectException;
import java.util.List;
import java.util.UUID;

import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.GitlabMergeRequest;
import org.gitlab.api.models.GitlabProject;
import org.gitlab.api.models.GitlabRepositoryFile;
import org.gitlab.api.models.GitlabUser;
import org.junit.Before;
import org.junit.Test;

public class GitlabAPIT {

	GitlabAPI api;

	private static final String TEST_URL = System.getProperty("TEST_URL",
			"http://git.newtouchone.com");
	private static final String TEST_TOKEN = System.getProperty("TEST_TOKEN",
			"rizspvNxMjmJU6xT7Xe1");

	String rand = UUID.randomUUID().toString().replace("-", "").substring(0, 8);

	@Before
	public void setup() throws IOException {
		api = GitlabAPI.connect(TEST_URL, TEST_TOKEN);
		try {
			api.dispatch().with("login", "INVALID").with("password", rand)
					.to("session", GitlabUser.class);
		} catch (ConnectException e) {
			 e.printStackTrace();
		} catch (IOException e) {
			final String message = e.getMessage();
			if (!message.equals("{\"message\":\"401 Unauthorized\"}")) {
				throw new AssertionError("Expected an unauthorized message", e);
			}
		}
	}

	
	public void testConnect() throws IOException {
		assertEquals(GitlabAPI.class, api.getClass());
	}
	
	

	public void createProject() throws IOException{
	    Integer userId=api.getCurrentSession().getId();
	    List<GitlabProject> projects=api.getProjects();
	    for(GitlabProject project:projects){
	    	System.out.println(project.getName()+"  id:"+project.getId());
	    	List<GitlabMergeRequest>  gitlabMergeRequests=api.getAllMergeRequests(project);
	    	for(GitlabMergeRequest gitlabMergeRequest:gitlabMergeRequests){
	    		System.out.println(gitlabMergeRequest.getDescription());
	    	}
	    }
	   
	 
	    //GitlabProject project=api.createUserProject(userId, "test_demo");
	    //System.out.println(project.getId());
	} 
	@Test
	public void  getGitlabRepositoryFile(){
		List<GitlabRepositoryFile>  gitlabRepositoryFiles=api.gitLabRepositoryFiles(23);
		for(GitlabRepositoryFile gitlabMergeRequest:gitlabRepositoryFiles){
    		System.out.println(gitlabMergeRequest.getFileName()+" "+gitlabMergeRequest.getFilePath());
    	}
	}

	private String randVal(String postfix) {
		return rand + "-" + postfix;
	}
}
