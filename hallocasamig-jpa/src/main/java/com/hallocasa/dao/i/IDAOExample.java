package com.hallocasa.dao.i;

import com.hallocasa.entities.EntityExample;

public interface IDAOExample {

	EntityExample findByCode(Integer code);
	
	boolean saveExample(EntityExample entityExample);
	
}
