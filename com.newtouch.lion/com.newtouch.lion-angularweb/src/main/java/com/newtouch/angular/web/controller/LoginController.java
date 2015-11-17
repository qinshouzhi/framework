/**
 * 
 */
package com.newtouch.angular.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newtouch.lion.model.system.User;
import com.newtouch.lion.web.controller.AbstractController;

/**
 * @author lixiaohao
 *
 */
@Controller
@RequestMapping("/user/")
public class LoginController extends  AbstractController{

	@RequestMapping("index")
	@ResponseBody
	public User index(){
		System.out.println("进来了！");
		User user = new User();
		user.setUsername("小明");
		user.setDescription("我就是小明");
		return user;
	}
}
