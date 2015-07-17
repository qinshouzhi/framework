/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: User.java 9552 2015年7月17日 下午10:28:22 WangLijun$
*/
package com.newtouch.lion.cache.model; 
/**
 * <p>
 * Title: User 测试类
 * </p>
 * <p>
 * Description: User 测试类
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
public class User {
	/**ID*/
	private Long id;
	/**用户名*/
	private String username;
	/**Email*/
	private String email;
	/**性别*/
	private Integer gender;
	/**地址*/
	private String address;
	/**ZIP*/
	private String zipCode;
	/**备注*/
	private String memo;
	
	
	public User() {
	    super();
	}
	
	
	
	
	/**
	 * @param id
	 * @param username
	 * @param email
	 * @param gender
	 * @param address
	 * @param zipCode
	 * @param memo
	 */
	public User(Long id, String username, String email, Integer gender,
			String address, String zipCode, String memo) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.gender = gender;
		this.address = address;
		this.zipCode = zipCode;
		this.memo = memo;
	}




	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the gender
	 */
	public Integer getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}
	/**
	 * @param memo the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
}

	