package com.hallocasa.commons.services;

import java.util.List;

import javax.ejb.Remote;

import com.hallocasa.commons.exceptions.services.AppAlreadyActivatedException;
import com.hallocasa.commons.exceptions.services.FeatureAlreadyActivatedException;
import com.hallocasa.commons.vo.PartnerVO;

/**
 * Remote services related to Partner
 * 
 * @author David Mantilla
 * @since 1.7
 */
@Remote
public interface PartnerServicesRemote {
	/* static fields */

	/**
	 * Activates an application for a partner. This service doesn't assign any
	 * feature but only creates the relation between application and partner
	 * 
	 * @param partnerId
	 * @param appId
	 * @param activaterAccountId If of the account who is executing the action
	 * @throws AppAlreadyActivatedException
	 */
	public void activateApp(long partnerId, long appId, long activaterAccountId)
		throws AppAlreadyActivatedException;

	/**
	 * Activates a feature for an application of the partner. The partner must
	 * have already a relationship with the application, otherwise a
	 * ValidationException is thrown
	 * 
	 * @param partnerId
	 * @param appId
	 * @param features
	 * @param activaterAccountId Id of the account who is executing this action
	 * @throws FeatureAlreadyActivatedException When the partner already has the
	 *             feature assigned in the application
	 */
	public void activateFeature(long partnerId, long appId, long[] features,
		long activaterAccountId) throws FeatureAlreadyActivatedException;

	/**
	 * Removes application assignment from the partner. All features and
	 * application-accounts assignment are removed too
	 * 
	 * @param partnerId
	 * @param appId
	 * @param deActivaterAccountId
	 */
	public void deActivateApp(long partnerId, long appId, long deActivaterAccountId);
	
	
	/**
	 * @param appId
	 * @return
	 */
	public List<PartnerVO> findPartnersByApp(Long appId);

	/* Methods */

	/* Getters & Setters */
}
