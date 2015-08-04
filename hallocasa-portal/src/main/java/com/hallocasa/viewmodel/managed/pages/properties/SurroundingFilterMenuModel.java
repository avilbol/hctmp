package com.hallocasa.viewmodel.managed.pages.properties;

import java.io.Serializable;

import com.hallocasa.business.dataentities.Agriculture;
import com.hallocasa.business.dataentities.AnimalAmmount;
import com.hallocasa.business.dataentities.AverageAge;
import com.hallocasa.business.dataentities.FlowersAmmount;
import com.hallocasa.business.dataentities.Fructiculture;
import com.hallocasa.business.dataentities.Humidity;
import com.hallocasa.business.dataentities.IndsutrialDriver;
import com.hallocasa.business.dataentities.Industry;
import com.hallocasa.business.dataentities.PopulationDensity;
import com.hallocasa.business.dataentities.RainIntensity;
import com.hallocasa.business.dataentities.RainPeriod;
import com.hallocasa.business.dataentities.RegionGrowthRate;
import com.hallocasa.business.dataentities.SeaLevel;
import com.hallocasa.business.dataentities.SoilType;
import com.hallocasa.business.services.TypeServices;

/**
 * This class represents the presentation model of the filter menu to controls
 * the left menu filter in the properties browsing
 *
 * @author David Mantilla
 */
public class SurroundingFilterMenuModel extends PropertiesFilterMenuModelBase
		implements Serializable {

	private static final long serialVersionUID = -2152395502220663649L;

	public SurroundingFilterMenuModel(TypeServices typeServices) {
		super(typeServices, 1);
		initialize();
	}

	/**
	 * Initialize the filter model
	 */
	private void initialize() {
		// For socio-economic stratum

		// TODO:
		// Create filter by type (PropertyTopography)
		// createTypeFilter(PropertyTopography.class, "propertyTopographyList",
		// "PropertyBrowse.Property.topography");
		// Add menuItem filter by socioeconomic stratum

		// Add menuItem filter by soil type
		createTypeFilter(SoilType.class, "propertyEnvirontmen.soilType",
				"PropertyBrowse.Property.soil");

		// Create filter by Max Nigth Temperature
		createBetweenFilter(Double.class, "propertyEnvirontmen.temperature",
				"PropertyBrowse.Property.maxNightimeTemperature");

		// Create filter by Min Nigth Temperature
		createBetweenFilter(Double.class, "propertyEnvirontmen.temperature2",
				"PropertyBrowse.Property.minNightimeTemperature");

		// Create filter by Max Daytime Temperature
		createBetweenFilter(Double.class, "propertyEnvirontmen.temperature1",
				"PropertyBrowse.Property.maxDaytimeTemperature");

		// Create filter by Min Daytime Temperature
		createBetweenFilter(Double.class, "propertyEnvirontmen.temperature3",
				"PropertyBrowse.Property.minDaytimeTemperature");

		// Add menuItem filter by rain Period
		createTypeFilter(RainPeriod.class, "propertyEnvirontmen.rainPeriod",
				"PropertyBrowse.Property.rainPeriod");

		// Add menuItem filter by rain intensity
		createTypeFilter(RainIntensity.class,
				"propertyEnvirontmen.rainIntensity",
				"PropertyBrowse.Property.rainIntensity");

		// Add menuItem filter by rain intensity
		createTypeFilter(RainIntensity.class,
				"propertyEnvirontmen.rainIntensity",
				"PropertyBrowse.Property.rainIntensity");

		// Add menuItem filter by Humidity
		createTypeFilter(Humidity.class, "propertyEnvirontmen.humidity",
				"PropertyBrowse.Property.humidity");

		// Add menuItem filter by SeaLevel
		createTypeFilter(SeaLevel.class, "propertyEnvirontmen.seaLevel",
				"PropertyBrowse.Property.seaLevel");

		// Add menuItem filter by Fructiculture
		createTypeFilter(Fructiculture.class,
				"propertyEnvirontmen.fructiculture",
				"PropertyBrowse.Property.fruticulture");

		// Add menuItem filter by Animal Ammount
		createTypeFilter(AnimalAmmount.class,
				"propertyEnvirontmen.animalAmmount",
				"PropertyBrowse.Property.animalAmount");

		// Add menuItem filter by Flowers Ammount
		createTypeFilter(FlowersAmmount.class,
				"propertyEnvirontmen.flowersAmmount",
				"PropertyBrowse.Property.flowersAmount");

		// Add menuItem filter by Agriculture
		createTypeFilter(Agriculture.class, "propertyEnvirontmen.agriculture",
				"PropertyBrowse.Property.agriculture");

		// Add menuItem filter by Industry
		createTypeFilter(Industry.class, "propertyEnvirontmen.industry",
				"PropertyBrowse.Property.industry");

		// TODO:
		// Add menuItem filter by EnvirontmentPublicTransport
		// createTypeFilter(EnvirontmentPublicTransport.class,
		// "propertyEnvirontmen.environtmentPublicTransportList",
		// "PropertyBrowse.Property.publicTransport");
		// Add menuItem filter by PopulationDensity
		createTypeFilter(PopulationDensity.class,
				"propertyEnvirontmen.populationDensity",
				"PropertyBrowse.Property.populationDensity");

		// Add menuItem filter by AverageAge
		createTypeFilter(AverageAge.class, "propertyEnvirontmen.averageAge",
				"PropertyBrowse.Property.averageAge");

		// TODO: Description of Surroundings
		// Add menuItem filter by RegionGrowthRate
		createTypeFilter(RegionGrowthRate.class,
				"propertyEnvirontmen.regionGrowthRate",
				"PropertyBrowse.Property.regionGrowthRate");

		// Add menuItem filter by IndsutrialDriver
		createTypeFilter(IndsutrialDriver.class,
				"propertyEnvirontmen.indsutrialDriver",
				"PropertyBrowse.Property.indsutrialDriver");
	}

}
