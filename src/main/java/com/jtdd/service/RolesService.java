package com.jtdd.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jtdd.dao.imp.RoleDaoImpl;
import com.jtdd.entity.Login;
import com.jtdd.entity.LoginRole;
import com.jtdd.entity.Role;

@Service
@Transactional
public class RolesService {
	
	private static Logger logger = LoggerFactory.getLogger(RolesService.class);
	
	@Autowired
	private RoleDaoImpl roleDaoImpl;
	
	/**
	 * 用过用户登录  得到用户的角色
	 * @param login
	 * @return
	 */
	public List<Integer> getRolesByLogin(Login login){
		List<Integer> rolesId = new ArrayList<Integer>();
		Set<LoginRole> loginRoles =login.getLoginRoles();
		if(loginRoles ==null){
			logger.info("该用户的角色为空");
			return null;
		}
		try {
			for (LoginRole loginRole : loginRoles) {
				rolesId.add(loginRole.getRole().getRoleId());
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("遍历LoginRole异常");
			e.printStackTrace();
		}
		return rolesId;
	}
	
	/**
	 * 得到所有的角色
	 * @return
	 */
	public List<Role> getRoles(){
		List<Role> roles = null;
		String hql = "from Role";
		try {
			roles = roleDaoImpl.find(hql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return roles;
	}
	
	public boolean updateRoles(Role role){
		try {
			roleDaoImpl.update(role);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
}
