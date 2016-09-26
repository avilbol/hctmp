package com.hallocasa.dao.i;

import javax.ejb.Local;

import com.hallocasa.entities.EntityExample;

@Local
public interface IDAOExample {

	EntityExample findByCode(Integer code);
	
	boolean saveExample(EntityExample entityExample);
	
}
