package com.jtdd.action;

import java.util.ArrayList;
import java.util.List;

import javax.json.Json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jtdd.entity.Major;
import com.jtdd.entity.StudentClass;
import com.jtdd.service.StudentClassService;

@Controller
public class StudentClassAction {
	
	@Autowired
	StudentClassService service;
	
	/**
	 * 通过专业Id获取班级的信息
	 * @param majorId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getClass",method=RequestMethod.POST)
	public String getClass(@RequestParam("majorId")Integer majorId) {
		List<StudentClass> classes = service.getClass(majorId);
		if(classes.size()>0) {
			return JSON.toJSONString(classes);
		}else {
			return null;
		}
	}
}
