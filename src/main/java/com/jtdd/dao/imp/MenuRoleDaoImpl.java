package com.jtdd.dao.imp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jtdd.entity.Menu;
import com.jtdd.entity.MenuRole;

@Repository

public class MenuRoleDaoImpl extends BaseDAOImpl<MenuRole> {
	
	@Autowired
	private BaseDAOImpl<MenuRole> baseDAOImpl;
	
	@Autowired
	private MenuDaoImpl menuDaoImpl;
	
	/**
	 * 通过角色的ids  得到所有的菜单
	 * @param rolesId
	 * @return
	 */
	public List<Menu> getMenusByRoleIds(List<Integer> rolesId){
		List<Menu> allmenus = new ArrayList<Menu>();
		for (Integer roleid : rolesId) {
			allmenus = this.getMenusByRoleId(roleid, allmenus);
		}
		return allmenus;
	}
	
	/**
	 * 通过角色的id 得到菜单
	 * @param roleId
	 * @return
	 */
	public List<Menu> getMenusByRoleId(Integer roleId,List<Menu> allmenus){
		List<MenuRole> menusRole = new ArrayList<MenuRole>();
		String hqlone = "from MenuRole where role.id = ? and menu.menuGrade = ?";
		Object [] param = {roleId,1};
		//获取一级菜单
		menusRole = baseDAOImpl.find(hqlone, param);
		for (MenuRole m : menusRole) {
/*			System.out.println("menuId:"+m.getMenu().getMenuId());
			System.out.println("name:"+m.getMenu().getMenuName());*/
			Menu menu = m.getMenu();
			//获取该父菜单的所有子菜单
			List<Menu> childMenus = this.getMenuRoleTwo(roleId,m.getMenu().getMenuId());
/*			for (Menu menu2 : childMenus) {
				System.out.println("一级菜单:"+menu.getMenuName()+"二级菜单:"+menu2.getMenuName());
			}*/
			
			//设置子菜单
			menu.setChildmenus(childMenus);
			//去除重复的菜单
			if(!allmenus.contains(menu)){
				allmenus.add(menu);
			}
		}
		return allmenus;
	}
	
	/**
	 * 在角色和菜单表中获二级菜单
	 * @param roleId
	 * @return
	 */
	public List<Menu> getMenuRoleTwo(Integer roleId,Integer fatherId){
		List<Menu> list = new ArrayList<Menu>();
		String hql = "from MenuRole where role.id = ? and menu.menuGrade = ? and menu.menuFatherId = ?";
		Object[] param = {roleId,2,fatherId};
		List<MenuRole> menuRoles =baseDAOImpl.find(hql, param);
		for (MenuRole menuRole : menuRoles) {
			list.add(menuRole.getMenu());
		}
		return list;
	}
	
	/**
	 * 添加菜单和权限 即给角色分配权限
	 * @param menuRole
	 * @return
	 */
	public boolean insertMenuRole(MenuRole menuRole){
		try {
			baseDAOImpl.save(menuRole);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 添加菜单和权限 即给角色分配权限
	 * @param menuRole
	 * @return
	 */
	public Integer insertMenuRoleOutInteger(MenuRole menuRole){
		try {
			return (Integer)baseDAOImpl.save(menuRole);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 删除菜单角色记录
	 * @param menuRoleId
	 * @return
	 */
	public boolean deleteMenuRole(Integer menuRoleId){
		String hql = "delete MenuRole as m where m.id =?";
		Object[] param = {menuRoleId};
		;
		if(baseDAOImpl.executeHql(hql, param)>0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 通过角色id 得到所有的MenuRole
	 * @param roleId
	 * @return
	 */
	public List<MenuRole> getMenuIdByRoleId(Integer roleId){
		String hql = "from MenuRole mr where mr.role.roleId = ?";
		Object[] param = {roleId};
		List<MenuRole> menuRoles = baseDAOImpl.find(hql, param);
		return menuRoles;
	}
}
