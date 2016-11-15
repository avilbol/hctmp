package com.hallocasa.entities.properties;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.hallocasa.entities.i.HallocasaEntity;
import com.hallocasa.persistence.converters.HcBooleanConverter;
import com.hallocasa.persistence.converters.PropertyDatatypeConverter;
import com.hallocasa.vo.hcfilter.properties.PropertyDatatype;

/**
 * This entity represents a field of a property
 * 
 * @author Alexander Villamil
 */
@Entity
@Table(name = "property_field")
@NamedQueries({
		@NamedQuery(name = EntityPropertyField.QUERY_FIND_BY_FILTER, query = "select f from EntityPropertyField f where f.id IN ("
				+ "select ff.propertyField.id from EntityPropertyFieldFilter ff " + "where ff.filter.id = ?1)"),
		@NamedQuery(name = EntityPropertyField.QUERY_FIND_ALL, query = "select pf from EntityPropertyField pf"),
		@NamedQuery(name = EntityPropertyField.QUERY_FIND_IN, query = "select pf from EntityPropertyField pf where pf.id IN ?1") })
public class EntityPropertyField implements HallocasaEntity {

	public static final String QUERY_FIND_BY_FILTER = "EntityPropertyField.QueryFindByFilter";
	public static final String QUERY_FIND_ALL = "EntityPropertyField.QueryFindAll";
	public static final String QUERY_FIND_IN = "EntityPropertyField.QueryFindIn";
	public static final String NATIVE_QUERY_FIND_BY_PK = "select pf.id from property_field pf"
			+ " join (select * from property_field_condition_option where condition_level = 0 and parent_property_field_id=31) ptype"
			+ " on ptype.property_field_id = pf.id and ?1 = ptype.parent_property_field_option_id"
			+ " join (select * from property_field_condition_option where condition_level = 0 and parent_property_field_id=32) plocation"
			+ " on plocation.property_field_id = pf.id and ?2 = plocation.parent_property_field_option_id"
			+ " join (select * from property_field_condition_option where condition_level = 0 and parent_property_field_id=33) pproposal"
			+ " on pproposal.property_field_id = pf.id and ?3 = pproposal.parent_property_field_option_id"
			+ " join (select * from property_field_condition_option where condition_level = 0 and parent_property_field_id=34) pcountry"
			+ " on pcountry.property_field_id = pf.id and ?4 = pcountry.parent_property_field_option_id" + ""
			+ " union"
			+ " select pf.id from property_field pf"
			+ " join (select * from property_field_condition_option where condition_level = 1 and parent_property_field_id=31) ptype"
			+ " on ptype.property_field_id = pf.id and ?1 = ptype.parent_property_field_option_id"
			+ " join (select * from property_field_condition_option where condition_level = 1 and parent_property_field_id=32) plocation"
			+ " on plocation.property_field_id = pf.id and ?2 = plocation.parent_property_field_option_id"
			+ " join (select * from property_field_condition_option where condition_level = 1 and parent_property_field_id=33) pproposal"
			+ " on pproposal.property_field_id = pf.id and ?3 = pproposal.parent_property_field_option_id"
			+ " join (select * from property_field_condition_option where condition_level = 1 and parent_property_field_id=34) pcountry"
			+ " on pcountry.property_field_id = pf.id and ?4 = pcountry.parent_property_field_option_id";

	/**
	 * Property field identifier
	 */
	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "lang")
	private String lang;

	@Convert(converter = HcBooleanConverter.class)
	@Column(name = "basic")
	private Boolean basic;

	@Convert(converter = HcBooleanConverter.class)
	@Column(name = "primary_key")
	private Boolean primaryKey;

	@Convert(converter = HcBooleanConverter.class)
	@Column(name = "multilanguage_value")
	private Boolean multilanguageValue;

	@JoinColumn(name = "property_field_type_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private EntityPropertyFieldType propertyFieldType;

	@JoinColumn(name = "property_field_value_type_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private EntityPropertyFieldValueType propertyFieldValueType;

	@Convert(converter = PropertyDatatypeConverter.class)
	@Column(name = "text_type")
	private PropertyDatatype textType;
	
	@Convert(converter = PropertyDatatypeConverter.class)
	@Column(name = "data1_type")
	private PropertyDatatype data1Type;
	
	@Convert(converter = PropertyDatatypeConverter.class)
	@Column(name = "data2_type")
	private PropertyDatatype data2Type;
	
	@Convert(converter = PropertyDatatypeConverter.class)
	@Column(name = "data3_type")
	private PropertyDatatype data3Type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getBasic() {
		return basic;
	}

	public void setBasic(Boolean basic) {
		this.basic = basic;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public Boolean getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(Boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Boolean getMultilanguageValue() {
		return multilanguageValue;
	}

	public void setMultilanguageValue(Boolean multilanguageValue) {
		this.multilanguageValue = multilanguageValue;
	}

	public EntityPropertyFieldType getPropertyFieldType() {
		return propertyFieldType;
	}

	public void setPropertyFieldType(EntityPropertyFieldType propertyFieldType) {
		this.propertyFieldType = propertyFieldType;
	}

	public EntityPropertyFieldValueType getPropertyFieldValueType() {
		return propertyFieldValueType;
	}

	public void setPropertyFieldValueType(EntityPropertyFieldValueType propertyFieldValueType) {
		this.propertyFieldValueType = propertyFieldValueType;
	}

	public PropertyDatatype getTextType() {
		return textType;
	}

	public void setTextType(PropertyDatatype textType) {
		this.textType = textType;
	}

	public PropertyDatatype getData1Type() {
		return data1Type;
	}

	public void setData1Type(PropertyDatatype data1Type) {
		this.data1Type = data1Type;
	}

	public PropertyDatatype getData2Type() {
		return data2Type;
	}

	public void setData2Type(PropertyDatatype data2Type) {
		this.data2Type = data2Type;
	}

	public PropertyDatatype getData3Type() {
		return data3Type;
	}

	public void setData3Type(PropertyDatatype data3Type) {
		this.data3Type = data3Type;
	}
}
