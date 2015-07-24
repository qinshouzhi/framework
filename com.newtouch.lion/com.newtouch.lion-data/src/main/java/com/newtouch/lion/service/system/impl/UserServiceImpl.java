
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

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.newtouch.lion.common.sql.HqlUtils;
import com.newtouch.lion.dao.system.GroupDao;
import com.newtouch.lion.dao.system.RoleDao;
import com.newtouch.lion.dao.system.UserDao;
import com.newtouch.lion.dao.system.UserGroupDao;
import com.newtouch.lion.dao.system.UserRoleDao;
import com.newtouch.lion.json.JSONParser;
import com.newtouch.lion.model.system.Group;
import com.newtouch.lion.model.system.Role;
import com.newtouch.lion.model.system.User;
import com.newtouch.lion.model.system.UserGroup;
import com.newtouch.lion.model.system.UserRole;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.AbstractService;
import com.newtouch.lion.service.datagrid.DataColumnService;
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
	private UserGroupDao userGroupDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private DataColumnService dataColumnService;
	@Autowired
	private GroupDao groupDao;
	@Autowired
	private RoleDao roleDao;
	
	@Value("${super.username}")
	private String superUserName;
	

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
	
	
	

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.UserService#doFindRoles(java.lang.Long)
	 */
	@Override
	public Set<Role> doFindRoles(Long id) {
		User user=this.doFindById(id);
		//将用户关联的用户组下的角色和用户联系的角色相合并
		Set<Role> userRoles=user.getRoles();
		Set<Group> groups=user.getGroups();
		for(Group group:groups){
			userRoles.addAll(group.getRoles());
		}
		return userRoles;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.service.system.UserService#doFindByUserName(java.lang
	 * .String)
	 */
	@Override
	public User doFindByUserName(String username) {
		String hql = "from  User u where u.username=:username";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		List<User> list = this.userDao.query(hql, params);
		if (!CollectionUtils.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
	
	

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.UserService#doFindByEmpolyeeCode(java.lang.String)
	 */
	@Override
	public User doFindByEmpolyeeCode(String employeeCode) {
		String hql="from User u where u.employeeCode=:employeeCode";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("employeeCode", employeeCode);
		List<User> list = this.userDao.query(hql, params);
		if (!CollectionUtils.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.UserService#doFindByEmail(java.lang.String)
	 */
	@Override
	public User doFindByEmail(String email) {
		String hql="from User u where u.email=:email";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", email);
		List<User> list = this.userDao.query(hql, params);
		if (!CollectionUtils.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
	
	
	

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.UserService#checkUsername(java.lang.String)
	 */
	@Override
	public Boolean checkUsername(String username) {
		boolean flag=false;
		if(StringUtils.isEmpty(username)){
			return true;
		}		
		User user = this.doFindByUserName(username.trim());
		if (user != null && user.getUsername().equals(username)) {
			flag =true;
		}
		return flag;
	}


	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.UserService#checkEmployeeCode(java.lang.String)
	 */
	@Override
	public Boolean checkEmployeeCode(String employeeCode) {
		boolean flag=false;
		if(StringUtils.isEmpty(employeeCode)){
			return true;
		}		
		User user = this.doFindByEmpolyeeCode(employeeCode.trim());
		if (user != null && user.getEmployeeCode().equals(employeeCode.trim())) {
			flag =true;
		}
		return flag;
	}
	
	


	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.UserService#checkEmail(java.lang.String)
	 */
	@Override
	public Boolean checkEmail(String email) {
		boolean flag=false;
		if(StringUtils.isEmpty(email)){
			return true;
		}		
		User user = this.doFindByEmail(email.trim());
		if (user != null && user.getEmail().equals(email.trim())) {
			flag =true;
		}
		return flag;
	}

	
	

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.UserService#checkUsername(java.lang.String, java.lang.Long)
	 */
	@Override
	public Boolean checkUsername(String username, Long id) {
		boolean flag=false;
		if(StringUtils.isEmpty(username)){
			return true;
		}		
		User user = this.doFindByUserName(username.trim());
		if (user != null&&!user.getId().equals(id)&& user.getUsername().equals(username)) {
			flag =true;
		}
		return flag;
	}


	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.UserService#checkEmployeeCode(java.lang.String, java.lang.Long)
	 */
	@Override
	public Boolean checkEmployeeCode(String employeeCode, Long id) {
		boolean flag=false;
		if(StringUtils.isEmpty(employeeCode)){
			return true;
		}		
		User user = this.doFindByEmpolyeeCode(employeeCode.trim());
		if (user != null&&!user.getId().equals(id) && user.getEmployeeCode().equals(employeeCode.trim())) {
			flag =true;
		}
		return flag;
	}


	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.UserService#checkEmail(java.lang.String, java.lang.Long)
	 */
	@Override
	public Boolean checkEmail(String email, Long id) {
		boolean flag=false;
		if(StringUtils.isEmpty(email)){
			return true;
		}		
		User user = this.doFindByEmail(email.trim());
		if (user != null &&!user.getId().equals(id)&& user.getEmail().equals(email.trim())) {
			flag =true;
		}
		return flag;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.newtouch.framework.service.system.UserService#doCreateUser(com.lion
	 * .framework.model.system.User)
	 */
	@Override
	public void doCreateUser(User user) {
		this.userDao.save(user);
	}
	
	

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.UserService#getSuperUsername()
	 */
	@Override
	public String getSuperUsername() {
		return this.superUserName;
	}

	


	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.UserService#checkSuperUserById(java.lang.Long)
	 */
	@Override
	public boolean checkSuperUserById(Long id) {
		boolean flag=false;
		User user=this.doFindById(id);
		if(user!=null&&user.getUsername().equals(this.superUserName)){
			 flag=true;
		}
		return flag;
	}


	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.UserService#checkSuperUserByUserName(java.lang.String)
	 */
	@Override
	public boolean checkSuperUserByUserName(String username) {
		boolean flag=false;
		User user=this.doFindByUserName(username);
		if(user!=null&&user.getUsername().equals(this.superUserName)){
			flag=true;
		}
		return flag;
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
	public int doDeleteById(Long id) {
		String hql = "delete from User u where u.id=:id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return this.userDao.updateHQL(hql, params);
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
	
	
	

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.UserService#doFindByCriteriaUserGroup(com.newtouch.lion.query.QueryCriteria)
	 */
	@Override
	public PageResult<UserGroup> doFindUserGroupByCriteria(
			QueryCriteria criteria) {
		
		String queryEntry = "select new com.newtouch.lion.model.system.UserGroup(id,username,employeeCode,realnameZh,realnameEn) from User ";

		String[] whereBodies = { "username like :username", "employeeCode like :employeeCode","email like :email" };

		String fromJoinSubClause = "";

		Map<String, Object> params = criteria.getQueryCondition();

		String orderField = criteria.getOrderField();

		String orderDirection = criteria.getOrderDirection();

		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause,
				whereBodies, orderField, orderDirection, params);

		int pageSize = criteria.getPageSize();

		int startIndex = criteria.getStartIndex();

		PageResult<UserGroup> pageResult = this.userGroupDao.query(hql,
				HqlUtils.generateCountHql(hql, null), params, startIndex,
				pageSize);
		return pageResult;
	}
	
	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.UserService#doFindByCriteriaUserGroup(com.newtouch.lion.query.QueryCriteria)
	 */
	@Override
	public PageResult<UserRole> doFindUserRoleByCriteria(
			QueryCriteria criteria,Long roleId) {
		
		String queryEntry = "select new com.newtouch.lion.model.system.UserRole(id,username,employeeCode,realnameZh,realnameEn) from User ";
		
		String[] whereBodies = { "username like :username", "employeeCode like :employeeCode","email like :email" };
		
		String fromJoinSubClause = "";

		Map<String, Object> params = criteria.getQueryCondition();

		String orderField = criteria.getOrderField();

		String orderDirection = criteria.getOrderDirection();

		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause,
				whereBodies, orderField, orderDirection, params);

		int pageSize = criteria.getPageSize();

		int startIndex = criteria.getStartIndex();
		
		PageResult<UserRole> pageResult = this.userRoleDao.query(hql,HqlUtils.generateCountHql(hql, null),params,startIndex,pageSize);
		
		//如果查询为空，则直接返回数据
		if(CollectionUtils.isEmpty(pageResult.getContent())){
			return pageResult;
		}
		//以下代码检查是否已授权到用户组的角色
		criteria=new QueryCriteria();
		criteria.setStartIndex(0);
		criteria.setPageSize(pageResult.getPageSize());
		//当前ID集合
		List<UserRole> userRoles=pageResult.getContent();
		List<Long> userIds=new ArrayList<Long>();
		for(UserRole userRole:userRoles){
			userIds.add(userRole.getId());
		}
		// 查询条件 参数类型 用户名
		if (roleId!=null&&roleId>0) {
			criteria.addQueryCondition("roleId",roleId);
		}
		//查询所有空的
		if(!CollectionUtils.isEmpty(userIds)){
			criteria.addQueryCondition("userIds",userIds);
		}
		
		PageResult<User> result=this.doFindByCriteriaAndRole(criteria);
		
		Map<Long,Long> userIdsMap=new HashMap<Long,Long>();
		
		for(User user:result.getContent()){
			userIdsMap.put(user.getId(), user.getId());
		}
		
		List<UserRole> contents=new ArrayList<UserRole>();
		
		for(UserRole userRole:userRoles){
			if(userIdsMap.containsKey(userRole.getId())){
				userRole.setRoleId(roleId);
			}
			contents.add(userRole);
		}
		pageResult.setContent(contents);
		return pageResult;
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.UserService#doFindByCriteriaAndGroup(com.newtouch.lion.query.QueryCriteria)
	 */
	@Override
	public PageResult<User> doFindByCriteriaAndGroup(QueryCriteria criteria) {
		String queryEntry = " select user from User as  user inner join fetch user.groups g ";

		String[] whereBodies = { "user.nameZh like :nameZh","g.id =:groupId","user.id in(:userIds)"};

		String fromJoinSubClause = "";

		Map<String, Object> params = criteria.getQueryCondition();

		String orderField = criteria.getOrderField();

		String orderDirection = criteria.getOrderDirection();

		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause,
				whereBodies, orderField, orderDirection, params);
		
		String countHql=HqlUtils.generateCountHql(hql,"user.id ");

		int pageSize = criteria.getPageSize();

		int startIndex = criteria.getStartIndex();

		PageResult<User> pageResult = this.userDao.query(hql,countHql, params, startIndex,pageSize);
		
		return pageResult;
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.system.UserService#doFindByCriteriaAndGroup(com.newtouch.lion.query.QueryCriteria)
	 */
	@Override
	public PageResult<User> doFindByCriteriaAndRole(QueryCriteria criteria) {
		String queryEntry = " select user from User as  user inner join fetch user.roles r ";

		String[] whereBodies = { "user.nameZh like :nameZh","r.id =:roleId","user.id in(:userIds)"};

		String fromJoinSubClause = "";

		Map<String, Object> params = criteria.getQueryCondition();

		String orderField = criteria.getOrderField();

		String orderDirection = criteria.getOrderDirection();

		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause,
				whereBodies, orderField, orderDirection, params);
		
		String countHql=HqlUtils.generateCountHql(hql,"user.id ");

		int pageSize = criteria.getPageSize();

		int startIndex = criteria.getStartIndex();

		PageResult<User> pageResult = this.userDao.query(hql,countHql, params, startIndex,pageSize);
		
		return pageResult;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.newtotuch.framework.service.system.UserService#doFindByCriteria(com
	 * .lion.framework.common.QueryCriteria)
	 */
	@Override
	public PageResult<User> doFindByCriteria(QueryCriteria criteria) {
		String queryEntry = " from User ";

		String[] whereBodies = { "username like :username", "employeeCode like :employeeCode","email like :email" };

		String fromJoinSubClause = "";

		Map<String, Object> params = criteria.getQueryCondition();

		String orderField = criteria.getOrderField();

		String orderDirection = criteria.getOrderDirection();

		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause,
				whereBodies, orderField, orderDirection, params);

		int pageSize = criteria.getPageSize();

		int startIndex = criteria.getStartIndex();
		System.out.println("11111111111111"+pageSize+"//"+startIndex);
		PageResult<User> pageResult = this.userDao.query(hql, 
				HqlUtils.generateCountHql(hql, null), params, startIndex, 
				pageSize);
		System.out.println("222222222222222222222"+pageResult);
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
				Group group = groupDao.findById(groupId);
				if (group != null) {
					user.getGroups().remove(group);
				}
			}
		}

		if (targetGroupIds != null && targetGroupIds.size() > 0) {
			for (Long groupId : targetGroupIds) {
				Group group = groupDao.findById(groupId);
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
				Role role = this.roleDao.findById(roleId);
				if (role != null) {
					user.getRoles().remove(role);
				}
			}
		}

		if (targetRoleIds != null && targetRoleIds.size() > 0) {
			for (Long roleId : targetRoleIds) {
				Role role = this.roleDao.findById(roleId);
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
