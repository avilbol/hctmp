/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.exceptions;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.exceptions.services.ServiceException;

/**
 * @author David Mantilla
 */
public class EntityNotFoundException extends ServiceException {

    private static final long serialVersionUID = -8907577987271918324L;
    private Long entityId;

    public EntityNotFoundException(String message, String messageKey,
            Long entityId, Throwable cause) {
        super(message, messageKey, cause);
        this.entityId = entityId;
    }

    @Override
    public String getLocalizedMessage(Language language) {
        String message = super.getLocalizedMessage(language);
        return message.replaceAll("\\{0\\}", entityId + "");
    }

}
