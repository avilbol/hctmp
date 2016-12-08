package com.hallocasa.linkbuilders;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import com.hallocasa.utils.constants.exceptions.SecurityException;

import com.hallocasa.utils.constants.exceptions.FatalException;
import com.hallocasa.utils.security.CodecUtils;
import com.hallocasa.vo.Language;
import com.hallocasa.vo.PasswordRecoveryToken;

public class UserActivationLinkUtils {

    public static final String TOKEN_KEY = "83250.8~*_!;W%y!54L_T^.=.7~-=R-Z^~_Zr^:fo|9;.w4X!6;*r^-^_+~|N38f7;~N3+!l:%%n.%70N:R.%Gc910=2BQ+.|0.!+c7;^%-F-|=;97v^!~-1_~1A5::l";

    public static final String ROOT_URL = "http://www.hallocasa.com";
    
    public static final String PASSWORD_RECOVERY_URL = "/password_recovery";
    
    public static final String USER_ACTIVATION_URL = "/user_activation";
    
    /**
     * Builds the URL to user activation
     *
     * @param email
     * @param userId
     *
     * @return The built URL
     */
    public static String buildUserActivationUrl(String email, Long userId, Language language) {
        Map<String, String> params = new HashMap<>();
        params.put("user_activation_token", generateActivationKey(userId, email));
        params.put("lang", language.getLocale());
        try {
			params.put("email", URLEncoder.encode(email, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			throw new FatalException("Unexpected error", e);
		}
        return getAbsolutePath(ROOT_URL + USER_ACTIVATION_URL, params);
    }
    
    public static String buildPasswordRecoveryUrl(PasswordRecoveryToken token, Language language){
        Map<String, String> params = new HashMap<>();
        params.put("password_recovery_token", token.getTokenContent());
        params.put("lang", language.getLocale());
        return getAbsolutePath(ROOT_URL + PASSWORD_RECOVERY_URL, params);
    }

    /**
     * Creates a signature of the time and email to make impossible to fake the
     * URL
     *
     * @param accountId
     *
     * @param email
     * @return the generated token
     */
    public static String generateActivationKey(Long accountId, String email) {
        return CodecUtils.md5((accountId == null ? "" : accountId) + email + TOKEN_KEY);
    }
    
    public static void validateActivationKey(String email, String activationKey){
    	String strToValidate = CodecUtils.md5(email + TOKEN_KEY);
    	if(!strToValidate.equals(activationKey)){
    		throw new SecurityException(
    				"Invalid activation key", SecurityException.INVALID_AUTH_CODE);
    	}
    }
    
    /**
     * @param params
     * @return
     */
    private static String getAbsolutePath(String url, Map<String, String> params) {
        StringBuilder str = new StringBuilder();
        str.append(url);
        if (params != null) {
            str.append("?");
            for (String key : params.keySet()) {
                str.append("&").append(key).append("=").append(params.get(key));
            }
        }
        return str.toString();
    }

    /* Getters & Setters */
}