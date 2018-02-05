package com.jtdd.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jtdd.entity.Authority;
import com.jtdd.entity.Json;
import com.jtdd.service.AuthorityService;
import com.jtdd.service.RoleAuthRelationService;


@Controller
@RequestMapping("authority")
public class AuthorityAction {
	
	@Autowired
	private AuthorityService authorityService;
	@Autowired
	private RoleAuthRelationService roleAuthRelationService;
	
	
	@RequestMapping("getAuthority")
	@ResponseBody
	public List<Authority> getAuthoritys(){
		List<Authority> authorities = null;
		authorities = authorityService.getAllAuthority();
		return authorities;
	}
	
	/**
	 * 更新权限资源信息
	 * @param authority
	 * @return
	 */
	@RequestMapping("update_authority")
	@ResponseBody
	public Json updateAuthority(Authority authority){
		Json json = new Json();
		if(authorityService.updateAuthority(authority)){
			json.setMsg("更新成功");
			json.setSuccess(true);
		}else{
			json.setMsg("更新失败");
			json.setSuccess(false);		
		}
		return json;
	}
	
	/**
	 * 删除权限资源
	 * @param authId
	 * @return
	 */
	@RequestMapping("delete_auh")
	@ResponseBody
	public Json deleteAuthority(@RequestParam("authId") Integer authId){
		Json json = new Json();
		
		if(roleAuthRelationService.deleteRoleAuthByAuthId(authId)){
			json.setMsg("删除角色和权限成功");
			json.setSuccess(true);
		}else{
			System.out.println("删除角色和权限失败");
			json.setMsg("删除角色和权限失败");
			json.setSuccess(false);
		}
		if(authorityService.deleteAuthorityById(authId)){
			json.setMsg("删除成功");
			json.setSuccess(true);
		}else{
			json.setMsg("删除资源权限失败");
			System.out.println("删除资源权限失败");
			json.setSuccess(false);
		}
		return json;
	}
	
	@RequestMapping("add_authority")
	@ResponseBody
	public Json addAuthority(Authority authority){
		Json json = new Json();
		Integer id = authorityService.addAuthority(authority);
		if(id>0){
			json.setMsg("添加成功");
			json.setObj(id);
			json.setSuccess(true);
		}else{
			json.setMsg("添加失败");
			json.setSuccess(false);
		}
		return json;
	}
}
