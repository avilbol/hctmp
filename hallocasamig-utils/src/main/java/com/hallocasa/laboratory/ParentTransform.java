package com.hallocasa.laboratory;

public class ParentTransform {

	String description;

	String profundization;
	
	ChildTransform child;

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

	public ChildTransform getChild() {
		return child;
	}

	public void setChild(ChildTransform child) {
		this.child = child;
	}
}
