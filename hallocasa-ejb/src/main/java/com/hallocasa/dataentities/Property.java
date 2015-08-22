/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.dataentities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.hallocasa.commons.beans.Identificable;

/**
 * @author David Mantilla
 */
@Entity
@Table(name = "property")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Property.findAll", query = "SELECT p FROM Property p"),
    @NamedQuery(name = "Property.findByPropertyId",
            query = "SELECT p FROM Property p WHERE p.propertyId = :propertyId"),
    @NamedQuery(name = "Property.findByTitle",
            query = "SELECT p FROM Property p WHERE p.title = :title"),
    @NamedQuery(name = "Property.findByRoomsCount",
            query = "SELECT p FROM Property p WHERE p.roomsCount = :roomsCount"),
    @NamedQuery(name = "Property.findBySquareMetersLiving",
            query = "SELECT p FROM Property p WHERE p.squareMetersLiving = :squareMetersLiving"),
    @NamedQuery(name = "Property.findByBathrooms",
            query = "SELECT p FROM Property p WHERE p.bathrooms = :bathrooms"),
    @NamedQuery(name = "Property.findByPrice",
            query = "SELECT p FROM Property p WHERE p.price = :price"),
    @NamedQuery(name = "Property.findByAgentFeePercent",
            query = "SELECT p FROM Property p WHERE p.agentFeePercent = :agentFeePercent"),
    @NamedQuery(name = "Property.findByGaragePurchasePrice",
            query = "SELECT p FROM Property p WHERE p.garagePurchasePrice = :garagePurchasePrice"),
    @NamedQuery(name = "Property.findByMontlyFees",
            query = "SELECT p FROM Property p WHERE p.montlyFees = :montlyFees"),
    @NamedQuery(name = "Property.findBySquareMetersProperty",
            query = "SELECT p FROM Property p WHERE p.squareMetersProperty = :squareMetersProperty"),
    @NamedQuery(name = "Property.findByParkingSpotsCount",
            query = "SELECT p FROM Property p WHERE p.parkingSpotsCount = :parkingSpotsCount"),
    @NamedQuery(name = "Property.findByFloorsCount",
            query = "SELECT p FROM Property p WHERE p.floorsCount = :floorsCount"),
    @NamedQuery(name = "Property.findByFloor",
            query = "SELECT p FROM Property p WHERE p.floor = :floor"),
    @NamedQuery(name = "Property.findByBalconyCount",
            query = "SELECT p FROM Property p WHERE p.balconyCount = :balconyCount"),
    @NamedQuery(name = "Property.findByRentedFlag",
            query = "SELECT p FROM Property p WHERE p.rentedFlag = :rentedFlag"),
    @NamedQuery(name = "Property.findByElevatorFlag",
            query = "SELECT p FROM Property p WHERE p.elevatorFlag = :elevatorFlag"),
    @NamedQuery(name = "Property.findByGerdenFlag",
            query = "SELECT p FROM Property p WHERE p.gerdenFlag = :gerdenFlag"),
    @NamedQuery(name = "Property.findByConciergeFlag",
            query = "SELECT p FROM Property p WHERE p.conciergeFlag = :conciergeFlag"),
    @NamedQuery(name = "Property.findByLastModernization",
            query = "SELECT p FROM Property p WHERE p.lastModernization = :lastModernization"),
    @NamedQuery(name = "Property.findByPoolFlag",
            query = "SELECT p FROM Property p WHERE p.poolFlag = :poolFlag"),
    @NamedQuery(name = "Property.findByBuiltInKitchenFlag",
            query = "SELECT p FROM Property p WHERE p.builtInKitchenFlag = :builtInKitchenFlag"),
    @NamedQuery(name = "Property.findByYearOfConstruction",
            query = "SELECT p FROM Property p WHERE p.yearOfConstruction = :yearOfConstruction")})
public class Property implements Serializable, Identificable<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "property_id")
    private Integer propertyId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "title")
    private String title;
    @Column(name = "rooms_count")
    private Integer roomsCount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "square_meters_living")
    private int squareMetersLiving;
    @Column(name = "bathrooms")
    private Integer bathrooms;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private double price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "agent_fee_percent")
    private double agentFeePercent;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
    // consider using these annotations to enforce field validation
    @Column(name = "garage_purchase_price")
    private Double garagePurchasePrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "montly_fees")
    private double montlyFees;
    @Basic(optional = false)
    @NotNull
    @Column(name = "square_meters_property")
    private int squareMetersProperty;
    @Column(name = "parking_spots_count")
    private Integer parkingSpotsCount;
    @Column(name = "floors_count")
    private Integer floorsCount;
    @Column(name = "floor")
    private Integer floor;
    @Column(name = "balcony_count")
    private Integer balconyCount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rented_flag")
    private boolean rentedFlag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "elevator_flag")
    private boolean elevatorFlag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gerden_flag")
    private boolean gerdenFlag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "concierge_flag")
    private boolean conciergeFlag;
    @Column(name = "last_modernization")
    private Integer lastModernization;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pool_flag")
    private boolean poolFlag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "built_in_kitchen_flag")
    private boolean builtInKitchenFlag;
    @Column(name = "year_of_construction")
    private Integer yearOfConstruction;
    @Lob
    @Size(max = 65535)
    @Column(name = "video_url")
    private String videoUrl;
    @ManyToMany(mappedBy = "propertyList", fetch = FetchType.LAZY)
    private List<ParkingType> parkingTypeList;
    @ManyToMany(mappedBy = "propertyList", fetch = FetchType.LAZY)
    private List<PropertyCharacteristicType> propertyCharacteristicTypeList;
    @JoinTable(name = "property_has_use", joinColumns = {
        @JoinColumn(name = "property_id", referencedColumnName = "property_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "property_use_id", referencedColumnName = "property_use_id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<PropertyUse> propertyUseList;
    @ManyToMany(mappedBy = "propertyList", fetch = FetchType.LAZY)
    private List<InhabitantType> inhabitantTypeList;
    @ManyToMany(mappedBy = "propertyList", fetch = FetchType.LAZY)
    private List<Image> imageList;
    @ManyToMany(mappedBy = "propertyList", fetch = FetchType.LAZY)
    private List<BuildingCharacteristicType> buildingCharacteristicTypeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "property", fetch = FetchType.LAZY)
    private List<PropertyAccess> propertyAccessList;
    @JoinColumn(name = "socioeconomic_stratum_id", referencedColumnName = "socioeconomic_stratum_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SocioeconomicStratum socioeconomicStratum;
    @JoinColumn(name = "property_environtment_id", referencedColumnName = "property_environtment_id",
            nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PropertyEnvirontment propertyEnvirontmen;
    @JoinColumn(name = "sewage_water_id", referencedColumnName = "sewage_water_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SewageWater sewageWater;
    @JoinColumn(name = "service_type_id", referencedColumnName = "service_type_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ServiceType serviceType;
    @JoinColumn(name = "security_id", referencedColumnName = "security_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Security security;
    @JoinColumn(name = "basement_type_id", referencedColumnName = "basement_type_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private BasementType basementType;
    @JoinColumn(name = "construction_method_id", referencedColumnName = "construction_method_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ConstructionMethod constructionMethod;
    @JoinColumn(name = "energy_type_id", referencedColumnName = "energy_type_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EnergyType energyType;
    @JoinColumn(name = "fee_rate_id", referencedColumnName = "fee_rate_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private FeeRate feeRate;
    @JoinColumn(name = "forniture_type_id", referencedColumnName = "forniture_type_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private FornitureType fornitureType;
    @JoinColumn(name = "geo_delimitation_id", referencedColumnName = "geo_delimitation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GeoDelimitation geoDelimitation;
    @JoinColumn(name = "heating_type_id", referencedColumnName = "heating_type_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private HeatingType heatingType;
    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Location location;
    @JoinColumn(name = "ten_price_development_id", referencedColumnName = "price_development_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PriceDevelopment priceDevelopment;
    @JoinColumn(name = "three_price_development_id", referencedColumnName = "price_development_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PriceDevelopment priceDevelopment1;
    @JoinColumn(name = "property_condition_id", referencedColumnName = "property_condition_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PropertyCondition propertyCondition;
    @JoinColumn(name = "property_type_id", referencedColumnName = "property_type_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PropertyType propertyType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "property", fetch = FetchType.LAZY)
    private List<PropertyTopography> propertyTopographyList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "property", fetch = FetchType.LAZY)
    private List<PropertyWater> propertyWaterList;

    public Property() {
    }

    public Property(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public Property(Integer propertyId, String title, int squareMetersLiving,
            double price, double agentFeePercent, double montlyFees,
            int squareMetersProperty, boolean rentedFlag, boolean elevatorFlag,
            boolean gerdenFlag, boolean conciergeFlag, boolean poolFlag,
            boolean builtInKitchenFlag) {
        this.propertyId = propertyId;
        this.title = title;
        this.squareMetersLiving = squareMetersLiving;
        this.price = price;
        this.agentFeePercent = agentFeePercent;
        this.montlyFees = montlyFees;
        this.squareMetersProperty = squareMetersProperty;
        this.rentedFlag = rentedFlag;
        this.elevatorFlag = elevatorFlag;
        this.gerdenFlag = gerdenFlag;
        this.conciergeFlag = conciergeFlag;
        this.poolFlag = poolFlag;
        this.builtInKitchenFlag = builtInKitchenFlag;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRoomsCount() {
        return roomsCount;
    }

    public void setRoomsCount(Integer roomsCount) {
        this.roomsCount = roomsCount;
    }

    public int getSquareMetersLiving() {
        return squareMetersLiving;
    }

    public void setSquareMetersLiving(int squareMetersLiving) {
        this.squareMetersLiving = squareMetersLiving;
    }

    public Integer getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAgentFeePercent() {
        return agentFeePercent;
    }

    public void setAgentFeePercent(double agentFeePercent) {
        this.agentFeePercent = agentFeePercent;
    }

    public Double getGaragePurchasePrice() {
        return garagePurchasePrice;
    }

    public void setGaragePurchasePrice(Double garagePurchasePrice) {
        this.garagePurchasePrice = garagePurchasePrice;
    }

    public double getMontlyFees() {
        return montlyFees;
    }

    public void setMontlyFees(double montlyFees) {
        this.montlyFees = montlyFees;
    }

    public int getSquareMetersProperty() {
        return squareMetersProperty;
    }

    public void setSquareMetersProperty(int squareMetersProperty) {
        this.squareMetersProperty = squareMetersProperty;
    }

    public Integer getParkingSpotsCount() {
        return parkingSpotsCount;
    }

    public void setParkingSpotsCount(Integer parkingSpotsCount) {
        this.parkingSpotsCount = parkingSpotsCount;
    }

    public Integer getFloorsCount() {
        return floorsCount;
    }

    public void setFloorsCount(Integer floorsCount) {
        this.floorsCount = floorsCount;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getBalconyCount() {
        return balconyCount;
    }

    public void setBalconyCount(Integer balconyCount) {
        this.balconyCount = balconyCount;
    }

    public boolean getRentedFlag() {
        return rentedFlag;
    }

    public void setRentedFlag(boolean rentedFlag) {
        this.rentedFlag = rentedFlag;
    }

    public boolean getElevatorFlag() {
        return elevatorFlag;
    }

    public void setElevatorFlag(boolean elevatorFlag) {
        this.elevatorFlag = elevatorFlag;
    }

    public boolean getGerdenFlag() {
        return gerdenFlag;
    }

    public void setGerdenFlag(boolean gerdenFlag) {
        this.gerdenFlag = gerdenFlag;
    }

    public boolean getConciergeFlag() {
        return conciergeFlag;
    }

    public void setConciergeFlag(boolean conciergeFlag) {
        this.conciergeFlag = conciergeFlag;
    }

    public Integer getLastModernization() {
        return lastModernization;
    }

    public void setLastModernization(Integer lastModernization) {
        this.lastModernization = lastModernization;
    }

    public boolean getPoolFlag() {
        return poolFlag;
    }

    public void setPoolFlag(boolean poolFlag) {
        this.poolFlag = poolFlag;
    }

    public boolean getBuiltInKitchenFlag() {
        return builtInKitchenFlag;
    }

    public void setBuiltInKitchenFlag(boolean builtInKitchenFlag) {
        this.builtInKitchenFlag = builtInKitchenFlag;
    }

    public Integer getYearOfConstruction() {
        return yearOfConstruction;
    }

    public void setYearOfConstruction(Integer yearOfConstruction) {
        this.yearOfConstruction = yearOfConstruction;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @XmlTransient
    public List<ParkingType> getParkingTypeList() {
        return parkingTypeList;
    }

    public void setParkingTypeList(List<ParkingType> parkingTypeList) {
        this.parkingTypeList = parkingTypeList;
    }

    @XmlTransient
    public List<PropertyCharacteristicType> getPropertyCharacteristicTypeList() {
        return propertyCharacteristicTypeList;
    }

    public void setPropertyCharacteristicTypeList(
            List<PropertyCharacteristicType> propertyCharacteristicTypeList) {
        this.propertyCharacteristicTypeList = propertyCharacteristicTypeList;
    }

    @XmlTransient
    public List<PropertyUse> getPropertyUseList() {
        return propertyUseList;
    }

    public void setPropertyUseList(List<PropertyUse> propertyUseList) {
        this.propertyUseList = propertyUseList;
    }

    @XmlTransient
    public List<InhabitantType> getInhabitantTypeList() {
        return inhabitantTypeList;
    }

    public void setInhabitantTypeList(List<InhabitantType> inhabitantTypeList) {
        this.inhabitantTypeList = inhabitantTypeList;
    }

    @XmlTransient
    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    @XmlTransient
    public List<BuildingCharacteristicType> getBuildingCharacteristicTypeList() {
        return buildingCharacteristicTypeList;
    }

    public void setBuildingCharacteristicTypeList(
            List<BuildingCharacteristicType> buildingCharacteristicTypeList) {
        this.buildingCharacteristicTypeList = buildingCharacteristicTypeList;
    }

    @XmlTransient
    public List<PropertyAccess> getPropertyAccessList() {
        return propertyAccessList;
    }

    public void setPropertyAccessList(List<PropertyAccess> propertyAccessList) {
        this.propertyAccessList = propertyAccessList;
    }

    public SocioeconomicStratum getSocioeconomicStratum() {
        return socioeconomicStratum;
    }

    public void setSocioeconomicStratum(
            SocioeconomicStratum socioeconomicStratum) {
        this.socioeconomicStratum = socioeconomicStratum;
    }

    public SewageWater getSewageWater() {
        return sewageWater;
    }

    public void setSewageWater(SewageWater sewageWater) {
        this.sewageWater = sewageWater;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    public BasementType getBasementType() {
        return basementType;
    }

    public void setBasementType(BasementType basementType) {
        this.basementType = basementType;
    }

    public ConstructionMethod getConstructionMethod() {
        return constructionMethod;
    }

    public void setConstructionMethod(ConstructionMethod constructionMethod) {
        this.constructionMethod = constructionMethod;
    }

    public EnergyType getEnergyType() {
        return energyType;
    }

    public void setEnergyType(EnergyType energyType) {
        this.energyType = energyType;
    }

    public FeeRate getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(FeeRate feeRate) {
        this.feeRate = feeRate;
    }

    public FornitureType getFornitureType() {
        return fornitureType;
    }

    public void setFornitureType(FornitureType fornitureType) {
        this.fornitureType = fornitureType;
    }

    public GeoDelimitation getGeoDelimitation() {
        return geoDelimitation;
    }

    public void setGeoDelimitation(GeoDelimitation geoDelimitation) {
        this.geoDelimitation = geoDelimitation;
    }

    public HeatingType getHeatingType() {
        return heatingType;
    }

    public void setHeatingType(HeatingType heatingType) {
        this.heatingType = heatingType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public PriceDevelopment getPriceDevelopment() {
        return priceDevelopment;
    }

    public void setPriceDevelopment(PriceDevelopment priceDevelopment) {
        this.priceDevelopment = priceDevelopment;
    }

    public PriceDevelopment getPriceDevelopment1() {
        return priceDevelopment1;
    }

    public void setPriceDevelopment1(PriceDevelopment priceDevelopment1) {
        this.priceDevelopment1 = priceDevelopment1;
    }

    public PropertyCondition getPropertyCondition() {
        return propertyCondition;
    }

    public void setPropertyCondition(PropertyCondition propertyCondition) {
        this.propertyCondition = propertyCondition;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    @XmlTransient
    public List<PropertyTopography> getPropertyTopographyList() {
        return propertyTopographyList;
    }

    public void setPropertyTopographyList(
            List<PropertyTopography> propertyTopographyList) {
        this.propertyTopographyList = propertyTopographyList;
    }

    @XmlTransient
    public List<PropertyWater> getPropertyWaterList() {
        return propertyWaterList;
    }

    public void setPropertyWaterList(List<PropertyWater> propertyWaterList) {
        this.propertyWaterList = propertyWaterList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (propertyId != null ? propertyId.hashCode() : 0);
        return hash;
    }

    @Override
    public Integer getId() {
        return getPropertyId();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Property)) {
            return false;
        }
        Property other = (Property) object;
        if ((this.propertyId == null && other.propertyId != null)
                || (this.propertyId != null && !this.propertyId
                .equals(other.propertyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.Property[ propertyId="
                + propertyId + " ]";
    }

    public PropertyEnvirontment getPropertyEnvirontment() {
        return propertyEnvirontmen;
    }

    public void setPropertyEnvirontment(PropertyEnvirontment propertyEnvirontmen) {
        this.propertyEnvirontmen = propertyEnvirontmen;
    }
    /**
     * ************************************************************************
     * Constanst
     */
    /**
     * *************************************************************************
     * Instance variable
     */
    /**
     * *************************************************************************
     * Constructor
     */
    /**
     * *************************************************************************
     * Methods
     */
    /**
     * *************************************************************************
     * Getters y Setters *************************************************************************
     */
}
