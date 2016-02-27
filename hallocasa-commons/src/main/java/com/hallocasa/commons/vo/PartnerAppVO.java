package com.hallocasa.commons.vo;

import java.util.List;

import com.hallocasa.commons.vo.interfaces.ValueObject;

/**
 * @author German Quinones
 *
 * @since 1.7
 */
public class PartnerAppVO implements ValueObject {
	
	public final static String features_ = "allowedFeatures";
	
	private Long partnerId;
	private Long appId;
	private List<FeatureVO> allowedFeatures;
	
	/**
	 * @return
	 */
	public Long getPartnerId() {
		return partnerId;
	}
	
	/**
	 * @param partnerId
	 */
	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}
	
	/**
	 * @return
	 */
	public Long getAppId() {
		return appId;
	}
	
	/**
	 * @param appId
	 */
	public void setAppId(Long appId) {
		this.appId = appId;
	}

	/**
	 * @return
	 */
	public List<FeatureVO> getAllowedFeatures() {
		return allowedFeatures;
	}

	/**
	 * @param allowedFeatures
	 */
	public void setAllowedFeatures(List<FeatureVO> allowedFeatures) {
		this.allowedFeatures = allowedFeatures;
	}
	
	
}
