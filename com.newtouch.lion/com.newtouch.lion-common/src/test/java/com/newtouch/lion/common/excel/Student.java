/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: Student.java 9552 2015年1月26日 下午5:54:39 WangLijun$
 */
package com.newtouch.lion.common.excel;

import java.util.Date;

/**
 * <p>
 * Title:Excel导出功能测试类
 * </p>
 * <p>
 * Description:Excel导出功能测试类
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
public class Student {
	
	private long id;
	
	private String name;
	
	private int age;
	
	private boolean sex;
	
	private Date birthday;

	private Date regDate;
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(long id, String name, int age, boolean sex, Date birthday,Date regDate) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.birthday = birthday;
		this.regDate=regDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	/**
	 * @return the regDate
	 */
	public Date getRegDate() {
		return regDate;
	}

	/**
	 * @param regDate the regDate to set
	 */
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean getSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	
}
