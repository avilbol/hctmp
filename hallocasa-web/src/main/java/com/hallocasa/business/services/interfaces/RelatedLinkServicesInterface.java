/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.business.services.interfaces;

import com.hallocasa.business.dataentities.Link;
import com.hallocasa.business.dataentities.LinkType;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Jhon Fredy Martínez Realpe
 */
public interface RelatedLinkServicesInterface extends Serializable {

    /**
     * Declaration method to query all links belonging to a LinkType.
     *
     * @param idLinkType. Unique identifier of the LinkType.
     * @return List<Link>. List of all links.
     *
     * @author: Jhon Fredy Martínez Realpe
     */
    List<Link> searchRelatedLinks(int idLinkType);

    /**
     * Declaration method to query all type of link.
     *
     * @return List<LinkType>. List of all links.
     */
    List<LinkType> searchLinksType();
}
