package com.jtdd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jtdd.dao.imp.AuthorityDaoImpl;
import com.jtdd.entity.Authority;
import com.jtdd.entity.RoleAuthRelation;

@Service
@Transactional
public class AuthorityService {
	
	@Autowired
	private AuthorityDaoImpl authorityDaoImpl;
	@Autowired
	private FilterChainDefinitionsService filterChainDefinitionsService;
	
	/**
	 * 得到所有的权限
	 * @return
	 */
	public List<Authority> getAllAuthority(){
		return authorityDaoImpl.getAllAuthority();
	}
	
	/**
	 * 更新权限资源
	 * @param authority
	 * @return
	 */
	public boolean updateAuthority(Authority authority){
		try {
			authorityDaoImpl.update(authority);
			//重新更新默认的权限
			filterChainDefinitionsService.reloadFilterChains();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 通过权限的id 删除该条记录
	 * @param authId
	 * @return
	 */
	public boolean deleteAuthorityById(Integer authId){
		String hql = "delete Authority as au where au.authId = ?";
		Object[] param = {authId};
		Integer count = authorityDaoImpl.executeHql(hql, param);
		if(count>0&&count!=null){
			//重新更新默认的权限
			filterChainDefinitionsService.reloadFilterChains();
			return true;
		}
		return false;
	}
	
	/**
	 * 添加权限资源
	 * @param authority
	 * @return
	 */
	public Integer addAuthority(Authority authority){
		
		Integer newId = (Integer) authorityDaoImpl.save(authority);
		if(newId>0 && newId!=null){
			//重新更新默认的权限
			filterChainDefinitionsService.reloadFilterChains();
			return newId;
		}
		return 0;
	}
}
