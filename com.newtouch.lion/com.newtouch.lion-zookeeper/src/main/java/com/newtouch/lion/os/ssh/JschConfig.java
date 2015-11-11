/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: SshConfig.java 9552 2015年5月27日 下午3:04:34 WangLijun$
*/
package com.newtouch.lion.os.ssh; 
/**
 * <p>
 * Title: Linux 操作系统连接信息
 * </p>
 * <p>
 * Description: Linux 操作系统连接信息
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
public class JschConfig {
	/***
	 * IP
	 */
	private String ip;
	/**
	 * Hostname
	 * */
	private String hostname;
	/**用户名*/
	private String username;
	/**密码*/
	private String password;
	/**端口*/
	private int port=22;
	
	public JschConfig() {
		 super();
	}
	
	
	

	/**
	 * @param ip
	 * @param username
	 * @param password
	 */
	public JschConfig(String ip, String username, String password,int port) {
		this.ip = ip;
		this.username = username;
		this.password = password;
		this.port=port;
	}
	
	

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}




	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}




	/**
	 * @param ip
	 * @param hostname
	 * @param username
	 * @param password
	 * @param port
	 */
	public JschConfig(String ip, String hostname, String username,
			String password, int port) {
		this.ip = ip;
		this.hostname = hostname;
		this.username = username;
		this.password = password;
		this.port = port;
	}




	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the hostname
	 */
	public String getHostname() {
		return hostname;
	}

	/**
	 * @param hostname the hostname to set
	 */
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}

	