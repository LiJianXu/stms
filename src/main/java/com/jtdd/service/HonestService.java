package com.jtdd.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jtdd.dao.imp.BaseDAOImpl;
import com.jtdd.dao.imp.HonestDaoImpl;
import com.jtdd.entity.CountEntity;
import com.jtdd.entity.Executes;
import com.jtdd.entity.Honest;
import com.jtdd.entity.Page;
import com.jtdd.entity.Rules;
import com.jtdd.entity.Rules;

@org.springframework.transaction.annotation.Transactional
@Service
public class HonestService {

	@Autowired
	HonestDaoImpl impl;
	@Autowired
	BaseDAOImpl<Honest> bDaoImpl;

	@Autowired
	BaseDAOImpl<Rules> rDaoImpl;
	/**
	 * 获取所有的诚信中中的违纪情况
	 * 
	 * @author 陈飞
	 * @param hql语句
	 * @return 返回List说明数据找到成功，如果为null就是没有找到数据
	 */
	public Page findExercises(Integer studentClassId, Integer menuId, Integer pageNum) {
		// TODO Auto-generated method stub
		Page page = new Page();
		page = impl.findExercises(studentClassId, menuId, pageNum);
		if (page == null) {
			return null;
		} else {
			return page;
		}
	}

	/**
	 * 添加Honest
	 * 
	 * @param honest
	 * @return
	 */
	public Serializable saveHonest(Honest honest) {
		Serializable serializable = bDaoImpl.save(honest);
		if (serializable != null) {
			return serializable;
		} else {
			return null;
		}

	}

	// 更新数据成功
	public boolean updateHonest(Integer Id, String remarks, Integer ruleId, String studentName, String studentNumber,Date recordTime) {
		try {
			List<Integer> list = new ArrayList<>();
			list.add(Id);
			Object[] param = list.toArray();
			String hql = "from Honest where id=?";
			String rule = "from Rules where id=?";
			Honest honest = bDaoImpl.get(hql, param);
			List<Integer> list2 = new ArrayList<>();
			list2.add(ruleId);
			Object[] param2 = list2.toArray();
			Rules rules = rDaoImpl.get(rule, param2);
			System.out.println("规则"+rules.getRulesName());
			if (honest != null && rules!=null) {
				System.out.println("更新数据");
				honest.setRemarks(remarks);
				honest.setRules(rules);
				honest.setStatusId(1);
				honest.setStudentName(studentName);
				honest.setStudentNumber(studentNumber);
				honest.setRecordTime(recordTime);
				try {
					System.out.println(honest.getStudentName());
					boolean result=bDaoImpl.update1(honest);
					return result;
				}catch (Exception e) {
					// TODO: handle exception
					System.out.println("更新失败"+e.getMessage());
					return false;
				}

			} else {
				return false;
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return false;
		}
		

	}

	// 删除数据
	public boolean delHonest(Integer Id) {
		List<Integer> list = new ArrayList<>();
		list.add(Id);
		Object[] param = list.toArray();
		String hql = "from Honest where id=?";
		Honest honest = bDaoImpl.get(hql, param);
		if (honest != null) {
			honest.setStatusId(0);
			try {
				System.out.println(honest.getStudentName());
				boolean result=bDaoImpl.update1(honest);
				return result;
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println("更新失败"+e.getMessage());
				return false;
			}
		} else {
			return false;
		}

	}
	/**
	 * 通过学号得到诚信信息
	 * @param studentNumber
	 * @return
	 */
	public List<CountEntity> countHonestByStudentNumber(String studentNumber){
		List<Honest> honests = null;
		List<CountEntity> countEntities = new ArrayList<CountEntity>();
		String hql = "from Honest as e where e.studentNumber = ?";
		Object[] param = {studentNumber};
		honests  = impl.countHonest(hql, param);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Integer first = 100;
		for (int i = 0; i < honests.size(); i++) {
			CountEntity countEntity = new CountEntity();
			countEntity.setAllScore(first);
			countEntity.setId(honests.get(i).getId());
			countEntity.setDate(simpleDateFormat.format(honests.get(i).getRecordTime()));
			//诚信表
			countEntity.setType(CountEntity.HONEST);
			Rules rules = honests.get(i).getRules();
			if(rules.getRulesScore() > 0){
				countEntity.setSubScore("无");
				countEntity.setAddScore(rules.getRulesName()+" "+rules.getRulesScore());
			}else{
				countEntity.setAddScore("无");
				countEntity.setSubScore(rules.getRulesName()+" "+rules.getRulesScore());
			}
			first = countEntity.getAllScore()+rules.getRulesScore();
			countEntity.setAllScore(first);
			countEntities.add(countEntity);
		}
		return countEntities;
	}
	
}
