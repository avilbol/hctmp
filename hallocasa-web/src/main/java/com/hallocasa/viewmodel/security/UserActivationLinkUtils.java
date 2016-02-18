package com.hallocasa.viewmodel.security;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.codec.CodecUtils;
import com.hallocasa.dataentities.app.Token;
import com.hallocasa.view.navigation.HallocasaViewEnum;
import com.hallocasa.view.navigation.ViewParamEnum;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class UserActivationLinkUtils {

    /**
     * Token key
     */
    public static final String TOKEN_KEY = "83250.8~*_!;W%y!54L_T^.=.7~-=R-Z^~_Zr^:fo|9;.w4X!6;*r^-^_+~|N38f7;~N3+!l:%%n.%70N:R.%Gc910=2BQ+.|0.!+c7;^%-F-|=;97v^!~-1_~1A5::l";

    /* static fields */

    /* instance variables */

    /* constructors */

    /* Methods */
    /**
     * Builds the URL to user activation
     *
     * @param email
     * @param userId
     *
     * @return The built URL
     */
    public static String buildUrl(String email, long userId) {

        Map<String, String> params = new HashMap<>();
        params.put(ViewParamEnum.TOKEN.getParamKey(),
                generateActivationKey(userId, email));
        try {
            params.put(ViewParamEnum.EMAIL.getParamKey(), URLEncoder
                    .encode(email, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e); // UTF-8 is not going to generates
            // this exception ever
        }
        return HallocasaViewEnum.USER_ACTIVATION.getAbsolutePath(params);
    }
    
    public static String buildPasswordRecoveryUrl(Token token, Language language){
        Map<String, String> params = new HashMap<>();
        params.put(ViewParamEnum.RECOVERY_PASSWORD.getParamKey(),
                token.getTokenContent());
        params.put(ViewParamEnum.LANGUAGE.getParamKey(), language.toString());
        return HallocasaViewEnum.HOME.getAbsolutePath(params);
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
    public static String generateActivationKey(long accountId, String email) {
        return CodecUtils.md5(accountId + email + TOKEN_KEY);
    }

    /* Getters & Setters */
}
