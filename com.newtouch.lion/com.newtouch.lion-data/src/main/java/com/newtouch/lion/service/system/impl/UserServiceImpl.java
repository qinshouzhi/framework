/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TsUserServiceImpl.java 9552 2012-12-31 下午7:27:40 WangLijun$
 */
package com.newtouch.lion.service.system.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.newtouch.lion.common.sql.HqlUtils;
import com.newtouch.lion.dao.system.UserDao;
import com.newtouch.lion.json.JSONParser;
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
 * Title: 用户管理Service实现类
 * </p>
 * <p>
 * Description: 用于处理用户登录验证、新增、查询、保存
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
@Service("userService")
public class UserServiceImpl extends AbstractService implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private DataColumnService dataColumnService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private RoleService roleService;
	
	

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.UserService#doFindRoles(java.lang.String)
	 */
	@Override
	public List<Role> doFindRoles(String userName) {
		User user=this.doFindByUserName(userName);
		//将用户关联的用户组下的角色和用户联系的角色相合并
		Set<Role> userRoles=user.getRoles();
		Set<Group> groups=user.getGroups();
		for(Group group:groups){
			userRoles.addAll(group.getRoles());
		}
		return new ArrayList<Role>(userRoles);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.UserService#doFindByUserName(java.lang
	 * .String)
	 */
	@Override
	public User doFindByUserName(String userName) {
		String hql = "from  User u where u.username=:userName";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userName", userName);
		List<User> list = this.userDao.query(hql, params);
		if (!CollectionUtils.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.service.system.UserService#doCreateUser(com.lion
	 * .framework.model.system.User)
	 */
	@Override
	public void doCreateUser(User user) {
		this.userDao.save(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.service.system.UserService#doDelete(com.lion
	 * .framework.model.system.User)
	 */
	@Override
	public void doDelete(User user) {
		this.userDao.remove(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.service.system.UserService#doDeleteById(java.lang
	 * .Long)
	 */
	@Override
	public User doDeleteById(Long id) {
		// TODO Auto-generated method stub
		User user = this.doGetById(id);
		if (user != null) {
			this.doDelete(user);
		}
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.service.system.UserService#doFindById(java.lang
	 * .Long)
	 */
	@Override
	public User doFindById(Long id) {
		return this.userDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.service.system.UserService#doGetById(java.lang.
	 * Long)
	 */
	@Override
	public User doGetById(Long id) {
		return this.userDao.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.service.system.UserService#doFindAll()
	 */
	@Override
	public List<User> doFindAll() {
		return this.userDao.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.service.system.UserService#doFindByManageId(java
	 * .lang.Long)
	 */
	@Override
	public List<User> doFindByManageId(Long manageId) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.service.system.UserService#doUpdate(com.lion
	 * .framework.model.system.User)
	 */
	@Override
	public User doUpdate(User user) {

		this.userDao.update(user);
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.UserService#doDeleteGroupsForUser(java
	 * .util.List, com.lion.framework.model.system.User)
	 */
	@Override
	public void doDeleteGroupsForUser(List<Long> deleteGroupIds, User user) {
		// TODO
		Set<Group> groups = user.getGroups();
		Set<Group> deleteGroups = new HashSet<Group>();
		for (Group group : groups) {
			if (deleteGroupIds.contains(group.getId())) {
				group.getUsers().remove(user);
				deleteGroups.add(group);
			}
		}
		user.getGroups().removeAll(deleteGroups);
		this.userDao.update(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.UserService#doDeleteRoleSForUser(java
	 * .util.List, com.lion.framework.model.system.User)
	 */
	@Override
	public void doDeleteRoleSForUser(List<Long> deleteRolesIds, User user) {
		Set<Role> roles = user.getRoles();
		Set<Role> deleteRoles = new HashSet<Role>();
		for (Role role : roles) {
			if (deleteRolesIds.contains(role.getId())) {
				role.getUsers().remove(user);
				deleteRoles.add(role);
			}
		}
		user.getRoles().removeAll(deleteRoles);
		this.userDao.update(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.service.system.UserService#doFindByCriteria(com
	 * .lion.framework.common.QueryCriteria)
	 */
	@Override
	public PageResult<User> doFindByCriteria(QueryCriteria criteria) {
		String queryEntry = " from User ";

		String[] whereBodies = { "username like :username" };

		String fromJoinSubClause = "";

		Map<String, Object> params = criteria.getQueryCondition();

		String orderField = criteria.getOrderField();

		String orderDirection = criteria.getOrderDirection();

		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause,
				whereBodies, orderField, orderDirection, params);

		int pageSize = criteria.getPageSize();

		int startIndex = criteria.getStartIndex();

		PageResult<User> pageResult = this.userDao.query(hql,
				HqlUtils.generateCountHql(hql, null), params, startIndex,
				pageSize);

		return pageResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.UserService#doFindByCriteria(com.lion
	 * .framework.common.QueryCriteria, java.lang.String)
	 */
	@Override
	public String doFindByCriteria(QueryCriteria criteria, String tableId) {
		PageResult<User> pageResult = this.doFindByCriteria(criteria);
		Set<String> properties = this.dataColumnService
				.doFindColumnsByTableId(tableId);
		return JSONParser.toJSONDataGridString(pageResult, properties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.UserService#doFindByCriteria(com.lion
	 * .framework.common.QueryCriteria, java.lang.String)
	 */
	@Override
	public String doFindAllByCriteria(QueryCriteria criteria, String tableId) {
		QueryCriteria queryCriteria = new QueryCriteria();
		queryCriteria.setPageSize(99999999);
		List<User> users = this.doFindByCriteria(queryCriteria).getContent();
		Set<String> properties = this.dataColumnService
				.doFindColumnsByTableId(tableId);
		return JSONParser.toJSONDataGridString(users, properties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.UserService#doFindAllAuthGroupsById
	 * (java.lang.Long, java.lang.String)
	 */
	@Override
	public String doFindAllAuthGroupsById(Long userId, String tableId) {
		User user = this.doFindById(userId);
		Set<String> properties = this.dataColumnService
				.doFindColumnsByTableId(tableId);
		return JSONParser.toJSONDataGridString(user.getGroups(), properties);
	}
	
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.UserService#doFindAllAuthRole(java.
	 * lang.Long, java.lang.String)
	 */
	@Override
	public String doFindAllAuthRole(Long userId, String tableId) {
		User user = this.doFindById(userId);
		Set<String> properties = this.dataColumnService
				.doFindColumnsByTableId(tableId);
		return JSONParser.toJSONDataGridString(user.getRoles(), properties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.UserService#doAuthGroupToUser(java.
	 * util.List, java.util.List, com.lion.framework.model.system.User)
	 */
	@Override
	public void doAuthGroupToUser(List<Long> targetGroupIds,
			List<Long> deleteGroupIds, User user) {
		if (user == null) {
			return;
		}

		Set<Group> groups = null;

		if (deleteGroupIds != null && deleteGroupIds.size() > 0) {
			for (Long groupId : deleteGroupIds) {
				Group group = groupService.doFindById(groupId);
				if (group != null) {
					user.getGroups().remove(group);
				}
			}
		}

		if (targetGroupIds != null && targetGroupIds.size() > 0) {
			for (Long groupId : targetGroupIds) {
				Group group = groupService.doFindById(groupId);
				if (group == null) {
					continue;
				}
				groups = user.getGroups();
				if (groups == null) {
					groups = new HashSet<Group>();
					user.setGroups(groups);
				}
				user.getGroups().add(group);
			}
		}
		this.userDao.update(user);
		this.userDao.flush();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.UserService#doAuthRoleToUser(java.util
	 * .List, java.util.List, com.lion.framework.model.system.User)
	 */
	@Override
	public void doAuthRoleToUser(List<Long> targetRoleIds,
			List<Long> deleteRoleIds, User user) {
		if (user == null) {
			return;
		}

		Set<Role> roles = null;

		if (deleteRoleIds != null && deleteRoleIds.size() > 0) {
			for (Long roleId : deleteRoleIds) {
				Role role = this.roleService.doFindById(roleId);
				if (role != null) {
					user.getRoles().remove(role);
				}
			}
		}

		if (targetRoleIds != null && targetRoleIds.size() > 0) {
			for (Long roleId : targetRoleIds) {
				Role role = this.roleService.doFindById(roleId);
				if (role == null) {
					continue;
				}
				roles = user.getRoles();
				if (roles == null) {
					roles = new HashSet<Role>();
					user.setRoles(roles);
				}
				user.getRoles().add(role);
			}
		}

		this.userDao.update(user);
		this.userDao.flush();
	}
}
