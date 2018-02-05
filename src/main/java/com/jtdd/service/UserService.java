package com.jtdd.service;

import com.jtdd.dao.BaseDao;
import com.jtdd.entity.Login;
import com.jtdd.entity.Page;
import com.jtdd.entity.Role;
import com.jtdd.entity.Student;
import com.jtdd.entity.UserDetailed;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserService {
    @Autowired
    private BaseDao<Login> loginDao;
    @Autowired
    private UserDataService userDataService;
	@Autowired
	private LoginRoleService loginRoleService;
    /**
     * 判断该用户名是否被注册过
     * @param name
     * @return
     */
    public Login checkLogin(String name){
    	Login l = loginDao.get("from Login where loginName=?",name);
    	return l;
    }
    /**
     * 通过用户名和密码得到登录对象
     * @param name
     * @param password
     * @return
     */
    public Login getLoginByNameAndPassword(String name,String password){
    	Login login = null;
    	String [] param = new String[2];
    	param[0] = name;
    	param[1] = password;
    	login=loginDao.get("from Login where loginName=? and loginPassword=?",param);
    	return login;
    }
    
    /**
     * 通过用户名得到对象
     * @param name
     * @return
     */
    public Login getLoginByName(String name){
    	Login l = loginDao.get("from Login where loginName=?",name);
    	return l;
    }
    
    /**
     * 通过用户资料的id  得到登录的id
     * @param userId
     * @return
     */
    public Integer getLoginIdByUserDateId(Integer userId){
    	Login login = loginDao.get("from Login where loginUserId = ?",userId);
    	try {
    		if(login.getLoginId()>0){
        		return login.getLoginId();
        	}else{
        		return 0 ;
        	}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return 0;
    }
    
    /**
     * 得到分页对象
     * @return
     */
    public Page getUser(Integer page,Integer size){
    	loginDao.init(page,size,Login.class);
    	return loginDao.getPage();
    }
    
    /**
     * 添加一个学生信息
     * @param login
     * @return
     */
    public boolean insertStudent(Login login){
    	UserDetailed userDetailed = new UserDetailed();
    	userDetailed.setUserNumber(login.getLoginName());
    	userDetailed.setUserName("姓名");
    	userDetailed.setContactPhone("未填写");
    	userDetailed.setUserEmail("未填写");
    	userDetailed.setUserImg("header.jpg");
    	Role role = new Role();
    	role.setRoleName("student");
    	role.setRoleId(2);
    	userDetailed.setRole(role);
    	Integer id = userDataService.insertUserData(userDetailed);
    	if(id>0){
    		Login login2 = new Login();
    		login2.setLoginName(login.getLoginName());
    		login2.setLoginTime(new Date());
    		login2.setLoginUserId(id);
    		login2.setLoginPassword(login.getLoginPassword());
    		login2.setLoginIp("0.0.0.0");
    		try {
    			Integer newid = (Integer) loginDao.save(login2);
   
        		if(loginRoleService.insert(2, newid)){
        			return true;
        		}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
    		return false;
    	}else{
    		return false;
    	}
    	
    }
}
