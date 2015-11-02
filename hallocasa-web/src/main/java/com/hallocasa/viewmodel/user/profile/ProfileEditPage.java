/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.user.profile;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.i18n.MultiLanguageText;
import com.hallocasa.commons.vo.CountryVO;
import com.hallocasa.commons.vo.StateVO;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.model.application.HallocasaApplication;
import com.hallocasa.model.session.WebSession;
import com.hallocasa.services.location.local.CountryServices;
import com.hallocasa.view.context.ViewContext;
import com.hallocasa.view.navigation.HallocasaViewEnum;
import com.hallocasa.view.navigation.HallocasaViewNames;
import com.hallocasa.view.navigation.NavigationHandler;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * View model for profile page
 *
 * @author Alexander Villamil
 * @since 1.7
 */
@Named(value = HallocasaViewNames.USER_PROFILE_EDIT)
@ViewScoped
public class ProfileEditPage implements Serializable {

    /* instance variables */
    private UserVO user;

    /* dependencies */
    @Inject
    private WebSession webSession;
   
    @Inject
    private ViewContext viewContext;
    @Inject
    private NavigationHandler navigationHandler;
    @Inject
    private HallocasaApplication halloCasaApplication;
    
    @EJB
    private CountryServices countryServices;
    
    /**
     * Country list
     */
    private List<CountryVO> countries;
    
    /**
     * Language list
     */
    private List<Language> languages;
    
    
    
    /**
     * States list
     */
    private List<StateVO> states;
    
    /**
     * English description
     */
    private String userDescriptionEn;
    
    /**
     * User description
     */
    private Map<Language, String> userDescription;
    
    /**
     * Spanish description
     */
    private String userDescriptionEs;
    
    /**
     * Deutsch description
     */
    private String userDescriptionDe;
    
    /**
     * Default constructor
     */
    public ProfileEditPage() {
    }

    /**
     * Initialize the bean
     */
    @PostConstruct
    public void initialize() {
        user = webSession.getCurrentUser();
        countries = countryServices.getCountries();
        states = new ArrayList<StateVO>();
        languages = new ArrayList<Language>();
        languages.add(Language.de);
        languages.add(Language.en);
        languages.add(Language.es);
        userDescription = new HashMap<Language, String>();
        for(Language languaje : languages){
            MultiLanguageText mtLangTxt = user.getUserDescription();
            userDescription.put(languaje, mtLangTxt == null ? "" : user.getUserDescription().getText(languaje));
        }
       
    }
    
    /**
     * Process click event over save button
     */
    public void processSaveClick(){
        // TODO : ask if this is correct
        try {
            // TODO : Implement service
            user.getUserDescription().setDe(userDescription.get(Language.de));
            user.getUserDescription().setEn(userDescription.get(Language.en));
            user.getUserDescription().setEs(userDescription.get(Language.es));
            navigationHandler.redirectToPage(HallocasaViewEnum.MY_PROFILE);
            // TODO : Create bundles for these texts
            viewContext.showGlobalInfoMessage("Hecho", "El perfil ha sido guardado satisfactoriamente");
        } catch (Exception ex) { // What exception type?
            viewContext.showGlobalCustomErrorMessage(ex.getMessage(), "");
        }
    }
    
    /**
     * Process when button "Edit image" is clicked
     */
    public void processEditImageClick(){
        
    }
    
    /**
     * Process click event on cancel button
     */
    public void processCancelClick(){
        navigationHandler.redirectToPage(HallocasaViewEnum.MY_PROFILE);
    }

    /**
     * Process country selection event
     */
    public void processCountrySelect(){
        // TODO ; Modify this method using definitely Services
        CountryVO cvo = user.getCountry();
        List<StateVO> states = new ArrayList<StateVO>();
        if(cvo.getId().equals(1)){
             states = new ArrayList<StateVO>();
             StateVO svo = new StateVO();
             svo.setId((long) 1);
             svo.setStateName("Cundinamarca");
             states.add(svo);
             svo = new StateVO();
             svo.setId((long) 2);
             svo.setStateName("Huila");
             states.add(svo);
             svo = new StateVO();
             svo.setId((long) 3);
             svo.setStateName("Bogotá D.C");
             states.add(svo);
        }
        else if(cvo.getId().equals(2)){
             states = new ArrayList<StateVO>();
             StateVO svo = new StateVO();
             svo.setId((long) 1);
             svo.setStateName("Berlin");
             states.add(svo);
             svo = new StateVO();
             svo.setId((long) 2);
             svo.setStateName("Bradenburg");
             states.add(svo);
             svo = new StateVO();
             svo.setId((long) 3);
             svo.setStateName("Hamburg");
             states.add(svo);
        }
        else{
             states = new ArrayList<StateVO>();
             StateVO svo = new StateVO();
             svo.setId((long) 1);
             svo.setStateName("State 1");
             states.add(svo);
             svo = new StateVO();
             svo.setId((long) 2);
             svo.setStateName("State 2");
             states.add(svo);
             svo = new StateVO();
             svo.setId((long) 3);
             svo.setStateName("State 3");
             states.add(svo);
        }
    }
    
    /**
     * Getter for user
     *
     * @return user
     */
    public UserVO getUser() {
        return user;
    }

    public List<CountryVO> getCountries() {
        return countries;
    }

    public void setCountries(List<CountryVO> countries) {
        this.countries = countries;
    }

    public List<StateVO> getStates() {
        return states;
    }

    public void setStates(List<StateVO> states) {
        this.states = states;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }
    
   
    
    public String getUserDescription(Language language){
         return user.getUserDescription().getText(language);
    }

    public Map<Language, String> getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(Map<Language, String> userDescription) {
        this.userDescription = userDescription;
    }
    
    
}
