package com.jtdd.filter;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 过滤器的序列化类的实现
 * @author ljx
 * CreateTime:2017年11月19日
 */
public class CustomerJsonSerializer {

	ObjectMapper mapper ;
	JacksonJsonFilter jacksonJsonFilter ;
	
	//初始化 mapper 和filter
	public CustomerJsonSerializer() {
		 mapper = new ObjectMapper();
		 jacksonJsonFilter =new JacksonJsonFilter();
		 //给mapper 设置 过滤的Filter
		mapper.setFilterProvider(jacksonJsonFilter);
	}

	
	/**
	 * @param clazz 需要过滤的class 
	 * @param include 包含的字段    用,隔开
	 * @param filter 需要过滤的字段    用,隔开
	 */
	public void filter(Class<?> clazz,String include,String filter){
		if(clazz == null){
			return;
		}
		if(include!=null && include.length()>0){
			//设置过滤器  包含
			jacksonJsonFilter.include(clazz, include.split(","));
		}else if(filter !=null && filter.length()>0){
			//设置过滤器  过滤字段
			jacksonJsonFilter.filter(clazz, filter.split(","));
		}
		mapper.addMixIn(clazz, jacksonJsonFilter.getClass());
	}
	
	/**
	 * 把对象转换成json
	 * @param object
	 * @return
	 * @throws JsonProcessingException
	 */
	public String toJson(Object object) throws JsonProcessingException {
		return mapper.writeValueAsString(object);
	}
	
}
