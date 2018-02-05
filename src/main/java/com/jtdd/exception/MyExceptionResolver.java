package com.jtdd.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class MyExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
			Exception ex) {
		ModelAndView m= new ModelAndView();
		// TODO Auto-generated method stub
		if(ex instanceof AuthorizationException){
			m.setViewName("redirect:/unauthorized.jsp");
		}else{
			ex.printStackTrace();
			m.setViewName("redirect:/500.jsp");
		}
		return m;
	}

}
