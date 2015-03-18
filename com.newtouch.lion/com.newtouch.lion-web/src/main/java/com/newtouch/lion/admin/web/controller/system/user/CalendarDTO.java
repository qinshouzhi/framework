
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: CalendarDTO.java 9552 Mar 16, 2015 4:37:33 PM MaoJiaWei$
*/
package com.newtouch.lion.admin.web.controller.system.user; 
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
 * Company: NewTouch
 * </p>
 * 
 * @author MaoJiaWei
 * @version 1.0
 */
public class CalendarDTO {
	private int id;
	private String start;
	private String end;
	private String title;
	private boolean allday;
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
	 * @return the start
	 */
	public String getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(String start) {
		this.start = start;
	}
	/**
	 * @return the end
	 */
	public String getEnd() {
		return end;
	}
	/**
	 * @param end the end to set
	 */
	public void setEnd(String end) {
		this.end = end;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the allday
	 */
	public boolean isAllday() {
		return allday;
	}
	/**
	 * @param allday the allday to set
	 */
	public void setAllday(boolean allday) {
		this.allday = allday;
	}
	
}

	