package com.hallocasa.vo.dto;

import java.util.List;

public class UserListRequest {

	private Integer userNumber;
	
	private List<Long> excludeIdList;

	public Integer getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(Integer userNumber) {
		this.userNumber = userNumber;
	}

	public List<Long> getExcludeIdList() {
		return excludeIdList;
	}

	public void setExcludeIdList(List<Long> excludeIdList) {
		this.excludeIdList = excludeIdList;
	}
}
