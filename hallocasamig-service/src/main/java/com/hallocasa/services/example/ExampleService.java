package com.hallocasa.services.example;

import com.hallocasa.vo.Example;

public interface ExampleService {

	public Example findById(Integer code);
	
	public boolean save(Example example);
}
