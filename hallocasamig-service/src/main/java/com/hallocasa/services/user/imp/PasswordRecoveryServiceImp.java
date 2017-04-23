package com.hallocasa.services.user.imp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.lang3.RandomStringUtils;

import com.hallocasa.dao.i.IDAOPasswordRecoveryToken;
import com.hallocasa.dao.i.IDAOUser;
import com.hallocasa.entities.EntityPasswordRecoveryToken;
import com.hallocasa.entities.EntityUser;
import com.hallocasa.linkbuilders.UserActivationLinkUtils;
import com.hallocasa.services.messaging.exceptions.MailServicesErrorException;
import com.hallocasa.services.messaging.local.MailServices;
import com.hallocasa.services.messaging.local.MailServices.BuildInMailType;
import com.hallocasa.services.user.PasswordRecoveryService;
import com.hallocasa.utils.constants.exceptions.BadRequestException;
import com.hallocasa.utils.constants.exceptions.FatalException;
import com.hallocasa.utils.constants.exceptions.SecurityException;
import com.hallocasa.utils.constants.parsing.HallocasaConvert;
import com.hallocasa.utils.security.CodecUtils;
import com.hallocasa.vo.Language;
import com.hallocasa.vo.PasswordRecoveryRequest;
import com.hallocasa.vo.PasswordRecoveryToken;

@Stateless
public class PasswordRecoveryServiceImp implements PasswordRecoveryService {
   
    @EJB
    private MailServices mailServices;
    
    @EJB
    private IDAOPasswordRecoveryToken daoPasswordRecoveryToken;
    
    @EJB
    private IDAOUser daoUser;

    @Override
    public void sendPasswordRecovery(String email, String url) {
        Optional<EntityUser> entUser = daoUser.find(email);
        if(!entUser.isPresent()){
            throw new SecurityException("User with email entered, not found",
            		SecurityException.FORBIDDEN);
        }
        PasswordRecoveryToken token = createRecoveryPasswordToken(entUser.get().getId());
        EntityUser entUserVal = entUser.get();
        Map<String, String> params = new HashMap<>();
        params.put("PASSWORD_LINK", generateRecoveryPasswordLink(token, 
        		(Language) HallocasaConvert.toValueObject(entUserVal.getLanguage()), url));
        daoPasswordRecoveryToken.saveToken((EntityPasswordRecoveryToken) 
        		HallocasaConvert.toEntity(token));
        List<String> emails = new ArrayList<>();
        emails.add(email);
        try{
        	mailServices.sendMail(BuildInMailType.PASSWORD_RECOVERY, 
                     new Locale(entUser.get().getLanguage().getLocale()), emails, params);
        } catch(MailServicesErrorException e){
        	throw new FatalException("Unexpected error", e);
        }
    }
    
    @Override
    public void confirmPasswordRecovery(PasswordRecoveryRequest passwordRecoveryRequest) {
        if (passwordRecoveryRequest == null){
        	throw new BadRequestException("Inexistent password recovery request");
        }
    	if (passwordRecoveryRequest.getNewPassword() == null) {
            throw new BadRequestException("this request must include the new password");
        }
    	if (passwordRecoveryRequest.getPasswordRecoveryToken() == null) {
            throw new BadRequestException("this request must include the password recovery token");
        }
        if (passwordRecoveryRequest.getPasswordRecoveryToken().getIdAssociated() == null) {
            throw new BadRequestException("this request must include the user id to update");
        }
        validatePasswordRecoveryToken(passwordRecoveryRequest
    			.getPasswordRecoveryToken().getTokenContent());
        PasswordRecoveryToken token = passwordRecoveryRequest.getPasswordRecoveryToken();
        String codecPassword = CodecUtils.encryptPassword(passwordRecoveryRequest.getNewPassword());
        daoUser.updatePassword(token.getIdAssociated().longValue(), codecPassword);
        daoPasswordRecoveryToken.deleteToken(token.getIdAssociated());
    }
    
    @Override
    public PasswordRecoveryRequest validatePasswordRecoveryToken(String passwordRecoveryToken) {
    	if(passwordRecoveryToken == null){
    		throw new BadRequestException("The password recovery token must be supplied");
    	}
    	EntityPasswordRecoveryToken token = daoPasswordRecoveryToken
       		 .getToken(passwordRecoveryToken);
    	if(token == null){
    		throw new SecurityException("Invalid password recovery token", SecurityException.INVALID_AUTH_CODE);
    	}
    	return new PasswordRecoveryRequest((PasswordRecoveryToken) HallocasaConvert.toValueObject(token));
    }
    
    private PasswordRecoveryToken createRecoveryPasswordToken(Long userId) {
        Calendar cal = Calendar.getInstance();
        String tokenContent = RandomStringUtils.randomAlphanumeric(50) + cal.getTimeInMillis(); 
        PasswordRecoveryToken token = new PasswordRecoveryToken();
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
    
    private String generateRecoveryPasswordLink(PasswordRecoveryToken token, Language language, String url){
        return UserActivationLinkUtils.buildPasswordRecoveryUrl(token, language, url);
    }
}
