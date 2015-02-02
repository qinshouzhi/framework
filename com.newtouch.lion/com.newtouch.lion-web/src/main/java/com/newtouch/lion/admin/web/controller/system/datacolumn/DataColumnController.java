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
import com.newtouch.lion.common.date.DateUtil;
import com.newtouch.lion.common.file.FileUtil;
import com.newtouch.lion.data.DataTable;
import com.newtouch.lion.model.datagrid.DataColumn;
import com.newtouch.lion.model.datagrid.DataGrid;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.datagrid.DataColumnService;
import com.newtouch.lion.service.datagrid.DataGridService;
import com.newtouch.lion.service.excel.ExcelExportService;
import com.newtouch.lion.web.controller.AbstractController;
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
@RequestMapping("/system/datacolumn/")
public class DataColumnController extends AbstractController{
	private final Logger logger = LoggerFactory.getLogger(super.getClass());
	/** 默认排序字段 */
	private static final String DEFAULT_ORDER_FILED_NAME = "id";
	/** 首页返回路径 */
	private static final String INDEX_RETURN = "system/datacolumn/index";
	/** 新增对话返回路径 */
	private static final String ADD_DIALOG_RETURN = "system/datacolumn/adddialog";
	/** 修改对话返回路径 */
	private static final String EDIT_DIALOG_RETURN = "system/datacolumn/editdialog";
	/** 首页显示列表名称 */
	@SuppressWarnings("unused")
	private static final String INDEX_TB = "datacolumn_tb";
	@Autowired
	private DataColumnService dataColumnService;
	/**DataGrid表格*/
	@Autowired
	private DataGridService dataGridService;
	/**Excel通用导出*/
	@Autowired
	private ExcelExportService excelExportService;
	
	/** 新增的对话框 */
	@RequestMapping(value = "adddialog")
	public String addDialog() {
		return ADD_DIALOG_RETURN;
	}

	/** 删除 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public ModelAndView delete(@RequestParam Long id, ModelAndView modelAndView) {
		Map<String, String> params = new HashMap<String, String>();
		int updateRow = this.dataColumnService.doDeleteById(id);
		if (updateRow > 0) {
			params.put(BindResult.SUCCESS,"sys.datacolumn.delete.success");
		} else {
			params.put(BindResult.SUCCESS,"sys.datacolumn.delete.fail");
		}
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return this.getJsonView(modelAndView);
	}

	/** 添加 */
	@RequestMapping(value = "add")
	@ResponseBody
	public ModelAndView add(
			@Valid @ModelAttribute("dataGridVo") DataColumnVo dataColumnVo,
			Errors errors, ModelAndView modelAndView) {
		if (!errors.hasErrors()&& this.isExistByName(dataColumnVo.getName())) {
			errors.rejectValue(DataColumnVo.NAME,
					"sys.dataColumn.form.nameen.existed.message",
					new Object[] { dataColumnVo.getName() }, null);
		}
		//是否错误消息
		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return this.getJsonView(modelAndView);
		}
		DataColumn dataColumn = new DataColumn();

		BeanUtils.copyProperties(dataColumnVo, dataColumn);
		dataColumnService.doCreate(dataColumn);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, "sys.datacolumn.add.success");
		modelAndView.addObject(BindMessage.SUCCESS, params);
		
		return this.getJsonView(modelAndView);
	}

	/** 编辑对话框 */
	@RequestMapping(value = "editdialog")
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
	@RequestMapping(value = "edit")
	@ResponseBody
	public ModelAndView edit(
			@Valid @ModelAttribute("dataColumn") DataColumnVo dataColumnVo,
			Errors errors, ModelAndView modelAndView) {

		modelAndView=this.getJsonView(modelAndView);
		if (!errors.hasErrors() && dataColumnVo.getId() == null) {
			errors.reject("sys.dataColumn.form.id.empty");
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView; 
		} 
		DataColumn dataColumn = dataColumnService.doGetById(dataColumnVo.getId());
		if (dataColumn == null) {
			errors.reject("sys.datacolumn.form.id.empty");
			return modelAndView;
		}
		
		if (!errors.hasErrors()
				&& this.isExistByName(dataColumnVo.getName(),dataColumn.getName())) {errors.rejectValue(DataColumnVo.NAME,	"sys.dataColumn.form.name.existed.message",new Object[] { dataColumnVo.getName() }, null);

		}

		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}

		BeanUtils.copyProperties(dataColumnVo, dataColumn);
		dataColumnService.doUpdate(dataColumn);

		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, "sys.datacolumn.edit.success");
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}

	/** 首页显示 */
	@RequestMapping(value = "index")
	public String index() {
		return INDEX_RETURN;
	}

	/** 列表显示 */
	@RequestMapping(value = "list")
	@ResponseBody
	public DataTable<DataColumn> lists(HttpServletRequest servletRequest, Model model,
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
		PageResult<DataColumn> pageResult = this.dataColumnService.doFindByCriteria(queryCriteria);
		return pageResult.getDataTable();
	}
	
	/*add by maojiawei*/
	private Boolean isExistByName(String name) {
		Boolean flag = false;
		if (StringUtils.isNotEmpty(name)) {
			flag = dataColumnService.doIsExistByName(name.trim());
		}
		return flag;
	}
	/*add by maojiawei*/
	private Boolean isExistByName(String name, String oldName) {
		Boolean flag = false;
		if (StringUtils.isNotEmpty(name) && !name.equals(oldName)) {
			flag = dataColumnService.doIsExistByName(name.trim());
		}
		return flag;
	}
	/*add by maojiawei*/
	@RequestMapping(value = "checkisexitnameen")
	@ResponseBody
	public String checkIsExistByNameEn(HttpServletRequest servletRequest,
			@RequestParam(required = false) String name,@RequestParam(required=false) Long id) {
		Boolean flag=Boolean.FALSE;
		
		if(id==null){
			flag = this.isExistByName(name)? false : true;
		}else{
			DataColumn dataColumn = dataColumnService.doGetById(id);
			if(dataColumn==null){
				flag = this.isExistByName(name)? false : true;
			}else{
				flag=this.isExistByName(name, dataColumn.getName())?false:true;
			}
		}
		return flag.toString();
	}
	
	/****
	 * 
	 * @param tableId
	 * @param dataColumnVo
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "export")
	@ResponseBody
	public ModelAndView exportExcel(@RequestParam(required=false) String tableId,@RequestParam(required = false) String sort,@RequestParam(required = false) String order,@ModelAttribute("dataColumn") DataColumnVo dataColumnVo,ModelAndView modelAndView){
				
		DataGrid dataGrid=dataGridService.doFindByTableIdAndSort(tableId);
		QueryCriteria queryCriteria=new QueryCriteria();
		queryCriteria.setPageSize(10000);
		// 设置排序字段及排序方向
		if (StringUtils.isNotEmpty(sort) && StringUtils.isNotEmpty(order)) {
			queryCriteria.setOrderField(sort);
			queryCriteria.setOrderDirection(order);
		} else {
			queryCriteria.setOrderField(DEFAULT_ORDER_FILED_NAME);
			queryCriteria.setOrderDirection("ASC");
		}
		//查询条件 dataGirdId按模糊查询
		if(dataColumnVo.getDataGridId() != null){
			queryCriteria.addQueryCondition("dataGridId",dataColumnVo.getDataGridId());
		}
		// 查询条件 name
		if (StringUtils.isNotEmpty(dataColumnVo.getName())) {
			queryCriteria.addQueryCondition("name", "%"+dataColumnVo.getName()+"%");
		}
		PageResult<DataColumn> result=dataColumnService.doFindByCriteria(queryCriteria);
		Map<String, Map<Object, Object>> fieldCodeTypes = new HashMap<String, Map<Object, Object>>();

		Map<String, String> dataFormats = new HashMap<String, String>();		
		dataFormats.put("birthday", DateUtil.FORMAT_DATE_YYYY_MM_DD);
		//创建.xls的文件名
		String fileName=this.createFileName(FileUtil.EXCEL_EXTENSION);
		
		modelAndView.addObject("title", dataGrid.getTitle());
		
		Long startTime=System.currentTimeMillis();
		
		fileName=excelExportService.export(dataGrid, result.getContent(), fileName, fieldCodeTypes, dataFormats);
		
		logger.info("fileName:{}",fileName);
		
		Long costTime=System.currentTimeMillis()-startTime;
		
		modelAndView.addObject(FILENAME,fileName);
		
		logger.info("export Excel {} cost:{} time,fileName:{}",dataGrid.getTitle(),costTime,fileName);
		logger.info("out Excel导出");
		return this.getExcelView(modelAndView);
	}
}
