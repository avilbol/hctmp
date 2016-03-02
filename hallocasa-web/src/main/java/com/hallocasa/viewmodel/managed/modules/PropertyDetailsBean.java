/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.managed.modules;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.hallocasa.dataentities.wcm.EnvirontmentPublicTransport;
import com.hallocasa.dataentities.wcm.Property;
import com.hallocasa.dataentities.wcm.PropertyEnvirontment;
import com.hallocasa.dataentities.wcm.PropertyTopography;
import com.hallocasa.dataentities.wcm.RestaurantType;
import com.hallocasa.dataentities.wcm.ShoppingWay;
import com.hallocasa.dataentities.wcm.SportPossibility;
import com.hallocasa.services.interfaces.PropertyServicesInterface;
import com.hallocasa.services.interfaces.PublicTransportServicesInterface;
import com.hallocasa.services.interfaces.SportServicesInterface;

/**
 *
 * @author Jhon Fredy Martínez Realpe
 */
@ManagedBean(name = "propertyDetailsBean")
@ViewScoped
public class PropertyDetailsBean implements Serializable {

	private static final long serialVersionUID = 1L;
	// EJBs
	@EJB
	private transient PropertyServicesInterface propertyServices;
	@EJB
	private transient PublicTransportServicesInterface publicTransportServices;
	@EJB
	private transient SportServicesInterface sportServices;
	// Objects
	private Property property;
	private String propertyTopography;
	private PropertyEnvirontment propertyEnvirontment;
	private String publicTransport;
	private String restaurants;
	private String shopping;
	private String sports;

	@PostConstruct
	public void init() {
		getParameter();
	}

	/**
	 * Obtains the parameter passed by QueryString
	 *
	 * @author: Jhon Fredy Martínez Realpe
	 */
	private void getParameter() {
		final FacesContext facesContext = FacesContext.getCurrentInstance();
		final Map<String, String> parameters = facesContext
				.getExternalContext().getRequestParameterMap();
		final int idProperty = Integer.parseInt(parameters.get("property")
				.toString());

		searchPropertyDetails(idProperty);
	}

	/**
	 * Query the details of the property identified by the id passed by
	 * QueryString
	 *
	 * @param idProperty
	 */
	private void searchPropertyDetails(int idProperty) {
		final List<EnvirontmentPublicTransport> listEnvirontmentPublicTransport;
		final List<SportPossibility> listSportPossibility;
		final List<PropertyTopography> listPropertyTopography = propertyServices
				.findByPropertyId(idProperty);
		property = propertyServices.findById(idProperty);

		if (property != null) {
			propertyEnvirontment = property.getPropertyEnvirontment();
			listEnvirontmentPublicTransport = publicTransportServices
					.findEnvirontmentPublicTransportByPropertyEnvirontmentId(propertyEnvirontment
							.getPropertyEnvirontmentId());
			listSportPossibility = sportServices
					.findByPropertyEnvirontmentId(propertyEnvirontment
							.getPropertyEnvirontmentId());

			createTopography(listPropertyTopography);
			createPublicTransport(listEnvirontmentPublicTransport);
			createRestaurants(propertyEnvirontment.getRestaurantTypeList());
			createShopping(propertyEnvirontment.getShoppingWayList());
			createSport(listSportPossibility);
		}

	}

	/**
	 * Building the topography of a property from a list.
	 *
	 * @param listPropertyTopography
	 *            . List of topographies belonging to a property.
	 */
	private void createTopography(
			List<PropertyTopography> listPropertyTopography) {
		final StringBuilder topography = new StringBuilder();

		for (PropertyTopography pt : listPropertyTopography) {
			topography.append(pt.getTopography().getName());
			topography.append(", ");
		}

		propertyTopography = removeFinalSeparator(topography.toString());
	}

	/**
	 * Building the list, separated by commas, of public transport.
	 *
	 * @param listEnvirontmentPublicTransport
	 *            .
	 *
	 */
	private void createPublicTransport(
			List<EnvirontmentPublicTransport> listEnvirontmentPublicTransport) {
		final StringBuilder transport = new StringBuilder();

		for (EnvirontmentPublicTransport pt : listEnvirontmentPublicTransport) {
			transport.append(pt.getPublicTransport().getName());
			transport.append(", ");
		}

		publicTransport = removeFinalSeparator(transport.toString());
	}

	/**
	 * Building the list, separated by commas, of types of restaurant near the
	 * property.
	 *
	 * @param listRestaurantType
	 *            .
	 *
	 */
	private void createRestaurants(List<RestaurantType> listRestaurantType) {
		final StringBuilder restaurant = new StringBuilder();

		for (RestaurantType restaurantType : listRestaurantType) {
			restaurant.append(restaurantType.getName());
			restaurant.append(", ");
		}

		restaurants = removeFinalSeparator(restaurant.toString());
	}

	/**
	 * Building the list, separated by commas and shops nearby property.
	 *
	 * @param listShoppingWay
	 */
	private void createShopping(List<ShoppingWay> listShoppingWay) {
		final StringBuilder shoppingWay = new StringBuilder();

		for (ShoppingWay sw : listShoppingWay) {
			shoppingWay.append(sw.getName());
			shoppingWay.append(", ");
		}

		shopping = removeFinalSeparator(shoppingWay.toString());
	}

	/**
	 * Building a list, separated by commas, sports practiced in the place where
	 * the property is.
	 *
	 * @param listSportPossibility
	 */
	private void createSport(List<SportPossibility> listSportPossibility) {
		final StringBuilder sport = new StringBuilder();

		for (SportPossibility sp : listSportPossibility) {
			sport.append(sp.getSport().getName());
			sport.append(", ");
		}

		sports = removeFinalSeparator(sport.toString());
	}

	/**
	 * Removes blanks and last caracterer of a string.
	 *
	 * @param string
	 * @return String
	 */
	private String removeFinalSeparator(String string) {
		final String stringAux = string.trim();
		final int stringSize = stringAux.length();

		return (stringSize == 0) ? null : stringAux
				.substring(0, stringSize - 1);
	}

	// Getters and Setters
	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public String getPropertyTopography() {
		return propertyTopography;
	}

	public void setPropertyTopography(String propertyTopography) {
		this.propertyTopography = propertyTopography;
	}

	public PropertyEnvirontment getPropertyEnvirontment() {
		return propertyEnvirontment;
	}

	public void setPropertyEnvirontment(
			PropertyEnvirontment propertyEnvirontment) {
		this.propertyEnvirontment = propertyEnvirontment;
	}

	public String getPublicTransport() {
		return publicTransport;
	}

	public void setPublicTransport(String publicTransport) {
		this.publicTransport = publicTransport;
	}

	public String getSports() {
		return sports;
	}

	public void setSports(String sports) {
		this.sports = sports;
	}

	public String getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(String restaurants) {
		this.restaurants = restaurants;
	}

	public String getShopping() {
		return shopping;
	}

	public void setShopping(String shopping) {
		this.shopping = shopping;
	}
}
