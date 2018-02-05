package com.jtdd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jtdd.dao.imp.RoleAuthRelationDaoImpl;
import com.jtdd.entity.RoleAuthRelation;

@Service
@Transactional
public class RoleAuthRelationService {
	
	@Autowired
	private RoleAuthRelationDaoImpl relationDaoImpl;
	
	/**
	 * 通过角色id 得到权限角色信息
	 * @param roleId
	 * @return
	 */
	public List<RoleAuthRelation> getRoleAuthByRoleId(Integer roleId){
		List<RoleAuthRelation> list = null;
		String hql = "from RoleAuthRelation r where r.role.roleId = ?";
		Object[] param={roleId};
		list = relationDaoImpl.find(hql, param);
		return list;
	}
	
	/**
	 * 通过角色和资源的id 删除该记录
	 * @param roleAuthId
	 * @return
	 */
	public boolean deleteRoleAuthById(Integer roleAuthId){
		String hql = "delete RoleAuthRelation as r where r.relationId = ?";
		Object[] param = {roleAuthId};
		try {
			Integer i = relationDaoImpl.executeHql(hql, param);
			if(i>0){
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 通过资源的id 删除该记录
	 * @param roleAuthId
	 * @return
	 */
	public boolean deleteRoleAuthByAuthId(Integer authId){
		String hql = "delete RoleAuthRelation as r where r.authority.authId = ?";
		Object[] param = {authId};
		try {
			Integer i = relationDaoImpl.executeHql(hql, param);
			if(i>0){
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 插入记录
	 * @param roleAuthRelation
	 * @return
	 */
	public Integer addRoleAuth(RoleAuthRelation roleAuthRelation){
		try {
			return (Integer) relationDaoImpl.save(roleAuthRelation);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
}
