package com.jtdd.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jtdd.shiro.MyRealm;

@Service
public class ReleasepPermissionService {
	@Autowired
	private MyRealm myShiroRealm;
	
	public void releasePermission(String userName){
		Subject subject = SecurityUtils.getSubject();
		String realmName = subject.getPrincipals().getRealmNames().iterator().next();
		System.out.println("ream中的用户:"+realmName);
		System.out.println("传入的用户:"+userName);
		//shiroRealm.clearAllCachedAuthorizationInfo2();//清楚所有用户权限  
        //第一个参数为用户名,第二个参数为realmName,test想要操作权限的用户   
        SimplePrincipalCollection principals = new SimplePrincipalCollection(userName,realmName);   
        subject.runAs(principals);   
        myShiroRealm.getAuthorizationCache().remove(subject.getPrincipals());   
        myShiroRealm.getAuthorizationCache().remove(userName);   
        subject.releaseRunAs();
	}
}
