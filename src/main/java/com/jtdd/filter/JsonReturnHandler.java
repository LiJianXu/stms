package com.jtdd.filter;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.jtdd.entity.UserDetailed;

public class JsonReturnHandler implements HandlerMethodReturnValueHandler {

	@Override
	public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest)
			throws Exception {
		// 该处理就是最后一个handle
		mavContainer.setRequestHandled(true);
	
		HttpServletResponse httpServletResponse = webRequest.getNativeResponse(HttpServletResponse.class);
		//获取返回的注解
		Annotation annotation[] =  returnType.getMethodAnnotations();

		//json 序列化类
		CustomerJsonSerializer jsonSerializer = new CustomerJsonSerializer();
		Arrays.asList(annotation).forEach(a->{
			if(a instanceof JSON){
				JSON json = (JSON) a;
				jsonSerializer.filter(json.type(), json.include(), json.filter());
			}else if(a instanceof JSONS){
				JSONS jsons = (JSONS) a;
				for (JSON json : jsons.value()) {
					jsonSerializer.filter(json.type(), json.include(), json.filter());
				}
			}
		});
		
		httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
		//把返回的对象转换成json
	    String json = jsonSerializer.toJson(returnValue);
	    
	    httpServletResponse.getWriter().write(json);
	}
	
	/**
	 * 是否有JSON或者JSONS注解
	 */
	@Override
	public boolean supportsReturnType(MethodParameter arg0) {
		//如果有我们自定义的 JSON 注解 就用我们这个Handler 来处理
		boolean hasJsonAno= arg0.getMethodAnnotation(JSON.class) != null || arg0.getMethodAnnotation(JSONS.class) != null;
		return hasJsonAno;
	}

}
