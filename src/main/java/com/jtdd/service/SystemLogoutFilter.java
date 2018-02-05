package com.jtdd.service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 重写退出的过滤器
 * @author ljx
 * CreateTime:2017年12月11日
 */
@Service
public class SystemLogoutFilter extends LogoutFilter {
	
	private static Logger logger = LoggerFactory.getLogger(SystemLogoutFilter.class);
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		//清空用户详细资料
		httpServletRequest.getSession().setAttribute("userDetailed", "");
		logger.info("清空用户详细资料");
		Subject subject = getSubject(request, response);

        String redirectUrl = getRedirectUrl(request, response, subject);

        try {

            subject.logout();

        } catch (SessionException ise) {

           ise.printStackTrace();

        }
        issueRedirect(request, response, redirectUrl);
        return false;
	}
	
}
