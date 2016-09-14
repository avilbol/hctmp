package com.hallocasa.services.example.imp;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.DAOExample;
import com.hallocasa.entities.EntityExample;
import com.hallocasa.services.example.ExampleService;
import com.hallocasa.utils.constants.parsing.HallocasaConvert;
import com.hallocasa.vo.Example;

@Stateless
public class ExampleServiceImp implements ExampleService {

	@EJB
	private DAOExample daoExample;

	@Override
	public Example findById(Integer code) {
		return HallocasaConvert.<Example, EntityExample>toValueObject(daoExample.findByCode(code));
	}

	@Override
	public boolean save(Example example) {
		return daoExample.saveExample(HallocasaConvert.<Example, EntityExample>toEntity(example));
	}
}
