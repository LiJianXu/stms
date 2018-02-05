package com.jtdd.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jtdd.entity.Major;
import com.jtdd.entity.StudentDepartment;
import com.jtdd.filter.JSON;
import com.jtdd.service.MajorService;

@RestController
public class MajorAction {
	
	@Autowired
	MajorService mService;

	@RequestMapping("/get_majors")
	@JSON(type=Major.class,include="id,name")
	public List<Major> getMajorByDepartmentId(@RequestParam("dpId")Integer dpId){
		StudentDepartment depart = new StudentDepartment();
		depart.setId(dpId);
		List<Major> majors = mService.getStudentClass(depart);
		if(majors == null) {
			return null;
		}else {
			return majors;
		}
	}
	
}
