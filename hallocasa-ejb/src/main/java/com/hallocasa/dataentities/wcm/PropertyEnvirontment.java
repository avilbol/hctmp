/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hallocasa.dataentities.wcm;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author David Mantilla
 */
@Entity
@Table(name = "property_environtment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PropertyEnvirontment.findAll", query = "SELECT p FROM PropertyEnvirontment p"),
    @NamedQuery(name = "PropertyEnvirontment.findByPropertyEnvirontmentId", query = "SELECT p FROM PropertyEnvirontment p WHERE p.propertyEnvirontmentId = :propertyEnvirontmentId")})
public class PropertyEnvirontment implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "property_environtment_id")
    private Integer propertyEnvirontmentId;
    @JoinTable(name = "environtment_shopping_way", joinColumns = {
        @JoinColumn(name = "property_environtment_id", referencedColumnName = "property_environtment_id")}, inverseJoinColumns = {
        @JoinColumn(name = "shopping_way_id", referencedColumnName = "shopping_way_id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<ShoppingWay> shoppingWayList;
    @ManyToMany(mappedBy = "propertyEnvirontmentList", fetch = FetchType.LAZY)
    private List<MedicalFacility> medicalFacilityList;
    @JoinTable(name = "environtment_restaurant_type", joinColumns = {
        @JoinColumn(name = "property_environtment_id", referencedColumnName = "property_environtment_id")}, inverseJoinColumns = {
        @JoinColumn(name = "restaurant_type_id", referencedColumnName = "restaurant_type_id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<RestaurantType> restaurantTypeList;
    @JoinColumn(name = "theft_frequency_id", referencedColumnName = "theft_frequency_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TheftFrequency theftFrequency;
    @JoinColumn(name = "max_nightime_temperature_id1", referencedColumnName = "temperature_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Temperature temperature;
    @JoinColumn(name = "max_daytime_temperature_id", referencedColumnName = "temperature_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Temperature temperature1;
    @JoinColumn(name = "region_growth_rate_id", referencedColumnName = "region_growth_rate_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RegionGrowthRate regionGrowthRate;
    @JoinColumn(name = "population_density_id", referencedColumnName = "population_density_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PopulationDensity populationDensity;
    @JoinColumn(name = "paramilitary_frequency_id", referencedColumnName = "paramilitary_frequency_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ParamilitaryFrequency paramilitaryFrequency;
    @JoinColumn(name = "neighbor_community_id", referencedColumnName = "neighbor_community_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private NeighborCommunity neighborCommunity;
    @JoinColumn(name = "military_frequency_id", referencedColumnName = "military_frequency_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MilitaryFrequency militaryFrequency;
    @JoinColumn(name = "drugstores_range_id", referencedColumnName = "interest_place_range_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private InterestPlaceRange interestPlaceRange;
    @JoinColumn(name = "pharmacies_range_id", referencedColumnName = "interest_place_range_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private InterestPlaceRange interestPlaceRange1;
    @JoinColumn(name = "murders_range_id", referencedColumnName = "interest_place_range_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private InterestPlaceRange interestPlaceRange2;
    @JoinColumn(name = "medical_specialists_range_id", referencedColumnName = "interest_place_range_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private InterestPlaceRange interestPlaceRange3;
    @JoinColumn(name = "general_practitioners_range_id", referencedColumnName = "interest_place_range_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private InterestPlaceRange interestPlaceRange4;
    @JoinColumn(name = "hospitals_range_id", referencedColumnName = "interest_place_range_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private InterestPlaceRange interestPlaceRange5;
    @JoinColumn(name = "cinemas_range_id", referencedColumnName = "interest_place_range_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private InterestPlaceRange interestPlaceRange6;
    @JoinColumn(name = "min_nighttime_temperature_id", referencedColumnName = "temperature_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Temperature temperature2;
    @JoinColumn(name = "min_daytime_temperature_id", referencedColumnName = "temperature_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Temperature temperature3;
    @JoinColumn(name = "soil_type_id", referencedColumnName = "soil_type_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SoilType soilType;
    @JoinColumn(name = "sea_level_id", referencedColumnName = "sea_level_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SeaLevel seaLevel;
    @JoinColumn(name = "rain_period_id", referencedColumnName = "rain_period_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RainPeriod rainPeriod;
    @JoinColumn(name = "rain_intensity_id", referencedColumnName = "rain_intensity_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RainIntensity rainIntensity;
    @JoinColumn(name = "landscape_id", referencedColumnName = "landscape_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Landscape landscape;
    @JoinColumn(name = "industry_id", referencedColumnName = "industry_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Industry industry;
    @JoinColumn(name = "humidity_id", referencedColumnName = "humidity_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Humidity humidity;
    @JoinColumn(name = "flowers_ammount_id", referencedColumnName = "flowers_ammount_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private FlowersAmmount flowersAmmount;
    @JoinColumn(name = "animal_ammount_id", referencedColumnName = "animal_ammount_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AnimalAmmount animalAmmount;
    @JoinColumn(name = "agriculture_id", referencedColumnName = "agriculture_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Agriculture agriculture;
    @JoinColumn(name = "user_user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;
    @JoinColumn(name = "average_age_id", referencedColumnName = "average_age_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AverageAge averageAge;
    @JoinColumn(name = "fructiculture_id", referencedColumnName = "fructiculture_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Fructiculture fructiculture;
    @JoinColumn(name = "guerrilla_frequency_id", referencedColumnName = "guerrilla_frequency_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GuerrillaFrequency guerrillaFrequency;
    @JoinColumn(name = "indsutrial_driver_id", referencedColumnName = "indsutrial_driver_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private IndsutrialDriver indsutrialDriver;
    @JoinColumn(name = "sport_clubs_range_id", referencedColumnName = "interest_place_range_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private InterestPlaceRange interestPlaceRange7;
    @JoinColumn(name = "play_grounds_range_id", referencedColumnName = "interest_place_range_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private InterestPlaceRange interestPlaceRange8;
    @JoinColumn(name = "kinder_gartens_range_id", referencedColumnName = "interest_place_range_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private InterestPlaceRange interestPlaceRange9;
    @JoinColumn(name = "schools_range_id", referencedColumnName = "interest_place_range_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private InterestPlaceRange interestPlaceRange10;
    @JoinColumn(name = "universities_range_id", referencedColumnName = "interest_place_range_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private InterestPlaceRange interestPlaceRange11;
    @JoinColumn(name = "police_stations_range_id", referencedColumnName = "interest_place_range_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private InterestPlaceRange interestPlaceRange12;
    @JoinColumn(name = "music_clubs_count_range_id", referencedColumnName = "interest_place_range_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private InterestPlaceRange interestPlaceRange13;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "propertyEnvirontment", fetch = FetchType.LAZY)
    private List<SportPossibility> sportPossibilityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "propertyEnvirontment", fetch = FetchType.LAZY)
    private List<EnvirontmentPublicTransport> environtmentPublicTransportList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "propertyEnvirontmen", fetch = FetchType.LAZY)
    private List<Property> propertyList;

    public PropertyEnvirontment() {
    }

    public PropertyEnvirontment(Integer propertyEnvirontmentId) {
        this.propertyEnvirontmentId = propertyEnvirontmentId;
    }

    public Integer getPropertyEnvirontmentId() {
        return propertyEnvirontmentId;
    }

    public void setPropertyEnvirontmentId(Integer propertyEnvirontmentId) {
        this.propertyEnvirontmentId = propertyEnvirontmentId;
    }

    @XmlTransient
    public List<ShoppingWay> getShoppingWayList() {
        return shoppingWayList;
    }

    public void setShoppingWayList(List<ShoppingWay> shoppingWayList) {
        this.shoppingWayList = shoppingWayList;
    }

    @XmlTransient
    public List<MedicalFacility> getMedicalFacilityList() {
        return medicalFacilityList;
    }

    public void setMedicalFacilityList(List<MedicalFacility> medicalFacilityList) {
        this.medicalFacilityList = medicalFacilityList;
    }

    @XmlTransient
    public List<RestaurantType> getRestaurantTypeList() {
        return restaurantTypeList;
    }

    public void setRestaurantTypeList(List<RestaurantType> restaurantTypeList) {
        this.restaurantTypeList = restaurantTypeList;
    }

    public TheftFrequency getTheftFrequency() {
        return theftFrequency;
    }

    public void setTheftFrequency(TheftFrequency theftFrequency) {
        this.theftFrequency = theftFrequency;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public Temperature getTemperature1() {
        return temperature1;
    }

    public void setTemperature1(Temperature temperature1) {
        this.temperature1 = temperature1;
    }

    public RegionGrowthRate getRegionGrowthRate() {
        return regionGrowthRate;
    }

    public void setRegionGrowthRate(RegionGrowthRate regionGrowthRate) {
        this.regionGrowthRate = regionGrowthRate;
    }

    public PopulationDensity getPopulationDensity() {
        return populationDensity;
    }

    public void setPopulationDensity(PopulationDensity populationDensity) {
        this.populationDensity = populationDensity;
    }

    public ParamilitaryFrequency getParamilitaryFrequency() {
        return paramilitaryFrequency;
    }

    public void setParamilitaryFrequency(ParamilitaryFrequency paramilitaryFrequency) {
        this.paramilitaryFrequency = paramilitaryFrequency;
    }

    public NeighborCommunity getNeighborCommunity() {
        return neighborCommunity;
    }

    public void setNeighborCommunity(NeighborCommunity neighborCommunity) {
        this.neighborCommunity = neighborCommunity;
    }

    public MilitaryFrequency getMilitaryFrequency() {
        return militaryFrequency;
    }

    public void setMilitaryFrequency(MilitaryFrequency militaryFrequency) {
        this.militaryFrequency = militaryFrequency;
    }

    public InterestPlaceRange getInterestPlaceRange() {
        return interestPlaceRange;
    }

    public void setInterestPlaceRange(InterestPlaceRange interestPlaceRange) {
        this.interestPlaceRange = interestPlaceRange;
    }

    public InterestPlaceRange getInterestPlaceRange1() {
        return interestPlaceRange1;
    }

    public void setInterestPlaceRange1(InterestPlaceRange interestPlaceRange1) {
        this.interestPlaceRange1 = interestPlaceRange1;
    }

    public InterestPlaceRange getInterestPlaceRange2() {
        return interestPlaceRange2;
    }

    public void setInterestPlaceRange2(InterestPlaceRange interestPlaceRange2) {
        this.interestPlaceRange2 = interestPlaceRange2;
    }

    public InterestPlaceRange getInterestPlaceRange3() {
        return interestPlaceRange3;
    }

    public void setInterestPlaceRange3(InterestPlaceRange interestPlaceRange3) {
        this.interestPlaceRange3 = interestPlaceRange3;
    }

    public InterestPlaceRange getInterestPlaceRange4() {
        return interestPlaceRange4;
    }

    public void setInterestPlaceRange4(InterestPlaceRange interestPlaceRange4) {
        this.interestPlaceRange4 = interestPlaceRange4;
    }

    public InterestPlaceRange getInterestPlaceRange5() {
        return interestPlaceRange5;
    }

    public void setInterestPlaceRange5(InterestPlaceRange interestPlaceRange5) {
        this.interestPlaceRange5 = interestPlaceRange5;
    }

    public InterestPlaceRange getInterestPlaceRange6() {
        return interestPlaceRange6;
    }

    public void setInterestPlaceRange6(InterestPlaceRange interestPlaceRange6) {
        this.interestPlaceRange6 = interestPlaceRange6;
    }

    public Temperature getTemperature2() {
        return temperature2;
    }

    public void setTemperature2(Temperature temperature2) {
        this.temperature2 = temperature2;
    }

    public Temperature getTemperature3() {
        return temperature3;
    }

    public void setTemperature3(Temperature temperature3) {
        this.temperature3 = temperature3;
    }

    public SoilType getSoilType() {
        return soilType;
    }

    public void setSoilType(SoilType soilType) {
        this.soilType = soilType;
    }

    public SeaLevel getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(SeaLevel seaLevel) {
        this.seaLevel = seaLevel;
    }

    public RainPeriod getRainPeriod() {
        return rainPeriod;
    }

    public void setRainPeriod(RainPeriod rainPeriod) {
        this.rainPeriod = rainPeriod;
    }

    public RainIntensity getRainIntensity() {
        return rainIntensity;
    }

    public void setRainIntensity(RainIntensity rainIntensity) {
        this.rainIntensity = rainIntensity;
    }

    public Landscape getLandscape() {
        return landscape;
    }

    public void setLandscape(Landscape landscape) {
        this.landscape = landscape;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public Humidity getHumidity() {
        return humidity;
    }

    public void setHumidity(Humidity humidity) {
        this.humidity = humidity;
    }

    public FlowersAmmount getFlowersAmmount() {
        return flowersAmmount;
    }

    public void setFlowersAmmount(FlowersAmmount flowersAmmount) {
        this.flowersAmmount = flowersAmmount;
    }

    public AnimalAmmount getAnimalAmmount() {
        return animalAmmount;
    }

    public void setAnimalAmmount(AnimalAmmount animalAmmount) {
        this.animalAmmount = animalAmmount;
    }

    public Agriculture getAgriculture() {
        return agriculture;
    }

    public void setAgriculture(Agriculture agriculture) {
        this.agriculture = agriculture;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AverageAge getAverageAge() {
        return averageAge;
    }

    public void setAverageAge(AverageAge averageAge) {
        this.averageAge = averageAge;
    }

    public Fructiculture getFructiculture() {
        return fructiculture;
    }

    public void setFructiculture(Fructiculture fructiculture) {
        this.fructiculture = fructiculture;
    }

    public GuerrillaFrequency getGuerrillaFrequency() {
        return guerrillaFrequency;
    }

    public void setGuerrillaFrequency(GuerrillaFrequency guerrillaFrequency) {
        this.guerrillaFrequency = guerrillaFrequency;
    }

    public IndsutrialDriver getIndsutrialDriver() {
        return indsutrialDriver;
    }

    public void setIndsutrialDriver(IndsutrialDriver indsutrialDriver) {
        this.indsutrialDriver = indsutrialDriver;
    }

    public InterestPlaceRange getInterestPlaceRange7() {
        return interestPlaceRange7;
    }

    public void setInterestPlaceRange7(InterestPlaceRange interestPlaceRange7) {
        this.interestPlaceRange7 = interestPlaceRange7;
    }

    public InterestPlaceRange getInterestPlaceRange8() {
        return interestPlaceRange8;
    }

    public void setInterestPlaceRange8(InterestPlaceRange interestPlaceRange8) {
        this.interestPlaceRange8 = interestPlaceRange8;
    }

    public InterestPlaceRange getInterestPlaceRange9() {
        return interestPlaceRange9;
    }

    public void setInterestPlaceRange9(InterestPlaceRange interestPlaceRange9) {
        this.interestPlaceRange9 = interestPlaceRange9;
    }

    public InterestPlaceRange getInterestPlaceRange10() {
        return interestPlaceRange10;
    }

    public void setInterestPlaceRange10(InterestPlaceRange interestPlaceRange10) {
        this.interestPlaceRange10 = interestPlaceRange10;
    }

    public InterestPlaceRange getInterestPlaceRange11() {
        return interestPlaceRange11;
    }

    public void setInterestPlaceRange11(InterestPlaceRange interestPlaceRange11) {
        this.interestPlaceRange11 = interestPlaceRange11;
    }

    public InterestPlaceRange getInterestPlaceRange12() {
        return interestPlaceRange12;
    }

    public void setInterestPlaceRange12(InterestPlaceRange interestPlaceRange12) {
        this.interestPlaceRange12 = interestPlaceRange12;
    }

    public InterestPlaceRange getInterestPlaceRange13() {
        return interestPlaceRange13;
    }

    public void setInterestPlaceRange13(InterestPlaceRange interestPlaceRange13) {
        this.interestPlaceRange13 = interestPlaceRange13;
    }

    @XmlTransient
    public List<SportPossibility> getSportPossibilityList() {
        return sportPossibilityList;
    }

    public void setSportPossibilityList(List<SportPossibility> sportPossibilityList) {
        this.sportPossibilityList = sportPossibilityList;
    }

    @XmlTransient
    public List<EnvirontmentPublicTransport> getEnvirontmentPublicTransportList() {
        return environtmentPublicTransportList;
    }

    public void setEnvirontmentPublicTransportList(List<EnvirontmentPublicTransport> environtmentPublicTransportList) {
        this.environtmentPublicTransportList = environtmentPublicTransportList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (propertyEnvirontmentId != null ? propertyEnvirontmentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof PropertyEnvirontment)) {
            return false;
        }
        PropertyEnvirontment other = (PropertyEnvirontment) object;
        if ((this.propertyEnvirontmentId == null && other.propertyEnvirontmentId != null) || (this.propertyEnvirontmentId != null && !this.propertyEnvirontmentId.equals(other.propertyEnvirontmentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.PropertyEnvirontment[ propertyEnvirontmentId=" + propertyEnvirontmentId + " ]";
    }

    @XmlTransient
    public List<Property> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;
    }

}
