/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: DataGridController.java 9552 2014-3-31 下午04:53:44 WangLijun$
 */
package com.newtouch.lion.admin.web.controller.system.datagrid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.newtouch.lion.admin.web.model.system.datagrid.DataGridVo;
import com.newtouch.lion.data.DataTable;
import com.newtouch.lion.model.datagrid.DataGrid;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.datagrid.DataGridService;
import com.newtouch.lion.web.constant.ConstantMessage;
import com.newtouch.lion.web.servlet.view.support.BindMessage;
import com.newtouch.lion.web.servlet.view.support.BindResult;

/**
 * <p>
 * Title: 表格控件管理类
 * </p>
 * <p>
 * Description: 表格控件管理类
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
@Controller(value = "sysDataGridContorller")
@RequestMapping("/system/datagrid/")
public class DataGridController {
	private final Logger logger = LoggerFactory.getLogger(super.getClass());
	/** 默认排序字段 */
	private static final String DEFAULT_ORDER_FILED_NAME = "id";
	/** 首页返回路径 */
	private static final String INDEX_RETURN = "system/datagrid/index";
	/** 新增对话返回路径 */
	private static final String ADD_DIALOG_RETURN = "system/datagrid/dialog/add";
	/** 修改对话返回路径 */
	private static final String EDIT_DIALOG_RETURN = "system/datagrid/editdialog";

	/** 首页显示列表名称 */
	@SuppressWarnings("unused")
	private static final String INDEX_TB = "datagrid_dt";
	@Autowired
	private DataGridService dataGridService;
	
	/** 新增的对话框 */
	@RequestMapping(value = "dialog/add.htm")
	public String addDialog() {
		return ADD_DIALOG_RETURN;
	}

	/** 编辑对话框 */
	@RequestMapping(value = "editdialog")
	public String editDialog(@RequestParam Long id, Model model) {
		if (id != null) {
			DataGrid dataGrid = this.dataGridService.doGetById(id);
			model.addAttribute("dataGrid", dataGrid);
		} else {
			logger.error("Eidt Object id is not null!");
		}
		return EDIT_DIALOG_RETURN;
	}

	/** 首页显示 */
	@RequestMapping(value = "index")
	public String index() {
		return INDEX_RETURN;
	}

	/** DataGrid列表 */
	@RequestMapping(value = "combox")
	@ResponseBody
	public List<DataGrid> comobx() {
		return this.dataGridService.doFindAll();
	}

	/** 首页显示 */
	@RequestMapping(value = "comboxwithtype")
	@ResponseBody
	public String comobxByType(@RequestParam(required = false) String type) {
		return this.dataGridService.doFindComboxByType(type);
	}

	@RequestMapping(value = "delete")
	@ResponseBody
	public ModelAndView delete(@RequestParam Long id, ModelAndView modelAndView) {
		Map<String, String> params = new HashMap<String, String>();
		int updateRow = this.dataGridService.doDeleteById(id);
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

	@RequestMapping(value = "add")
	@ResponseBody
	public ModelAndView add(
			@Valid @ModelAttribute("dataGrid") DataGridVo dataGridVo,
			Errors errors, ModelAndView modelAndView) {
		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}
		DataGrid dataGrid = new DataGrid();
		BeanUtils.copyProperties(dataGridVo, dataGrid);
		this.dataGridService.doCreateDataGrid(dataGrid);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, ConstantMessage.ADD_SUCCESS_MESSAGE_CODE);
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}

	@RequestMapping(value = "checktableid")
	@ResponseBody
	public String checkTableId(String tableId) {
		Boolean flag = true;
		if (StringUtils.isEmpty(tableId)) {
			return flag.toString();
		}
		DataGrid dataGrid = this.dataGridService
				.doFindByTableId(tableId.trim());
		if (dataGrid != null && StringUtils.isNotEmpty(dataGrid.getTableId())) {
			flag = Boolean.FALSE;
		}
		return flag.toString();
	}

	@RequestMapping(value = "edit")
	@ResponseBody
	public ModelAndView edit(
			@Valid @ModelAttribute("dataGrid") DataGridVo dataGridVo,
			Errors errors, ModelAndView modelAndView) {

		DataGrid dataGrid = null;
		if (dataGridVo.getId() != null) {
			dataGrid = this.dataGridService.doGetById(dataGridVo.getId());
			if (dataGrid == null) {
				errors.reject(ConstantMessage.EDIT_ISEMPTY_FAIL_MESSAGE_CODE);
			}
		} else {
			errors.reject(ConstantMessage.EDIT_ISEMPTY_FAIL_MESSAGE_CODE);
		}

		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}
		BeanUtils.copyProperties(dataGridVo, dataGrid);

		this.dataGridService.doUpdate(dataGrid);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS,
				ConstantMessage.EDIT_SUCCESS_MESSAGE_CODE);
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}

	/** 列表显示 */
	@RequestMapping(value = "list")
	@ResponseBody
	public DataTable<DataGrid> lists(Model model,
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "15") int rows,
			@RequestParam(required = false) String sort,
			@RequestParam(required = false) String order,
			@RequestParam(required = false) String type,
			@RequestParam(required = false) String tableId) {
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
		if (StringUtils.isNotEmpty(tableId)) {
			queryCriteria.addQueryCondition("tableId", "%" + tableId + "%");
		}

		if (StringUtils.isNotEmpty(type)) {
			queryCriteria.addQueryCondition("type", type);
		}
		PageResult<DataGrid> pageResult = this.dataGridService.doFindByCriteria(queryCriteria);
		return pageResult.getDataTable();
	}

}
