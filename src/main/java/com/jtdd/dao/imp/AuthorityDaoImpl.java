package com.jtdd.dao.imp;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jtdd.entity.Authority;
import com.jtdd.service.FilterChainDefinitionsService;


@Repository
public class AuthorityDaoImpl extends BaseDAOImpl<Authority> {
	
	@Autowired
	private BaseDAOImpl<Authority> baseDAOImpl;
	
/*	@Autowired
	private FilterChainDefinitionsService filterChainDefinitionsService;*/
	
	/**
	 * 得到所有的权限
	 * @return
	 */
	public List<Authority> getAllAuthority(){
		String hql = "from Authority";
		return baseDAOImpl.find(hql);
	}
	
	/**
	 * 创建权限
	 * @param authority
	 * @return
	 */
	public Authority createAuthority(Authority authority){
		Authority authority2=null;
		authority2 = baseDAOImpl.get(Authority.class, baseDAOImpl.save(authority));
		//filterChainDefinitionsService.reloadFilterChains();
		if(authority2!=null){
			return authority2;
		}else{
			return null;
		}
	}
	
	/**
	 * 删除权限
	 * @param authority
	 * @return
	 */
	public void deletedAuthority(Authority authority){
		baseDAOImpl.delete(authority);
		//filterChainDefinitionsService.reloadFilterChains();
	}

}
