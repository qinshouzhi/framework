/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: CommandRequest001.java 9552 2015年1月28日 上午1:34:46 WangLijun$
*/
package com.newtouch.lion.adpater.command.base; 

import com.thoughtworks.xstream.annotations.XStreamAlias;

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
@XStreamAlias("ser:queryKnowCustomer")
public class CommandRequest0101 extends BaseCommand{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7634260154463645476L;
	
	  /**用户名*/
    @XStreamAlias("partyName")
    private String partyName;
    
    @XStreamAlias("regType")
    private String regType;
    
    @XStreamAlias("regNo")
    private String regNo;
    
    @XStreamAlias("pageSize")
    private String pageSize;
    
    
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
	 * @return the pageSize
	 */
	public String getPageSize() {
		return pageSize;
	}


	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}


	public CommandRequest0101(String partyName, String regType, String regNo,
			String pageSize) {
		super();
		this.partyName = partyName;
		this.regType = regType;
		this.regNo = regNo;
		this.pageSize = pageSize;
	}
    
    
}

	