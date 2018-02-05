package com.jtdd.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jtdd.dao.BaseDao;
import com.jtdd.dao.imp.BaseDAOImpl;
import com.jtdd.dao.imp.UserDateDaoImpl;
import com.jtdd.entity.Page;
import com.jtdd.entity.UserDetailed;

@Transactional
@Service
public class UserDataService {
	
	@Autowired
	private UserDateDaoImpl userDateDaoImpl;
	
	@Autowired
	private BaseDAOImpl<UserDetailed> uBaseDAOImpl;
	
	public List<UserDetailed> getuserDates(){
		return userDateDaoImpl.getAllUsersData();
	}
	
/*	public UserDetailed getUserDateAndRoles(){
		UserDetailed userDetailed = null;
		
		return userDetailed;
	}*/
	
	/**
	 * 通过分页得到用户资料
	 * @param page
	 * @param size
	 * @return
	 */
	public Page getUserData(Integer page,Integer size){
		uBaseDAOImpl.init(page, size, UserDetailed.class);
		return uBaseDAOImpl.getPage();
	}
	
	/**
	 * 插入用户资料
	 * @param userDetailed
	 * @return
	 */
	public Integer insertUserData(UserDetailed userDetailed){
		try {
			Integer id = 0;
			try {
				id = (Integer)userDateDaoImpl.insertUserDatail(userDetailed);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			if(id>0 && id !=null){
				return id;
			}else{
				return 0;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 通过用户的账号 得到用户详细资料
	 * @param userNumber
	 * @return
	 */
	public UserDetailed getUserDetailByUserNumber(String userNumber){
		UserDetailed u = null;
		String hql = "from UserDetailed where userNumber = ?";
		Object[] param ={userNumber};
		u = uBaseDAOImpl.get(hql,param);
		if(u!=null){
			return u;
		}else{
			return null;
		}
	}
	
	/**
	 * 更新用户头像
	 * @param userDetailed
	 * @param imgPath
	 * @return
	 */
	public boolean updateUserImg(UserDetailed userDetailed,String imgPath){
		userDetailed.setUserImg(imgPath);
		try {
			uBaseDAOImpl.saveOrUpdate(userDetailed);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
}
