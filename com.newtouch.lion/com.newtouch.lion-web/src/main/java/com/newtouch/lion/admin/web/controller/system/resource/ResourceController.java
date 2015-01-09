/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: ResourcecController.java 9552 2014-2-17 下午03:38:18 WangLijun$
 */
package com.newtouch.lion.admin.web.controller.system.resource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.newtouch.lion.admin.web.model.system.resource.ResourceVo;
import com.newtouch.lion.common.codelist.CodeListConstant;
import com.newtouch.lion.common.date.DateUtil;
import com.newtouch.lion.json.JSONParser;
import com.newtouch.lion.model.system.Resource;
import com.newtouch.lion.model.system.User;
import com.newtouch.lion.service.system.ResourceService;
import com.newtouch.lion.tree.TreeNode;
import com.newtouch.lion.util.ResourceConvertUtil;
import com.newtouch.lion.util.ResourceTreeUtil;
import com.newtouch.lion.web.constant.ConstantMessage;
import com.newtouch.lion.web.servlet.view.support.BindMessage;
import com.newtouch.lion.web.servlet.view.support.BindResult;
import com.newtouch.lion.web.shiro.session.LoginSecurityUtil;

/**
 * <p>
 * Title: 资源控制类
 * </p>
 * <p>
 * Description: 资源控制类
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
@Controller(value = "sysResourceController")
public class ResourceController {

	private final Logger logger = LoggerFactory.getLogger(super.getClass());

	private static final String INDEX_RETURN = "/system/resource/index";

	private static final String INDEX_TREE_TB = "sys_resource_lists_tb";

	private static final String ADD_DIALOG_RETURN = "/system/resource/adddialog";

	private static final String EDIT_DIALOG_RETURN = "/system/resource/editdialog";

	@Autowired
	private ResourceService resourceService;

	@RequestMapping(value = "/system/resource/adddialog")
	public String addDialog(@RequestParam(required = false) Long parentId,
			Model model) {
		if (parentId == null) {
			List<Resource> list = this.resourceService.doFindFirstLevel();
			if (!CollectionUtils.isEmpty(list)) {
				parentId = list.get(0).getId();
			}
		}
		model.addAttribute("parentId", parentId);
		return ADD_DIALOG_RETURN;
	}

	@RequestMapping("/system/resource/editdialog")
	public String editDialog(Model model,
			@RequestParam(required = false) Long id) {
		if (id != null) {
			Resource resource = this.resourceService.doFindById(id);
			model.addAttribute("resource", resource);
		} else {
			logger.error("Eidt Object id is not null!");
		}
		return EDIT_DIALOG_RETURN;
	}

	@RequestMapping(value = "/system/resource/add")
	@ResponseBody
	public ModelAndView add(
			@Valid @ModelAttribute("resourceVo") ResourceVo resourceVo,
			Errors errors, ModelAndView modelAndView) {
		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}
		Resource resource = new Resource();
		BeanUtils.copyProperties(resourceVo, resource);
		this.resourceService.doCreateResource(resource);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, ConstantMessage.ADD_SUCCESS_MESSAGE_CODE);
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}

	@RequestMapping(value = "/system/resource/edit")
	@ResponseBody
	public ModelAndView edit(
			@Valid @ModelAttribute("resourceVo") ResourceVo resourceVo,
			Errors errors, ModelAndView modelAndView) {
		Resource resource = null;
		if (resourceVo.getId() != null) {
			resource = this.resourceService.doFindById(resourceVo.getId());
			if (resource == null) {
				errors.reject(ConstantMessage.EDIT_ISEMPTY_FAIL_MESSAGE_CODE);
			}
		} else {
			errors.reject(ConstantMessage.EDIT_ISEMPTY_FAIL_MESSAGE_CODE);
		}

		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}
		BeanUtils.copyProperties(resourceVo, resource);
		this.resourceService.doUpdate(resource);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS,ConstantMessage.EDIT_SUCCESS_MESSAGE_CODE);
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}

	@RequestMapping(value = "/system/resource/delete")
	@ResponseBody
	public ModelAndView delete(@RequestParam Long id, ModelAndView modelAndView) {
		Map<String, String> params = new HashMap<String, String>();
		Resource resource = this.resourceService.doDelete(id);
		if (resource != null) {
			params.put(BindResult.SUCCESS,ConstantMessage.DELETE_SUCCESS_MESSAGE_CODE);
		} else {
			params.put(BindResult.SUCCESS,ConstantMessage.DELETE_FAIL_MESSAGE_CODE);
		}
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}

	@RequestMapping(value = "/system/resource/menutree")
	@ResponseBody
	public String menutree(HttpServletRequest request,@RequestParam Long resourceId, Model model) {
		User user =LoginSecurityUtil.getUser();
		List<Resource> resources=resourceService.doFindByParentId(resourceId);
		String[]  menuResourceType={CodeListConstant.RESTYPE_MODULE_MENU_CATEGORY,CodeListConstant.RESTYPE_MODULE_CATEGORY_ITEM};
		List<Resource>  userResources=resourceService.doFindByUserIdAndType(user.getId(), menuResourceType);
		Map<Long,Resource>  menuResourcesMap=ResourceConvertUtil.convertListToMap(userResources);
		List<TreeNode> children=ResourceTreeUtil.resourceAttr(resources, menuResourcesMap,Boolean.TRUE);
		Set<String>  properties=new HashSet<String>();		
		properties.add("id");		
		properties.add("text");		
		properties.add("checked");	
		properties.add("state");		
		properties.add("path");		
		properties.add("attributes");		
		properties.add("children");
		String result=JSONParser.toJSONString(children,properties);
		return result;
	}

	@RequestMapping(value = "/system/resource/comboxtree")
	@ResponseBody
	public String comboxTree() {
		List<Resource> resources = resourceService.doFindFirstLevel();
		Set<String> properties = new HashSet<String>();
		properties.add("id");
		properties.add("nameZh");
		properties.add("resources");
		properties.add("target");
		properties.add("path");
		properties.add("attributes");
		String result = JSONParser.toJSONString(resources,DateUtil.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS, properties,Boolean.TRUE);
		return result.replace("resources", "children").replace("nameZh", "text");
	}

	@RequestMapping(value = "/system/resource/index")
	public String index(HttpServletRequest request) {
		return INDEX_RETURN;
	}

	@RequestMapping(value = "/system/resource/lists")
	@ResponseBody
	public String lists() {
		return resourceService.doFindAllToTree(INDEX_TREE_TB);
	}

}
