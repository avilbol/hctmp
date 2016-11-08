package com.hallocasa.laboratory;

public class Child {

	int childIdentifier;
	
	String childDescription;
	
	String childProfundization;

	public Child(int childIdentifier, String childDescription, String childProfundization) {
		super();
		this.childIdentifier = childIdentifier;
		this.childDescription = childDescription;
		this.childProfundization = childProfundization;
	}

	public int getChildIdentifier() {
		return childIdentifier;
	}

	public void setChildIdentifier(int childIdentifier) {
		this.childIdentifier = childIdentifier;
	}

	public String getChildDescription() {
		return childDescription;
	}

	public void setChildDescription(String childDescription) {
		this.childDescription = childDescription;
	}

	public String getChildProfundization() {
		return childProfundization;
	}

	public void setChildProfundization(String childProfundization) {
		this.childProfundization = childProfundization;
	}
}
