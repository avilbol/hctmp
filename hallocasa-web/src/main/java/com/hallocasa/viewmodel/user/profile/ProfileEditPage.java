/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.user.profile;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.i18n.MultiLanguageText;
import com.hallocasa.commons.vo.CityVO;
import com.hallocasa.commons.vo.CountryVO;
import com.hallocasa.commons.vo.ImageContainer;
import com.hallocasa.commons.vo.StateVO;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.model.application.HallocasaApplicationImpl;
import com.hallocasa.model.session.WebSession;
import com.hallocasa.services.FileServices;
import com.hallocasa.services.interfaces.UserServices;
import com.hallocasa.services.location.local.CountryServices;
import com.hallocasa.utils.ApplicationFileUtils;
import com.hallocasa.view.utils.FormatUtils;
import com.hallocasa.view.context.ViewContext;
import com.hallocasa.view.i18n.Messages;
import com.hallocasa.view.navigation.HallocasaViewEnum;
import com.hallocasa.view.navigation.NavigationHandler;
import com.hallocasa.viewmodel.security.LoginDialog;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import thirdparty.org.apache.commons.io.FileUtils;

/**
 * View model for profile page
 *
 * @author Alexander Villamil
 * @since 1.7
 */
@ManagedBean
@ViewScoped
public class ProfileEditPage implements Serializable {

    /* instance variables */
    private UserVO user;

    /* class variables */
    private static final Logger LOG = Logger.getLogger(LoginDialog.class.getName());
    
    @EJB
    CountryServices countryServices;
    
    @EJB
    private UserServices userServices;
    
    /* dependencies */
    @Inject
    private WebSession webSession;
    @Inject
    private ViewContext viewContext;
    @Inject
    private NavigationHandler navigationHandler;
    @Inject
    private HallocasaApplicationImpl halloCasaApplication;
    
    /**
     * Language list
     */
    private List<Language> languages;
   
    /**
     * States list
     */
    private List<StateVO> states;
    
    /**
     * States list
     */
    private List<CityVO> cities;
    
    /**
     * User description
     */
    private Map<Language, String> userDescription;
    
    /**
     * User images path
     */
    private String userImagesPath;
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
        user = userServices.find(webSession.getCurrentUser().getId());
        if(user.getCountry() != null){
        	states = countryServices.getStates(user.getCountry().getId());
        }
        if(user.getState() != null){
        	cities = countryServices.getCities(user.getState().getId());
        }
        languages = halloCasaApplication.getLanguages();
    }
    
    /**
     * Process change on checkboxes of languages selected
     */
    public void processLanguagesSelectedChange(){
        if(user.getUserDescription() == null){
            user.setUserDescription(new MultiLanguageText());
        }
        for(Language lang : user.getSpokenLanguages()){
            if(FormatUtils.isEmptyValue(user.getUserDescription().getLangValue(lang))){
               user.getUserDescription().setLangValue(lang, ""); 
            }
        }
    }
    
    /**
     * Process click event over save button
     */
    public void processSaveClick(){
        try {
            user.setConfirmedFlag(Boolean.TRUE);
            if(this.user.getImage() != null){
            	manageUserImage();
            }
            user.setImage(new ImageContainer(user.getImage().getUrl()));
            userServices.save(user);
            navigationHandler.redirectToPage(HallocasaViewEnum.MY_PROFILE);
            viewContext.showGlobalInfoMessage("Hecho", "El perfil ha sido guardado satisfactoriamente");
        } catch (Exception ex) { 
            LOG.log(Level.SEVERE, "Unexepcted error", ex);
            viewContext.showGlobalErrorMessage(Messages.UNEXPECTED_ERROR, null);
            viewContext.showGlobalCustomErrorMessage(ex.getMessage(), "");
        }
    }
    
    public void manageUserImage(){
        try{
            String imageUrl = this.user.getImage().getUrl();
            String imagePath = ApplicationFileUtils.getAbsolutePath(imageUrl);
            File sourceFile = new File(ApplicationFileUtils.getAbsoluteUrl(imageUrl));
            String mimeType = ApplicationFileUtils.getMimeType(sourceFile);
            String ext = mimeType.equals("image/png") ? "png" : "jpg";
            File destFile = new File(imagePath + "/user" + this.user.getId() + "." + ext);
            if(!sourceFile.getName().equals(destFile.getName())){
            	FileUtils.deleteQuietly(destFile);
            	FileUtils.copyFile(sourceFile, destFile); 
            }
            this.user.getImage().setUrl(ApplicationFileUtils.getRelativePath(imageUrl) 
                    + "/" + destFile.getName());
            File f = new File(FileServices.USER_IMAGES_PATH);
            File[] matchingFiles = f.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return !name.startsWith("user");
                }
            });
            for(File file : matchingFiles){
                FileUtils.forceDelete(file);
            }
        } catch(IOException e){
            viewContext.showGlobalErrorMessage("error", "error");
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
       cities = new ArrayList<>();
       user.setCity(null);
       user.setState(null);
       if(user.getCountry() != null)
            states = countryServices.getStates(user.getCountry().getId());
       else
           states = new ArrayList<>();
    }
    
     /**
     * Process country selection event
     */
    public void processStateSelect(){
       user.setCity(null);
       if(user.getState() != null)
            cities = countryServices.getCities(user.getState().getId());
       else
           cities = new ArrayList<>();
    }
    
    public String getCountryName(CountryVO country){
        return country.getCountryName().getText(webSession.getCurrentLanguage());
    }
    
    public String getStateName(StateVO state){
        return state.getStateName().getText(webSession.getCurrentLanguage());
    }
    
    public String getCityName(CityVO city){
        return city.getCityName().getText(webSession.getCurrentLanguage());
    }
    
    /**
     * Getter for user
     *
     * @return user
     */
    public UserVO getUser() {
        return user;
    }

    public List<StateVO> getStates() {
        return states;
    }

    public void setStates(List<StateVO> states) {
        this.states = states;
    }

    public List<CityVO> getCities() {
        return cities;
    }

    public void setCities(List<CityVO> cities) {
        this.cities = cities;
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

    public String getUserImagesPath() {
        return userImagesPath;
    }

    public void setUserImagesPath(String userImagesPath) {
        this.userImagesPath = userImagesPath;
    }
    
}
