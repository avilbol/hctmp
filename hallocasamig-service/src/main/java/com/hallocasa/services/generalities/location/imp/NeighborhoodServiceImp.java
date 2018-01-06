package com.hallocasa.services.generalities.location.imp;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.IDAONeighborhood;
import com.hallocasa.entities.EntityNeighborhood;
import com.hallocasa.services.generalities.location.NeighborhoodService;
import com.hallocasa.utils.constants.parsing.HallocasaConvert;
import com.hallocasa.vo.Neighborhood;

/**
 * Implementation of service for neighborhoods
 */
@Stateless
public class NeighborhoodServiceImp implements NeighborhoodService {

	@EJB
	IDAONeighborhood daoNeighborhood;
	
	@Override
	public List<Neighborhood> findByCityId(Integer cityId) {
		List<EntityNeighborhood> resultNeighborhoods = new LinkedList<EntityNeighborhood>();
		resultNeighborhoods.addAll(daoNeighborhood.findGenericUse());
		resultNeighborhoods.addAll(daoNeighborhood.findByCityId(cityId));
		return toValueObject(resultNeighborhoods);
	}
	
	@Override
	public List<Neighborhood> findByCityIds(List<Integer> cityIds) {
		List<EntityNeighborhood> resultNeighborhoods = new LinkedList<EntityNeighborhood>();
		resultNeighborhoods.addAll(daoNeighborhood.findGenericUse());
		resultNeighborhoods.addAll(daoNeighborhood.findByCityIds(cityIds));
		return toValueObject(resultNeighborhoods);
	}
	
	private List<Neighborhood> toValueObject(List<EntityNeighborhood> entList){
		List<Neighborhood> neighborhoodList = new LinkedList<Neighborhood>();
		for(EntityNeighborhood entNeighborhood : entList){
			neighborhoodList.add((Neighborhood) HallocasaConvert.toValueObject(entNeighborhood));
		}
		return neighborhoodList;
	}

	@Override
	public List<Neighborhood> findByParentId(Integer parentId) {
		return findByCityId(parentId);
	}

	@Override
	public List<Neighborhood> findByParentIdList(List<Integer> parentIdList) {
		return findByCityIds(parentIdList);
	}
}
