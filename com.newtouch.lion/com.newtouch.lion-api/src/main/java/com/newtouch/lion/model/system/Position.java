
/*
* Copyright (c)  2014, Newtouch
* All rights reserved. 
*
* $id: Position.java 9552 2014年12月24日 下午1:38:06 WangLijun$
*/
package com.newtouch.lion.model.system; 

import java.util.HashSet;
import java.util.Set;

import com.newtouch.lion.model.VersionEntity;

/**
 * <p>
 * Title: 岗位信息
 * </p>
 * <p>
 * Description: 岗位信息, 岗位之间存在上下级关系
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class Position extends VersionEntity<Long>{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 3530629463630759624L;
	/**记录ID*/
	private Long  id;
	/**
	 * @Fields nameEn：岗位英文名称
	 */
	private String nameEn;
	/**
	 * @Fields nameZh：岗位中文名称
	 */
	private String nameZh;
	/**
	 *@Fields description:岗位描述
	 */
	private String description;
	/**@Fields position： 父级岗位*/
	private Position position;
	/**@Fields department： 岗位所属部门*/
	private Department department;
	/**@Fields positions：该岗位所有下级岗位*/
	private Set<Position> positions = new HashSet<Position>(0);
	/**@Fields User 该岗位所有的用户集合*/
	private Set<User> users = new HashSet<User>(0);
	
	@Override
	public Long getId() {
		return this.id;
	}

	/**
	 * @return the nameEn
	 */
	public String getNameEn() {
		return nameEn;
	}

	/**
	 * @param nameEn the nameEn to set
	 */
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	/**
	 * @return the nameZh
	 */
	public String getNameZh() {
		return nameZh;
	}

	/**
	 * @param nameZh the nameZh to set
	 */
	public void setNameZh(String nameZh) {
		this.nameZh = nameZh;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * @return the positions
	 */
	public Set<Position> getPositions() {
		return positions;
	}

	/**
	 * @param positions the positions to set
	 */
	public void setPositions(Set<Position> positions) {
		this.positions = positions;
	}

	/**
	 * @return the users
	 */
	public Set<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}
}

	