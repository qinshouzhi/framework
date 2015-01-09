/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: UserController.java 9552 2014-2-22 下午05:32:34 WangLijun$
 */
package com.newtouch.lion.admin.web.controller.system.user;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.newtouch.lion.admin.web.model.system.user.PasswordVo;
import com.newtouch.lion.admin.web.model.system.user.UserVo;
import com.newtouch.lion.common.lang.LongUtils;
import com.newtouch.lion.json.JSONParser;
import com.newtouch.lion.model.system.Group;
import com.newtouch.lion.model.system.Resource;
import com.newtouch.lion.model.system.Role;
import com.newtouch.lion.model.system.User;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.system.GroupService;
import com.newtouch.lion.service.system.PasswordEncoderService;
import com.newtouch.lion.service.system.ResourceService;
import com.newtouch.lion.service.system.RoleService;
import com.newtouch.lion.service.system.UserService;
import com.newtouch.lion.tree.TreeNode;
import com.newtouch.lion.util.ResourceConvertUtil;
import com.newtouch.lion.util.ResourceTreeUtil;
import com.newtouch.lion.web.constant.ConstantMessage;
import com.newtouch.lion.web.servlet.view.support.BindMessage;
import com.newtouch.lion.web.servlet.view.support.BindResult;
import com.newtouch.lion.web.shiro.session.LoginSecurityUtil;

/**
 * <p>
 * Title: 后台管理登录用户控制类
 * </p>
 * <p>
 * Description: 后台管理登录用户控制类
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
@Controller(value = "sysUserController")
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(super.getClass());
	/** 默认排序字段 */
	private static final String DEFAULT_ORDER_FILED_NAME = "id";
	/** 密码修改 */
	private static final String EDIT_USER_PASSWORD_RETURN = "/system/user/pwdindex";
	/** 我的资源 */
	private static final String USERINFO_RETURN = "/system/user/userinfo";
	/** 首页 */
	private static final String INDEX_RETURN = "/system/user/index";
	/** 编辑对话框 */
	private static final String EDIT_DIALOG_RETURN = "/system/user/editdialog";
	/** 授权对话框 */
	private static final String AUTH_DIALOG_RETURN = "/system/user/authdialog";
	/** 首页列表名称 */
	private static final String INDEX_TB = "userlist_dt";
	/** 已授权组 */
	private static final String AUTH_USER_GROUPS_TB = "usergroup_tb";
	/** 已关联用色 */
	private static final String AUTH_USER_ROLES_LISTS = "userrole_tb";
	/** 所有关联角色 */
	private static final String AUTH_ROLES_TB = "authrole_tb";

	/** 所有关联角色 */
	private static final String AUTH_GROUPS_TB = "authgroup_tb";
	/** 默认密码 */
	private static final String DEFAULT_PASSWORD = "111aaa";
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private PasswordEncoderService passwordEncoderService;
	@Autowired
	private ResourceService resourceService;

	/** 首页显示 */
	@RequestMapping(value = "/system/user/index")
	public String index() {
		return INDEX_RETURN;
	}

	/** 添加提交 */
	@RequestMapping(value = "/system/user/add")
	@ResponseBody
	public ModelAndView add(@Valid @ModelAttribute("userVo") UserVo userVo,
			Errors errors, ModelAndView modelAndView) {

		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}
		User user = new User();

		BeanUtils.copyProperties(userVo, user);

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, 1);// TODO 默认账户有效期为一年；

		Calendar credentialExpiredCalendar = Calendar.getInstance();
		credentialExpiredCalendar.add(Calendar.MONTH, 3);// TODO 默认密码有效期为3个月；

		user.setAccountExpiredDate(calendar.getTime());
		user.setCredentialExpiredDate(credentialExpiredCalendar.getTime());
		String passwordEncoder = passwordEncoderService.encodePassword(
				DEFAULT_PASSWORD, null);
		// TODO 密码加密码
		user.setPassword(passwordEncoder);
		// 将登录用户转换为小写
		user.setUsername(user.getUsername().toLowerCase());

		userService.doCreateUser(user);

		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, ConstantMessage.ADD_SUCCESS_MESSAGE_CODE);
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}

	/** 授权 对话框 */
	@RequestMapping(value = "/system/user/authdialog")
	public String authDialog(@RequestParam(required = false) Long id,
			Model model) {
		User user = this.userService.doFindById(id);
		model.addAttribute("user", user);
		return AUTH_DIALOG_RETURN;
	}

	/** 显示已关联的用户组 */
	@RequestMapping(value = "/system/user/authgroups")
	@ResponseBody
	public String authGroups(@RequestParam(required = false) Long id) {
		return this.userService
				.doFindAllAuthGroupsById(id, AUTH_USER_GROUPS_TB);
	}

	/** 显示已关联的角色 */
	@RequestMapping(value = "/system/user/authroles")
	@ResponseBody
	public String authRoles(@RequestParam(required = false) Long id) {
		return this.userService.doFindAllAuthRole(id, AUTH_USER_ROLES_LISTS);
	}

	/** 显示所有的角色 */
	@RequestMapping(value = "/system/user/roles")
	@ResponseBody
	public String roles() {
		QueryCriteria queryCriteria = new QueryCriteria();
		queryCriteria.setPageSize(99999999);
		return this.roleService.doFindByCriteria(queryCriteria, AUTH_ROLES_TB);
	}

	/** 显示所有的角色 */
	@RequestMapping(value = "/system/user/groups")
	@ResponseBody
	public String groups() {
		QueryCriteria queryCriteria = new QueryCriteria();
		queryCriteria.setPageSize(99999999);
		return this.groupService
				.doFindAuthGroups(queryCriteria, AUTH_GROUPS_TB);
	}

	/** 为用户添加用户组集合 */
	@RequestMapping(value = "/system/user/addgroups")
	@ResponseBody
	public ModelAndView addGroups(@RequestParam(required = false) Long userId,
			@RequestParam(required = false) String groupId,
			ModelAndView modelAndView) {
		// TODO 验证输入
		User user = this.userService.doFindById(userId);
		List<Long> targetGroupIds = LongUtils.parserStringToLong(groupId,LongUtils.SPARATOR_COMMA);
		List<Long> deleteGroupIds = new ArrayList<Long>();
		for (Group group : user.getGroups()) {
			if (targetGroupIds.contains(group.getId())) {
				targetGroupIds.remove(group.getId());
			} else {
				deleteGroupIds.add(group.getId());
			}
		}
		logger.info("targetGroupIds:{}", targetGroupIds);
		logger.info("deleteGroupIds:{}", deleteGroupIds);

		this.userService
				.doAuthGroupToUser(targetGroupIds, deleteGroupIds, user);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, ConstantMessage.ADD_SUCCESS_MESSAGE_CODE);
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}

	/** 为用户添加用户组集合 */
	@RequestMapping(value = "/system/user/addroles")
	@ResponseBody
	public ModelAndView addRoles(@RequestParam(required = false) Long userId,
			@RequestParam(required = false) String roleId,
			ModelAndView modelAndView) {
		// TODO 验证输入
		User user = this.userService.doFindById(userId);
		List<Long> targetRoleIds = LongUtils.parserStringToLong(roleId,
				LongUtils.SPARATOR_COMMA);
		List<Long> deleteRoleIds = new ArrayList<Long>();
		for (Role role : user.getRoles()) {
			if (targetRoleIds.contains(role.getId())) {
				targetRoleIds.remove(role.getId());
			} else {
				deleteRoleIds.add(role.getId());
			}
		}
		logger.info("targetRoleIds:{}", targetRoleIds);
		logger.info("deleteRoleIds:{}", deleteRoleIds);
		this.userService.doAuthRoleToUser(targetRoleIds, deleteRoleIds, user);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, ConstantMessage.ADD_SUCCESS_MESSAGE_CODE);
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}

	/** 编辑对话框 */
	@RequestMapping(value = "/system/user/editdialog")
	public String editDialog(@RequestParam(required = false) Long id,
			Model model) {
		User user = this.userService.doFindById(id);
		model.addAttribute("user", user);
		return EDIT_DIALOG_RETURN;
	}

	/** 编辑提交 */
	@RequestMapping(value = "/system/user/edit")
	@ResponseBody
	public ModelAndView edit(@Valid @ModelAttribute("userVo") UserVo userVo,
			Errors errors, ModelAndView modelAndView) {

		User user = null;
		if (userVo.getId() != null) {
			user = this.userService.doFindById(userVo.getId());
			if (user == null) {
				errors.reject(ConstantMessage.EDIT_ISEMPTY_FAIL_MESSAGE_CODE);
			}
		} else {
			errors.reject(ConstantMessage.EDIT_ISEMPTY_FAIL_MESSAGE_CODE);
		}

		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}
		userVo.setAccountExpiredDate(user.getAccountExpiredDate());
		userVo.setCredentialExpiredDate(user.getCredentialExpiredDate());
		userVo.setPassword(user.getPassword());

		BeanUtils.copyProperties(userVo, user);
		// 将登录用户转换为小写
		user.setUsername(user.getUsername().toLowerCase());
		this.userService.doUpdate(user);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS,
				ConstantMessage.EDIT_SUCCESS_MESSAGE_CODE);
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}

	/** 列表显示 */
	@RequestMapping(value = "/system/user/lists")
	@ResponseBody
	public String lists(HttpServletRequest servletRequest, Model model,
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "15") int rows,
			@RequestParam(required = false) String sort,
			@RequestParam(required = false) String order,
			@RequestParam(required = false) String username) {
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
		if (StringUtils.isNotEmpty(username)) {
			queryCriteria.addQueryCondition("username", "%" + username + "%");
		}
		return this.userService.doFindByCriteria(queryCriteria, INDEX_TB);
	}

	@RequestMapping(value = "/system/user/pwdindex")
	public String loadUserEditPasswordPage(HttpServletRequest request,
			Model model) {
		User user=LoginSecurityUtil.getUser();
		// 获取用户登录的IP地址
		//userDetails.setIpAddress(IPAddressUtil.getIPAddress(request));
		model.addAttribute("userDetails", user);
		return EDIT_USER_PASSWORD_RETURN;
	}

	@RequestMapping(value = "/system/user/edituserpwd")
	@ResponseBody
	public ModelAndView editUserPassword(
			@Valid @ModelAttribute("passwordVo") PasswordVo passwordVo,
			Errors errors, ModelAndView modelAndView) {
		if (StringUtils.isEmpty(passwordVo.getOldPwd())) {
			errors.rejectValue("oldPwd", null, "请输入旧密码");
		}

		if (StringUtils.isEmpty(passwordVo.getPwd())) {
			errors.rejectValue("pwd", null, "请输入新的密码");
		}

		if (StringUtils.isEmpty(passwordVo.getRePwd())) {
			errors.rejectValue("pwd", null, "请输入新的确认密码");
		}
		/* 判断旧密码、新密码、确认新密码不能为空 */
		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}

		// 判断旧密码不能新密码相同
		if (passwordVo.getOldPwd().equals(passwordVo.getPwd())) {
			errors.rejectValue("pwd", null, "新密码不能和旧密码相同，请重新输入");
		}
		// 判断两次输入的新密码是否一致
		if (!passwordVo.getPwd().equals(passwordVo.getRePwd())) {
			errors.rejectValue("pwd", null, "您输入的旧密码不正确，请重新输入");
		}
		/* 返回旧密码不能与新密码相同，及新密码和确认新密码是否一致 */
		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}

		User user =LoginSecurityUtil.getUser();
		// 获取用户登录的IP地址
		user = this.userService.doFindById(user.getId());
		String passwordEncoder = passwordEncoderService.encodePassword(passwordVo.getOldPwd(), null);
		if (!passwordEncoder.equals(user.getPassword())) {
			// ValidationUtils.rejectIfEmpty(errors,
			// "oldPwd",null,"您输入的旧密码不正确，请重新输入");
			errors.rejectValue("oldPwd", null, "您输入的旧密码不正确，请重新输入");
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}
		String newPasswordEncoder = this.passwordEncoderService.encodePassword(
				passwordVo.getPwd(), null);
		user.setPassword(newPasswordEncoder);
		this.userService.doUpdate(user);

		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS,
				ConstantMessage.EDIT_SUCCESS_MESSAGE_CODE);
		modelAndView.addObject(BindMessage.SUCCESS, params);

		return modelAndView;
	}

	/** 我的信息 */
	@RequestMapping(value = "/system/user/userinfo")
	public String userInfo(Model model) {
	 
		model.addAttribute("user", LoginSecurityUtil.getUser());
		return USERINFO_RETURN;
	}

	/** 我的资源 */
	@RequestMapping(value = "/system/user/myresource")
	@ResponseBody
	public String myResource() {
		User user =LoginSecurityUtil.getUser();
		
		List<Resource> resources = resourceService.doFindFirstLevel();

		List<Resource> userResources = this.resourceService.doFindByUserId(user.getId());

		Map<Long, Resource> menuResourcesMap = ResourceConvertUtil.convertListToMap(userResources);

		List<TreeNode> children = ResourceTreeUtil.resourceAttrUser(resources,menuResourcesMap, Boolean.TRUE, 0);

		Set<String> properties = new HashSet<String>();
		
		properties.add("id");
		properties.add("text");
		properties.add("checked");
		properties.add("state");
		properties.add("path");
		properties.add("attributes");
		properties.add("children");
		String result = JSONParser.toJSONString(children, properties);
		return result;
	}
}
