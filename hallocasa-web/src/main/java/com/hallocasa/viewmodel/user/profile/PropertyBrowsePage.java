package com.hallocasa.viewmodel.user.profile;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.hallocasa.commons.vo.properties.PropertyVO;
import com.hallocasa.commons.vo.properties.filters.PropertyFilter;
import com.hallocasa.services.interfaces.PropertyFilteringServices;
import com.hallocasa.services.interfaces.PropertyServices;
import com.hallocasa.view.navigation.NavigationHandler;

@ManagedBean
@ViewScoped
public class PropertyBrowsePage implements Serializable {

	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = -6006649582382274792L;
	
    @EJB
    private PropertyServices propertyServices;
    
    @EJB
    private PropertyFilteringServices propertyFilteringServices;
    
    /**
	 * Navigation handler
	 */
	@Inject
	private NavigationHandler navigationHandler;
	
	
	/**
	 * Property VO list
	 */
	private List<PropertyVO> propertyList;
	
	/**
	 * Filter for properties
	 */
	private PropertyFilter propertyFilter;

    /**
     * Default constructor
     */
    public PropertyBrowsePage() {
    }
    /**
     * Initialize the bean
     */
    @PostConstruct
    public void initialize() {
    	
    }
    
    /**
     * Process click event over edit button
     */
    public void goToViewProperty(PropertyVO propertyVO){
    	// TODO: Implement
    }
	
    public void reloadProperties(){
    	propertyList = propertyServices.find(propertyFilter);
    }
    
	public List<PropertyVO> getPropertyList() {
		return propertyList;
	}
	
	public void setPropertyList(List<PropertyVO> propertyList) {
		this.propertyList = propertyList;
	}
	
	public PropertyFilter getPropertyFilter() {
		if(propertyFilter == null)
			propertyFilter = propertyFilteringServices.getFilterScheme();
		return propertyFilter;
	}
	
	public void setPropertyFilter(PropertyFilter propertyFilter) {
		this.propertyFilter = propertyFilter;
	}
}
