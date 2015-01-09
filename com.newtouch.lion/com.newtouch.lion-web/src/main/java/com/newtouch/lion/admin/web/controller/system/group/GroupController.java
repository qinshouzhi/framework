/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: GroupController.java 9552 2014-3-31 上午11:01:46 WangLijun$
 */
package com.newtouch.lion.admin.web.controller.system.group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.newtouch.lion.admin.web.model.system.group.GroupVo;
import com.newtouch.lion.common.lang.LongUtils;
import com.newtouch.lion.model.system.Group;
import com.newtouch.lion.model.system.Role;
import com.newtouch.lion.model.system.User;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.system.GroupService;
import com.newtouch.lion.service.system.RoleService;
import com.newtouch.lion.service.system.UserService;
import com.newtouch.lion.web.constant.ConstantMessage;
import com.newtouch.lion.web.servlet.view.support.BindMessage;
import com.newtouch.lion.web.servlet.view.support.BindResult;

/**
 * <p>
 * Title: 用户组管理
 * </p>
 * <p>
 * Description: 用户组管理包含添加、删除、修改及用户组授权等
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: NewTouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
@Controller
public class GroupController {

	private final Logger logger = LoggerFactory.getLogger(super.getClass());
	/** 默认排序字段名称 */
	private static final String DEFAULT_ORDER_FILED_NAME = "id";
	/** 默认列表 */
	public static final String INDEX_LISTS_TB = "sys_group_list_tb";
	/** 所有用户组列表名称 */
	private static final String AUTH_GROUP_USERS__TB = "sys_authuserlistforgroup_tb";
	/** 所有角色列表名称 */
	private static final String AUTH_GROUP_ROLES_TB = "sys_authrolelistforgroup_tb";
	/** 所有用户组列表名称 */
	private static final String AUTH_USERS_TB = "sys_authuserforgroup_tb";
	/** 所有角色列表名称 */
	private static final String AUTH_ROLES_TB = "sys_authroleforgroup_tb";
	/** 用户组管理首页 */
	public static final String INDEX_RETURN = "/system/group/index";
	/** 添加页面 */
	public static final String ADD_DIALOG_RETURN = "/system/group/adddialog";
	/** 编辑页面 */
	public static final String EDIT_DIALOG_RETURN = "/system/group/editdialog";
	/** 授权页面 */
	public static final String AUTH_DIALOG_RETURN = "/system/group/authdialog";

	@Autowired
	private GroupService groupService;
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	/** 用户组数据添加对话框 */
	@RequestMapping(value = "/system/group/adddialog")
	public String addDialog() {
		return ADD_DIALOG_RETURN;
	}

	/** 用户组数据添加保存 */
	@RequestMapping(value = "/system/group/add")
	@ResponseBody
	public ModelAndView add(@Valid @ModelAttribute("group") GroupVo groupVo,
			Errors errors, ModelAndView modelAndView) {

		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}

		Group group = new Group();
		BeanUtils.copyProperties(groupVo, group);
		groupService.doCreateGroup(group);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, ConstantMessage.ADD_SUCCESS_MESSAGE_CODE);
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}

	/** 用户组编辑对话框 */
	@RequestMapping(value = "/system/group/editdialog")
	public String editDialog(@RequestParam Long id, Model model) {
		if (id != null) {
			Group group = groupService.doFindById(id);
			model.addAttribute("group", group);
		} else {
			logger.error("Eidt Object id is not null!");
		}
		return EDIT_DIALOG_RETURN;
	}

	/** 编辑用户组 */
	@RequestMapping(value = "/system/group/edit")
	@ResponseBody
	public ModelAndView edit(@Valid @ModelAttribute("group") GroupVo groupVo,
			Errors errors, ModelAndView modelAndView) {

		Group group = null;
		if (groupVo.getId() != null) {
			group = this.groupService.doFindById(groupVo.getId());
			if (group == null) {
				errors.reject(ConstantMessage.EDIT_ISEMPTY_FAIL_MESSAGE_CODE);
			}
		} else {
			errors.reject(ConstantMessage.EDIT_ISEMPTY_FAIL_MESSAGE_CODE);
		}

		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}

		BeanUtils.copyProperties(groupVo, group);
		groupService.doUpdate(group);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS,
				ConstantMessage.EDIT_SUCCESS_MESSAGE_CODE);
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}

	/** 用户组授权对话框 */
	@RequestMapping(value = "/system/group/authdialog")
	public String authDialog(@RequestParam Long id, Model model) {
		if (id != null) {
			Group group = groupService.doFindById(id);
			model.addAttribute("group", group);
		} else {
			logger.error("Eidt Object id is not null!");
		}
		return AUTH_DIALOG_RETURN;
	}

	/** 授权到用户 */
	@RequestMapping(value = "/system/group/addusertogroup")
	@ResponseBody
	public ModelAndView addUsers(@RequestParam(required = false) Long groupId,
			@RequestParam(required = false) String userId,
			ModelAndView modelAndView) {
		// TODO 验证输入
		Group group = this.groupService.doFindById(groupId);
		List<Long> targetUserIds = LongUtils.parserStringToLong(userId,LongUtils.SPARATOR_COMMA);
		List<Long> deleteUserIds = new ArrayList<Long>();
		for (User user : group.getUsers()) {
			if (targetUserIds.contains(user.getId())) {
				targetUserIds.remove(user.getId());
			} else {
				deleteUserIds.add(user.getId());
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("targetUserIds[]：" + targetUserIds.toString());
			logger.info("deleteUserIds[]:" + deleteUserIds.toString());
		}
		this.groupService.idoAuthUserToGroup(targetUserIds, deleteUserIds,
				group);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, ConstantMessage.ADD_SUCCESS_MESSAGE_CODE);
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}

	/** 授权到角色 */
	@RequestMapping(value = "/system/group/addroletogroup")
	@ResponseBody
	public ModelAndView addRoles(@RequestParam(required = false) Long groupId,
			@RequestParam(required = false) String roleId,
			ModelAndView modelAndView) {
		// TODO 验证输入
		Group group = this.groupService.doFindById(groupId);
		List<Long> targetRoleIds = LongUtils.parserStringToLong(roleId,LongUtils.SPARATOR_COMMA);
		List<Long> deleteRoleIds = new ArrayList<Long>();
		for (Role role : group.getRoles()) {
			if (targetRoleIds.contains(role.getId())) {
				targetRoleIds.remove(role.getId());
			} else {
				deleteRoleIds.add(role.getId());
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("targetUserIds[]：" + targetRoleIds.toString());
			logger.info("deleteUserIds[]:" + deleteRoleIds.toString());
		}
		this.groupService
				.doAuthRoleToGroup(targetRoleIds, deleteRoleIds, group);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, ConstantMessage.ADD_SUCCESS_MESSAGE_CODE);
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}

	/** 查询用户组已授权的角色 */
	@RequestMapping(value = "/system/group/authroles")
	@ResponseBody
	public String authRoles(@RequestParam(required = false) Long id) {
		return this.groupService.doFindAuthRolesById(id, AUTH_GROUP_ROLES_TB);
	}

	/** 查询用户组已授权的用户 */
	@RequestMapping(value = "/system/group/authusers")
	@ResponseBody
	public String authUsers(@RequestParam(required = false) Long id) {
		return this.groupService.doFindAuthUsersById(id, AUTH_GROUP_USERS__TB);
	}

	/** 查询所有的用户 */
	@RequestMapping(value = "/system/group/users")
	@ResponseBody
	public String users() {
		QueryCriteria queryCriteria = new QueryCriteria();
		queryCriteria.setPageSize(99999999);
		return this.userService.doFindAllByCriteria(queryCriteria,
				AUTH_USERS_TB);
	}

	/** 查询所有的角色 */
	@RequestMapping(value = "/system/group/roles")
	@ResponseBody
	public String roles() {
		QueryCriteria queryCriteria = new QueryCriteria();
		queryCriteria.setPageSize(99999999);
		return this.roleService.doFindByCriteria(queryCriteria, AUTH_ROLES_TB);
	}

	@RequestMapping(value = "/system/group/index")
	public String index() {
		return INDEX_RETURN;
	}

	/** 删除用户组 */
	@RequestMapping(value = "/system/group/delete")
	@ResponseBody
	public ModelAndView delete(@RequestParam Long id, ModelAndView modelAndView) {
		Map<String, String> params = new HashMap<String, String>();
		int updateRow = this.groupService.doDeleteById(id);
		if (updateRow > 0) {
			params.put(BindResult.SUCCESS,
					ConstantMessage.DELETE_SUCCESS_MESSAGE_CODE);
		} else {
			params.put(BindResult.SUCCESS,
					ConstantMessage.DELETE_FAIL_MESSAGE_CODE);
		}
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}

	@RequestMapping(value = "/system/group/lists")
	@ResponseBody
	public String list(HttpServletRequest servletRequest, Model model,
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "15") int rows,
			@RequestParam(required = false) String sort,
			@RequestParam(required = false) String order,
			@RequestParam(required = false) String nameZh) {
		QueryCriteria queryCriteria = new QueryCriteria();
		// 设置分页 启始页
		queryCriteria.setStartIndex(rows * (page - 1));
		// 每页大小
		queryCriteria.setPageSize(rows);
		// 设置排序字段及排序方向
		if (StringUtils.isNotEmpty(sort) && StringUtils.isNotEmpty(order)) {
			queryCriteria.setOrderField(sort);
			queryCriteria.setOrderDirection(order);
		} else {
			queryCriteria.setOrderField(DEFAULT_ORDER_FILED_NAME);
			queryCriteria.setOrderDirection(QueryCriteria.ASC);
		}
		// 查询条件 参数类型
		if (StringUtils.isNotEmpty(nameZh)) {
			queryCriteria.addQueryCondition("nameZh", "%" + nameZh + "%");
		}
		return this.groupService
				.doFindAuthGroups(queryCriteria, INDEX_LISTS_TB);
	}
}
