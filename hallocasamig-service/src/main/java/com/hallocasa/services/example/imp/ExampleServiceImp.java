package com.hallocasa.services.example.imp;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.IDAOExample;
import com.hallocasa.entities.EntityExample;
import com.hallocasa.services.example.ExampleService;
import com.hallocasa.utils.constants.parsing.HallocasaConvert;
import com.hallocasa.vo.Example;

@Stateless
public class ExampleServiceImp implements ExampleService {

	@EJB
	private IDAOExample daoExampler;

	@Override
	public Example findById(Integer code) {
		return (Example) HallocasaConvert.toValueObject(daoExampler.findByCode(code));
	}

	@Override
	public boolean save(Example example) {
		return daoExampler.saveExample((EntityExample) HallocasaConvert.toEntity(example));
	}
}
