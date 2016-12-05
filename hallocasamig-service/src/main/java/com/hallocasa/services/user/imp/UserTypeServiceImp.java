package com.hallocasa.services.user.imp;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.IDAOUserType;
import com.hallocasa.entities.EntityUserType;
import com.hallocasa.services.user.UserTypeService;
import com.hallocasa.utils.constants.parsing.HallocasaConvert;
import com.hallocasa.vo.UserType;

@Stateless
public class UserTypeServiceImp implements UserTypeService {

	@EJB	
	IDAOUserType daoUserType;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UserType> find() {
		List<EntityUserType> entList = daoUserType.find();
		List<UserType> userTypeList = new LinkedList<>();
		for(EntityUserType entUserType : entList){
			userTypeList.add((UserType)HallocasaConvert.toValueObject(entUserType));
		}
		return userTypeList;
	}
}
