package com.jtdd.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jtdd.dao.BaseDao;
import com.jtdd.dao.imp.BaseDAOImpl;
import com.jtdd.entity.CountEntity;
import com.jtdd.entity.Executes;
import com.jtdd.entity.Page;
import com.jtdd.entity.Rules;

@Transactional
@Service
public class ExecutionService {

	@Autowired
	private BaseDao<Executes> excutesDao;
	
	/**
	 * 添加执行力管理记录
	 * @param executes
	 * @return
	 * @throws Exception
	 */
	public int addExcutes(Executes executes) throws Exception {
		int count = (int) excutesDao.save(executes);
		return count;
	}
	
	public void updateExcutes(Executes executes) throws Exception {
		 excutesDao.update(executes);
	}
	
	/**
	 * 查询执行记录
	 * @param ruleId
	 * @param classId
	 * @param majorId
	 * @param departmentId
	 * @return
	 */
	public Page findExecutesRecord(Integer menuId,Integer classId,Integer pageNum,Integer size) {
		List<Object> paramList = new ArrayList<>(); 
		paramList.add(menuId);
		Page page = new Page();
		if(classId != null) {
			String hql = "select new Executes(e.id,e.rules,e.studentNumber,e.studentName,e.menuId,e.descript,e.statusId,e.recordTime) from Executes as e,Student as s,Rules as r where e.studentNumber = s.studentNumber and e.rules = r.id and e.menuId=? and s.studentClassId =?  and e.statusId=1";
			paramList.add(classId);
			Object param[] = paramList.toArray();
			System.out.println(param[0]);
			excutesDao.init2(pageNum, size, hql,param);
			page = excutesDao.getPage(param);
		}
		return page;
	}
	
	/**
	 * 根据学生的学号查询记录
	 * @param studentNumber
	 * @return
	 */
	public Page findExecutesByStudentId(String studentNumber,Integer pageNum,Integer size){
		Object param[] = {studentNumber};
		Page page = new Page();
		try {
			excutesDao.init2(pageNum, size,"from Executes as e where e.studentNumber=?",param);
			page = excutesDao.getPage(param);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	
	public Integer deleteExcetesRecord(Integer id) {
		try {
			Executes excutes = excutesDao.get("from Executes where id=?", id);
			excutes.setStatusId(0);
			excutesDao.save(excutes);
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 通过学号得到执行力信息
	 * @param studentNumber
	 * @return
	 */
	public List<CountEntity> countExecutesByStudentNumber(String studentNumber){
		List<Executes> executes = null;
		List<CountEntity> countEntities = new ArrayList<CountEntity>();
		String hql = "from Executes as e where e.studentNumber = ?";
		Object[] param = {studentNumber};
		executes  = excutesDao.find(hql, param);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Integer first = 100;
		for (int i = 0; i < executes.size(); i++) {
			CountEntity countEntity = new CountEntity();
			countEntity.setAllScore(first);
			countEntity.setId(executes.get(i).getId());
			countEntity.setDate(simpleDateFormat.format(executes.get(i).getRecordTime()));
			//执行力表
			countEntity.setType(CountEntity.EXECUTION);
			Rules rules = executes.get(i).getRules();
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
