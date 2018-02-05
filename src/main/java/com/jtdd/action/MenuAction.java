package com.jtdd.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlProcessor.ResolutionMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jtdd.entity.Json;
import com.jtdd.entity.Login;
import com.jtdd.entity.Menu;
import com.jtdd.entity.MenuRole;
import com.jtdd.entity.Role;
import com.jtdd.service.MenuRoleService;
import com.jtdd.service.MenuService;
import com.jtdd.service.RolesService;
import com.jtdd.service.UserService;

@Controller
@RequestMapping("menu")
public class MenuAction {
	
	@Autowired
	private UserService userService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private RolesService rolesService;
	@Autowired
	private MenuRoleService menuRoleService;
	
	/**
	 * 得到目录  根据角色
	 * @return
	 */
	@RequestMapping(value="getMenus",method=RequestMethod.GET)
	@ResponseBody
	public List<Menu> getMenusByRoles(){
		List<Menu> menus = null;
		Subject currentUser = SecurityUtils.getSubject();
		String name = (String) currentUser.getPrincipal();
/*		System.out.println("用户名:"+name);*/
		//得到用户信息
		Login login = userService.getLoginByName(name);
		//得到用户的角色的id
		List<Integer> rolesId = rolesService.getRolesByLogin(login);
		//通过角色的id 获取菜单的信息
		menus = menuService.getMenusByRolesId(rolesId);
		return menus;
	}
	
	public void insertMenuRole(List<Integer> rolesId,List<Integer> menusId){
		for (int j = 0; j < menusId.size(); j++) {
			MenuRole menuRole = new MenuRole();
			Menu menu = new Menu();
			menu.setMenuId(menusId.get(j));
			menuRole.setMenu(menu);
			Role role2=new Role();
			role2.setRoleId(rolesId.get(j));
			menuRole.setRole(role2);
			menuRoleService.insertMenuRole(menuRole);
		}
	}
	
	@RequestMapping("get_all_menus")
	@ResponseBody
	public Json getMenus(){
		Json json = new Json();
		List<Menu> menus = null;
		menus = menuService.getAllMenus();
		if(menus!=null){
			json.setMsg("得到所有的菜单");
			json.setSuccess(true);
			json.setObj(menus);
		}else{
			json.setMsg("得到所有的菜单失败");
			json.setSuccess(false);
		}
		return json;
	}
}
