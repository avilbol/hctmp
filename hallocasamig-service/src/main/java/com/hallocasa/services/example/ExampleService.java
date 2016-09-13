package com.hallocasa.services.example;

import com.hallocasa.vo.Example;

public interface ExampleService {

	public Example findById(Example example);
	
	public boolean save(Example example);
}
