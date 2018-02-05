package com.jtdd.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jtdd.dao.imp.MenuDaoImpl;
import com.jtdd.dao.imp.MenuRoleDaoImpl;
import com.jtdd.entity.Menu;

@Service
public class MenuService {
	private static Logger Logger = LoggerFactory.getLogger(MenuService.class);
	
	@Autowired
	private MenuRoleDaoImpl menuRoleDaoImpl;
	@Autowired
	private MenuDaoImpl menuDaoImpl;
	/**
	 * 通过用户id 所有的菜单
	 * @param userId
	 * @return
	 */
	public List<Menu> getMenusByUserId(Integer userId){
		List<Menu> menus = new ArrayList<Menu>();
		return menus;
	}
	
	/**
	 * 通过多个角色 得到菜单
	 * @param rolesId
	 * @return
	 */
	public List<Menu> getMenusByRolesId(List<Integer> rolesId){
		List<Menu> lMenus =null;
		try {
			lMenus = menuRoleDaoImpl.getMenusByRoleIds(rolesId);
		} catch (Exception e) {
			// TODO: handle exception
			Logger.info("通过多个角色 得到菜单失败");
			e.printStackTrace();
		}
		return lMenus;
	}
	
	/**
	 * 获取所有的菜单
	 * @return
	 */
	public List<Menu> getAllMenus(){
		List<Menu> lMenus =null;
		String hql = "from Menu m where m.menuId > 1 and m.menuFatherId = 1";
		try {
			lMenus = menuDaoImpl.find(hql);
			for (Menu menu : lMenus) {
				menu.setChildmenus(this.getAllMenusTwo(menu.getMenuId()));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return lMenus;
	}
	
	/**
	 * 获取二级菜单  通过一级菜单的id
	 * @param oneMenuId
	 * @return
	 */
	public List<Menu> getAllMenusTwo(Integer oneMenuId){
		List<Menu> list = null;
		String hql ="from Menu m where m.menuGrade = 2 and m.menuFatherId =?";
		Object[] param = {oneMenuId};
		list = menuDaoImpl.find(hql, param);
		return list;
	}
}
