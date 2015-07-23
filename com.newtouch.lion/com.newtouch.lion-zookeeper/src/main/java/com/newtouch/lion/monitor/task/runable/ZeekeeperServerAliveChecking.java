/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ZeekeeperServerAliveChecking.java 9552 2015年5月29日 下午10:16:15 WangLijun$
*/
package com.newtouch.lion.monitor.task.runable; 

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.newtouch.lion.alarm.model.AlarmSettings;
import com.newtouch.lion.monitor.task.GlobalInstance;
import com.newtouch.lion.util.ObjectUtil;
import com.newtouch.lion.util.ServletUtil;
import com.newtouch.lion.zookeeper.model.Subscriber;
import com.newtouch.lion.zookeeper.model.ZooKeeperCluster;

/**
 * <p>
 * Title: Zookeeper节点服务器自检查
 * </p>
 * <p>
 * Description: Zookeeper自检查节点服务器是指对集群中每个IP所在ZK节点上的PATH: /WATCHMAN.MONITOR.ALIVE.CHECKING 定期进行三次如下流程 :<br>
 *  节点连接 - 数据发布 - 修改通知 - 获取数据 - 数据对比, 三次流程均成功视为该节点处于正常状态。<br>
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
public class ZeekeeperServerAliveChecking implements Runnable{
	/***
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger( ZeekeeperServerAliveChecking.class );

	/**Zookeeper Cluster 的信息*/
	private ZooKeeperCluster zooKeeperCluster;
	/**警告信息*/
	private AlarmSettings alarmSettings;
	 

	/**
	 * @param zooKeeperCluster
	 * @param alarmSettings
	 */
	public ZeekeeperServerAliveChecking(ZooKeeperCluster zooKeeperCluster,
			AlarmSettings alarmSettings) {
		this.zooKeeperCluster = zooKeeperCluster;
		this.alarmSettings = alarmSettings;
	}





	@Override
	public void run() {
		
	}
	
	
	private void checkAlive(){
		//判断ZookeeperCluster是否空，集群地址是否为空
		if(ObjectUtil.isBlank(this.zooKeeperCluster,this.alarmSettings)&&CollectionUtils.isEmpty(this.zooKeeperCluster.getServerList())){
			return;
		}
		//Zookeeper集群地址
		for(String server:this.zooKeeperCluster.getServerList()){
			if(StringUtils.isEmpty(server)){
				continue;
			}
			
			//解析IP地址
			String zookeeperIp=ServletUtil.paraseIpAndPortFromServer(server)[0];
			if(0==GlobalInstance.getZooKeeperStatusType(zookeeperIp)){
				logger.info("{} is checking. no need to check.",zookeeperIp);
				continue;
			}
			
			//将放入到要检查，标识为正在检查
			GlobalInstance.putZooKeeperStatusType(zookeeperIp,0);
			logger.info("{} not check,start to check now time...",zookeeperIp);
			
			String ip=server.split(":")[0];
			
			 String emailList=this.alarmSettings.getEmailList();
			 String phoneList=this.alarmSettings.getPhoneList();
			 
			 if(StringUtils.isNotEmpty(this.alarmSettings.getMaxDelayOfCheck())){
				 Subscriber sub=null;
			 }
			 
			 
			
		}
		
	}
}

	