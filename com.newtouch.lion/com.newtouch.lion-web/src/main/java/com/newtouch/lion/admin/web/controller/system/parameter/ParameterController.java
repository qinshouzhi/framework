/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: ParameterController.java 9552 2014-2-18 下午01:28:48 WangLijun$
 */
package com.newtouch.lion.admin.web.controller.system.parameter;

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

import com.newtouch.lion.admin.web.model.system.parameter.ParameterVo;
import com.newtouch.lion.common.date.DateUtil;
import com.newtouch.lion.json.JSONParser;
import com.newtouch.lion.model.datagrid.DataColumn;
import com.newtouch.lion.model.system.Parameter;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.datagrid.DataColumnService;
import com.newtouch.lion.service.system.ParameterService;
import com.newtouch.lion.web.servlet.view.support.BindMessage;
import com.newtouch.lion.web.servlet.view.support.BindResult;

/**
 * <p>
 * Title: 系统参数控制类
 * </p>
 * <p>
 * Description: 系统参数控制类
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
public class ParameterController {

	@SuppressWarnings("unused")
	private final Logger logger = LoggerFactory.getLogger(super.getClass());

	/** 参数首页 */
	private static final String INDEX_RETURN = "/system/parameter/index";
	/** 参数添加首页 */
	private static final String ADD_DIALOG_RETURN = "/system/parameter/adddialog";
	/** 参数编辑首页 */
	private static final String EDIT_DIALOG_RETURN = "/system/parameter/editdialog";
	/** 默认排序字段名称 */
	private static final String DEFAULT_ORDER_FILED_NAME = "id";

	@Autowired
	private ParameterService parameterService;

	@Autowired
	protected DataColumnService dataColumnService;

	@RequestMapping(value = "/system/parameter/index")
	public String index(HttpServletRequest servletRequest, Model model) {
		return INDEX_RETURN;
	}

	@RequestMapping(value = "/system/parameter/editdialog")
	public String editDialog(@RequestParam(required = false) Long id,
			Model model) {
		Parameter parameter = parameterService.doFindById(id);
		model.addAttribute("parameter", parameter);
		return EDIT_DIALOG_RETURN;
	}

	@RequestMapping(value = "/system/parameter/adddialog")
	public String addDialog(HttpServletRequest servletRequest, Model model) {
		return ADD_DIALOG_RETURN;
	}

	@RequestMapping(value = "/system/parameter/edit")
	@ResponseBody
	public ModelAndView edit(
			@Valid @ModelAttribute("parameter") ParameterVo parameterVo,
			Errors errors, ModelAndView modelAndView) {

		if (!errors.hasErrors() && parameterVo.getId() == null) {
			errors.reject("sys.parameter.form.id.empty");
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}
		Parameter parameter = parameterService.doFindById(parameterVo.getId());

		if (parameter == null) {
			errors.reject("sys.parameter.form.id.empty");
			return modelAndView;
		}

		if (!errors.hasErrors()
				&& this.isExistByNameEn(parameterVo.getNameEn(),
						parameter.getNameEn())) {
			errors.rejectValue(ParameterVo.NAMEEN,
					"sys.parameter.form.nameen.existed.message",
					new Object[] { parameterVo.getNameEn() }, null);
		}

		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}

		BeanUtils.copyProperties(parameterVo, parameter);
		parameterService.doUpdate(parameter);

		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, "sys.parameter.edit.success");
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}

	@RequestMapping(value = "/system/parameter/delete")
	@ResponseBody
	public ModelAndView delete(@RequestParam Long id, ModelAndView modelAndView) {
		Map<String, String> params = new HashMap<String, String>();
		int updateRow = this.parameterService.doDeleteById(id);
		if (updateRow > 0) {
			params.put(BindResult.SUCCESS, "sys.parameter.delete.success");
		} else {
			params.put(BindResult.SUCCESS, "sys.parameter.delete.fail");
		}
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}

	@RequestMapping(value = "/system/parameter/add")
	@ResponseBody
	public ModelAndView add(
			@Valid @ModelAttribute("parameter") ParameterVo parameterVo,
			Errors errors, ModelAndView modelAndView) {
		if (!errors.hasErrors()
				&& this.isExistByNameEn(parameterVo.getNameEn())) {
			errors.rejectValue(ParameterVo.NAMEEN,
					"sys.parameter.form.nameen.existed.message",
					new Object[] { parameterVo.getNameEn() }, null);
		}
		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}

		Parameter parameter = new Parameter();

		BeanUtils.copyProperties(parameterVo, parameter);
		parameterService.doCreate(parameter);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, "sys.parameter.add.success");
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}

	@RequestMapping(value = "/system/parameter/checkisexitnameen")
	@ResponseBody
	public String checkIsExistByNameEn(HttpServletRequest servletRequest,
			@RequestParam(required = false) String nameEn) {
		Boolean flag = this.isExistByNameEn(nameEn) == true ? false : true;
		return flag.toString();
	}

	private Boolean isExistByNameEn(String nameEn) {
		Boolean flag = false;
		if (StringUtils.isNotEmpty(nameEn)) {
			flag = parameterService.doIsExistByNameEn(nameEn.trim());
		}
		return flag;
	}

	private Boolean isExistByNameEn(String nameEn, String oldNameEn) {
		Boolean flag = false;
		if (StringUtils.isNotEmpty(nameEn) && !nameEn.equals(oldNameEn)) {
			flag = parameterService.doIsExistByNameEn(nameEn.trim());
		}
		return flag;
	}

	@RequestMapping(value = "/system/parameter/lists")
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
			queryCriteria.setOrderField(DEFAULT_ORDER_FILED_NAME);
			queryCriteria.setOrderDirection("ASC");
		}
		// 查询条件 参数类型
		if (StringUtils.isNotEmpty(type)) {
			queryCriteria.addQueryCondition("type", type);
		}

		PageResult<Parameter> pageResult = parameterService
				.doFindByCriteria(queryCriteria);

		Set<String> filterColumn = this.getColumns("sys_parameter_lists_tb");

		String result = this.getJSONString(pageResult.getTotalCount(),
				pageResult.getContent(), filterColumn,
				DateUtil.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS, Boolean.TRUE);

		return result;
	}

	/** 根据TableId配置DataGrid */
	protected Set<String> getColumns(String tableId) {
		List<DataColumn> dataColumns = this.dataColumnService
				.doFindByTableId(tableId);
		Set<String> properties = new HashSet<String>();
		for (DataColumn dataColumn : dataColumns) {
			properties.add(dataColumn.getField());
		}
		return properties;
	}

	protected String getJSONString(Long total, List<Parameter> objects,
			Set<String> properties, String datePattern,
			Boolean includeProperties) {
		StringBuffer sb = new StringBuffer();
		sb.append("{\"total\":");
		sb.append(total);
		sb.append(",\"rows\":");
		if (objects == null) {
			// log.info("-----objects is null");
		}
		// log.info("objects.size():"+objects.size());
		String objectJSONStr = JSONParser.toJSONString(objects, datePattern,
				properties, includeProperties);
		sb.append(objectJSONStr);
		sb.append("}");
		return sb.toString();
	}
}
