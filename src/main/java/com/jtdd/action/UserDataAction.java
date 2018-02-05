package com.jtdd.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jtdd.entity.Login;
import com.jtdd.entity.Page;
import com.jtdd.entity.Role;
import com.jtdd.entity.UserDetailed;
import com.jtdd.filter.JSON;
import com.jtdd.filter.JSONS;
import com.jtdd.service.LoginRoleService;
import com.jtdd.service.UserDataService;
import com.jtdd.service.UserService;

@Controller
public class UserDataAction {
	
	@Autowired
	private UserDataService UserDataService;
	@Autowired
	private UserService userService;
	@Autowired 
	private LoginRoleService loginRoleService;
	/**
	 * 得到所有的用户信息  测试
	 * @return
	 */
	@RequestMapping("/selectAllUser")
	public String getUserData(){
		System.out.println(UserDataService.getuserDates().get(0).getUserName());
		return "admin";
	}
	
	/**
	 * 得到前10条用户资料信息
	 * @return
	 */
	@RequestMapping("getUser")
	@ResponseBody
	public Page getUser(){
		return userService.getUser(1,10);
	}
	
	/**
	 * 得到用户信息分页
	 * @return
	 */
	@RequestMapping("getUserDataPage")
	@JSONS({
		@JSON(type=UserDetailed.class,include ="userId,userNumber,userName,userEmail,contactPhone,role"),
		@JSON(type=Role.class,include="roleId,roleName")
	})
	public Page getUserDataPage(@RequestParam("page") Integer page,@RequestParam("size") Integer size){
		return UserDataService.getUserData(page,size);
	}
	/**
	 * 得到用户信息  分页
	 * @return
	 */
	@RequestMapping("getUserDataPage2")
	@JSON(type=UserDetailed.class,include ="userNumber,userName")
	public List<UserDetailed> getUserData2(){
		return UserDataService.getuserDates();
	}
	
	@RequestMapping("/getUserDateRoles")
	@ResponseBody
	public Page getUserDateAndRoles(@RequestParam("page") Integer page,@RequestParam("size") Integer size){
		//获得分页的信息
		Page page2 = UserDataService.getUserData(page,size);
		return page2;
	}
}
