/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: SessionController.java 9552 2015年2月26日 下午9:04:15 WangLijun$
*/
package com.newtouch.lion.admin.web.controller.session; 

import java.util.List;

import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newtouch.lion.data.DataTable;
import com.newtouch.lion.model.session.SessionModel;
import com.newtouch.lion.service.session.SessionService;
import com.newtouch.lion.web.controller.AbstractController;
import com.newtouch.lion.web.model.QueryDt;

/**
 * <p>
 * Title:Session管理
 * </p>
 * <p>
 * Description: Session管理（读取会话列表、强制退出）
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
@Controller
@RequestMapping("/sessions")
public class SessionController extends AbstractController {
	/**首页*/
	private static final String  INDEX_RETURN="/sessions/index";
	
	/**SessionDao*/
	@Autowired
	private SessionDAO  sessionDAO;
	
	@Autowired
	private SessionService sessionService;
	
	/**返回到首页**/
	@RequestMapping("index")
	public String  index(){
		return INDEX_RETURN;
	}
	
	/***
	 * 用户在线列表
	 * @param query
	 * @return
	 */
	@RequestMapping("actives")
	@ResponseBody
	public DataTable<SessionModel> list(QueryDt query) {
 		
		
		List<SessionModel> list=sessionService.getActiveSessions();
		DataTable<SessionModel> dataTable=new DataTable<SessionModel>((long) list.size(),list,query.getRequestId()); 
		return dataTable;
	}
}	

	