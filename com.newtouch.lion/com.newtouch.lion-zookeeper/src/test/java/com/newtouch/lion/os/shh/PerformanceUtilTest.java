/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: PerformanceUtil.java 9552 2015年5月28日 上午11:12:52 WangLijun$
*/
package com.newtouch.lion.os.shh; 

import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.newtouch.lion.os.model.Performance;
import com.newtouch.lion.os.model.ReleaseInfo;
import com.newtouch.lion.os.ssh.JschConfig;
import com.newtouch.lion.os.ssh.PerformanceUtil;

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
public class PerformanceUtilTest {
	
	@Test
	public void releaseCentOS(){
		JschConfig config=new JschConfig("192.168.205.36","root", "newtouch.cn",22);
		ReleaseInfo releaseInfo=PerformanceUtil.getReleaseInfo(config);
		System.out.println(releaseInfo.toString());
	}
	
	@Test
	public void releaseUbuntu(){
		JschConfig config=new JschConfig("192.168.203.205","root", "RDC",22);
		ReleaseInfo releaseInfo=PerformanceUtil.getReleaseInfo(config);
		System.out.println(releaseInfo.toString());
	}
	
	@Test
	public void Performance(){
		//JschConfig config=new JschConfig("192.168.1.62","hadoop", "Accp1234",22); CentOS6.5 
		JschConfig config=new JschConfig("192.168.205.36","root", "newtouch.cn",22); //CentOS7.0
		Performance performance=PerformanceUtil.getPerformance(config);
		System.out.println(performance.toString());
	}
	
	@Test
	public void getDiskUsage(){
		//JschConfig config=new JschConfig("192.168.1.62","hadoop", "Accp1234",22); //CentOS6.5 
		JschConfig config=new JschConfig("192.168.205.36","root", "newtouch.cn",22); //CentOS7.0
		Map<String, String> diskUsages=PerformanceUtil.getDiskUsage(config);
		for(Entry<String,String>  entry:diskUsages.entrySet()){
			System.out.println(entry.getKey()+" "+entry.getValue());
		}
	}
}

	