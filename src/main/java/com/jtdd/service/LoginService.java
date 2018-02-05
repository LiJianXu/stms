package com.jtdd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jtdd.dao.imp.LoginDaoImpl;
import com.jtdd.entity.Login;

@Service
public class LoginService {
	
	@Autowired
	private LoginDaoImpl loginDaoImpl;
	
	/**
	 * 通过用户的id 得到登录id
	 * @param userId
	 * @return
	 */
	public Integer getLoginIdByUserId(Integer userId){
		String hql = "from Login where loginUserId = ?";
		Object[] param = {userId};
		try {
			Login login = loginDaoImpl.get(hql, param);
			if(login!=null){
				return login.getLoginId();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("通过用户的id  得到登录信息id异常");
		}
		return 0;
	}
}
