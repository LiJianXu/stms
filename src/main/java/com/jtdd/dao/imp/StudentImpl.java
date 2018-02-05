package com.jtdd.dao.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jtdd.entity.Student;

@Repository
public class StudentImpl extends BaseDAOImpl<Student> {
	@Autowired
	private BaseDAOImpl<Student> baseDAOImpl;
	
	/**
	 * 通过学号  得到学生信息
	 * @param studentNumber
	 * @return
	 */
	public Student getStudent(String studentNumber){
		Student student =null;
		String hql = "from Student where studentNumber=?";
		Object[] param = {(Object)studentNumber};
		student = baseDAOImpl.get(hql,param);
		return student;
	}
}
