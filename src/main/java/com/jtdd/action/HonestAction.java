package com.jtdd.action;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


import javax.json.Json;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jtdd.entity.Executes;
import com.jtdd.entity.CountEntity;
import com.jtdd.entity.Honest;
import com.jtdd.entity.Page;
import com.jtdd.entity.Rules;
import com.jtdd.entity.Student;

import com.jtdd.filter.JSON;
import com.jtdd.filter.JSONS;
import com.jtdd.entity.UserDetailed;
/*import com.jtdd.service.HonestService;*/
import com.jtdd.service.HonestService;
import com.jtdd.service.RuleService;
import com.jtdd.service.StudentService;

@RequestMapping("/honest")
@Controller
public class HonestAction {
	@Autowired
	HonestService service;
	@Autowired
	private RuleService ruleService;
	@Autowired
	private StudentService stService;

	/**
	 * 获取所有的诚信中的早操违纪情况
	 * 
	 * @author 陈飞
	 * @param hql语句
	 * @return 返回List说明数据找到成功，如果为null就是没有找到数据
	 */

	@RequestMapping(value = "/findExercises", method = RequestMethod.GET)
	@JSONS({ @JSON(type = Honest.class, include = "id,rules,studentName,studentNumber,remarks,recordTime"),
			@JSON(type = Rules.class, include = "id,rulesName,rulesDescribe,rulesScore") })
	public Page findExercises(@RequestParam("studentClassId") Integer studentClassId,
			@RequestParam("menuId") Integer menuId, @RequestParam("pageNum") Integer pageNum) {
		// TODO Auto-generated method stub
		Page page = service.findExercises(studentClassId, menuId, pageNum);
		List<Honest> honests = page.getList();
		honests.forEach((h) -> {
			Rules rules = h.getRules();
			Rules rules2 = new Rules();
			rules2.setId(rules.getId());
			rules2.setRulesName(rules.getRulesName());
			rules2.setRulesDescribe(rules.getRulesDescribe());
			rules2.setRulesScore(rules.getRulesScore());
			h.setRules(rules2);
		});
		return page;
	}

	/**
	 * 添加违纪的人员
	 * 
	 * @author 陈飞
	 * @param hql语句
	 * @return 返回List说明数据找到成功，如果为null就是没有找到数据
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/saveHonest", method = RequestMethod.POST)
	public String saveHonest(@RequestParam("remarks") String remarks, @RequestParam("menuId") Integer menuId,
			@RequestParam("studentName") String studentName, @RequestParam("ruleId") Integer ruleId,
			@RequestParam("studentNum") String studentNum, @RequestParam("date") Date date) throws ParseException {
		// TODO Auto-generated method stub
		Rules rules = ruleService.findById(ruleId);
		if (rules == null) {
			return "规则未选择";
		}
		Student st = stService.getStudentByNumber(studentNum);
		if (st == null) {
			return "学生不存在";
		}
		Honest honest = new Honest();
		honest.setRules(rules);
		honest.setMenuId(menuId);
		honest.setRecordTime(date);
		honest.setRemarks(remarks);
		honest.setStatusId(1);
		honest.setStudentName(studentName);
		honest.setStudentNumber(studentNum);
		Serializable serializable = service.saveHonest(honest);
		if (serializable != null) {
			return serializable.toString();
		} else {
			return "保存数据失败";
		}
	}

	/**
	 * 更新数据操作
	 * 
	 * @param remarks
	 * @param ruleId
	 * @param studentName
	 * @param studentNumber
	 * @param recordTime
	 * @param id
	 * @return
	 * @throws ParseException 
	 */
	@ResponseBody
	@RequestMapping(value = "/updateHonest", method = RequestMethod.POST)
	public String updateHonest(@RequestParam("remarks") String remarks, @RequestParam("ruleId") Integer ruleId,
			@RequestParam("studentName") String studentName, @RequestParam("studentNumber") String studentNumber,
			@RequestParam("recordTime") String recordTime, @RequestParam("id") Integer id) throws ParseException {
		// TODO Auto-generated method stub
		System.out.println("ruleId"+ruleId);
		System.out.println("ruleId"+id);
		DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
		Date date = fmt.parse(recordTime);
		boolean update = service.updateHonest(id, remarks, ruleId, studentName, studentNumber, date);
		if (update == true) {
			return "true";
		} else {
			return "false";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/delHonest", method = RequestMethod.POST)
	public String delHonest(@RequestParam("id") Integer id) {
		// TODO Auto-generated method stub
		boolean del = service.delHonest(id);
		if (del == true) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * 得到诚信统计
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("/counts_honest")
	@ResponseBody
	public List<CountEntity> countHonestByStudentNumber(HttpSession httpSession){
		List<CountEntity> countEntities = null;
		UserDetailed userDetailed = (UserDetailed) httpSession.getAttribute("userDetailed");
		if(userDetailed!=null){
			countEntities = service.countHonestByStudentNumber(userDetailed.getUserNumber());
		}
		//把总分添加到session中
		for (int i = countEntities.size()-1; i < countEntities.size(); i++) {
			httpSession.setAttribute("countsHonest", countEntities.get(i).getAllScore());
		}
		return countEntities;
	}
	
}
