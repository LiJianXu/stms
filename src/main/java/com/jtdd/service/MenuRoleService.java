package com.jtdd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jtdd.dao.imp.BaseDAOImpl;
import com.jtdd.dao.imp.MenuRoleDaoImpl;
import com.jtdd.entity.Menu;
import com.jtdd.entity.MenuRole;
import com.jtdd.entity.Role;

@Service
@Transactional
public class MenuRoleService {
	
	@Autowired
	private MenuRoleDaoImpl menuRoleDaoImpl;
	
	/**
	 * 添加菜单角色
	 * @param menuRole
	 * @return
	 */
	public Integer insertMenuRole(MenuRole menuRole){
		
		return menuRoleDaoImpl.insertMenuRoleOutInteger(menuRole);
	}
	
	/**
	 * 通过角色id 得到所有的菜单
	 * @param roleId
	 * @return
	 */
	public List<MenuRole> getMenuIdByRoleId(Integer roleId){
		List<MenuRole> menuRoles = null;
		menuRoles = menuRoleDaoImpl.getMenuIdByRoleId(roleId);
		return menuRoles;
	}
	
	/**
	 * 通过菜单角色id 删除该条记录
	 * @param menuRoleId
	 * @return
	 */
	public boolean deleteByMenuRoleId(Integer menuRoleId){
		try {
			menuRoleDaoImpl.deleteMenuRole(menuRoleId);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
}
