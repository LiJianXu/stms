package com.jtdd.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jtdd.dao.imp.AllScorseDaoImpl;
import com.jtdd.entity.AllScorse;
import com.jtdd.entity.CountEntity;
import com.jtdd.entity.Honest;
import com.jtdd.entity.Student;
import com.jtdd.entity.UserDetailed;

@Service
public class AllScorseService {
	
	@Autowired
	private AllScorseDaoImpl allScorseDaoImpl;
	@Autowired
	private HonestService honestService;
	@Autowired
	private ExecutionService executionService;
	@Autowired
	private UserDataService userDataService;
	@Autowired
	private StudentService studentService;
	/**
	 * 通过用户的资料id  得到总分信息
	 * @param userDateUId
	 * @return
	 */
	public AllScorse getcountAll(Integer userDateUId){
		AllScorse allScorse = null;
		String hql ="from AllScorse as a where a.userId = ?";
		allScorse = allScorseDaoImpl.get(hql,userDateUId);
		return allScorse;
	}
	
	
	public AllScorse countAll(HttpSession httpSession){
		UserDetailed userDetailed = (UserDetailed) httpSession.getAttribute("userDetailed");
		AllScorse allScorse = null;
		Integer all = 0;
		// TODO Auto-generated method stub
		Integer honestScore = 0;
		//得到当前的总分
		allScorse = getcountAll(userDetailed.getUserId());
		if(allScorse == null){
			AllScorse allScorse2 = new AllScorse(userDetailed.getUserId(), 0, 0, 0, 0, 0, 0, "0","0","0","");
			allScorse = allScorseDaoImpl.get(AllScorse.class, allScorseDaoImpl.save(allScorse2));
		}
		List<CountEntity> countEntity = honestService.countHonestByStudentNumber(userDetailed.getUserNumber());
		if(countEntity.size()==0){
			honestScore = 0;
		}else{
			for (int i = countEntity.size()-2; i < countEntity.size(); i++) {
				honestScore = countEntity.get(i).getAllScore();
			}
		}
		allScorse.setHonestPoints(honestScore);
		Integer execution = 0;
		List<CountEntity> countEntities = executionService.countExecutesByStudentNumber(userDetailed.getUserNumber());
		if(countEntities.size()==0){
			execution = 0;
		}else{
			for (int i = countEntities.size()-2; i < countEntities.size(); i++) {
				execution = countEntities.get(i).getAllScore();
			}
		}
		allScorse.setExecutePoints(execution);
		//计算总数
		all = execution+honestScore;
		allScorse.setTotalPoints(all);
		
		Student student = studentService.getStudentByNumber(userDetailed.getUserNumber());
		
		System.out.println("班级学生总数:"+student.getStudentClass().getAllStudents());
		System.out.println("年级学生总数:"+student.getStudentDepartment().getMajorNumbers());

		if(updateAllScorse(allScorse)){
			System.out.println("统计完成");
		}else{
			System.out.println("统计失败");
		}
		return allScorse;
	}
	
	
	public boolean updateAllScorse(AllScorse allScorse){
		try {
			allScorseDaoImpl.update(allScorse);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
}
