/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.components.properties;

import com.hallocasa.view.components.base.BaseComponent;
import java.util.HashMap;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author David Mantilla
 */
@FacesComponent("PropertyAbstractComponent")
public class PropertyAbstractComponent extends BaseComponent {

    /* ------------------------------------------------------------
     * Constants
     * ------------------------------------------------------------ */

    /* ------------------------------------------------------------
     * Instance variable
     * ------------------------------------------------------------ */

    /* ------------------------------------------------------------
     * Instance attributes
     * ------------------------------------------------------------ */

    /* ------------------------------------------------------------
     * Constructor
     * ------------------------------------------------------------ */

    /* ------------------------------------------------------------
     * Methods
     * ------------------------------------------------------------ */
    @Override
    protected void initialize() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void saveComponent(FacesContext facesContext, HashMap<String, Object> map) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void restoreComponent(FacesContext facesContext, HashMap<String, Object> map) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    /* ------------------------------------------------------------
     * Getters y setters
     * ------------------------------------------------------------ */
}
