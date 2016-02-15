package com.hallocasa.viewmodel.user.present;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.hallocasa.commons.Language;
import com.hallocasa.model.application.HallocasaApplicationImpl;
import com.hallocasa.model.session.WebSession;

/**
 * View model for profile browser
 *
 * @author Alexander Villamil
 * @since 1.7
 */
@ManagedBean
@ViewScoped
public class ProfileBrowserPage {

	/* dependencies */
    @Inject
    private WebSession webSession;
	
	private Language language;
	
	@PostConstruct
	public void init(){
		this.language = webSession.getCurrentLanguage();
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}
}
