package com.jtdd.dao.imp;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jtdd.dao.UserDataDao;
import com.jtdd.entity.UserDetailed;

@Repository
public class UserDateDaoImpl extends BaseDAOImpl<UserDetailed>{

	@Autowired
	private BaseDAOImpl<UserDetailed> UserDetailedbase;
	
	/**
	 * 得到所有用户的资料
	 */
	public List<UserDetailed> getAllUsersData() {
		String hql="from UserDetailed";
		// TODO Auto-generated method stub
		return UserDetailedbase.find(hql);
	}
	
	/**
	 * 添加一个用户资料信息
	 * @param userDetailed
	 * @return
	 */
	public Serializable insertUserDatail(UserDetailed userDetailed){
		return UserDetailedbase.save(userDetailed);
	}
}
