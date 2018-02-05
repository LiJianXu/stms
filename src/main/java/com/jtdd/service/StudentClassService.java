package com.jtdd.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jtdd.dao.imp.BaseDAOImpl;
import com.jtdd.dao.imp.StudentClassImpl;
import com.jtdd.entity.StudentClass;

@Service

public class StudentClassService {
	
	@Autowired
	private StudentClassImpl studentClassImpl;
	
	/**
	 * 閫氳繃鐝骇id 寰楀埌鐝骇淇℃伅
	 * @param id
	 * @return
	 */
	public StudentClass getStudentClsssById(Integer id){
		StudentClass studentClass = studentClassImpl.get(StudentClass.class, id);
		if(studentClass!=null){
			return studentClass;
		}else{
			return null;
		}
	}
	
	/*
	 * 鏇存柊瀛︾敓鐨勭彮绾т俊鎭�
	 */
	//propagation=Propagation.REQUIRED,rollbackForClassName="Exception"
	@Transactional(propagation=Propagation.REQUIRED,rollbackForClassName="Exception")
	public boolean saveStuClass(StudentClass studentClass){
		try {
			if((Integer)studentClassImpl.save(studentClass)>0){
				System.out.println("寮�濮嬫姏鍑洪敊璇�");
				throw new Exception();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Autowired
	BaseDAOImpl<StudentClass> impl;
	
	/**
	 * 閫氳繃涓撲笟Id鑾峰彇鐝骇鐨勪俊鎭�
	 * @param majorId
	 * @return
	 */
	public List<StudentClass> getClass(Integer majorId) {
		List<Object> list = new ArrayList<>();
		list.add(majorId);
		Object[] objects = list.toArray();
		List<StudentClass> classes = impl.find("select new StudentClass(id, name) from StudentClass where major.id=?",objects);
		if(classes.size()>0) {
			return classes;
		}else {
			return null;
		}
	}
}
