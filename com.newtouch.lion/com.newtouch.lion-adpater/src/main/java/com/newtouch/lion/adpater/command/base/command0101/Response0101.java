/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: CommandResponse001.java 9552 2015年1月28日 上午1:38:08 WangLijun$
*/
package com.newtouch.lion.adpater.command.base.command0101; 

import com.thoughtworks.xstream.annotations.XStreamAlias;
/**
 * <p>
 * Title: 请求响应对象
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
@XStreamAlias("return")
public class Response0101{
	
	//客户号ID
	private String customerId;
	//分公司代码
	private String branchId;
	//姓名
	private String partyName;
	//出生日期
	private String custBirth;
	//性别
	private String custGender;
	//证件类型
	private String regType;
	//证件号码
	private String regNo;
	//单位名称
	private String companyName;
	//单位地址
	private String companyAddress;
	//团体投保单位名称
	@XStreamAlias("apCompanyName")
	private String apCompanyName;
	//家庭住址
	private String familyAddress;
	//家庭住址经度
	private String familyAddLongitude;
	//家庭住址纬度
	private String familyAddLatitude;
	
	
	
	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	/**
	 * @return the branchId
	 */
	public String getBranchId() {
		return branchId;
	}
	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	/**
	 * @return the partyName
	 */
	public String getPartyName() {
		return partyName;
	}
	/**
	 * @param partyName the partyName to set
	 */
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	/**
	 * @return the custBirth
	 */
	public String getCustBirth() {
		return custBirth;
	}
	/**
	 * @param custBirth the custBirth to set
	 */
	public void setCustBirth(String custBirth) {
		this.custBirth = custBirth;
	}
	/**
	 * @return the custGender
	 */
	public String getCustGender() {
		return custGender;
	}
	/**
	 * @param custGender the custGender to set
	 */
	public void setCustGender(String custGender) {
		this.custGender = custGender;
	}
	/**
	 * @return the regType
	 */
	public String getRegType() {
		return regType;
	}
	/**
	 * @param regType the regType to set
	 */
	public void setRegType(String regType) {
		this.regType = regType;
	}
	/**
	 * @return the regNo
	 */
	public String getRegNo() {
		return regNo;
	}
	/**
	 * @param regNo the regNo to set
	 */
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * @return the companyAddress
	 */
	public String getCompanyAddress() {
		return companyAddress;
	}
	/**
	 * @param companyAddress the companyAddress to set
	 */
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	/**
	 * @return the apCompanyName
	 */
	public String getApCompanyName() {
		return apCompanyName;
	}
	/**
	 * @param apCompanyName the apCompanyName to set
	 */
	public void setApCompanyName(String apCompanyName) {
		this.apCompanyName = apCompanyName;
	}
	/**
	 * @return the familyAddress
	 */
	public String getFamilyAddress() {
		return familyAddress;
	}
	/**
	 * @param familyAddress the familyAddress to set
	 */
	public void setFamilyAddress(String familyAddress) {
		this.familyAddress = familyAddress;
	}
	/**
	 * @return the familyAddLongitude
	 */
	public String getFamilyAddLongitude() {
		return familyAddLongitude;
	}
	/**
	 * @param familyAddLongitude the familyAddLongitude to set
	 */
	public void setFamilyAddLongitude(String familyAddLongitude) {
		this.familyAddLongitude = familyAddLongitude;
	}
	/**
	 * @return the familyAddLatitude
	 */
	public String getFamilyAddLatitude() {
		return familyAddLatitude;
	}
	/**
	 * @param familyAddLatitude the familyAddLatitude to set
	 */
	public void setFamilyAddLatitude(String familyAddLatitude) {
		this.familyAddLatitude = familyAddLatitude;
	}
}

	