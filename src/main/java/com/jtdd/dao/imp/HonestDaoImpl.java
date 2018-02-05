package com.jtdd.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jtdd.dao.BaseDao;
import com.jtdd.entity.Honest;
import com.jtdd.entity.Page;

@Repository
public class HonestDaoImpl{
	
	
	@Autowired
	BaseDAOImpl<Honest> baseImpl;
	@Autowired
	PageDaoImpl<Honest> pageDaoImpl;
	
	/**
	 * 获取所有的诚信中的早操违纪情况
	 * @author 陈飞
	 * @param hql语句
	 */
	public Page findExercises(Integer studentClassId,Integer menuId,Integer pageNum) {
		// TODO Auto-generated method stub
		Page page = new Page();
		String hql = "select new Honest(h.id,h.rules,h.studentName,h.studentNumber,h.remarks,h.recordTime) from Honest h,Student s,Rules r where s.studentNumber = h.studentNumber and h.rules = r.id and s.studentClassId=? and h.menuId=? and h.statusId=1";
		List<Object> objects = new ArrayList<>();
		objects.add(studentClassId);
		objects.add(menuId);
		if(studentClassId!=null && menuId!=null) {
			Object[] object = objects.toArray();
			baseImpl.init2(pageNum, 10, hql,object);
			page = baseImpl.getPage(object);
			return page;
		}else {
			return null;
		}
		
	}
	
	//统计诚信部分
	public List<Honest> countHonest(String hql,Object[] param){
		List<Honest> honests = null;
		honests = baseImpl.find(hql, param);
		return honests;
	}
}
