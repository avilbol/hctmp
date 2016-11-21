package com.hallocasa.viewmodel.user.profile;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.hallocasa.commons.vo.UserTypeVO;
import com.hallocasa.commons.vo.properties.PropertyVO;
import com.hallocasa.services.interfaces.PropertyServices;
import com.hallocasa.services.interfaces.UserServices;
import com.hallocasa.view.context.ViewContext;

@ManagedBean
@ViewScoped
public class PropertyListPage implements Serializable{

	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = -6006649582382274792L;
	
    @Inject
    private ViewContext viewContext;
    
    /* dependencies */
    @EJB
    private UserServices userServices;
    @EJB
    private PropertyServices propertyServices;
   
    @ManagedProperty(value = "#{globalProfilePage}")
	private GlobalProfilePage globalProfilePage;

    /**
     * Default constructor
     */
    public PropertyListPage() {
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
    public void onDeletePropertyClick(PropertyVO propertyVO){
    	propertyServices.delete(propertyVO);
    	globalProfilePage.refreshProperties();
        viewContext.showGlobalInfoMessage("Properties.Delete.Succesful", null);
    }
	
    public boolean isInhabilitedUser(){
    	List<UserTypeVO> userTypeList = globalProfilePage.getUser().getUserTypes();
    	return userTypeList == null || userTypeList.isEmpty();
    }
    
	public List<PropertyVO> getPropertyList(){
		return globalProfilePage.getPropertyVOList();
	}
	public GlobalProfilePage getGlobalProfilePage() {
		return globalProfilePage;
	}
	public void setGlobalProfilePage(GlobalProfilePage globalProfilePage) {
		this.globalProfilePage = globalProfilePage;
	}
}
