/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: CommandResponse001.java 9552 2015年1月28日 上午1:38:08 WangLijun$
*/
package com.newtouch.lion.adpater.command.base; 

import java.util.ArrayList;
import java.util.List;

import com.newtouch.lion.adpater.command.base.command0101.Response0101;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAliasType;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
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
@XStreamAlias("response")
//@XStreamImplicit(itemFieldName="return");
public class CommandResponse0101  extends BaseCommand{
	
	private static final long serialVersionUID = 5594411002569072072L;
	
	@XStreamImplicit(itemFieldName="return")	
	private  List<Response0101> list=new ArrayList<Response0101>();
	/**
	 * @return the list
	 */
	public List<Response0101> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<Response0101> list) {
		this.list = list;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CommandResponse0101 [list=" + list + "]";
	}
	 
	
	
}

	