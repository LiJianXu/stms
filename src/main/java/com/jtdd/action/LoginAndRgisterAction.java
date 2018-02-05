package com.jtdd.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.jtdd.entity.Login;
import com.jtdd.entity.UserDetailed;
import com.jtdd.service.LoginRoleService;
import com.jtdd.service.UserDataService;
import com.jtdd.service.UserService;
import com.jtdd.utils.CheckUtils;
import com.jtdd.utils.MD5Util;

@Controller
public class LoginAndRgisterAction {

	private static final Logger logger = LoggerFactory.getLogger(LoginAndRgisterAction.class);

	@Autowired
	private UserService userService;
	@Autowired
	private UserDataService userDataService;

	
	@RequestMapping("/login")
	@ResponseBody
	public String checkLogin(@RequestParam("username") String username, @RequestParam("password") String password,HttpSession httpSession) {
		Map<String, Object> result = new HashMap<>();
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			Subject currentUser = SecurityUtils.getSubject();
			/*
			 * List<String> roleIdentifiers = new ArrayList<String>();
			 * roleIdentifiers.add("admin"); roleIdentifiers.add("manger");
			 * roleIdentifiers.add("user"); boolean[] booleans
			 * =currentUser.hasRoles(roleIdentifiers); for (int i = 0; i <
			 * booleans.length; i++) { System.out.println("数组:"+booleans[i]); }
			 */
			if (!currentUser.isAuthenticated()) {
				System.out.println("正在验证");
				// 使用shiro来验证
				token.setRememberMe(true);
				currentUser.login(token);// 验证角色和权限
				UserDetailed userDetailed = userDataService.getUserDetailByUserNumber(username);
				//System.out.println("用户的头像地址:"+userDetailed.getUserImg());
				//设置用户信息
				httpSession.setAttribute("userDetailed", userDetailed);
			}
			result.put("success", true);
		} catch (UnknownAccountException e) {
			result.put("success", false);
			result.put("msg", "没有改该账户");
		} catch (AuthenticationException e) {
			// e.printStackTrace();
			result.put("success", false);
			result.put("msg", "密码错误！");
		}
		return JSONUtils.toJSONString(result);
	}

	/**
	 * 用户注册信息  注册一个学生信息
	 * 
	 * @param request
	 * @param name
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	@ResponseBody
	public String register(HttpServletRequest request,
			@RequestParam("name") @NotNull(message = "name{param.notNull}") String name,
			@RequestParam("password") @NotNull(message = "password{param.notNull}") String password) {
		Map<String, Object> result = new HashMap<>();

		if (userService.checkLogin(name) != null) {
			result.put("success", "false");
			result.put("msg", "该用户名已经被注册");
			return JSONUtils.toJSONString(result);
		}
		// 参数验证
		try {
			Login login = new Login();
			login.setLoginName(name);
			// 使用MD5加密 加密次数 1024次
			String type = "MD5";
			int times = 1024;
			//添加盐值
			Object salt = ByteSource.Util.bytes(name);
			//生成加密后的密码
			login.setLoginPassword(new SimpleHash(type, password, salt, times).toString());
			//添加一个学生
			if(userService.insertStudent(login)){
				result.put("succes", true);
				result.put("msg", "注册成功");
			}else{
				result.put("succes", false);
				result.put("msg", "注册失败");
			}
		} catch (Exception e) {
			logger.error("注册用户出错：", e);
			result.put("succes", false);
			result.put("msg", "注册出错，请检查你的信息是否填写正确。");
		}
		return JSONUtils.toJSONString(result);
	}
}
