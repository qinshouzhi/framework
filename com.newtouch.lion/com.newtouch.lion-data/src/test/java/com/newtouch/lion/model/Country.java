/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: Country.java 9552 2015年10月9日 下午4:57:08 WangLijun$
*/
package com.newtouch.lion.model; 
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
public class Country {
	private int id;
    private String countryname;
    private String countrycode;
    
    public Country() {
		 super();
	}
    
    
    
	/**
	 * @param id
	 * @param countryname
	 * @param countrycode
	 */
	public Country(int id, String countryname, String countrycode) {
		this.id = id;
		this.countryname = countryname;
		this.countrycode = countrycode;
	}



	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the countryname
	 */
	public String getCountryname() {
		return countryname;
	}
	/**
	 * @param countryname the countryname to set
	 */
	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}
	/**
	 * @return the countrycode
	 */
	public String getCountrycode() {
		return countrycode;
	}
	/**
	 * @param countrycode the countrycode to set
	 */
	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}
    
    
}

	