package com.hallocasa.vo;

import java.util.List;

import com.hallocasa.vo.i.ValueObject;

public class ComposedExample implements ValueObject {

	private Integer identifier;
	
	private Example example;
	
	private List<String> ocurrences;
	
	private List<String> ocurrencesFake;

	public Integer getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}

	public Example getExample() {
		return example;
	}

	public void setExample(Example example) {
		this.example = example;
	}

	public List<String> getOcurrences() {
		return ocurrences;
	}

	public void setOcurrences(List<String> ocurrences) {
		this.ocurrences = ocurrences;
	}

	public List<String> getOcurrencesFake() {
		return ocurrencesFake;
	}

	public void setOcurrencesFake(List<String> ocurrencesFake) {
		this.ocurrencesFake = ocurrencesFake;
	}
	
	
	
}
