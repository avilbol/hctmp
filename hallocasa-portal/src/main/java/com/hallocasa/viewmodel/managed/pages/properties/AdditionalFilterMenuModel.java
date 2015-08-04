package com.hallocasa.viewmodel.managed.pages.properties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.convert.BooleanConverter;

import com.hallocasa.business.dataentities.EnergyType;
import com.hallocasa.business.dataentities.HeatingType;
import com.hallocasa.business.dataentities.PriceDevelopment;
import com.hallocasa.business.dataentities.PropertyCondition;
import com.hallocasa.business.dataentities.PropertyUse;
import com.hallocasa.business.dataentities.Security;
import com.hallocasa.business.dataentities.SewageWater;
import com.hallocasa.business.services.TypeServices;
import com.hallocasa.business.services.filter.FilterCondition.FilterOperation;
import com.hallocasa.view.converters.IntegerConverter;
import com.hallocasa.viewmodel.components.filter.FilterSelectItem;

/**
 * This class represents the presentation model of the filter menu to controls
 * the left menu filter in the properties browsing
 *
 * @author David Mantilla
 */
public class AdditionalFilterMenuModel extends PropertiesFilterMenuModelBase
		implements Serializable {

	private static final long serialVersionUID = 883512296715486124L;

	public AdditionalFilterMenuModel(TypeServices typeServices) {
		super(typeServices, 1);
		initialize();
	}

	/**
	 * Initialize the filter model
	 */
	private void initialize() {

		// For Flags
		List<FilterSelectItem> flag = new ArrayList<>();
		flag.add(new FilterSelectItem(true, "Sí"));
		flag.add(new FilterSelectItem(false, "No"));

		// For socioeconomic stratum
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

		// TODO: property access
		// Create filter by type (PropertyAccess)
		// createTypeFilter(AccessType.class, "propertyAccessList.accessType",
		// "PropertyBrowse.Property.propertyAccess")
		// Create filter by type (EnergyType)
		createTypeFilter(EnergyType.class, "energyType",
				"PropertyBrowse.Property.energyType");

		// TODO: Create PropertyWater Filter
		// Create filter by type (SewageWater)
		createTypeFilter(SewageWater.class, "sewageWater",
				"PropertyBrowse.Property.sewageWater");

		// Create filter by type (Security)
		createTypeFilter(Security.class, "security",
				"PropertyBrowse.Property.security");

		// TODO: Suitable for
		// Create filter by type (PropertyUse)
		createTypeFilter(PropertyUse.class, "propertyUseList",
				"PropertyBrowse.Property.propertyUse");

		// Create filter by type (PropertyTopography)
		// createTypeFilter(PropertyTopography.class, "propertyTopographyList",
		// "PropertyBrowse.Property.topography");
		// Add menuItem filter by socioeconomic stratum
		createManyFilter(Integer.class, "socioeconomicStratum",
				"PropertyBrowse.Property.stratum", elements,
				new IntegerConverter());

		// Add menuItem filter by balcony
		createManyFilter(Integer.class, "balconyCount",
				"PropertyBrowse.Property.balcony", items,
				new IntegerConverter());

		// TODO: Cellar
		// Add menuItem filter by rented
		createManyFilter(Boolean.class, "rentedFlag",
				"PropertyBrowse.Property.rented", flag, new BooleanConverter());

		// Add menuItem filter by elevator
		createManyFilter(Boolean.class, "elevatorFlag",
				"PropertyBrowse.Property.elevator", flag,
				new BooleanConverter());

		// Add menuItem filter by garden
		createManyFilter(Boolean.class, "gerdenFlag",
				"PropertyBrowse.Property.garden", flag, new BooleanConverter());

		// Create filter by type (PropertyCondition)
		createTypeFilter(PropertyCondition.class, "propertyCondition",
				"PropertyBrowse.Property.propertyCondition");

		// Add menuItem filter by concierge
		createManyFilter(Boolean.class, "conciergeFlag",
				"PropertyBrowse.Property.concierge", flag,
				new BooleanConverter());

		// Add menuItem filter by Last Modernization
		createManyFilter(Integer.class, "lastModernization",
				"PropertyBrowse.Property.lastModernization", members,
				new IntegerConverter());

		// Create filter by type (HeatingType)
		createTypeFilter(HeatingType.class, "heatingType",
				"PropertyBrowse.Property.heating");

		// Add menuItem filter by Pool
		createManyFilter(Boolean.class, "poolFlag",
				"PropertyBrowse.Property.pool", flag, new BooleanConverter());

		// Create filter by type (TenPriceDevelopment)
		createTypeFilter(PriceDevelopment.class,
				"propertyEnvirontmen.priceDevelopment",
				"PropertyBrowse.Property.tenPriceDevelopment");

		// Create filter by type (threePriceDevelopment)
		createTypeFilter(PriceDevelopment.class,
				"propertyEnvirontmen.priceDevelopment1",
				"PropertyBrowse.Property.threePriceDevelopment");

		// Add menuItem filter by built in kitchen
		createManyFilter(Boolean.class, "builtInKitchenFlag",
				"PropertyBrowse.Property.builtInKitchen", flag,
				new BooleanConverter());

	}

}
