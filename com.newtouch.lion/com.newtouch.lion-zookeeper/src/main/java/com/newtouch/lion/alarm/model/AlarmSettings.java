package com.newtouch.lion.alarm.model;

/**
 * <p>
 * Title：预警信息配置
 * </p>
 * <p>
 * Description: 预警信息配置
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
public class AlarmSettings {
	/**监控集群ID*/
	private int clusterId;
	/**电话列表信息*/
	private String phoneList;
	/**收件人列表*/
	private String emailList;
	/**检查等待时间*/
	private String maxDelayOfCheck;
	/**最大CPU使用率*/
	private String maxCpuUsage;
	/**最大内存使用率*/
	private String maxMemoryUsage;
	/**操作系统最大负载*/
	private String maxLoad;
	/**设置最大连接*/
	private String maxConnectionPerIp;
	/**设置最监听接数*/
	private String maxWatchPerIp;
	/** 节点检测规则  */
	private String nodePathCheckRule;
	/**Zookeeper目录数据目录*/
	private String dataDir;
	/**Zookeeper目录：日志目录*/
	private String dataLogDir;
	/**设置的目录的最大使用率，如果超过这个阈值，会报警。*/
	private String maxDiskUsage;  
	/**检查次数*/
	private   int checkTime;
	public AlarmSettings() {
	}

	public AlarmSettings(int clusterId, String maxDelayOfCheck,
			String maxCpuUsage, String maxMemoryUsage, String maxLoad,
			String wangwangList, String phoneList, String emailList,
			String maxConnectionPerIp, String maxWatchPerIp, String dataDir,
			String dataLogDir, String maxDiskUsage, String nodePathCheckRule) {
		this.clusterId = clusterId;
		this.maxDelayOfCheck = maxDelayOfCheck;
		this.maxCpuUsage = maxCpuUsage;
		this.maxMemoryUsage = maxMemoryUsage;
		this.maxLoad = maxLoad;
		this.phoneList = phoneList;
		this.emailList = emailList;
		this.maxConnectionPerIp = maxConnectionPerIp;
		this.maxWatchPerIp = maxWatchPerIp;
		this.dataDir = dataDir;
		this.dataLogDir = dataLogDir;
		this.nodePathCheckRule = nodePathCheckRule;
		this.setMaxDiskUsage(maxDiskUsage);
	}

	public int getClusterId() {
		return clusterId;
	}

	public void setClusterId(int clusterId) {
		this.clusterId = clusterId;
	}

	public String getMaxDelayOfCheck() {
		return maxDelayOfCheck;
	}

	public void setMaxDelayOfCheck(String maxDelayOfCheck) {
		this.maxDelayOfCheck = maxDelayOfCheck;
	}

	/** 这里将返回30，即表达 30% */
	public String getMaxCpuUsage() {
		return maxCpuUsage;
	}

	/** 如果你想表达30%，那么这里传入30即可 */
	public void setMaxCpuUsage(String maxCpuUsage) {
		this.maxCpuUsage = maxCpuUsage;
	}

	public String getMaxMemoryUsage() {
		return maxMemoryUsage;
	}

	public void setMaxMemoryUsage(String maxMemoryUsage) {
		this.maxMemoryUsage = maxMemoryUsage;
	}

	public String getMaxLoad() {
		return maxLoad;
	}

	public void setMaxLoad(String maxLoad) {
		this.maxLoad = maxLoad;
	}

	public String getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(String phoneList) {
		this.phoneList = phoneList;
	}

	public String getEmailList() {
		return emailList;
	}

	public void setEmailList(String emailList) {
		this.emailList = emailList;
	}

	public void setMaxConnectionPerIp(String maxConnectionPerIp) {
		this.maxConnectionPerIp = maxConnectionPerIp;
	}

	public void setMaxWatchPerIp(String maxWatchPerIp) {
		this.maxWatchPerIp = maxWatchPerIp;
	}

	public String getMaxConnectionPerIp() {
		return maxConnectionPerIp;
	}

	public String getMaxWatchPerIp() {
		return maxWatchPerIp;
	}

	public String getDataDir() {
		return dataDir;
	}

	public void setDataDir(String dataDir) {
		this.dataDir = dataDir;
	}

	public String getDataLogDir() {
		return dataLogDir;
	}

	public void setDataLogDir(String dataLogDir) {
		this.dataLogDir = dataLogDir;
	}

	public String getMaxDiskUsage() {
		return maxDiskUsage;
	}

	public void setMaxDiskUsage(String maxDiskUsage) {
		this.maxDiskUsage = maxDiskUsage;
	}

	/**
	 * 这里会返回如下格式的内容：<br>
	 * [/:nileader,yinshi;/nileader:test]^{} 或<br>
	 * []^{ /:nileader,/nileader:test } <br>
	 * 分别表示：<br>
	 * "/"这个path下，只能够出现nileader和yinshi这两个节点，/nileader 这个path下，只能够出现test节点 或<br>
	 * "/"这个path下，不能够出现nileader这个节点，/nileader 这个path下，不能够出现test节点<br>
	 * 
	 * @return
	 */
	public String getNodePathCheckRule() {
		return nodePathCheckRule;
	}

	public void setNodePathCheckRule(String nodePathCheckRule) {
		this.nodePathCheckRule = nodePathCheckRule;
	}

	@Override
	public String toString() {
		return "AlarmSettings:[clusterId=" + clusterId + ", maxDelayOfCheck="
				+ maxDelayOfCheck + ", maxCpuUsage=" + maxCpuUsage
				+ ", maxMemoryUsage=" + maxMemoryUsage + ", maxLoad=" + maxLoad;
	}

	/**
	 * @return the checkTime
	 */
	public   int getCheckTime() {
		return checkTime;
	}

	/**
	 * @param checkTime the checkTime to set
	 */
	public   void setCheckTime(int checkTime) {
		 	this.checkTime=checkTime;
	}
	
	
	

}
