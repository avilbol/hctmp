/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.hallocasa.dataentities.wcm.Image;
import com.hallocasa.exceptions.EntityNotFoundException;
import com.hallocasa.services.interfaces.ImageServicesInterface;

/**
 * @author David Mantilla
 */
@Stateless
public class ImageServices implements ImageServicesInterface {

	public static final String EJB_RESOURCE_NAME = "ImageServices";

	@PersistenceContext(unitName = "RealStateDatabasePU")
	EntityManager em;

	/**
	 * Default constructor
	 */
	public ImageServices() {
	}

	/**
	 * Finds an image in the database by its id
	 *
	 * @param imageId
	 * @return
	 * @throws EntityNotFoundException
	 *             when the image is not in the database
	 */
	@Override
	public Image findImageById(Long imageId) throws EntityNotFoundException {
		Image image = em.find(Image.class, imageId);
		if (image == null) {
			throw new EntityNotFoundException("Image not found: Id " + imageId,
					"ImageService.Image.Notfound.Error", imageId, null);
		}
		return image;
	}

}
