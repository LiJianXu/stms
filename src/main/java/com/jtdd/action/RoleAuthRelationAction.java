package com.jtdd.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jtdd.entity.Authority;
import com.jtdd.entity.Json;
import com.jtdd.entity.Role;
import com.jtdd.entity.RoleAuthRelation;
import com.jtdd.entity.UserDetailed;
import com.jtdd.filter.JSON;
import com.jtdd.filter.JSONS;
import com.jtdd.service.ReleasepPermissionService;
import com.jtdd.service.RoleAuthRelationService;

@Controller
@RequestMapping("roleAuthRelation")
public class RoleAuthRelationAction {
	
	@Autowired
	private RoleAuthRelationService roleAuthRelationService;
	@Autowired
	private ReleasepPermissionService releasepPermissionService;
	
	
	
	@RequestMapping("get_roleAuth")
	@JSONS({
		@JSON(type = RoleAuthRelation.class,include="relationId,role,authority"),
		@JSON(type = Role.class,include="roleId"),
		@JSON(type = Authority.class,include="authId")
	})
	public List<RoleAuthRelation> getRoleAuthRelations(@RequestParam("roleId") Integer roleId){
		List<RoleAuthRelation> roleAuthRelations = roleAuthRelationService.getRoleAuthByRoleId(roleId);
		List<RoleAuthRelation> newroleAuthRelations = new ArrayList<RoleAuthRelation>();
		for (RoleAuthRelation roleAuthRelation : roleAuthRelations) {
			RoleAuthRelation newRoleAuthRelation = new RoleAuthRelation();
			newRoleAuthRelation.setRelationId(roleAuthRelation.getRelationId());
			Role role = new Role();
			role.setRoleId(roleAuthRelation.getRole().getRoleId());
			newRoleAuthRelation.setRole(role);
			Authority authority = new Authority();
			authority.setAuthId(roleAuthRelation.getAuthority().getAuthId());
			newRoleAuthRelation.setAuthority(authority);
			newroleAuthRelations.add(newRoleAuthRelation);
		}
		return newroleAuthRelations;
	}
	
	@RequestMapping("delete_roleAuth_id")
	@ResponseBody
	public Json deleteById(@RequestParam("roleAuthId") Integer roleAuthId,HttpSession httpSession){
		Json json = new Json();
		if(roleAuthRelationService.deleteRoleAuthById(roleAuthId)){
			//得到登录的session中的对象
			UserDetailed userDetailed = (UserDetailed) httpSession.getAttribute("userDetailed");
			if(userDetailed!=null){
				//重新设置权限
				releasepPermissionService.releasePermission(userDetailed.getUserNumber());
			}
			
			json.setMsg("删除成功");
			json.setSuccess(true);
		}else{
			json.setMsg("删除失败");
			json.setSuccess(false);
		}
		return json;
	}
	
	@RequestMapping(value="ad_roleAuth",method=RequestMethod.POST)
	@ResponseBody
	public Json insertRoleAuth(@RequestParam("roleId") Integer roleId,@RequestParam("authId") Integer authId,HttpSession httpSession){
		Json json = new Json();
		Role role = new Role();
		role.setRoleId(roleId);
		Authority authority = new Authority();
		authority.setAuthId(authId);
		RoleAuthRelation roleAuthRelation = new RoleAuthRelation();
		roleAuthRelation.setRole(role);
		roleAuthRelation.setAuthority(authority);
		Integer id = roleAuthRelationService.addRoleAuth(roleAuthRelation);
		if(id>0){
			//得到登录的session中的对象
			UserDetailed userDetailed = (UserDetailed) httpSession.getAttribute("userDetailed");
			if(userDetailed!=null){
				//重新设置权限
				releasepPermissionService.releasePermission(userDetailed.getUserNumber());
			}
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
