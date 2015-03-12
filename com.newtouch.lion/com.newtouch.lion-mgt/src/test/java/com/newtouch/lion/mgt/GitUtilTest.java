/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: GitUtilTest.java 9552 2015年3月11日 下午1:44:26 WangLijun$
*/
package com.newtouch.lion.mgt; 

import java.util.ArrayList;
import java.util.List;

import com.newtouch.lion.mgt.git.GitUtil;

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
public class GitUtilTest {
	
	private static final String path="D:/applog/lion/test_demo";
	
	public static void main(String[] args) throws Exception {
		List<String> files=new ArrayList<String>();
		files.add("pom.xml");
		GitUtil.commitToGitRepository(path,files,"初始化");
	}
}

	