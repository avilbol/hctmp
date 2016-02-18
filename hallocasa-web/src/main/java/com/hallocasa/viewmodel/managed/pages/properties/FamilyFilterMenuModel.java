package com.hallocasa.viewmodel.managed.pages.properties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.hallocasa.dataentities.wcm.InterestPlaceRange;
import com.hallocasa.dataentities.wcm.NeighborCommunity;
import com.hallocasa.services.TypeServices;
import com.hallocasa.services.filter.FilterCondition.FilterOperation;
import com.hallocasa.viewmodel.components.filter.FilterSelectItem;

/**
 * This class represents the presentation model of the filter menu to controls
 * the left menu filter in the properties browsing
 *
 * @author David Mantilla
 */
public class FamilyFilterMenuModel extends PropertiesFilterMenuModelBase
		implements Serializable {

	private static final long serialVersionUID = 6272474959256332188L;

	public FamilyFilterMenuModel(TypeServices typeServices) {
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

		// Add menuItem filter by play_grounds_range
		createTypeFilter(InterestPlaceRange.class,
				"propertyEnvirontmen.interestPlaceRange8",
				"PropertyBrowse.Property.playgrounds");

		// Add menuItem filter by kinder_gartens_range
		createTypeFilter(InterestPlaceRange.class,
				"propertyEnvirontmen.interestPlaceRange9",
				"PropertyBrowse.Property.kindergartens");

		// Create filter by type (NeighborCommunity)
		createTypeFilter(NeighborCommunity.class,
				"propertyEnvirontmen.neighborCommunity",
				"PropertyBrowse.Property.neighborCommunity");

		// Add menuItem filter by schools_range
		createTypeFilter(InterestPlaceRange.class,
				"propertyEnvirontmen.interestPlaceRange10",
				"PropertyBrowse.Property.schools");

		// Add menuItem filter by universities_range
		createTypeFilter(InterestPlaceRange.class,
				"propertyEnvirontmen.interestPlaceRange11",
				"PropertyBrowse.Property.universities");
	}

}
