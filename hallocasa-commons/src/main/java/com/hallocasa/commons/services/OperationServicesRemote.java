package com.hallocasa.commons.services;

import java.util.List;

import javax.ejb.Remote;

import com.hallocasa.commons.vo.OperationVO;

/**
 * Remote services related to Partner
 * 
 * @author German Quinones
 * @since 1.7
 */
@Remote
public interface OperationServicesRemote {
	/* static fields */

	/* Methods */

	/**
	 * Finds operation by a given partner id
	 * 
	 * @param partnerId
	 * @return list operations
	 */
	public List<OperationVO> findOperationsByPartner(Long partnerId);

	/* Getters & Setters */
}