package com.hallocasa.viewmodel.managed.pages.properties;

import java.io.Serializable;

import com.hallocasa.dataentities.InterestPlaceRange;
import com.hallocasa.dataentities.RestaurantType;
import com.hallocasa.dataentities.ShoppingWay;
import com.hallocasa.dataentities.Sport;
import com.hallocasa.services.TypeServices;

/**
 * This class represents the presentation model of the filter menu to controls
 * the left menu filter in the properties browsing
 *
 * @author David Mantilla
 */
public class FreeTimeFilterMenuModel extends PropertiesFilterMenuModelBase
		implements Serializable {

	private static final long serialVersionUID = -7129886235886186894L;

	public FreeTimeFilterMenuModel(TypeServices typeServices) {
		super(typeServices, 1);
		initialize();
	}

	/**
	 * Initialize the filter model
	 */
	private void initialize() {

		// TODO: Church

		// Add menuItem filter by sport_clubs_range
		createTypeFilter(InterestPlaceRange.class,
				"propertyEnvirontmen.interestPlaceRange7",
				"PropertyBrowse.Property.sportsClubs");

		// Add menuItem filter by musclubs_range
		createTypeFilter(InterestPlaceRange.class,
				"propertyEnvirontmen.interestPlaceRange13",
				"PropertyBrowse.Property.musicClubs");

		// Add menuItem filter by SportPossibility
		createTypeFilter(Sport.class, "\\sportPossibility.sport",
				"PropertyBrowse.Property.sportPossibility");

		// Add menuItem filter by ShoppingWay
		createTypeFilter(ShoppingWay.class,
				"propertyEnvirontmen.shoppingWayList",
				"PropertyBrowse.Property.shopping");

		// Add menuItem filter by RestaurantType
		createTypeFilter(RestaurantType.class,
				"propertyEnvirontmen.restaurantTypeList",
				"PropertyBrowse.Property.restaurants");

		// Add menuItem filter by cinemas_range
		createTypeFilter(InterestPlaceRange.class,
				"propertyEnvirontmen.interestPlaceRange6",
				"PropertyBrowse.Property.cinemas");

	}

}
