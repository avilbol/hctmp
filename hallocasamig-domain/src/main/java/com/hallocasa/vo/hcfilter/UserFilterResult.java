package com.hallocasa.vo.hcfilter;

import java.io.Serializable;
import java.util.List;

import com.hallocasa.vo.User;
import com.hallocasa.vo.i.ValueObject;

public class UserFilterResult implements ValueObject, Serializable{

	private static final long serialVersionUID = 4508583287121095603L;

	private Long count;
	
	private List<User> userList;

	public UserFilterResult() {
		super();
	}

	public UserFilterResult(Long count, List<User> userList) {
		super();
		this.count = count;
		this.userList = userList;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	} 
}
