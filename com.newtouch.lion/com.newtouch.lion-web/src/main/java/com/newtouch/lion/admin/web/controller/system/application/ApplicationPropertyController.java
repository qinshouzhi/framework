/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: CacheController.java 9552 2014-4-9 上午01:04:34 WangLijun$
 */
package com.newtouch.lion.admin.web.controller.system.application;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newtouch.lion.data.DataTable;
import com.newtouch.lion.model.application.ApplicationProperty;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.query.QueryCriteria;
import com.newtouch.lion.service.application.ApplicationPropertyService;

/**
 * <p>
 * Title: 项目属性配置类
 * </p>
 * <p>
 * Description: 项目属性配置类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: NewTouch
 * </p>
 * 
 * @author MaoJiaWei
 * @version 1.0
 */
@Controller(value = "sysApplicationPropertyContorller")
@RequestMapping("/system/applicationproperty/")
public class ApplicationPropertyController {
	
	/** 默认排序字段 */
	private static final String DEFAULT_ORDER_FILED_NAME = "id";
	
	private static final String INDEX_RETURN = "/system/applicationproperty/index";

	@SuppressWarnings("unused")
	private static final String INDEX_LIST_TB = "sys_app_property_list_tb";

	@Autowired
	private ApplicationPropertyService applicationPropertyService;
	
	/** 首页显示 */
	@RequestMapping(value = "index")
	public String index() {
		return INDEX_RETURN;
	}
	
	/** 列表显示 */
	@RequestMapping(value = "list")
	@ResponseBody
	public DataTable<ApplicationProperty> lists(Model model,
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
		PageResult<ApplicationProperty> pageResult = this.applicationPropertyService.doFindByCriteria(queryCriteria);
		return pageResult.getDataTable();
	}
}
