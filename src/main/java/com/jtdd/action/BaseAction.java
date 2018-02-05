package com.jtdd.action;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseAction {
	
	@RequestMapping(value="index")
	public String IntoIndex(){
		return "index";
	}
	
	@RequestMapping(value="welcome")
	public String Welcome(){
		return "welcome";
	}
	
	@RequestMapping(value="admin")
	public String Admin(){
		return "admin";
	}
	
	@RequestMapping(value="excel")
	public String Excel(){
		return "excel";
	}
	
	@RequestMapping(value="jurisdiction")
	public String Jurisdiction(){
		return "jurisdiction";
	}
	
	@RequestMapping(value="noPerssion")
	public String NoPerssion(){
		return "noPerssion";
	}
	
	@RequestMapping(value="/health/shenxin")
	public String Shenxin(){
		return "health/shenxin";
	}
	
	@RequestMapping(value="/shenpi/ketang")
	public String Ketang(){
		return "shenpi/ketang";
	}
	
	@RequestMapping(value="/shenpi/xuexi")
	public String Xuexi(){
		return "shenpi/xuexi";
	}
	
	@RequestMapping(value="/shenpi/zaocao")
	public String Zaocao(){
		return "shenpi/zaocao";
	}
	
	@RequestMapping(value="/shenpi/jilv")
	public String Jilv(){
		return "shenpi/jilv";
	}
	
	@RequestMapping(value="/shenpi/qita")
	public String Qita(){
		return "shenpi/qita";
	}
	
	@RequestMapping(value="/sincerity/discipline")
	public String Discipline(){
		return "sincerity/discipline";
	}
	@RequestMapping(value="/sincerity/other")
	public String Other(){
		return "sincerity/other";
	}
	@RequestMapping(value="/sincerity/study")
	public String Study(){
		return "sincerity/study";
	}
	
	@RequestMapping(value="/sincerity/zaocao")
	public String SinZaocao(){
		return "sincerity/zaocao";
	}
	@RequestMapping(value="/zhixingli/chuqin")
	public String Chuqin(){
		return "zhixingli/chuqin";
	}
	@RequestMapping(value="/zhixingli/jiti")
	public String Jiti(){
		return "zhixingli/jiti";
	}
	@RequestMapping(value="/zhixingli/ketang")
	public String ZhiKetang(){
		return "zhixingli/ketang";
	}
	@RequestMapping(value="/zhixingli/qinshi")
	public String Qinshi(){
		return "zhixingli/qinshi";
	}
	@RequestMapping(value="/authority/jurisdiction")
	public String AuthJrisdiction(){
		return "authority/jurisdiction";
	}
	@RequestMapping(value="/authority/user")
	public String AuthUser(){
		return "authority/user";
	}
	@RequestMapping(value="/authority/resoures")
	public String AuthResoures(){
		return "authority/resoures";
	}
	@RequestMapping(value="/student/center")
	public String StuCenter(){
		return "student/center";
	}
	
	@RequestMapping(value="/student/fiveEvaluate")
	public String StuFive(){
		return "student/fiveEvaluate";
	}

	@RequestMapping(value="/student/report")
	public String StuReport(){
		return "student/report";
	}
}
