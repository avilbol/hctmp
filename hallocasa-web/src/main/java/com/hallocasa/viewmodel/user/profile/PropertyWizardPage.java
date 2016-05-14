package com.hallocasa.viewmodel.user.profile;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.hallocasa.viewmodel.user.profile.GlobalProfilePage.MenuOption;
import com.hallocasa.viewmodel.user.profile.GlobalProfilePage.ProfileTabMode;
import com.hallocasa.viewmodel.user.profile.GlobalProfilePage.PropertyTabMode;

/**
 * Managed bean for wizard in properties
 * 
 * @author avillamil
 * 
 */
@ManagedBean
@ViewScoped
public class PropertyWizardPage implements Serializable {

	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = 5974980192541279963L;

	/**
	 * Wizard for creation
	 */
	private boolean wizardCreation;

	/**
	 * Phase of the process in wizard
	 */
	private PropertyWizardPhase wizardPhase;

	/**
	 * Initialize
	 */
	@PostConstruct
	public void initialize() {
		
	}
	
	/**
	 * Constants to represent possible phases of process.
	 */
	private enum PropertyWizardPhase {
		INIT, EDITION
	};

	public boolean isWizardCreation() {
		return wizardCreation;
	}

	public void setWizardCreation(boolean wizardCreation) {
		this.wizardCreation = wizardCreation;
	}

	public boolean getShowInitWizard() {
		return this.wizardCreation
				&& this.wizardPhase.equals(PropertyWizardPhase.INIT);
	}
}
