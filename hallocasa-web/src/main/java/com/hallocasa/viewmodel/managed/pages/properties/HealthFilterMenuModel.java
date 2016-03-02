package com.hallocasa.viewmodel.managed.pages.properties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.hallocasa.dataentities.wcm.InterestPlaceRange;
import com.hallocasa.dataentities.wcm.MedicalFacility;
import com.hallocasa.services.TypeServices;
import com.hallocasa.services.filter.FilterCondition.FilterOperation;
import com.hallocasa.viewmodel.components.filter.FilterSelectItem;

/**
 * This class represents the presentation model of the filter menu to controls
 * the left menu filter in the properties browsing
 *
 * @author David Mantilla
 */
public class HealthFilterMenuModel extends PropertiesFilterMenuModelBase
		implements Serializable {

	private static final long serialVersionUID = 609984116787166180L;

	public HealthFilterMenuModel(TypeServices typeServices) {
		super(typeServices, 1);
		initialize();
	}

	/**
	 * Initialize the filter model
	 */
	private void initialize() {
		// For socio-economic stratum
		List<FilterSelectItem> elements = new ArrayList<>();
		elements.add(new FilterSelectItem(1, "1"));
		elements.add(new FilterSelectItem(2, "2"));
		elements.add(new FilterSelectItem(3, "3"));
		elements.add(new FilterSelectItem(4, "4"));
		elements.add(new FilterSelectItem(5, "5"));
		elements.add(new FilterSelectItem(6, "6"));

		// Add menuItem filter (Custom item list)
		List<FilterSelectItem> items = new ArrayList<>();
		items.add(new FilterSelectItem(1, "1"));
		items.add(new FilterSelectItem(2, "2"));
		items.add(new FilterSelectItem(3, "3"));
		items.add(new FilterSelectItem(4, "4"));
		items.add(new FilterSelectItem(4, ">4", FilterOperation.GREATER_THAN));

		// For years of construction
		List<FilterSelectItem> members = new ArrayList<>();
		members.add(new FilterSelectItem(1, "<1", FilterOperation.LESS_THAN));
		members.add(new FilterSelectItem(2, 1, 5, "1-5"));
		members.add(new FilterSelectItem(3, 5, 20, "5-20"));
		members.add(new FilterSelectItem(4, 20, 50, "20-50"));
		members.add(new FilterSelectItem(5, 50, 100, "50-100"));
		members.add(new FilterSelectItem(100, ">100",
				FilterOperation.GREATER_THAN));

		// For Flags
		List<FilterSelectItem> flag = new ArrayList<>();
		flag.add(new FilterSelectItem(true, "Sí"));
		flag.add(new FilterSelectItem(false, "No"));

		// Add menuItem filter by hospitals_range
		createTypeFilter(InterestPlaceRange.class,
				"propertyEnvirontmen.interestPlaceRange5",
				"PropertyBrowse.Property.hospitals");

		// Add menuItem filter by general Practitioner
		createTypeFilter(InterestPlaceRange.class,
				"propertyEnvirontmen.interestPlaceRange4",
				"PropertyBrowse.Property.doctors");

		// Add menuItem filter by medical_specialists
		createTypeFilter(InterestPlaceRange.class,
				"propertyEnvirontmen.interestPlaceRange3",
				"PropertyBrowse.Property.specialists");

		// Create filter by type (MedicalFacility)
		createTypeFilter(MedicalFacility.class,
				"propertyEnvirontmen.medicalFacilityList",
				"PropertyBrowse.Property.medicalFacility");

		// Add menuItem filter by pharmacies_range
		createTypeFilter(InterestPlaceRange.class,
				"propertyEnvirontmen.interestPlaceRange1",
				"PropertyBrowse.Property.pharmacies");

		// Add menuItem filter by drugstores_range
		createTypeFilter(InterestPlaceRange.class,
				"propertyEnvirontmen.interestPlaceRange",
				"PropertyBrowse.Property.drugstores");
	}

}
