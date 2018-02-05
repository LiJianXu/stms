package com.jtdd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jtdd.dao.imp.StudentClassImpl;
import com.jtdd.dao.imp.StudentImpl;
import com.jtdd.entity.Major;
import com.jtdd.entity.Student;
import com.jtdd.entity.StudentClass;
import com.jtdd.entity.StudentDepartment;

@Service
@Transactional
public class StudentService {
	
	@Autowired
	private StudentImpl studentImpl;
	
	@Autowired
	private StudentClassService classService;
	@Autowired
	private MajorService majorService;
	@Autowired
	private StudentDepartmentService departmentService;
	
	/**
	 * 通过学号得到学生的所有信息  包括 班级 系 专业
	 * @param studentNumber
	 * @return
	 */
	public Student getStudentByNumber(String studentNumber){
		Student student = null;
		try {
			student = studentImpl.getStudent(studentNumber);
			if(student!=null){
				StudentClass studentClass = classService.getStudentClsssById(student.getStudentClassId());
				student.setStudentClass(studentClass);
				StudentDepartment studentDepartment = departmentService.getStuDepartMentById(student.getStudentDepartementId());
				student.setStudentDepartment(studentDepartment);
				return student;
			}
			return student;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return student;
	}
	
	/**
	 * 通过学号得到学生的基本信息
	 * @param number
	 * @return
	 */
	public Student getBaseStudentByNumber(String number){
		Student student = null;
		try {
			student = studentImpl.getStudent(number);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return student;
	}
	/*
	 * 更新学生信息
	 */
	public boolean updateStudent(Student student){
		try {
			if((Integer)studentImpl.save(student)>0){
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
}
