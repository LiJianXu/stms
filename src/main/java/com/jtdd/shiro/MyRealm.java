package com.jtdd.shiro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.jtdd.dao.imp.MenuDaoImpl;
import com.jtdd.dao.imp.MenuRoleDaoImpl;
import com.jtdd.entity.Login;
import com.jtdd.entity.LoginRole;
import com.jtdd.entity.Menu;
import com.jtdd.entity.RoleAuthRelation;
import com.jtdd.service.UserService;

public class MyRealm extends AuthorizingRealm{
	 //这里因为没有调用后台，直接默认只有一个用户("luoguohui"，"123456")
	@Autowired
	private UserService uSerivce;
	
	@Autowired
	private MenuRoleDaoImpl menuRoleDaoImpl;
	
    /* 
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) { 
        Set<String> roleNames = new HashSet<String>();  
        Set<String> permissions = new HashSet<String>(); 
        //List<Integer> rolesId = new ArrayList<Integer>();
        String userName = (String) principals.getPrimaryPrincipal();
        Login login = uSerivce.getLoginByName(userName);
        Set<LoginRole> loginRoles = login.getLoginRoles();
        for (LoginRole loginRole : loginRoles) {
        	//添加角色
        	roleNames.add(loginRole.getRole().getRoleName());
        	//System.out.println("角色:"+loginRole.getRole().getRoleName());
        	Set<RoleAuthRelation> relations = loginRole.getRole().getRoleAuthRelations();
        	for(RoleAuthRelation relation:relations){
        		//添加权限
        		permissions.add(relation.getAuthority().getAuthName());
        	}
		}
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);  
        info.setStringPermissions(permissions);
        return info;
    }

    /* 
     * 登录验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        Login user = uSerivce.checkLogin(token.getUsername());
        SimpleAuthenticationInfo  simpleAuthenticationInfo =null;
        //添加盐值
        ByteSource byteSource = ByteSource.Util.bytes(user.getLoginName());
        //封装认证对象
        simpleAuthenticationInfo=new SimpleAuthenticationInfo(user.getLoginName(), user.getLoginPassword(), byteSource, getName());
        return simpleAuthenticationInfo;
    }
}
