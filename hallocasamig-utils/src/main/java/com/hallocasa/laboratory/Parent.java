package com.hallocasa.laboratory;

public class Parent {

	int identifier;
	
	String description;
	
	String profundization;
	
	Child child;
	
	public Parent(int identifier, String description, String profundization, Child child) {
		super();
		this.identifier = identifier;
		this.description = description;
		this.profundization = profundization;
		this.child = child;
	}

	public int getIdentifier() {
		return identifier;
	}

	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProfundization() {
		return profundization;
	}

	public void setProfundization(String profundization) {
		this.profundization = profundization;
	}

	public Child getChild() {
		return child;
	}

	public void setChild(Child child) {
		this.child = child;
	}
}
