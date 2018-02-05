package com.jtdd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jtdd.dao.imp.BaseDAOImpl;
import com.jtdd.dao.imp.MajorDaoImpl;
import com.jtdd.entity.Major;
import com.jtdd.entity.StudentDepartment;

@Service
public class MajorService {
	
	@Autowired
	private MajorDaoImpl majorDaoImpl;
	@Autowired
	BaseDAOImpl<Major> impl;
	
	public Major getMajorById(Integer id){
		Major major = majorDaoImpl.get(Major.class, id);
		return major;
	}
	
	
	public List<Major> getStudentClass(StudentDepartment studentDepartment) {
		Object[] objects = new Object[1];
		objects[0] = studentDepartment.getId();
		List<Major> major = impl.find("select new Major(id, name) from Major where studentDepartment.id=?",objects);
		return major;
	}
}
