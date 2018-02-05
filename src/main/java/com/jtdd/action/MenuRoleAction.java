package com.jtdd.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jtdd.entity.Json;
import com.jtdd.entity.Menu;
import com.jtdd.entity.MenuRole;
import com.jtdd.entity.Role;
import com.jtdd.service.MenuRoleService;

@Controller
@RequestMapping("menuRole")
public class MenuRoleAction {
	
	@Autowired
	private MenuRoleService menuRoleService;
	
	
	@RequestMapping("get_menu_roleId")
	@ResponseBody
	public Json getMenuRoleByRoleId(@RequestParam("roleId") Integer roleId){
		Json json = new Json();
		List<MenuRole> menuRoles = menuRoleService.getMenuIdByRoleId(roleId);
		if(menuRoles!=null){
			json.setMsg("得到菜单角色");
			json.setSuccess(true);
			json.setObj(menuRoles);
		}else{
			json.setMsg("得到菜单角色失败");
			json.setSuccess(false);
		}
		return json;
	}
	
	/**
	 * 通过菜单角色id 删除该记录
	 * @param menuRoleId
	 * @return
	 */
	@RequestMapping(value="delete_menuRoleId")
	@ResponseBody
	public Json deleteByMenuRoleId(@RequestParam("menuRoleId") Integer menuRoleId){
		Json json = new Json();
		if(menuRoleService.deleteByMenuRoleId(menuRoleId)){
			json.setMsg("删除菜单角色");
			json.setSuccess(true);
		}else{
			json.setMsg("删除菜单角色失败");
			json.setSuccess(false);
		}
		return json;
	}
	
	/**
	 * 通过角色id 和菜单id 添加记录
	 * @param roleId
	 * @param menuId
	 * @return
	 */
	@RequestMapping(value="add_menuRole")
	@ResponseBody
	public Json add_menuRole(@RequestParam("roleId") Integer roleId,@RequestParam("menuId") Integer menuId){
		Json json = new Json();
		Role role = new Role();
		role.setRoleId(roleId);
		Menu menu = new Menu();
		menu.setMenuId(menuId);
		MenuRole menuRole = new MenuRole();
		menuRole.setRole(role);
		menuRole.setMenu(menu);
		Integer newId = menuRoleService.insertMenuRole(menuRole);
		if(newId>0){
			json.setMsg("添加菜单角色记录");
			json.setObj(newId);
			json.setSuccess(true);
		}else{
			json.setMsg("添加菜单角色记录失败");
			json.setSuccess(false);
		}
		return json;
	}
}
