/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: DemoServiceTest.java 9552 2013-3-22 上午9:20:28 WangLijun$
 */
package com.newtouch.lion.data.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.newtouch.lion.data.AllTest;
import com.newtouch.lion.model.system.DemoModel;
import com.newtouch.lion.service.system.DemoService;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class DemoServiceTest extends AllTest {

	@Autowired
	private DemoService demoService;

	//@Test
	public void doFindByParentIdTest() {
		List<DemoModel> demoModels = demoService.doFindByParentId();
		DemoModel demoModel=demoModels.get(0);
	 
		logger.info("id:{},nameZh:{},demoModel.getDemoModels().size():{}",demoModel.getId(),demoModel.getNameZh(),demoModel.getDemoModels().size());
		if(demoModel.getDemoModel()!=null){
			logger.info("id:{},name:{}",demoModel.getDemoModel().getId(),demoModel.getDemoModel().getNameZh());
		}
		logger.info("demoModels.size():",demoModels.size());

	}

	 @Test
	public void doCreateTest() {
		DemoModel demoModel1 = new DemoModel();
		demoModel1.setNameZh("HP1");

		DemoModel demoModel2 = new DemoModel();
		demoModel2.setNameZh("HP2");
		demoModel2.setDemoModel(demoModel1);

		DemoModel demoModel3 = new DemoModel();
		demoModel3.setNameZh("HP3");
		demoModel3.setDemoModel(demoModel1);

		Set<DemoModel> demoModels = new HashSet<DemoModel>();
		demoModels.add(demoModel2);
		demoModels.add(demoModel3);
		demoModel1.setDemoModels(demoModels);

		this.demoService.doCreateDemoModel(demoModel1);
	}

}
