package com.jtdd.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.jtdd.entity.StudentDepartment;
import com.jtdd.service.StudentDepartmentService;

@Controller
public class StudentDepartmentAction {
	
	@Autowired
	StudentDepartmentService service;
	
	/**
	 * 获取所有的系
	 * @return String
	 * @author chenfei
	 */
	@RequestMapping(value="/getDepartment",method=RequestMethod.GET)
	@com.jtdd.filter.JSON(type =StudentDepartment.class,include="id,name")
	public List<StudentDepartment> getDepartment() {
		List<StudentDepartment> list = service.getDepartment();
		if(list==null) {
			return null;
		}else {
			return list;
		}
	}
}
