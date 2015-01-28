/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: ParameterController.java 9552 2014-2-18 下午01:28:48 WangLijun$
 */
package com.newtouch.lion.admin.web.controller.system.parameter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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

import com.newtouch.lion.admin.web.model.system.parameter.ParameterVo;
import com.newtouch.lion.common.date.DateUtil;
import com.newtouch.lion.comparator.DataColumnComparator;
import com.newtouch.lion.data.DataTable;
import com.newtouch.lion.model.datagrid.DataColumn;
import com.newtouch.lion.model.datagrid.DataGrid;
import com.newtouch.lion.model.system.CodeList;
import com.newtouch.lion.model.system.CodeType;
import com.newtouch.lion.model.system.Parameter;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.datagrid.DataColumnService;
import com.newtouch.lion.service.datagrid.DataGridService;
import com.newtouch.lion.service.excel.ExcelExportService;
import com.newtouch.lion.service.system.CodeTypeService;
import com.newtouch.lion.service.system.ParameterService;
import com.newtouch.lion.web.controller.AbstractController;
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
@RequestMapping("/system/parameter/")
public class ParameterController extends AbstractController{
	
	private final Logger logger = LoggerFactory.getLogger(super.getClass());

	/** 参数首页 */
	private static final String INDEX_RETURN = "/system/parameter/index";
	/** 参数添加首页 */
	private static final String ADD_DIALOG_RETURN = "/system/parameter/dialog/add";
	/** 参数编辑首页 */
	private static final String EDIT_DIALOG_RETURN = "/system/parameter/dialog/edit";
	/** 默认排序字段名称 */
	private static final String DEFAULT_ORDER_FILED_NAME = "id";
	
	@Autowired
	private ParameterService parameterService;
	/**表格行数*/
	@Autowired
	protected DataColumnService dataColumnService;
	/**IM*/
	@Autowired
	private CodeTypeService codeTypeService;
	/**DataGrid表格*/
	@Autowired
	private DataGridService dataGridService;
	/**Excel通用导出*/
	@Autowired
	private ExcelExportService excelExportService;

	@RequestMapping(value = "index")
	public String index(HttpServletRequest servletRequest, Model model) {
		return INDEX_RETURN;
	}

	@RequestMapping(value = "dialogedit")
	public String editDialog(@RequestParam(required = false) Long id,
			Model model) {
		return EDIT_DIALOG_RETURN;
	}

	@RequestMapping(value = "dialog/add")
	public String addDialog(HttpServletRequest servletRequest, Model model) {
		return ADD_DIALOG_RETURN;
	}

	@RequestMapping(value = "edit")
	@ResponseBody
	public ModelAndView edit(
			@Valid @ModelAttribute("parameter") ParameterVo parameterVo,
			Errors errors, ModelAndView modelAndView) {
		modelAndView=this.getJsonView(modelAndView);
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
				&& this.isExistByNameEn(parameterVo.getNameEn(),parameter.getNameEn())) {errors.rejectValue(ParameterVo.NAMEEN,	"sys.parameter.form.nameen.existed.message",new Object[] { parameterVo.getNameEn() }, null);

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

	@RequestMapping(value = "delete")
	@ResponseBody
	public ModelAndView delete(@RequestParam Long id, ModelAndView modelAndView) {
		Map<String, String> params = new HashMap<String, String>();
		int updateRow = this.parameterService.doDeleteById(id);
		if (updateRow > 0) {
			params.put(BindResult.SUCCESS,"sys.parameter.delete.success");
		} else {
			params.put(BindResult.SUCCESS,"sys.parameter.delete.fail");
		}
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return this.getJsonView(modelAndView);
	}

	@RequestMapping(value = "add")
	@ResponseBody
	public ModelAndView add(
			@Valid @ModelAttribute("parameter") ParameterVo parameterVo,
			Errors errors, ModelAndView modelAndView) {
		if (!errors.hasErrors()&& this.isExistByNameEn(parameterVo.getNameEn())) {
			errors.rejectValue(ParameterVo.NAMEEN,
					"sys.parameter.form.nameen.existed.message",
					new Object[] { parameterVo.getNameEn() }, null);
		}
		//是否错误消息
		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return this.getJsonView(modelAndView);
		}
		Parameter parameter = new Parameter();

		BeanUtils.copyProperties(parameterVo, parameter);
		parameterService.doCreate(parameter);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, "sys.parameter.add.success");
		modelAndView.addObject(BindMessage.SUCCESS, params);
		
		return this.getJsonView(modelAndView);
	}

	@RequestMapping(value = "checkisexitnameen")
	@ResponseBody
	public String checkIsExistByNameEn(HttpServletRequest servletRequest,
			@RequestParam(required = false) String nameEn,@RequestParam(required=false) Long id) {
		Boolean flag=Boolean.FALSE;
		
		if(id==null){
			flag = this.isExistByNameEn(nameEn)? false : true;
		}else{
			Parameter parameter = parameterService.doFindById(id);
			if(parameter==null){
				flag = this.isExistByNameEn(nameEn)? false : true;
			}else{
				flag=this.isExistByNameEn(nameEn, parameter.getNameEn())?false:true;
			}
		}
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

	@RequestMapping(value = "list")
	@ResponseBody
	public DataTable<Parameter> list(HttpServletRequest servletRequest,
			Model model, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "15") int rows,
			@RequestParam(required = false) String sort,
			@RequestParam(required = false) String order,
			@ModelAttribute("parameter") ParameterVo parameterVo) {
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
		if (StringUtils.isNotEmpty(parameterVo.getType())) {
			queryCriteria.addQueryCondition("type", parameterVo.getType());
		}
		//查询条件 中文参数名称按模糊查询
		if(StringUtils.isNotEmpty(parameterVo.getNameZh())){
			queryCriteria.addQueryCondition("nameZh","%"+parameterVo.getNameZh()+"%");
		}

		PageResult<Parameter> pageResult = parameterService
				.doFindByCriteria(queryCriteria);
		return pageResult.getDataTable();
	}
	/****
	 * 
	 * @param tableId
	 * @param parameterVo
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "export")
	public ModelAndView exportExcel(@RequestParam(required=false) String tableId,@ModelAttribute("parameter") ParameterVo parameterVo,ModelAndView modelAndView){
		logger.info("in Excel导出");		
		String type="SystemParamter";
		
		DataGrid dataGrid=dataGridService.doFindByTableId(tableId);
		
		List<DataColumn> dataColumns = new ArrayList<DataColumn>(dataGrid.getColumns());
		
		Collections.sort(dataColumns, new DataColumnComparator());
		
		dataGrid.setSortColumns(dataColumns);
		
		QueryCriteria queryCriteria=new QueryCriteria();
		
		queryCriteria.setPageSize(99999);
		
		PageResult<Parameter> result=parameterService.doFindByCriteria(queryCriteria);
		
		
		CodeType codeType=codeTypeService.doFindTypeByNameEn(type);
		Map<Object, Object>  codeTypeMap=new HashMap<Object, Object>();
		for(CodeList codeList:codeType.getCodeLists()){
			codeTypeMap.put(codeList.getCodeValue(),codeList);
		}
		
		Map<String, Map<Object, Object>> fieldCodeTypes = new HashMap<String, Map<Object, Object>>();
		fieldCodeTypes.put("type",codeTypeMap);

		Map<String, String> dataFormats = new HashMap<String, String>();
		
		dataFormats.put("birthday", DateUtil.FORMAT_DATE_YYYY_MM_DD);
		
		String fileName="D:/app/excel/parameter"+DateUtil.formatDate(new Date(),DateUtil.FORMAT_DATETIME_YYYYMMDDHHMMSSSSS)+".xls";
		modelAndView.setViewName("reportExcelView");
		
		modelAndView.addObject(FILENAME,fileName);
		modelAndView.addObject("title", dataGrid.getTitle());
		Long startTime=System.currentTimeMillis();
		excelExportService.export(dataGrid, result.getContent(), fileName, fieldCodeTypes, dataFormats);
		Long costTime=System.currentTimeMillis()-startTime;
		logger.info("export Excel {} cost:{} time",dataGrid.getTitle(),costTime);
		logger.info("out Excel导出");
		return this.getExcelView(modelAndView);
	}

	 
}
