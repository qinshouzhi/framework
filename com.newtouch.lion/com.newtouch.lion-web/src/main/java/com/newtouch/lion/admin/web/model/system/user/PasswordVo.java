/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: PasswordVo.java 9552 2014-4-6 下午09:48:54 WangLijun$
 */
package com.newtouch.lion.admin.web.model.system.user;

import java.io.Serializable;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: NewTouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class PasswordVo implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 881413555539766568L;
	/** 旧密码 */
	private String oldPwd;
	/** 新密码 */
	private String pwd;
	/** 确认密码 */
	private String rePwd;

	/*** default */
	public PasswordVo() {
		super();
	};

	/**
	 * @param oldPwd
	 * @param pwd
	 * @param rePwd
	 */
	public PasswordVo(String oldPwd, String pwd, String rePwd) {
		super();
		this.oldPwd = oldPwd;
		this.pwd = pwd;
		this.rePwd = rePwd;
	}

	/**
	 * @return the oldPwd
	 */
	public String getOldPwd() {
		return oldPwd;
	}

	/**
	 * @param oldPwd
	 *            the oldPwd to set
	 */
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * @param pwd
	 *            the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * @return the rePwd
	 */
	public String getRePwd() {
		return rePwd;
	}

	/**
	 * @param rePwd
	 *            the rePwd to set
	 */
	public void setRePwd(String rePwd) {
		this.rePwd = rePwd;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PasswordVo [oldPwd=" + oldPwd + ", pwd=" + pwd + ", rePwd="
				+ rePwd + "]";
	}
}
