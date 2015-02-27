/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: SessionController.java 9552 2015年2月26日 下午9:04:15 WangLijun$
*/
package com.newtouch.lion.admin.web.controller.session; 

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.newtouch.lion.data.DataTable;
import com.newtouch.lion.model.session.SessionModel;
import com.newtouch.lion.service.session.SessionService;
import com.newtouch.lion.service.system.UserService;
import com.newtouch.lion.web.controller.AbstractController;
import com.newtouch.lion.web.model.QueryDt;
import com.newtouch.lion.web.servlet.view.support.BindMessage;

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
	
	 
	/**用户会话服务*/
	@Autowired
	private SessionService sessionService;
	/**用户服务*/
	@Autowired
	private UserService userService;
	
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
	/***
	 * 强制用户退出
	 * @param sessionId 用户会ID
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "forcelogout")
	@ResponseBody
	public ModelAndView forceLogout(@RequestParam String sessionId,Errors errors,ModelAndView modelAndView) {
		Map<String, String> params = new HashMap<String, String>();
		
		Subject currentUser=SecurityUtils.getSubject();
		//获取当前会话的
	    String currentSessionId=(String) currentUser.getSession(false).getId();
	    //判断是否是当前用户
	    if(currentSessionId.equals(sessionId)){
	    	errors.reject("","当前登录用户不能强制退出");
	    }
	    
		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return  this.getJsonView(modelAndView);
		}

	    SessionModel sessionModel=sessionService.getSession(sessionId);
		//判断是否是超级用户
	    if(userService.getSuperUsername().equals(sessionModel.getUsername())){
	    	errors.reject("","超级管理用户不能强制退出");
	    }
		if (errors.hasErrors()) {
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
			return  this.getJsonView(modelAndView);
		}
		//调用强制退出
		boolean flag= sessionService.forceLogout(sessionId);
		if(flag){
			modelAndView.addObject(BindMessage.SUCCESS, params);
		}else{
			errors.reject("","强制退出用户未成功");
			modelAndView.addObject(BindMessage.ERRORS_MODEL_KEY, errors);
		}
		return this.getJsonView(modelAndView);
	}
}	

	