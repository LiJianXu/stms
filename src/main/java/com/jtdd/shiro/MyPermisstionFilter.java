package com.jtdd.shiro;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import com.alibaba.fastjson.JSON;


/**
 * 自定义权限过滤器
 * @author ljx
 * CreateTime:2017年12月29日
 */
public class MyPermisstionFilter extends AuthorizationFilter {
	boolean b = true;
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		// TODO Auto-generated method stub
		Subject subject = getSubject(request, response);
        String[] permisstionArray = (String[]) mappedValue;
        for (String string : permisstionArray) {
        	if(!subject.isPermitted(string)){
        		if(this.isAjax(request)){
        			b = false;
        			Map<String, Boolean> m = new HashMap<String, Boolean>();
        			m.put("success",false);
        			response.getWriter().print(JSON.toJSONString(m));
        		}else{
        			return false;
        		}
        	}
        	b = true;
        	return true;
		}
		return false;
	}

	@Override
	protected void executeChain(ServletRequest request, ServletResponse response, FilterChain chain) throws Exception {
		// TODO Auto-generated method stub
		if(b==true){
			super.executeChain(request, response, chain);
		}
	}

	public boolean isAjax(ServletRequest request){
        String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
        if("XMLHttpRequest".equalsIgnoreCase(header)){
            System.out.println( "当前请求为Ajax请求");
            return Boolean.TRUE;
        }
        System.out.println(  "当前请求非Ajax请求");
        return Boolean.FALSE;
    }
}
