/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: CodeTypeController.java 9552 2014-4-8 上午10:06:58 WangLijun$
 */
package com.newtouch.lion.admin.web.controller.system.code;

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

import com.newtouch.lion.admin.web.model.system.code.CodeTypeVo;
import com.newtouch.lion.json.JSONParser;
import com.newtouch.lion.model.system.CodeType;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.system.CodeTypeService;
import com.newtouch.lion.web.constant.ConstantMessage;
import com.newtouch.lion.web.servlet.view.support.BindMessage;
import com.newtouch.lion.web.servlet.view.support.BindResult;

/**
 * <p>
 * Title: IM-编码类型
 * </p>
 * <p>
 * Description: IM-编码类型-Controller
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
@Controller("sysCodeTypeController")
public class CodeTypeController {

	private final Logger logger = LoggerFactory.getLogger(super.getClass());

	private static final String DEFAULT_FILED_NAME = "id";

	private static final String INDEX_LIST_TB = "sys_codetype_lists_tb";

	/** 参数编辑首页 */
	private static final String EDIT_DIALOG_RETURN = "/system/codetype/editdialog";

	@Autowired
	private CodeTypeService codeTypeService;

	/**
	 * 编码类型下拉列表
	 * */
	@RequestMapping("/system/codetype/combox")
	@ResponseBody
	public String combox() {
		List<CodeType> codeTypes = this.codeTypeService.doFindAll();
		Set<String> filterColumn = new HashSet<String>();
		filterColumn.add("id");
		filterColumn.add("nameEn");
		filterColumn.add("nameZh");
		return JSONParser.toJSONString(codeTypes, filterColumn);
	}

	@RequestMapping(value = "/system/codetype/editdialog")
	public String editDialog(@RequestParam Long id, Model model) {
		if (id != null) {
			CodeType codeType = this.codeTypeService.doFindById(id);
			model.addAttribute("codeType", codeType);
		} else {
			logger.error("Eidt Object id is not null!");
		}
		return EDIT_DIALOG_RETURN;
	}

	@RequestMapping(value = "/system/codetype/add")
	@ResponseBody
	public ModelAndView add(
			@Valid @ModelAttribute("codeList") CodeTypeVo codeTypeVo,
			Errors errors, ModelAndView modelAndView) {
		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}
		CodeType codeType = new CodeType();
		BeanUtils.copyProperties(codeTypeVo, codeType);
		codeTypeService.doCreateCodeType(codeType);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, ConstantMessage.ADD_SUCCESS_MESSAGE_CODE);
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}

	/** 编辑 */
	@RequestMapping(value = "/system/codetype/edit")
	@ResponseBody
	public ModelAndView edit(
			@Valid @ModelAttribute("codeTypeVo") CodeTypeVo codeTypeVo,
			Errors errors, ModelAndView modelAndView) {

		CodeType codeType = null;
		if (codeTypeVo.getId() != null) {
			codeType = this.codeTypeService.doFindById(codeTypeVo.getId());
			if (codeType == null) {
				errors.reject(ConstantMessage.EDIT_ISEMPTY_FAIL_MESSAGE_CODE);
			}
		} else {
			errors.reject(ConstantMessage.EDIT_ISEMPTY_FAIL_MESSAGE_CODE);
		}

		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}

		BeanUtils.copyProperties(codeTypeVo, codeType);
		codeTypeService.doUpdate(codeType);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS,
				ConstantMessage.EDIT_SUCCESS_MESSAGE_CODE);
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}

	@RequestMapping(value = "/system/codetype/delete")
	@ResponseBody
	public ModelAndView delete(@RequestParam Long id, ModelAndView modelAndView) {
		Map<String, String> params = new HashMap<String, String>();
		int updateRow = this.codeTypeService.doDeleteById(id);
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

	@RequestMapping(value = "/system/codetype/lists")
	@ResponseBody
	public String list(HttpServletRequest servletRequest, Model model,
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "15") int rows,
			@RequestParam(required = false) String sort,
			@RequestParam(required = false) String order,
			@RequestParam(required = false) String type) {
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
			queryCriteria.setOrderField(DEFAULT_FILED_NAME);
			queryCriteria.setOrderDirection(QueryCriteria.ASC);
		}
		// 查询条件 参数类型
		if (StringUtils.isNotEmpty(type)) {
			queryCriteria.addQueryCondition("type", type);
		}
		String result = this.codeTypeService.doFindByCriteria(queryCriteria,
				INDEX_LIST_TB);
		return result;
	}
}
