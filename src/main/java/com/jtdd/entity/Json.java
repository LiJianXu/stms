package com.jtdd.entity;

import java.io.Serializable;

/**
 * 返回结果
 * @author ljx
 * CreateTime:2017年12月18日
 */
public class Json implements Serializable {
	private String msg;
	private Boolean success = false;
	private Object obj;
	
	public Json() {
		super();
	}
	public Json(String msg, Boolean success, Object obj) {
		super();
		this.msg = msg;
		this.success = success;
		this.obj = obj;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
}
