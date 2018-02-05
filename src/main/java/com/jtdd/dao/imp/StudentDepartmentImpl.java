package com.jtdd.dao.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jtdd.entity.StudentDepartment;


@Repository
public class StudentDepartmentImpl extends BaseDAOImpl<StudentDepartment>{
	
	@Autowired
	BaseDAOImpl<StudentDepartment> baseImpl;
	
	/**
	 * 
	 */
	public List<StudentDepartment> getDepartment() {
		List<StudentDepartment> list = baseImpl.find("from StudentDepartment");
		if(list.size()>0) {
			return list;
		}else {
			return null;
		}
	}
	
}
