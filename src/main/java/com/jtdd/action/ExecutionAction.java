package com.jtdd.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jtdd.entity.CountEntity;
import com.jtdd.entity.Executes;
import com.jtdd.entity.Page;
import com.jtdd.entity.Rules;
import com.jtdd.entity.UserDetailed;
import com.jtdd.entity.Student;

import com.jtdd.filter.JSONS;
import com.jtdd.service.ExecutionService;
import com.jtdd.service.RuleService;
import com.jtdd.service.StudentService;

@Controller
public class ExecutionAction {
	
	@Autowired
	private ExecutionService executionService;
	@Autowired
	private RuleService ruleService;
	@Autowired
	private StudentService stService;
	
	/**
	 * 添加执行力管理记录
	 * @param studentNumber 学生学号
	 * @param studentName 学生姓名
	 * @param ruleId 规则Id
	 * @param describe 违纪描述
	 * @param menuId 菜单id
	 * @param statusId 记录状态
	 * @return
	 */
	@RequestMapping(value= "/add_executes",method = RequestMethod.POST)
	@ResponseBody
	public String addExecutionRecord(@RequestParam("studentNumber") String studentNumber,
			@RequestParam("studentName") String studentName,@RequestParam("ruleId") Integer ruleId,
			@RequestParam("describe") String describe,@RequestParam("menuId") int menuId,
			@RequestParam("date") String date,@RequestParam("id") Integer Id) {
        Map<String, Object> result = new HashMap<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-hh");
		try {
			Date RecorddTime = df.parse(date);
			Executes executes = new Executes();
			Rules rules = ruleService.findById(ruleId);
			if(rules == null) {
				result.put("msg", "请选择你要添加违规的规则");
				result.put("success",false);
				return JSON.toJSONString(result);
			}
			Student st = stService.getStudentByNumber(studentNumber);
			if(st == null) {
				result.put("msg", "插入记录学生的学号不不存在");
				result.put("success",false);
				return JSON.toJSONString(result);
			}
			executes.setRules(rules);
			executes.setMenuId(menuId);
			executes.setStatusId(1);
			executes.setStudentName(studentName);
			executes.setStudentNumber(studentNumber);
			executes.setRecordTime(RecorddTime);
			executes.setDescript(describe);
			if(Id == null) {
				executionService.addExcutes(executes);
			}else {
				executes.setId(Id);
				executionService.updateExcutes(executes);
			}
			result.put("success", true);
		}catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "添加失败");
			result.put("success", false);
		}
		return JSON.toJSONString(result);
	}
	
	/**
	 * 根据班级获得和角色获取执行记录
	 * @param classId
	 * @param ruleId
	 * @return
	 */
	@RequestMapping("/get_executes")
	@com.jtdd.filter.JSONS({
		@com.jtdd.filter.JSON(type=Executes.class,include="id,rules,studentNumber,studentName,descript,recordTime"),
		@com.jtdd.filter.JSON(type=Rules.class,include="id,rulesName,rulesScore")
	})
	public Page getExecutesRecord(@RequestParam("menuId") Integer menuId,@RequestParam("classId") Integer classId,
			@RequestParam("page") Integer pageNum,@RequestParam("size") Integer size) {
		Page page = executionService.findExecutesRecord(menuId, classId,pageNum,size);
		return page;
	}
	
	/**
	 * 根据学生Id查询记录
	 * @param studentId
	 * @return
	 */
	@RequestMapping("/get_excutes_by_studentId")
	@ResponseBody
	public String getExecutesRecordBystudentId(@RequestParam("studentId") String studentId,@RequestParam("page") Integer pageNum) {
		Page page = executionService.findExecutesByStudentId(studentId,pageNum,10);
		return JSON.toJSONString(page,SerializerFeature.DisableCircularReferenceDetect);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete_excutes_byId")
	@ResponseBody
	public String deleteExcetesRecord(@RequestParam("id") Integer id) {
		Map<String, Object> result = new HashMap<>();
		Integer i = executionService.deleteExcetesRecord(id);
		if(i == 0) {
			result.put("success", true);
		}else {
			result.put("success", false);
		}
		return JSON.toJSONString(result);
	}
	
	//统计执行力分数
	@RequestMapping("/counts_execution")
	@ResponseBody
	public List<CountEntity> getCountEntityExecution(HttpSession httpSession){
		List<CountEntity> countEntities = null;
		UserDetailed userDetailed = (UserDetailed) httpSession.getAttribute("userDetailed");
		if(userDetailed!=null){
			countEntities = executionService.countExecutesByStudentNumber(userDetailed.getUserNumber());
		}
		//把总分添加到session中
		for (int i = countEntities.size()-1; i < countEntities.size(); i++) {
			httpSession.setAttribute("countsExecution", countEntities.get(i).getAllScore());
		}
		return countEntities;
	}
}



