package com.jtdd.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jtdd.entity.Json;
import com.jtdd.entity.Student;
import com.jtdd.entity.StudentClass;
import com.jtdd.entity.StudentDepartment;
import com.jtdd.entity.UserDetailed;
import com.jtdd.service.StudentClassService;
import com.jtdd.service.StudentDepartmentService;
import com.jtdd.service.StudentService;
import com.jtdd.service.UserDataService;
import com.jtdd.utils.FileUtil;


@Controller
@RequestMapping("student")
public class StudentAction {
	
	@Autowired
	private StudentService stuService;
	@Autowired
	private UserDataService userDataService;
	@Autowired
	private StudentClassService studentClassService;
	@Autowired
	private StudentDepartmentService studentDepartmentService;
	
	/**
	 * 得到学生详细信息
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value="/getStudent",method=RequestMethod.GET)
	@ResponseBody
	public Student getCurrentStudent(HttpSession httpSession){
		Student student = null;
		UserDetailed userDetailed = (UserDetailed) httpSession.getAttribute("userDetailed");
		student = stuService.getStudentByNumber(userDetailed.getUserNumber());
		return student;
	}
	/**
	 * 上传文件
	 * @param file
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value="/upload_file",method=RequestMethod.POST)
	@ResponseBody
	public Json uploadFileImg(@RequestParam("file") MultipartFile file,HttpSession httpSession){
		Json json = null;
		String path = httpSession.getServletContext().getRealPath("/img/");
		json = FileUtil.fileUpLoad(file, path);
		UserDetailed userDetailed = (UserDetailed) httpSession.getAttribute("userDetailed");
		if(userDataService.updateUserImg(userDetailed, json.getObj().toString())){
			json.setMsg("上传成功");
			json.setSuccess(true);
			return json;
		}
		json.setMsg("上传失败");
		json.setSuccess(false);
		return json;
	}
	
	@RequestMapping(value="/upload_student",method=RequestMethod.POST)
	@ResponseBody
	public Json uploadStudent(Student student){
		Json json = new Json();
		
		Student studentNew = stuService.getStudentByNumber(student.getStudentNumber());
		studentNew.setStudentSex(student.getStudentSex());
		studentNew.setContactPhone(student.getContactPhone());
		studentNew.setStudentEmail(student.getStudentEmail());
		studentNew.setStudentNation(student.getStudentNation());
		studentNew.setStudentHometown(student.getStudentHometown());
		studentNew.setOnlineYear(student.getOnlineYear());
		studentNew.setGraduateYear(student.getGraduateYear());
		studentNew.setEntranceYear(student.getEntranceYear());
		studentNew.setStudentCard(student.getStudentCard());

		if(!stuService.updateStudent(studentNew)){
			System.out.println("更新学生信息失败");
			json.setSuccess(false);
			json.setMsg("更新学生信息失败");
			return json;
		}
		StudentClass studentClass = studentClassService.getStudentClsssById(student.getStudentClass().getId());
		studentClass.setName(student.getStudentClass().getName());
		studentClass.setSpecialty(student.getStudentClass().getSpecialty());
		
		
		if(!studentClassService.saveStuClass(studentClass)){
			System.out.println("更新学生班级失败");
			json.setSuccess(false);
			json.setMsg("更新学生班级失败");
			return json;
		}
		StudentDepartment studentDepartment = studentDepartmentService.getStuDepartMentById(student.getStudentDepartment().getId());
		studentDepartment.setName(student.getStudentDepartment().getName());
		
		if(!studentDepartmentService.saveStuDepartment(studentDepartment)){
			System.out.println("更新系部失败");
			json.setSuccess(false);
			json.setMsg("更新系部失败");
			return json;
		}
		//System.out.println("studentDepartment:"+student.getStudentDepartment().toString());
		json.setMsg("更新学生信息成功");
		json.setSuccess(true);
		return json;
	}
}
