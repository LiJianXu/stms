package com.jtdd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jtdd.dao.imp.StudentDepartmentImpl;
import com.jtdd.entity.StudentDepartment;

@Service
@Transactional
public class StudentDepartmentService {
	
	@Autowired
	private StudentDepartmentImpl studentDepartmentImpl;
	
	/**
	 * 通过系部表id 得到系部信息
	 * @param id
	 * @return
	 */
	public StudentDepartment getStuDepartMentById(Integer id){
		return studentDepartmentImpl.get(StudentDepartment.class, id);
	}
	
	/*
	 * 更新系部信息
	 */
	public boolean saveStuDepartment(StudentDepartment studentDepartment){
		try {
			if((Integer)studentDepartmentImpl.save(studentDepartment)>0){
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	
	public List<StudentDepartment> getDepartment() {
		List<StudentDepartment> list = studentDepartmentImpl.getDepartment();
		if(list == null) {
			return null;
		}else {
			return list;
		}
	}
}
