package com.hallocasa.commons.services;

import java.util.List;

import javax.ejb.Remote;

import com.hallocasa.commons.exceptions.services.PartnershipAlreadyExistsException;
import com.hallocasa.commons.vo.OperationVO;
import com.hallocasa.commons.vo.PartnershipVO;
import com.hallocasa.commons.vo.QueryResult;
import com.hallocasa.commons.vo.criteria.QueryCriteria;
import com.hallocasa.commons.vo.enums.PartnershipStatus;

/**
 * Services related to Operation
 * 
 * @author David Mantilla
 * @since 1.7
 */
@Remote
public interface PartnerShipServicesRemote {

	/* static fields */

	/* Methods */

	/**
	 * Get candidate operations to make a partner ship with. This means the
	 * result is a list of operation which haven't got a current partnership
	 * with the operationId, their partner has "appId" activated and finally
	 * their partner has "featureId" activated.
	 * 
	 * @param appId AppId that operationId and the resulting list have activated
	 * @param operationId The result list doesn't have a current partner ship
	 *            with this operationId, neither from or to.
	 * @param featureIds Feature(s) that result list's partner must have
	 *            activated to be included in the result list
	 * @param queryCriteria Criteria for the query
	 * @return The list of operation which match above conditions
	 */
	public QueryResult<OperationVO> findPartnerShipCandidateOperations(
		long appId, long operationId, long[] featureIds,
		QueryCriteria queryCriteria);

	/**
	 * Get a list of partnerships an operation has in a given application and
	 * with a given status(es)
	 * 
	 * @param appId
	 * @param operationId
	 * @param statuses array of statuses to filter by. If is null or empty then
	 *            all status are return
	 * @return The found list
	 */
	public List<PartnershipVO> findOperationPartnerships(long appId,
		long operationId, PartnershipStatus[] statuses);

	/**
	 * Creates a new partnership in pending status.
	 * 
	 * @param fromOperation
	 * @param toOperation
	 * @param appId Id of the application to create partnership
	 * @param creatorId Id of the account of the user who is creating the
	 *            partnership
	 * @return The created partnership
	 * @throws PartnershipAlreadyExistsException when the partnership exists
	 *             either with the same "from" and same "to" already exists, or
	 *             with inverted "from" and inverted "to"
	 */
	public PartnershipVO requestPartnership(long fromOperation,
		long toOperation, long appId, long creatorId)
		throws PartnershipAlreadyExistsException;

	/**
	 * 
	 * @param partnershipId Id of the partnership to change status in
	 * @param newStatus Enumeration of the new status
	 * @param modifierAccountId Id of the account of the use who is making the
	 *            change
	 * @return the modified partnership value object
	 */
	public PartnershipVO changePartnershipStatus(long partnershipId,
		PartnershipStatus newStatus, long modifierAccountId);

	/**
	 * Finds a partner ship by its id
	 * 
	 * @param id
	 * @return A value object with partnership information
	 */
	public PartnershipVO findPartnership(long id);

	/**
	 * Finds a list of partnerships give an array of ids
	 * 
	 * @param ids
	 * @return Found partnership list
	 */
	public List<PartnershipVO> findPartnership(long[] ids);

	/* Getters & Setters */
}
