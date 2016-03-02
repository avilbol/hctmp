/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.interfaces;

import com.hallocasa.dataentities.wcm.EnvirontmentPublicTransport;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Jhon Fredy Martínez Realpe
 */
public interface PublicTransportServicesInterface extends Serializable {

    /**
     *
     * @param propertyEnvirontmentId
     * @return List<EnvirontmentPublicTransport>
     */
    List<EnvirontmentPublicTransport> findEnvirontmentPublicTransportByPropertyEnvirontmentId(int propertyEnvirontmentId);
}
