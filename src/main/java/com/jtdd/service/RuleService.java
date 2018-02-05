package com.jtdd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jtdd.dao.BaseDao;
import com.jtdd.entity.Rules;

@Service
public class RuleService {
	
	@Autowired
	private BaseDao<Rules> ruleDao;
	
	public Rules findById(Integer ruleId) {
		return ruleDao.get("from Rules  where id=?",ruleId);
	}

}
