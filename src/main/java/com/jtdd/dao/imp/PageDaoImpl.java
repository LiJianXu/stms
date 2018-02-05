package com.jtdd.dao.imp;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.jtdd.entity.Page;


@Repository
public class PageDaoImpl<T> {

	private String hql;
	public Page page;
	public int start;
	public int size;
	@Autowired
	private BaseDAOImpl<T> baseDAOImpl;

	public void init(int start,int size, Class<?> c) {
		page = new Page();
		this.start = start;
		this.size = size;
		this.hql = "from " + c.getSimpleName();
		setRowCount(null);
		setTotalPage(null);
		setCurrentPage();
		setPrePage();
		setNextPage();
		setPreOrNextBoolean();
	}
	
	public void init2(int start,int size, String hql,Object [] param) {
		page = new Page();
		this.start = start;
		this.size = size;
		this.hql = hql;
		setRowCount(param);
		setTotalPage(param);
		setCurrentPage();
		setPrePage();
		setNextPage();
		setPreOrNextBoolean();
	}

	/**
	 * 获取总行数
	 * 
	 * @return
	 */
	public int getRowCount(Object [] param) {
		List<T> list = baseDAOImpl.find(hql,param);
		if (list.isEmpty()) {
			return 0;
		}
		return list.size();
	}

	public Page getPage() {
		Object [] param=null;
		List<T> t=baseDAOImpl.find(hql, param, start, size);
		page.setList(t);
		return page;
	}

	
	//获得分页后的对象
	public Page getPage2(Object [] param) {
		List<T> t=baseDAOImpl.find(hql, param, start, size);
		page.setList(t);
		return page;
	}

	public void setPreOrNextBoolean() {
		if (page.getCurrentPage() <= 1) {
			page.setHasPreviousPage(false);
		} else {
			page.setHasPreviousPage(true);
		}
		if (page.getCurrentPage() >= page.getTotalPage()) {
			page.setHasNextPage(false);
		} else {
			page.setHasNextPage(true);
		}
	}
	
	//设置当前页数
	public void setCurrentPage() {
		if (start < 1) {
			page.setCurrentPage(1);
		}
		if (start > page.getTotalPage()) {
			page.setCurrentPage(page.getTotalPage());
		}
		page.setCurrentPage(start);
	}

	//设置上一页
	public void setPrePage() {
		page.setPrePage(page.getCurrentPage() - 1);
	}
	//设置下一页
	public void setNextPage() {
		page.setNextPage(page.getCurrentPage() + 1);
	}

	//设置总页数
	public void setTotalPage(Object [] param) {
		int rowCount = getRowCount(param);
		int pageSize = page.getPageSize();
		if (rowCount > pageSize) {
			if (rowCount % pageSize == 0) {
				page.setTotalPage(rowCount / pageSize);
			} else {
				page.setTotalPage(1 + (rowCount / pageSize));
			}
		} else {
			page.setTotalPage(1);
		}
	}

	//设置总行数
	public void setRowCount(Object [] param) {
		page.setRowCount(getRowCount(param));
	}

	public int getStartIndex() {
		int startIndex = 0;
		if (start < 0) {
			startIndex = 0;
		} else {
			if (start > page.getTotalPage()) {
				startIndex = page.getPageSize() * (page.getTotalPage() - 1);
			} else {
				startIndex = page.getPageSize() * (start - 1);
			}
		}
		return startIndex;
	}
}
