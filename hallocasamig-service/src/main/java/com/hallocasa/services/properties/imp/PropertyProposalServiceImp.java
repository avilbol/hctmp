package com.hallocasa.services.properties.imp;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.properties.IDAOPropertyProposal;
import com.hallocasa.entities.properties.EntityPropertyProposal;
import com.hallocasa.services.properties.PropertyProposalService;
import com.hallocasa.utils.constants.parsing.HallocasaConvert;
import com.hallocasa.vo.hcfilter.properties.PropertyProposal;

/**
 * Service implementation for class {@link PropertyProposal}
 */
@Stateless
public class PropertyProposalServiceImp implements PropertyProposalService {

	@EJB
	IDAOPropertyProposal daoPropertyProposal;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PropertyProposal> find() {
		List<EntityPropertyProposal> entList = daoPropertyProposal.find();
		List<PropertyProposal> resultList = new LinkedList<>();
		for(EntityPropertyProposal entPropProposal : entList){
			resultList.add((PropertyProposal)HallocasaConvert.toValueObject(entPropProposal));
		}
		return resultList;
	}
}
