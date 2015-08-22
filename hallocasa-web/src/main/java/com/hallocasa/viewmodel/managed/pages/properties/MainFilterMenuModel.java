package com.hallocasa.viewmodel.managed.pages.properties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.hallocasa.dataentities.BuildingCharacteristicType;
import com.hallocasa.dataentities.ConstructionMethod;
import com.hallocasa.dataentities.FornitureType;
import com.hallocasa.dataentities.GeoDelimitation;
import com.hallocasa.dataentities.Location;
import com.hallocasa.dataentities.PropertyCharacteristicType;
import com.hallocasa.dataentities.PropertyType;
import com.hallocasa.dataentities.ServiceType;
import com.hallocasa.services.TypeServices;
import com.hallocasa.services.filter.FilterCondition.FilterOperation;
import com.hallocasa.services.interfaces.GeoDelimitationServicesInterface;
import com.hallocasa.view.converters.DoubleConverter;
import com.hallocasa.view.converters.IdentificableConverter;
import com.hallocasa.view.converters.IntegerConverter;
import com.hallocasa.viewmodel.components.filter.FilterSelectItem;
import com.hallocasa.viewmodel.components.filter.ManyFilterGroup;

/**
 * This class represents the presentation model of the filter menu to controls the left menu filter
 * in the properties browsing
 *
 * @author David Mantilla
 */
public class MainFilterMenuModel extends PropertiesFilterMenuModelBase
        implements Serializable {

    private static final long serialVersionUID = 2109133060284589126L;
    private final GeoDelimitationServicesInterface geoDelimitationServices;
    private ManyFilterGroup<GeoDelimitation> stateFilter;
    private ManyFilterGroup<GeoDelimitation> cityFilter;

    /**
     * Constructor
     *
     * @param typeServices
     * @param geoDelimitationServices
     */
    public MainFilterMenuModel(TypeServices typeServices,
            GeoDelimitationServicesInterface geoDelimitationServices) {
        super(typeServices, 1);
        this.geoDelimitationServices = geoDelimitationServices;
        initialize();
    }

    /**
     * Initialize the filter model
     */
    private void initialize() {
        // Add menuItem filter (Custom item list)
        List<FilterSelectItem> items = new ArrayList<>();
        items.add(new FilterSelectItem(1, "1"));
        items.add(new FilterSelectItem(2, "2"));
        items.add(new FilterSelectItem(3, "3"));
        items.add(new FilterSelectItem(4, "4"));
        items.add(new FilterSelectItem(4, ">4", FilterOperation.GREATER_THAN));

        // For Agent Fee
        List<FilterSelectItem> porcentage = new ArrayList<>();
        porcentage.add(new FilterSelectItem(0.999999, "<1%",
                FilterOperation.LESS_AND_EQUALS_THAN));
        porcentage.add(new FilterSelectItem(1.0, "1%"));
        porcentage.add(new FilterSelectItem(1.5, "1.5%"));
        porcentage.add(new FilterSelectItem(2.0, "2%"));
        porcentage.add(new FilterSelectItem(2.5, "2.5%"));
        porcentage.add(new FilterSelectItem(3.0, "3%"));
        porcentage.add(new FilterSelectItem(3.5, "3.5%"));
        porcentage.add(new FilterSelectItem(4.0, "4%"));
        porcentage.add(new FilterSelectItem(4.5, "4.5%"));
        porcentage.add(new FilterSelectItem(5.0, "5%"));
        porcentage.add(new FilterSelectItem(5.5, "5.5%"));
        porcentage.add(new FilterSelectItem(6.0, "6%"));
        porcentage.add(new FilterSelectItem(6.5, "6.5%"));
        porcentage.add(new FilterSelectItem(7.0, "7%"));
        porcentage.add(new FilterSelectItem(7.5, "7.5%"));
        porcentage.add(new FilterSelectItem(8.0, "8%"));
        porcentage.add(new FilterSelectItem(8.000001, ">8%",
                FilterOperation.GREATER_AND_EQUALS_THAN));

        // For years of construction
        List<FilterSelectItem> members = new ArrayList<>();
        members.add(new FilterSelectItem(1, "<1", FilterOperation.LESS_THAN));
        members.add(new FilterSelectItem(2, 1, 5, "1-5"));
        members.add(new FilterSelectItem(3, 5, 20, "5-20"));
        members.add(new FilterSelectItem(4, 20, 50, "20-50"));
        members.add(new FilterSelectItem(5, 50, 100, "50-100"));
        members.add(new FilterSelectItem(100, ">100",
                FilterOperation.GREATER_THAN));

        // Add Title filter
        createContainsFilter(String.class, "title",
                "PropertyBrowse.Property.title");

        // Create filter by type (Location)
        createTypeFilter(Location.class, "location",
                "PropertyBrowse.Property.location");

        // State Filter
        crateStateFilter();

        // City Filter
        cityFilter = createManyFilter(GeoDelimitation.class,
                "geoDelimitation.geoDelimitation", "PropertyBrowse.Property.city",
                new ArrayList<FilterSelectItem>(), null);

        // TODO: Minucipality
        // TODO: Description
        //
        // Create filter by type (propertyType)
        createTypeFilter(PropertyType.class, "propertyType",
                "PropertyBrowse.Property.propertyType");

        // Create filter by type (serviceType)
        createTypeFilter(ServiceType.class, "serviceType",
                "PropertyBrowse.Property.serviceType");

        // Create filter by roomsCount
        createManyFilter(Integer.class, "roomsCount",
                "PropertyBrowse.Property.roomsCount", items,
                new IntegerConverter());

        // Create filter by montly square meters living
        createBetweenFilter(Double.class, "squareMetersLiving",
                "PropertyBrowse.Property.squareMetersLiving");

        // Create filter by bathrooms
        createManyFilter(Integer.class, "bathrooms",
                "PropertyBrowse.Property.bathrooms", items,
                new IntegerConverter());

        // Create filter by price
        // TODO: The price must support multi-currrency: Pending to define story
        // user about this
        createBetweenFilter(Double.class, "price",
                "PropertyBrowse.Property.price");

        // Create filter by Agent Fee
        createManyFilter(Double.class, "agentFeePercent",
                "PropertyBrowse.Property.agentFeePercent", porcentage,
                new DoubleConverter());

        // garage price
        createBetweenFilter(Double.class, "garagePurchasePrice",
                "PropertyBrowse.Property.garagePurchasePrice");

        // Create filter by montly fees
        createBetweenFilter(Double.class, "montlyFees",
                "PropertyBrowse.Property.monthly-charges");

        // Add square meters filter
        createBetweenFilter(Double.class, "squareMetersProperty",
                "PropertyBrowse.Property.squareMetersProperty");

        // Create filter by type (fornitureType)
        createTypeFilter(FornitureType.class, "fornitureType",
                "PropertyBrowse.Property.forniture");

        // Create filter by type (PropertyCharacteristicType)
        createTypeFilter(PropertyCharacteristicType.class,
                "propertyCharacteristicTypeList",
                "PropertyBrowse.Property.propertyCharacteristics");

        // Create filter by type (buildingCharacteristicTypeList)
        createTypeFilter(BuildingCharacteristicType.class,
                "buildingCharacteristicTypeList",
                "PropertyBrowse.Property.buildingCharacteristics");

        // Create filter by type (ConstructionMethod)
        createTypeFilter(ConstructionMethod.class, "constructionMethod",
                "PropertyBrowse.Property.constructionMethod");

        // Create filter by parking
        createManyFilter(Integer.class, "parkingSpotsCount",
                "PropertyBrowse.Property.parking", items,
                new IntegerConverter());

        // Create filter by floorsCount
        createManyFilter(Integer.class, "floorsCount",
                "PropertyBrowse.Property.floors", items, new IntegerConverter());

        // Create filter by floor
        createManyFilter(Integer.class, "floor",
                "PropertyBrowse.Property.floor", items, new IntegerConverter());

        // Add menuItem filter by years of construction
        createManyFilter(Integer.class, "yearOfConstruction",
                "PropertyBrowse.Property.yearsOfConstruction", members,
                new IntegerConverter());

    }

    /**
     * Creates the state filter
     */
    private void crateStateFilter() {
        List<GeoDelimitation> states = geoDelimitationServices
                .findByParentId(GeoDelimitation.COLOMBIA_ID);

        IdentificableConverter<GeoDelimitation> converter = new IdentificableConverter<>(
                states);
        List<FilterSelectItem> filterSelectItems = new ArrayList<FilterSelectItem>();
        for (GeoDelimitation s : states) {
            FilterSelectItem fsi = new FilterSelectItem(s, s.getName());
            filterSelectItems.add(fsi);
        }

        stateFilter = createManyFilter(GeoDelimitation.class,
                "geoDelimitation.geoDelimitation", "PropertyBrowse.State",
                filterSelectItems, converter);

    }

    /**
     * @return the stateFilter
     */
    public ManyFilterGroup<GeoDelimitation> getStateFilter() {
        return stateFilter;
    }

    /**
     * Reloads the city list
     *
     * @param <Y>
     * @param listValue
     */
    public <Y> void reloadCities(List<Y> listValue) {

        // reload city list searching by each state 
        List<GeoDelimitation> cities = new ArrayList<>();
        for (Y stateY : listValue) {
            GeoDelimitation state = (GeoDelimitation) stateY;
            List<GeoDelimitation> partialCityList = geoDelimitationServices
                    .findByParentId(state.getId());
            cities.addAll(partialCityList);
        }

        // 
        IdentificableConverter<GeoDelimitation> converter = new IdentificableConverter<>(
                cities);
        List<FilterSelectItem> filterSelectItems = new ArrayList<>();
        for (GeoDelimitation s : cities) {
            FilterSelectItem fsi = new FilterSelectItem(s, s.getName());
            filterSelectItems.add(fsi);
        }

        cityFilter.clearFilterConditions();
        cityFilter.getOptionList().clear();
        cityFilter.getOptionList().addAll(filterSelectItems);
        cityFilter.setConverter(converter);

    }
}
