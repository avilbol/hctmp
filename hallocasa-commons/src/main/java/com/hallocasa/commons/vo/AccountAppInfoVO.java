package com.hallocasa.commons.vo;

import java.util.Map;

import com.hallocasa.commons.vo.interfaces.ValueObject;

/**
 * Value Object for Account Aplication information
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class AccountAppInfoVO implements ValueObject{
	
	private Long accountId;
	
	private Map<AppVO, AppAccessInfoVO> accountApplicationInfo;

	
	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Map<AppVO, AppAccessInfoVO> getAccountApplicationInfo() {
		return accountApplicationInfo;
	}

	public void setAccountApplicationInfo(
			Map<AppVO, AppAccessInfoVO> accountApplicationInfo) {
		this.accountApplicationInfo = accountApplicationInfo;
	}
	
	

}
