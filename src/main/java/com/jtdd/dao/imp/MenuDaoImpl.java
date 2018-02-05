package com.jtdd.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jtdd.entity.Menu;

@Repository
public class MenuDaoImpl extends BaseDAOImpl<Menu> {
	
	@Autowired
	private BaseDAOImpl<Menu> baseDAOImpl;
	
	/**
	 * 查询二级菜单
	 * @param fatherId
	 * @return
	 */
	public List<Menu> getMenuByFatherId(Integer fatherId){
		List<Menu> list = new ArrayList<Menu>();
		String hql = "from Menu where menuFatherId = ? and menuGrade = ?";
		Object[] param = {fatherId,2};
		list = baseDAOImpl.find(hql, param);
		return list;
	}

}
