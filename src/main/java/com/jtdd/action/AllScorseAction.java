package com.jtdd.action;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jtdd.entity.AllScorse;
import com.jtdd.entity.Json;
import com.jtdd.service.AllScorseService;

@Controller
public class AllScorseAction {
	@Autowired
	private AllScorseService allScorseService;

	@RequestMapping("countAll")
	@ResponseBody
	public Json getAllScorse(HttpSession httpSession){
		AllScorse allScorse =null; 
		Json json = new Json();
		//allScorseService.countAll(httpSession);
		try {
			allScorse = allScorseService.countAll(httpSession);
		} catch (Exception e) {
			// TODO: handle exception
			e.getSuppressed();
			json.setMsg("得到所有的分失败");
			json.setSuccess(false);
		}
		if(allScorse!=null){
			json.setMsg("得到所有的分");
			json.setObj(allScorse);
			json.setSuccess(true);
		}else{
			json.setMsg("得到所有的分失败");
			json.setSuccess(false);
		}
		return json;
	}
}
