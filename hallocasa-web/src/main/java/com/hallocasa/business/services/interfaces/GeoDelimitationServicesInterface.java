/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.business.services.interfaces;

import com.hallocasa.business.dataentities.GeoDelimitation;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Jhon Fredy Martínez Realpe
 */
public interface GeoDelimitationServicesInterface extends Serializable {

	/**
	 * Finds a list of Geo-delimitation that belongs to the same level
	 * 
	 * @param typeId
	 *            Level to filter by
	 * @return the founded list
	 */
	public List<GeoDelimitation> findByType(int typeId);

	/**
	 * Finds a list of geo-delimitation by a parent id
	 * 
	 * @param parentId
	 * @return
	 */
	public List<GeoDelimitation> findByParentId(long parentId);

}
