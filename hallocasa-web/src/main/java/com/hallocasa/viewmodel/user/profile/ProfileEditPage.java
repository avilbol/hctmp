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
import com.hallocasa.commons.vo.UserTypeVO;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.model.application.HallocasaApplicationImpl;
import com.hallocasa.model.session.WebSession;
import com.hallocasa.services.location.local.CountryServices;
import com.hallocasa.view.context.ViewContext;
import com.hallocasa.view.navigation.HallocasaViewEnum;
import com.hallocasa.view.navigation.NavigationHandler;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

/**
 * View model for profile page
 *
 * @author Alexander Villamil
 * @since 1.7
 */
@ManagedBean
@RequestScoped
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
    private HallocasaApplicationImpl halloCasaApplication;
    
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
     * User type list
     */
    private List<UserTypeVO> userTypes;
    
    /**
     * States list
     */
    private List<StateVO> states;
    
    /**
     * User description
     */
    private Map<Language, String> userDescription;
    
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
        
        // TODO : Call original service
        // countries = countryServices.getCountries();
        countries = this.getTempCountries();
        // TODO : Delete this line when userTypes functionality was correct
        // user.setUserTypes(this.getTempUserTypes());
        states = new ArrayList<>();
        processCountrySelect();
        languages = halloCasaApplication.getLanguages();
        userDescription = new HashMap<>(); 
        for(Language languaje : languages){
            MultiLanguageText mtLangTxt = user.getUserDescription();
            userDescription.put(languaje, mtLangTxt == null ? "" : user.getUserDescription().getText(languaje));
        }
    }
    
    /**
     * Process change on checkboxes of languages selected
     */
    public void processLanguagesSelectedChange(){
        System.out.println(user.getSpokenLanguages().size());
    }
    
    /**
     * Process click event over save button
     */
    public void processSaveClick(){
        // TODO : ask if this is correct
        try {
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
    
    // TODO: Delete when not needed. Temporary procediment
    public List<CountryVO> getTempCountries(){
        List<CountryVO> countryList = new ArrayList<>();
        countryList.add(new CountryVO(2l, "Alemania", Language.es, null, null));
        countryList.add(new CountryVO(1l, "Colombia", Language.es, null, null));
        return countryList;
    }
    
    public List<UserTypeVO> getTempUserTypes(){
        List<UserTypeVO> userTypeList = new ArrayList<>();
        UserTypeVO userTypeVo = new UserTypeVO();
        userTypeVo.setId(1l);
        MultiLanguageText mt = new MultiLanguageText();
        mt.setDe("Broker");
        mt.setEn("Broker");
        mt.setEs("Broker");
        userTypeVo.setUserTypeName(mt);
        userTypeList.add(userTypeVo);
        
        userTypeVo = new UserTypeVO();
        userTypeVo.setId(2l);
        mt = new MultiLanguageText();
        mt.setDe("Notary");
        mt.setEn("Notary");
        mt.setEs("Notary");
        userTypeVo.setUserTypeName(mt);
        userTypeList.add(userTypeVo);
        
        userTypeVo = new UserTypeVO();
        userTypeVo.setId(3l);
        mt = new MultiLanguageText();
        mt.setDe("Appraisor");
        mt.setEn("Appraisor");
        mt.setEs("Appraisor");
        userTypeVo.setUserTypeName(mt);
        userTypeList.add(userTypeVo);
        
        userTypeVo = new UserTypeVO();
        userTypeVo.setId(4l);
        mt = new MultiLanguageText();
        mt.setDe("Translator");
        mt.setEn("Translator");
        mt.setEs("Translator");
        userTypeVo.setUserTypeName(mt);
        userTypeList.add(userTypeVo);
        
        userTypeVo = new UserTypeVO();
        userTypeVo.setId(4l);
        mt = new MultiLanguageText();
        mt.setDe("Translator");
        mt.setEn("Translator");
        mt.setEs("Translator");
        userTypeVo.setUserTypeName(mt);
        userTypeList.add(userTypeVo);
        
        userTypeVo = new UserTypeVO();
        userTypeVo.setId(5l);
        mt = new MultiLanguageText();
        mt.setDe("Property Manager");
        mt.setEn("Property Manager");
        mt.setEs("Property Manager");
        userTypeVo.setUserTypeName(mt);
        userTypeList.add(userTypeVo);
        
        userTypeVo = new UserTypeVO();
        userTypeVo.setId(6l);
        mt = new MultiLanguageText();
        mt.setDe("Expert");
        mt.setEn("Expert");
        mt.setEs("Expert");
        userTypeVo.setUserTypeName(mt);
        userTypeList.add(userTypeVo);
        return userTypeList;
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
        CountryVO country = user.getCountry();
        if(user.getCountry() == null){
            country = countries.get(0);
        }
        // TODO : Switch to original Service when these are done
        // states = countryServices.getStates(user.getCountry().getId());
        states = new ArrayList<StateVO>();
        if(country.getId().longValue() == 2l){
            states.add(new StateVO());
            states.get(0).setId(1l);
            states.get(0).setStateName("Berlin");
            states.add(new StateVO());
            states.get(1).setId(2l);
            states.get(1).setStateName("Baviera");
        }
        else if(country.getId().longValue() == 1l){
            states.add(new StateVO());
            states.get(0).setId(1l);
            states.get(0).setStateName("Cundinamarca");
            states.add(new StateVO());
            states.get(1).setId(2l);
            states.get(1).setStateName("Antioquia");
        }
        states.get(1).setId(2l);
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

    public List<UserTypeVO> getUserTypes() {
        return userTypes;
    }

    public void setUserTypes(List<UserTypeVO> userTypes) {
        this.userTypes = userTypes;
    }
}
