/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: Test.java 9552 2015年3月11日 上午11:10:11 WangLijun$
 */
package com.newtouch.lion.mgt.freemarker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.newtouch.lion.mgt.model.Dependency;
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
public class Test {
	public static void main(String[] args) throws IOException, TemplateException {
		String outPath="D:/applog/lion/test_demo";
		String pomTemplate="pom_template.xml";
		PomModel pomModel = new PomModel();
		pomModel.setArtifactId("com.newtouch.lion-demo");
		pomModel.setGroupId("com.newtouch.lion");
		pomModel.setVersion("0.0.1");
		pomModel.setUrl("http:/www.newtouch.one/");
		pomModel.setName("com.newtouch.lion-mgt");
	 
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
		
		MessageTempleteManager.process(pomTemplate, pomModel,outPath);

	}
}
