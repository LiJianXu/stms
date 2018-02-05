package com.jtdd.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jtdd.dao.imp.LoginRoleDaoImpl;
import com.jtdd.entity.Login;
import com.jtdd.entity.LoginRole;
import com.jtdd.entity.Role;

@Service
public class LoginRoleService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LoginRoleDaoImpl loginRoleDaoImpl;
	
	/**
	 * 通过用户资料的id 得到用户的角色
	 * @param userDateId
	 * @return
	 */
	public List<Role> getRoleByUserDateId(Integer userDateId){
		List<Role> roList = new ArrayList<Role>();
		Integer loginId = userService.getLoginIdByUserDateId(userDateId);
		String hql = "from LoginRole where login.loginId=?";
		Object[] param ={loginId};
		List<LoginRole> loginRoles = loginRoleDaoImpl.find(hql, param);
		for (LoginRole loginRole : loginRoles) {
			roList.add(loginRole.getRole());
			//System.out.println("该用户的角色信息:"+loginRole.getRole().toString());
		}
		return roList;
	}
	
	/*
	 * 插入登录id 和 角色的关系
	 */
	public boolean insert(Integer roleId,Integer loginId){
		Role role = new Role();
		role.setRoleId(roleId);
		Login login = new Login();
		login.setLoginId(loginId);
		LoginRole loginRole = new LoginRole();
		loginRole.setLogin(login);
		loginRole.setRole(role);
		try {
			if((Integer)loginRoleDaoImpl.save(loginRole)>0){
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	/*
	 * 通过角色id和登录id  删除登录角色信息
	 */
	public boolean delete(Integer roleId,Integer loginId){
		try {
			String hql = "delete LoginRole as l where l.role.roleId=? and l.login.loginId =?";
			Object[] param = {roleId,loginId};
			;
			if(loginRoleDaoImpl.executeHql(hql, param)>0){
				return true;
			}else{
				return false;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
}
