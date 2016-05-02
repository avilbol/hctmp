package com.hallocasa.viewmodel.user.profile;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.model.controlaccess.ForbiddenException;
import com.hallocasa.model.session.WebSession;
import com.hallocasa.services.interfaces.UserServices;
import com.hallocasa.view.context.ViewContext;
import com.hallocasa.view.navigation.HallocasaViewEnum;
import com.hallocasa.view.navigation.NavigationHandler;

@ManagedBean
@ViewScoped
public class PropertyListPage {

	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = -6006649582382274792L;

	/* instance variables */
    private UserVO user;


    @Inject
    private ViewContext viewContext;
    
    /* dependencies */
    @Inject
    private WebSession webSession;
    @Inject
    private NavigationHandler navigationHandler;
    @EJB
    private UserServices userServices;

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
    public void onDeletePropertyClick(){
        viewContext.showGlobalInfoMessage("Eliminado", "Eliminado correctamente");
    }
	
}
