/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: CodeListController.java 9552 2014-2-18 下午04:35:02 WangLijun$
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

import com.newtouch.lion.admin.web.model.system.code.CodeListVo;
import com.newtouch.lion.common.date.DateUtil;
import com.newtouch.lion.json.JSONParser;
import com.newtouch.lion.model.datagrid.DataColumn;
import com.newtouch.lion.model.system.CodeList;
import com.newtouch.lion.model.system.CodeType;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.datagrid.DataColumnService;
import com.newtouch.lion.service.system.CodeListService;
import com.newtouch.lion.service.system.CodeTypeService;
import com.newtouch.lion.web.constant.ConstantMessage;
import com.newtouch.lion.web.servlet.view.support.BindMessage;
import com.newtouch.lion.web.servlet.view.support.BindResult;

/**
 * <p>
 * Title:IM字典列表
 * </p>
 * <p>
 * Description:IM字典列表
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: NewTouch
 * </p>
 * 
 * @author dengkang
 * @version 1.0
 */
@Controller(value = "sysCodelistController")
@RequestMapping("/system/codelist")
public class CodeListController {

	private final Logger logger = LoggerFactory.getLogger(super.getClass());
	/** 参数添加首页 */
	private static final String ADD_DIALOG_RETURN = "/system/codelist/adddialog";
	/** 参数编辑首页 */
	private static final String EDIT_DIALOG_RETURN = "/system/codelist/editdialog";
	/** 默认排序字段 */
	private static final String DEFAULT_FILED_NAME = "id";
	@Autowired
	private CodeListService codeListService;
	@Autowired
	protected DataColumnService dataColumnService;
	@Autowired
	private CodeTypeService codeTypeService;

	@RequestMapping(value = "/editdialog")
	public String editDialog(@RequestParam Long id, Model model) {
		if (id != null) {
			CodeList codeList = this.codeListService.doFindById(id);
			model.addAttribute("codeList", codeList);
		} else {
			logger.error("Eidt Object id is not null!");
		}
		return EDIT_DIALOG_RETURN;
	}

	/** 编辑 */
	@RequestMapping(value = "edit")
	@ResponseBody
	public ModelAndView edit(
			@Valid @ModelAttribute("codeListVo") CodeListVo codeListVo,
			Errors errors, ModelAndView modelAndView) {

		CodeList codeList = null;
		if (codeListVo.getId() != null) {
			codeList = this.codeListService.doFindById(codeListVo.getId());
			if (codeList == null) {
				errors.reject(ConstantMessage.EDIT_ISEMPTY_FAIL_MESSAGE_CODE);
			}
		} else {
			errors.reject(ConstantMessage.EDIT_ISEMPTY_FAIL_MESSAGE_CODE);
		}

		if (!errors.hasErrors()
				&& this.isExistByNameEn(codeListVo.getNameEn(),
						codeList.getNameEn())) {
			errors.rejectValue(CodeListVo.NAMEEN,
					"sys.codeList.form.nameen.existed.message",
					new Object[] { codeListVo.getNameEn() }, null);
		}

		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}

		BeanUtils.copyProperties(codeListVo, codeList);
		CodeType codeType = this.codeTypeService.doFindById(codeListVo
				.getCodeTypeId());
		codeList.setCodeType(codeType);
		codeListService.doUpdateObj(codeList);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS,
				ConstantMessage.EDIT_SUCCESS_MESSAGE_CODE);
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}

	@RequestMapping(value = "/adddialog")
	public String addDialog(HttpServletRequest servletRequest, Model model) {
		return ADD_DIALOG_RETURN;
	}

	@RequestMapping(value = "/add")
	@ResponseBody
	public ModelAndView add(
			@Valid @ModelAttribute("codeList") CodeListVo codeListVo,
			Errors errors, ModelAndView modelAndView) {

		if (!errors.hasErrors() && this.isExistByNameEn(codeListVo.getNameEn())) {
			errors.rejectValue(CodeListVo.NAMEEN,
					"sys.codeList.form.nameen.existed.message",
					new Object[] { codeListVo.getNameEn() }, null);
		}
		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return modelAndView;
		}

		CodeList codeList = new CodeList();

		BeanUtils.copyProperties(codeListVo, codeList);
		CodeType codeType = this.codeTypeService.doFindById(codeListVo
				.getCodeTypeId());
		codeList.setCodeType(codeType);
		codeListService.doCreateObj(codeList);
		Map<String, String> params = new HashMap<String, String>();
		params.put(BindResult.SUCCESS, ConstantMessage.ADD_SUCCESS_MESSAGE_CODE);
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public ModelAndView delete(@RequestParam Long id, ModelAndView modelAndView) {
		Map<String, String> params = new HashMap<String, String>();
		int updateRow = this.codeListService.doDeleteById(id);
		if (updateRow > 0) {
			params.put(BindResult.SUCCESS, "sys.codeList.delete.success");
		} else {
			params.put(BindResult.SUCCESS, "sys.codeList.delete.fail");
		}
		modelAndView.addObject(BindMessage.SUCCESS, params);
		return modelAndView;
	}

	private Boolean isExistByNameEn(String nameEn, String oldNameEn) {
		Boolean flag = false;
		if (StringUtils.isNotEmpty(nameEn) && !nameEn.equals(oldNameEn)) {
			flag = codeListService.doIsExistByNameEn(nameEn.trim());
		}
		return flag;
	}

	private Boolean isExistByNameEn(String nameEn) {
		Boolean flag = false;
		if (StringUtils.isNotEmpty(nameEn)) {
			flag = codeListService.doIsExistByNameEn(nameEn.trim());
		}
		return flag;
	}

	@RequestMapping(value = "/lists")
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
			queryCriteria.setOrderDirection("ASC");
		}
		// 查询条件 参数类型
		if (StringUtils.isNotEmpty(type)) {
			queryCriteria.addQueryCondition(QueryCriteria.ASC, type);
		}

		PageResult<CodeList> pageResult = codeListService
				.doFindByCriteria(queryCriteria);

		Set<String> filterColumn = this.getColumns("sys_codelist_tb");

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

	protected String getJSONString(Long total, List<CodeList> objects,
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

	@RequestMapping(value = "/checkisexitnameen")
	@ResponseBody
	public String checkIsExistByNameEn(HttpServletRequest servletRequest, @RequestParam(required = false) String nameEn) {
		Boolean flag = this.isExistByNameEn(nameEn) == true ? false : true;
		return flag.toString();
	}
}
