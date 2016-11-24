package com.hallocasa.services.properties;

import java.util.List;

import com.hallocasa.vo.hcfilter.properties.PropertyProposal;

/**
 * Contract service for class {@link PropertyProposal}
 * @author avillamil
 */
public interface PropertyProposalService {

	/**
	 * Find all property proposals available on system
	 */
	public List<PropertyProposal> find();	
}
