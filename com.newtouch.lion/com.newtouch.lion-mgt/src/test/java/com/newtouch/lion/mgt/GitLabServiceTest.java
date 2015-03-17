/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: GitLabServiceTest.java 9552 2015年3月17日 下午3:20:57 WangLijun$
*/
package com.newtouch.lion.mgt; 

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.newtouch.lion.mgt.gitlab.GitLabService;
import com.newtouch.lion.mgt.model.Dependency;
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
public class GitLabServiceTest extends AllTests{
	String pomTemplate="pom_template.xml";
	PomModel pomModel;
	@Autowired
	private GitLabService gitLabService;
	
	@Before
	public void initPom(){
	
		pomModel = new PomModel();
		pomModel.setArtifactId("com.newtouch.lion-demo1");
		pomModel.setGroupId("com.newtouch.lion");
		pomModel.setVersion("0.0.1-SNAPSHOT");
		pomModel.setUrl("http:/www.newtouchone.com/");
		pomModel.setName("com.newtouch.lion-demo1");
	 
		//Pom继承
		Dependency parent=new Dependency();
		parent.setArtifactId("com.newtouch.lion-parent");
		parent.setGroupId("com.newtouch.lion");
		parent.setVersion("0.0.2-SNAPSHOT");
		pomModel.setParent(parent);
		
		//列表
		List<Dependency> dependencies=new ArrayList<Dependency>();
		//common 组件
		Dependency common=new Dependency();
		common.setArtifactId("com.newtouch.lion-common");
		common.setGroupId("com.newtouch.lion");
		common.setVersion("0.0.2-SNAPSHOT");
		dependencies.add(common);
		//API组件
		Dependency api=new Dependency();
		api.setArtifactId("com.newtouch.lion-api");
		api.setGroupId("com.newtouch.lion");
		api.setVersion("0.0.2-SNAPSHOT");
		dependencies.add(api);
		
		//Data
		Dependency data=new Dependency();
		data.setArtifactId("com.newtouch.lion-data");
		data.setGroupId("com.newtouch.lion");
		data.setVersion("0.0.2-SNAPSHOT");
		dependencies.add(data);
		
		//service
		Dependency service=new Dependency();
		service.setArtifactId("com.newtouch.lion-service");
		service.setGroupId("com.newtouch.lion");
		service.setVersion("0.0.2-SNAPSHOT");
		dependencies.add(service);
		
		//service
		Dependency web=new Dependency();
		web.setArtifactId("com.newtouch.lion-service");
		web.setGroupId("com.newtouch.lion");
		web.setVersion("0.0.2-SNAPSHOT");
		dependencies.add(web);
		//显示依赖
		pomModel.setDependencies(dependencies);
	}
	
	@Test
	public void createProject() throws IOException{
		String xml=gitLabService.createPomXML(pomModel);
		Integer  userId=gitLabService.currentUserId();
		Integer  projectId=gitLabService.createProject(userId, pomModel.getArtifactId());
		if(projectId!=null){
			System.out.println(projectId);
			gitLabService.addPomFile(projectId,xml);
		}
	}
}

	