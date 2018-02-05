package com.jtdd.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jtdd.entity.Json;
import com.jtdd.service.LoginRoleService;
import com.jtdd.service.LoginService;

@RequestMapping("loginRole")
@Controller
public class LoginRoleAction {
	
	@Autowired
	private LoginService loginService;
	@Autowired
	private LoginRoleService loginRoleService;
	
	/**
	 * 操作用户的角色
	 * @param roleId
	 * @param userId
	 * @param type 0为删除 1为添加
	 * @return
	 */
	@RequestMapping(value="operate",method=RequestMethod.POST)
	@ResponseBody
	public Json operateLoginRole(@RequestParam("roleId") Integer roleId,@RequestParam("userId") Integer userId,@RequestParam("type") Integer type){
		Json json = new Json();
		Integer loginId = loginService.getLoginIdByUserId(userId);
		if(type==0){
			if(loginRoleService.delete(roleId, loginId)){
				json.setMsg("删除成功");
				json.setSuccess(true);
			}else{
				json.setMsg("删除失败");
				json.setSuccess(false);
			}
			return json;
		}else if(type==1){
			if(loginRoleService.insert(roleId, loginId)){
				json.setMsg("插入成功");
				json.setSuccess(true);
			}else{
				json.setMsg("插入失败");
				json.setSuccess(false);
			}
			return json;
		}
		return json;
	}
	
/*	*//**
	 * 通过登录角色的id 删除该记录
	 * @param loginRoleId
	 * @return
	 *//*
	@RequestMapping(value="delete",method=RequestMethod.POST)
	@ResponseBody
	public Json deleteLoginRole(@RequestParam("loginRoleId") Integer loginRoleId){
		Json json = new Json();
		if(loginRoleService.delete(loginRoleId)){
			json.setMsg("删除成功");
			json.setSuccess(true);
			return json;
		}
		json.setMsg("删除失败");
		json.setSuccess(false);
		return json;
	}*/
}
