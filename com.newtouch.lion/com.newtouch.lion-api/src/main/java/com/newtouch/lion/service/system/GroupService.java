/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: GroupService.java 9552 2013-1-12 下午1:42:55 WangLijun$
 */
package com.newtouch.lion.service.system;

import java.util.List;

import com.newtouch.lion.model.datagrid.DataColumn;
import com.newtouch.lion.model.system.Group;
import com.newtouch.lion.model.system.User;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;

/**
 * <p>
 * Title: 用户组
 * </p>
 * <p>
 * Description: 用户组
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public interface GroupService {

	public void doCreateGroup(Group group);

	/***
	 * 为用户添加用户组
	 * 
	 * @param groupIds
	 * @param user
	 * */
	public void doAddGroupsToUser(List<Long> groupIds, User user);

	/** 将用户集合添加用户组 */
	public void doAddUsersToGroup(List<Long> userIds, Group group);

	/** 将用户集合从用户组中删除 */
	public void doDeleteUsersFromGroup(List<Long> userIds, Group group);

	/** 将角色授权给用户组 */
	public void idoAuthUserToGroup(List<Long> targetUserIds,
			List<Long> deleteUserIds, Group group);

	/** 将角色集合添加到用户组中 */
	public void doAddRolesToGroup(List<Long> roleIds, Group group);

	/** 将角色集合从用户组中删除 */
	public void doDeleteRolesFromGroup(List<Long> roleIds, Group group);

	/** 将角色授权给用户组 */
	public void doAuthRoleToGroup(List<Long> targetRoleIds,
			List<Long> deleteRoleIds, Group group);

	/**
	 * 查询所有用户组
	 * 
	 * @return List<Group>
	 * */
	public List<Group> doFindAll();

	/** 查询所有用户组 */
	public List<Group> doFindGroups();

	/**
	 * 根据查询条件查询用户组和显示列表字段，并返回JSON字符
	 * 
	 * @param criteria
	 *            查询字段
	 * @param tableId
	 *            列表名称
	 * @return String JSON字符串
	 * */
	public String doFindAuthGroups(QueryCriteria criteria, String tableId);

	/**
	 * 根据用户ID查询用户所属用户组，并返回用户组列表
	 * 
	 * @param userId
	 * @return List<Group>
	 * */
	public List<Group> doFindByUserId(Long userId);

	/** 根据角色Id查询该角色关联的用户组，并返回用户组列表 */
	public List<Group> doFindByRoleId(Long roleId);

	/**
	 * 根据用户组ID，查询已经授权的用户，并返回JSON字符串
	 * 
	 * @param groupId
	 *            用户组ID
	 * @param tableId
	 * @return JSON字符串
	 * */
	public String doFindAuthUsersById(Long groupId, String tableId);

	/**
	 * 根据用户组ID，查询已授权的角色，并返回JSON字符串
	 * 
	 * @param groupId
	 *            用户组ID
	 * @param tableId
	 * @return JSON字符串
	 * */
	public String doFindAuthRolesById(Long groupId, String tableId);

	public Group doFindById(Long id);

	public void doDelete(Group group);

	public int doDeleteById(Long id);

	public Group doUpdate(Group group);

	public Group doGetById(Long id);

	public PageResult<Group> doFindByCriteria(QueryCriteria criteria);

	public List<DataColumn> doFindByTableId(String tableId);
}
