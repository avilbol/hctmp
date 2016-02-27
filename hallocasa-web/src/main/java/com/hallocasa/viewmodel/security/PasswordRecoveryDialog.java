/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.security;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.codec.CodecUtils;
import com.hallocasa.commons.exceptions.services.ServiceException;
import com.hallocasa.commons.i18n.ValidationMessages;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.dataentities.app.Token;
import com.hallocasa.model.application.HallocasaApplicationImpl;
import com.hallocasa.model.session.WebSession;
import com.hallocasa.services.interfaces.SecurityServices;
import com.hallocasa.services.interfaces.UserServices;
import com.hallocasa.services.messaging.exceptions.MailServicesErrorException;
import com.hallocasa.services.messaging.local.MailServices;
import com.hallocasa.services.messaging.local.MailServices.BuildInMailType;
import com.hallocasa.view.context.ViewContext;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author Alexander Villamil
 */
@ManagedBean(name = "passwordRecoveryDialog")
@ViewScoped
public class PasswordRecoveryDialog {
    
     /* class variables */
    private static final Logger LOG = Logger.getLogger(PasswordRecoveryDialog.class.getName());
    
    private boolean settingUp;
    
    private String email;
    
    private Integer userId;
    
    private String newPassword;
    
    private String newPasswordBrother;
    
    @Inject
    private WebSession webSession;
    
    @EJB
    private MailServices mailServices;
    
    @EJB
    private UserServices userServices;
    
    @EJB
    private SecurityServices securityServices;
    
    @Inject
    private ViewContext viewContext;
    
     /**
     * Initialize
     */
    @PostConstruct
    public void initialize(){
        this.settingUp = true;
        this.email = null;
    }

    public void sendPasswordRecovery() throws MailServicesErrorException{
        UserVO user = userServices.find(this.email);
        if(user == null){
            viewContext.showGlobalErrorMessage("ForgotPassword.enterEmail.errorNotFound", 
                    "ForgotPassword.enterEmail.errorNotFound");
            return;
        }
        
        Token token = createRecoveryPasswordToken(user.getId());
        
        Map<String, String> params = new HashMap<>();
        params.put("PASSWORD_LINK", generateRecoveryPasswordLink(token, user.getLanguage()));
        
        
        securityServices.saveToken(token);
        List<String> emails = new ArrayList<>();
        emails.add(this.email);
        mailServices.sendMail(BuildInMailType.PASSWORD_RECOVERY, 
                user.getLanguage().getLocale(), emails, params);
        this.settingUp = false;
    }
    
    public void confirmPasswordRecovery() {
        if (!validateForm()) {
            return;
        }
        try {
            this.userId = HallocasaApplicationImpl.getInstance().getUserIdInRecoveryProcess();
            UserVO user = userServices.find(this.userId);
            user.setPassword(CodecUtils.encryptPassword(this.newPassword));
            userServices.save(user);
            securityServices.deleteToken(this.userId);
            this.settingUp = false;
        } catch (ServiceException ex) {
            viewContext.showGlobalErrorMessage("Common.UnexpectedError.Message", 
                    null);
            LOG.log(Level.SEVERE, ex.getMessage());
        }
    }
    
    public boolean isSettingUp() {
        return settingUp;
    }

    public void setSettingUp(boolean settingUp) {
        this.settingUp = settingUp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordBrother() {
        return newPasswordBrother;
    }

    public void setNewPasswordBrother(String newPasswordBrother) {
        this.newPasswordBrother = newPasswordBrother;
    }
    
    private Token createRecoveryPasswordToken(Long userId) {
        //String tokenContent = UserActivationLinkUtils.generateActivationKey(userId, this.email);
        Calendar cal = Calendar.getInstance();
        String tokenContent = RandomStringUtils.randomAlphanumeric(50) + cal.getTimeInMillis(); 
        Token token = new Token();
        token.setActive(true);
        Date date = new Date();
        token.setExpeditionDate(new Date());
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);  
        token.setDueDate(cal.getTime());
        token.setIdAssociated(userId.intValue());
        token.setTokenContent(tokenContent);
        return token;
    }
    
    private String generateRecoveryPasswordLink(Token token, Language language){
        return UserActivationLinkUtils.buildPasswordRecoveryUrl(token, language);
    }
 
    
     /**
     * Execute additional validations, that means other than bean validations
     *
     * @return
     */
    private boolean validateForm() {
        if (!newPasswordBrother.equals(newPassword)) {
            viewContext.showGlobalCustomErrorMessage(
                    ValidationMessages.getString(
                            ValidationMessages.RECOVERY_PASSWORD_CONFIRM_NOT_MATCH,
                            webSession.getCurrentLanguage().getLocale()),
                    null);
            return false;
        }
        return true;
    }
    
}
