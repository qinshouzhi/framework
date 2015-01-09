/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: GroupServiceImpl.java 9552 2013-1-12 下午8:36:38 WangLijun$
 */
package com.newtouch.lion.service.system.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.lion.common.sql.HqlUtils;
import com.newtouch.lion.dao.system.GroupDao;
import com.newtouch.lion.json.JSONParser;
import com.newtouch.lion.model.datagrid.DataColumn;
import com.newtouch.lion.model.system.Group;
import com.newtouch.lion.model.system.Role;
import com.newtouch.lion.model.system.User;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.AbstractService;
import com.newtouch.lion.service.datagrid.DataColumnService;
import com.newtouch.lion.service.system.GroupService;
import com.newtouch.lion.service.system.RoleService;
import com.newtouch.lion.service.system.UserService;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
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
@Service("groupService")
public class GroupServiceImpl extends AbstractService implements GroupService {

	@Autowired
	private GroupDao groupDao;

	@Autowired
	private DataColumnService dataColumnService;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doCreateGroup(com.lion
	 * .framework.model.system.Group)
	 */
	@Override
	public void doCreateGroup(Group group) {
		this.groupDao.save(group);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doFindByTableId(java.lang
	 * .String)
	 */
	@Override
	public List<DataColumn> doFindByTableId(String tableId) {
		return this.dataColumnService.doFindByTableId(tableId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doAddGroupsToUser(java
	 * .util.List, com.lion.framework.model.system.User)
	 */
	@Override
	public void doAddGroupsToUser(List<Long> groupIds, User user) {
		if (groupIds == null || groupIds.size() == 0 || user == null) {
			return;
		}
		Set<Group> groups = new HashSet<Group>();
		for (Long groupId : groupIds) {
			Group group = this.doFindById(groupId);
			Set<User> users = group.getUsers();
			if (users == null) {
				users = new HashSet<User>();
				group.setUsers(users);
			}
			group.getUsers().add(user);
			groups.add(group);
		}
		// user.getGroups().addAll(groups);
		this.groupDao.updateObjects(groups);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doAddUsersToGroup(java
	 * .util.List, com.lion.framework.model.system.Group)
	 */
	@Override
	public void doAddUsersToGroup(List<Long> userIds, Group group) {
		if (userIds == null || userIds.size() == 0 || group == null) {
			return;
		}

		for (Long userId : userIds) {
			User user = this.userService.doFindById(userId);
			group.getUsers().add(user);
		}
		this.doUpdate(group);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doDeleteUsersFromGroup
	 * (java.util.List, com.lion.framework.model.system.Group)
	 */
	@Override
	public void doDeleteUsersFromGroup(List<Long> userIds, Group group) {
		if (userIds == null || userIds.size() == 0 || group == null) {
			return;
		}
		Set<User> users = group.getUsers();

		Set<User> deleteUsers = new HashSet<User>();

		for (User user : users) {
			if (userIds.contains(user.getId())) {
				user.getGroups().remove(group);
				deleteUsers.add(user);
			}
		}
		group.getUsers().remove(deleteUsers);
		this.doUpdate(group);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doAddRolesToGroup(java
	 * .util.List, com.lion.framework.model.system.Group)
	 */
	@Override
	public void doAddRolesToGroup(List<Long> roleIds, Group group) {
		if (roleIds == null || roleIds.size() == 0 || group == null) {
			return;
		}
		for (Long roldeId : roleIds) {
			Role role = this.roleService.doFindById(roldeId);
			group.getRoles().add(role);
		}
		this.doUpdate(group);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#idoAuthUserToGroup(java
	 * .util.List, java.util.List, com.lion.framework.model.system.Group)
	 */
	@Override
	public void idoAuthUserToGroup(List<Long> targetUserIds,
			List<Long> deleteUserIds, Group group) {
		this.doDeleteUsersFromGroup(deleteUserIds, group);
		this.doAddUsersToGroup(targetUserIds, group);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doDeleteRolesFromGroup
	 * (java.util.List, com.lion.framework.model.system.Group)
	 */
	@Override
	public void doDeleteRolesFromGroup(List<Long> roleIds, Group group) {
		if (roleIds == null || roleIds.size() == 0 || group == null) {
			return;
		}
		Set<Role> users = group.getRoles();

		Set<Role> deleteUsers = new HashSet<Role>();

		for (Role role : users) {
			if (roleIds.contains(role.getId())) {
				role.getGroups().remove(group);
				deleteUsers.add(role);
			}
		}
		group.getRoles().removeAll(deleteUsers);
		this.doUpdate(group);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doAuthRoleToGroup(java
	 * .util.List, java.util.List, com.lion.framework.model.system.Group)
	 */
	@Override
	public void doAuthRoleToGroup(List<Long> targetRoleIds,
			List<Long> deleteRoleIds, Group group) {
		this.doDeleteRolesFromGroup(deleteRoleIds, group);
		this.doAddRolesToGroup(targetRoleIds, group);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.service.system.GroupService#doFindAll()
	 */
	@Override
	public List<Group> doFindAll() {
		return this.groupDao.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doFindByUserId(java.lang
	 * .Long)
	 */
	@Override
	public List<Group> doFindByUserId(Long userId) {
		String hql = "from Group where users.id=:userId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		return this.groupDao.query(hql, params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doFindByRoleId(java.lang
	 * .Long)
	 */
	@Override
	public List<Group> doFindByRoleId(Long roleId) {
		String hql = "from Group where roles.id=:roleId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", roleId);
		return this.groupDao.query(hql, params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doFindById(java.lang.Long)
	 */
	@Override
	public Group doFindById(Long id) {
		// TODO Auto-generated method stub
		return this.groupDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doDelete(com.lion.framework
	 * .model.system.Group)
	 */
	@Override
	public void doDelete(Group group) {
		this.groupDao.remove(group);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doDeleteById(java.lang
	 * .Long)
	 */
	@Override
	public int doDeleteById(Long id) {
		String hql = "delete from Group p where p.id=:id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return this.groupDao.updateHQL(hql, params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doUpdate(com.lion.framework
	 * .model.system.Group)
	 */
	@Override
	public Group doUpdate(Group group) {
		this.groupDao.update(group);
		return group;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doGetById(java.lang.Long)
	 */
	@Override
	public Group doGetById(Long id) {
		return groupDao.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doFindByCriteria(com.lion
	 * .framework.common.QueryCriteria)
	 */
	@Override
	public PageResult<Group> doFindByCriteria(QueryCriteria criteria) {
		String queryEntry = " from Group ";

		String[] whereBodies = { "nameZh like :nameZh" };

		String fromJoinSubClause = "";

		Map<String, Object> params = criteria.getQueryCondition();

		String orderField = criteria.getOrderField();

		String orderDirection = criteria.getOrderDirection();

		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause,
				whereBodies, orderField, orderDirection, params);

		int pageSize = criteria.getPageSize();

		int startIndex = criteria.getStartIndex();

		PageResult<Group> pageResult = this.groupDao.query(hql,
				HqlUtils.generateCountHql(hql, null), params, startIndex,
				pageSize);

		return pageResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doFindAuthGroups(com.lion
	 * .framework.common.QueryCriteria, java.lang.String)
	 */
	@Override
	public String doFindAuthGroups(QueryCriteria queryCriteria, String tableId) {
		List<Group> groups = this.doFindByCriteria(queryCriteria).getContent();
		Set<String> properties = this.dataColumnService
				.doFindColumnsByTableId(tableId);
		return JSONParser.toJSONDataGridString(groups, properties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doFindAuthUsersById(java
	 * .lang.Long, java.lang.String)
	 */
	@Override
	public String doFindAuthUsersById(Long groupId, String tableId) {
		Group group = this.doFindById(groupId);
		Set<String> properties = this.dataColumnService
				.doFindColumnsByTableId(tableId);
		return JSONParser.toJSONDataGridString(group.getUsers(), properties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.GroupService#doFindAuthRolesById(java
	 * .lang.Long, java.lang.String)
	 */
	@Override
	public String doFindAuthRolesById(Long groupId, String tableId) {
		Group group = this.doFindById(groupId);
		Set<String> properties = this.dataColumnService
				.doFindColumnsByTableId(tableId);
		return JSONParser.toJSONDataGridString(group.getRoles(), properties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.service.system.GroupService#doFindGroups()
	 */
	@Override
	public List<Group> doFindGroups() {
		QueryCriteria queryCriteria = new QueryCriteria();
		queryCriteria.setPageSize(99999999);
		return this.doFindByCriteria(queryCriteria).getContent();
	}
}
