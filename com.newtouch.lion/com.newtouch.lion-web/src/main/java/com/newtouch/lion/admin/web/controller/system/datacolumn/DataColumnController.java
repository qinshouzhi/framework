/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: DataColumnController.java 9552 2014-3-31 下午04:55:04 WangLijun$
 */
package com.newtouch.lion.admin.web.controller.system.datacolumn;

import java.util.HashMap;
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

import com.newtouch.lion.admin.web.model.system.datacolumn.DataColumnVo;
import com.newtouch.lion.model.datagrid.DataColumn;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.datagrid.DataColumnService;
import com.newtouch.lion.web.constant.ConstantMessage;
import com.newtouch.lion.web.servlet.view.support.BindMessage;
import com.newtouch.lion.web.servlet.view.support.BindResult;

/**
 * <p>
 * Title: 表格列表详细管控制类
 * </p>
 * <p>
 * Description: 表格列表详细管控制类
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
@Controller(value = "sysDataColumnController")
public class DataColumnController {
	private final Logger logger = LoggerFactory.getLogger(super.getClass());
	/** 默认排序字段 */
	private static final String DEFAULT_ORDER_FILED_NAME = "id";
	/** 首页返回路径 */
	private static final String INDEX_RETURN = "/system/datacolumn/index";
	/** 新增对话返回路径 */
	private static final String ADD_DIALOG_RETURN = "/system/datacolumn/adddialog";
	/** 修改对话返回路径 */
	private static final String EDIT_DIALOG_RETURN = "/system/datacolumn/editdialog";
	/** 首页显示列表名称 */
	private static final String INDEX_TB = "datacolumn_tb";
	@Autowired
	private DataColumnService dataColumnService;

	/** 新增的对话框 */
	@RequestMapping(value = "/system/datacolumn/adddialog")
	public String addDialog() {
		return ADD_DIALOG_RETURN;
	}

	/** 删除 */
	@RequestMapping(value = "/system/datacolumn/delete")
	@ResponseBody
	public ModelAndView delete(@RequestParam Long id, ModelAndView modelAndView) {
		Map<String, String> params = new HashMap<String, String>();
		int updateRow = this.dataColumnService.doDeleteById(id);
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

	/** 添加 */
	@RequestMapping(value = "/system/datacolumn/add")
	@ResponseBody
	public ModelAndView add(
			@Valid @ModelAttribute("dataGridVo") DataColumnVo dataColumnVo,
			Errors errors, ModelAndView modelAndView) {
		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}
		DataColumn dataColumn = new DataColumn();
		BeanUtils.copyProperties(dataColumnVo, dataColumn);
		this.dataColumnService.doCreateDataColumn(dataColumn);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, ConstantMessage.ADD_SUCCESS_MESSAGE_CODE);
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}

	/** 编辑对话框 */
	@RequestMapping(value = "/system/datacolumn/editdialog")
	public String editDialog(@RequestParam Long id, Model model) {
		if (id != null) {
			DataColumn dataColumn = this.dataColumnService.doGetById(id);
			model.addAttribute("dataColumn", dataColumn);
		} else {
			logger.error("Eidt Object id is not null!");
		}
		return EDIT_DIALOG_RETURN;
	}

	/** 编辑 */
	@RequestMapping(value = "/system/datacolumn/edit")
	@ResponseBody
	public ModelAndView edit(
			@Valid @ModelAttribute("d") DataColumnVo dataColumnVo,
			Errors errors, ModelAndView modelAndView) {

		DataColumn dataColumn = null;
		if (dataColumnVo.getId() != null) {
			dataColumn = this.dataColumnService.doGetById(dataColumnVo.getId());
			if (dataColumn == null) {
				errors.reject(ConstantMessage.EDIT_ISEMPTY_FAIL_MESSAGE_CODE);
			}
		} else {
			errors.reject(ConstantMessage.EDIT_ISEMPTY_FAIL_MESSAGE_CODE);
		}

		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}
		BeanUtils.copyProperties(dataColumnVo, dataColumn);

		this.dataColumnService.doUpdate(dataColumn);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS,
				ConstantMessage.EDIT_SUCCESS_MESSAGE_CODE);
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}

	/** 首页显示 */
	@RequestMapping(value = "/system/datacolumn/index")
	public String index() {
		return INDEX_RETURN;
	}

	/** 列表显示 */
	@RequestMapping(value = "/system/datacolumn/lists")
	@ResponseBody
	public String lists(HttpServletRequest servletRequest, Model model,
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "15") int rows,
			@RequestParam(required = false) String sort,
			@RequestParam(required = false) String order,
			@RequestParam(required = false) Long dataGridId) {
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
		if (dataGridId != null) {
			queryCriteria.addQueryCondition("dataGridId", dataGridId);
		}
		return this.dataColumnService.doFindByCriteria(queryCriteria, INDEX_TB);
	}

}
