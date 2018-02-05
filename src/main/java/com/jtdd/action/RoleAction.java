package com.jtdd.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jtdd.entity.Json;
import com.jtdd.entity.Role;
import com.jtdd.service.LoginRoleService;
import com.jtdd.service.RolesService;

@Controller
@RequestMapping("roles")
public class RoleAction {
	
	@Autowired
	private RolesService rolesService;
	@Autowired
	private LoginRoleService loginRoleService;

	@RequestMapping("get_roles")
	@ResponseBody
	public List<Role> getRoles(){
		List<Role> roles = null;
		roles = rolesService.getRoles();
		return roles;
	}
	
	@RequestMapping("get_roles_userId")
	@ResponseBody
	public List<Role> getRolesByUserId(@RequestParam("userId") Integer userId){
		List<Role> roles = null;
		//根据资料的id 获取所有的角色
		roles = loginRoleService.getRoleByUserDateId(userId);
		//再次封装角色对象  解决hibernate 懒加载问题
		List<Role> roles2 =new ArrayList<Role>();
		for (Role role : roles) {
			Role role2 = new Role();
			role2.setRoleId(role.getRoleId());
			role2.setRoleName(role.getRoleName());
			roles2.add(role2);
		}
		return roles2;
	}
	
	/**
	 * 更新角色信息
	 * @param roleId
	 * @param roleName
	 * @return
	 */
	@RequestMapping(value="update_role",method=RequestMethod.POST)
	@ResponseBody
	public Json updateRole(@RequestParam("roleId") Integer roleId,@RequestParam("roleName") String roleName){
		Json json = new Json();
		Role role = new Role();
		role.setRoleId(roleId);
		role.setRoleName(roleName);
		if(rolesService.updateRoles(role)){
			json.setMsg("更新成功");
			json.setSuccess(true);
		}else{
			json.setMsg("更新失败");
			json.setSuccess(false);
		}
		return json;
	}
}
