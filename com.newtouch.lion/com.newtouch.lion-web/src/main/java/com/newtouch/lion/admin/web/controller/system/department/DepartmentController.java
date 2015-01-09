/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: DepartmentController.java 9552 2014-4-3 下午02:05:57 WangLijun$
 */
package com.newtouch.lion.admin.web.controller.system.department;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

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

import com.newtouch.lion.admin.web.model.system.department.DepartmentVo;
import com.newtouch.lion.model.system.Department;
import com.newtouch.lion.service.system.DepartmentService;
import com.newtouch.lion.web.constant.ConstantMessage;
import com.newtouch.lion.web.servlet.view.support.BindMessage;
import com.newtouch.lion.web.servlet.view.support.BindResult;

/**
 * <p>
 * Title: 部门管理控制器
 * </p>
 * <p>
 * Description: 部门管理包括添加、删除、查询、修改等功能
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
public class DepartmentController {

	private final Logger logger = LoggerFactory.getLogger(super.getClass());

	private static final String INDEX_LISTS_TB = "sys_department_lists";

	private static final String ADD_DIALOG_RETURN = "/system/department/adddialog";

	private static final String EDIT_DIALOG_RETURN = "/system/department/editdialog";

	@Autowired
	private DepartmentService departmentService;

	@RequestMapping("/system/department/adddialog")
	public String addAdialog(Model model,
			@RequestParam(required = false) Long parentId) {
		model.addAttribute("parentId", parentId);
		return ADD_DIALOG_RETURN;
	}

	@RequestMapping("/system/department/editdialog")
	public String editDialog(Model model,
			@RequestParam(required = false) Long id) {
		if (id != null) {
			Department department = this.departmentService
					.doFindDepartmentById(id);
			model.addAttribute("department", department);
		} else {
			logger.error("Eidt Object id is not null!");
		}
		return EDIT_DIALOG_RETURN;
	}

	@RequestMapping(value = "/system/department/add")
	@ResponseBody
	public ModelAndView add(
			@Valid @ModelAttribute("departmentVo") DepartmentVo departmentVo,
			Errors errors, ModelAndView modelAndView) {
		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}
		Department department = new Department();
		BeanUtils.copyProperties(departmentVo, department);
		this.departmentService.doCreateDepartment(department);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, ConstantMessage.ADD_SUCCESS_MESSAGE_CODE);
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}

	@RequestMapping(value = "/system/department/edit")
	@ResponseBody
	public ModelAndView edit(
			@Valid @ModelAttribute("departmentVo") DepartmentVo departmentVo,
			Errors errors, ModelAndView modelAndView) {
		Department department = null;
		if (departmentVo.getId() != null) {
			department = this.departmentService
					.doFindDepartmentById(departmentVo.getId());
			if (department == null) {
				errors.reject(ConstantMessage.EDIT_ISEMPTY_FAIL_MESSAGE_CODE);
			}
		} else {
			errors.reject(ConstantMessage.EDIT_ISEMPTY_FAIL_MESSAGE_CODE);
		}

		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}
		BeanUtils.copyProperties(departmentVo, department);
		this.departmentService.doUpdate(department);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS,
				ConstantMessage.EDIT_SUCCESS_MESSAGE_CODE);
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}

	@RequestMapping(value = "/system/department/delete")
	@ResponseBody
	public ModelAndView delete(@RequestParam Long id, ModelAndView modelAndView) {
		Map<String, String> params = new HashMap<String, String>();
		Department department = this.departmentService.doDelete(id);
		if (department != null) {
			params.put(BindResult.SUCCESS,
					ConstantMessage.DELETE_SUCCESS_MESSAGE_CODE);
		} else {
			params.put(BindResult.SUCCESS,
					ConstantMessage.DELETE_FAIL_MESSAGE_CODE);
		}
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}

	@RequestMapping("/system/department/lists")
	@ResponseBody
	public String lists() {
		return this.departmentService.doFindAll(INDEX_LISTS_TB);
	}

	@RequestMapping("/system/department/comboxtree")
	@ResponseBody
	public String comboxTree() {
		String jsonStr = departmentService.doFindFirstLevelToTree();
		jsonStr = jsonStr.replace("departments", "children");
		jsonStr = jsonStr.replace("nameZh", "text");
		return jsonStr;
	}
}
