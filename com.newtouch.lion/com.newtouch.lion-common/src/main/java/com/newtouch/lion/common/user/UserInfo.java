/**
 * 
 */
package com.newtouch.lion.common.user;

import java.io.Serializable;

/**
 * @author wanglijun
 * 
 */
public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String username;
	
	private Long id;
	
	private String realName;
	
	private String password;
	
	private String userIP;
	
	private String macAddress;

	public UserInfo() {
	}

	
	
	/**
	 * @param username
	 * @param id
	 */
	public UserInfo(String username, Long id) {
		super();
		this.username = username;
		this.id = id;
	}



	public UserInfo(String username, Long id, String realName, String password,
			String userIP) {
		this.username = username;
		this.id = id;
		this.realName = realName;
		this.password = password;
		this.userIP = userIP;
	}

	public Long getId() {
		return this.id;
	}

	public String getLoginId() {
		return this.username;
	}

	public String getName() {
		return this.realName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLoginId(String loginId) {
		this.username = loginId;
	}

	public void setName(String name) {
		this.realName = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public java.util.Locale getPreferredLocale() {
		// return
		return null;
	}

	public String getUserIP() {
		return this.userIP;
	}

	public void setUserIP(String userIP) {
		this.userIP = userIP;
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
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}



	/**
	 * @param realName the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}



	/**
	 * @return the macAddress
	 */
	public String getMacAddress() {
		return macAddress;
	}



	/**
	 * @param macAddress the macAddress to set
	 */
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	
	
}
