package com.hallocasa.commons.vo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.hallocasa.commons.vo.interfaces.ValueObject;

/**
 * Value Object with access information for a given application
 * 
 * @author David Mantilla
 * @since 1.7
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AppAccessInfo", propOrder = { "useCases", "operations",
	"supervisedOperations", "profiles", "informationGroups" })
public class AppAccessInfoVO implements ValueObject {

	/* static fields */

	/* instance variables */
	private List<UseCaseVO> useCases;
	private List<OperationVO> operations;
	private List<OperationVO> supervisedOperations;
	private List<InformationGroupVO> informationGroups;
	private List<ProfileVO> profiles;

	/* constructors */

	/**
	 * Default Constructor
	 */
	public AppAccessInfoVO() {
		useCases = new ArrayList<>();
		operations = new ArrayList<>();
		informationGroups = new ArrayList<>();
	}

	/* Methods */

	/* Getters & Setters */

	/**
	 * Getter for useCases
	 * 
	 * @return the useCases
	 */
	public List<UseCaseVO> getUseCases() {
		return useCases;
	}

	/**
	 * Setter for useCases
	 * 
	 * @param useCases the useCases to set
	 */
	public void setUseCases(List<UseCaseVO> useCases) {
		this.useCases = useCases;
	}

	/**
	 * Getter for operations
	 * 
	 * @return the operations
	 */
	public List<OperationVO> getOperations() {
		return operations;
	}

	/**
	 * Setter for operations
	 * 
	 * @param operations the operations to set
	 */
	public void setOperations(List<OperationVO> operations) {
		this.operations = operations;
	}

	/**
	 * Getter for supervisedOperations
	 * 
	 * @return the supervisedOperations
	 */
	public List<OperationVO> getSupervisedOperations() {
		return supervisedOperations;
	}

	/**
	 * Setter for supervisedOperations
	 * 
	 * @param supervisedOperations the supervisedOperations to set
	 */
	public void setSupervisedOperations(List<OperationVO> supervisedOperations) {
		this.supervisedOperations = supervisedOperations;
	}

	public List<ProfileVO> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<ProfileVO> profiles) {
		this.profiles = profiles;
	}

	/**
	 * Getter for informationGroups
	 * 
	 * @return the informationGroups
	 */
	public List<InformationGroupVO> getInformationGroups() {
		return informationGroups;
	}

	/**
	 * Setter for informationGroups
	 * 
	 * @param informationGroups the informationGroups to set
	 */
	public void setInformationGroups(List<InformationGroupVO> informationGroups) {
		this.informationGroups = informationGroups;
	}

}
