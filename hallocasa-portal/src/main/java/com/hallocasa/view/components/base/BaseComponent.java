/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.components.base;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.el.ELException;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.application.Application;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.view.facelets.FaceletContext;

/**
 *
 * @author David Mantilla
 */
public abstract class BaseComponent extends UINamingContainer {


    private Boolean initializedFlag = Boolean.FALSE;
    private String fullIdPath;
    @SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(BaseComponent.class.getName());

    public BaseComponent() {
    }

    /**
     * @param event
     */
    public void onPrerender(ComponentSystemEvent event) {
        if (!initializedFlag.booleanValue()) {
            initialize();
            initializedFlag = Boolean.TRUE;
        }
    }

    protected abstract void initialize();

    @Override
    public Object saveState(FacesContext facesContext) {
        HashMap<String, Object> stateSavingMap = new HashMap<>();
        stateSavingMap.put("super", super.saveState(facesContext));
        stateSavingMap.put("initializedFlag", initializedFlag);
        saveComponent(facesContext, stateSavingMap);
        return stateSavingMap;
    }

    @Override
    public void restoreState(FacesContext facesContext, Object arg1) {
        @SuppressWarnings("unchecked")
        HashMap<String, Object> stateSavingMap = (HashMap<String, Object>) arg1;
        super.restoreState(facesContext, stateSavingMap.get("super"));
        initializedFlag = (Boolean) stateSavingMap.get("initializedFlag");
        restoreComponent(facesContext, stateSavingMap);
    }

    protected abstract void saveComponent(FacesContext facesContext, HashMap<String, Object> map);

    protected abstract void restoreComponent(FacesContext facesContext, HashMap<String, Object> map);

    /**
     * this functions returns the full component id from the form
     *
     * @return
     */
    private String buildFullIdPath() {
        UIComponent parent = this.getParent();
        StringBuilder str = new StringBuilder();
        str.append(getId());
        while (parent != null) {
            if ((parent instanceof NamingContainer)) {
                str.insert(0, getSeparatorChar(FacesContext.getCurrentInstance()));
                str.insert(0, parent.getId());
            }
            parent = parent.getParent();
        }
        return str.toString();
    }

    /**
     * This method allows load programmatically a
     *
     * @param fileName resource file name which is in the folder
     * resources/components and should be like "componentName.xhtml"
     * @param parentComponent the component parent for the new created component
     * @param id Id of the component
     * @param attributes, if it is null no attributes is set in the component
     * @return the created component cc
     */
    protected UIComponent createCompositeComponente(String fileName, UIComponent parentComponent, String id,
            Map<String, Object> attributes) {
      /*  UIComponent cc;
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        FaceletContext faceletContext = (FaceletContext) context.getAttributes()
                .get(FaceletContext.FACELET_CONTEXT_KEY);

        // create the facelet component
        Resource resource = application.getResourceHandler().createResource(fileName, "components");
        cc = application.createComponent(context, resource);
        cc.setId(id); // Mandatory for the case composite is part of UIForm! Otherwise JSF can't find inputs.

        // create the component to be populated by the facelet
        UIComponent implementation = application.createComponent(UIPanel.COMPONENT_TYPE);
        implementation.setRendererType("javax.faces.Group");
        cc.getFacets().put(UIComponent.COMPOSITE_FACET_NAME, implementation);

        // set the attributes
        if (attributes != null) {
            for (String key : attributes.keySet()) {
                cc.getAttributes().put(key, attributes.get(key));
            }
        }

        // Now include the composite component file in the given parent.
        parentComponent.getChildren().add(cc);
        parentComponent.pushComponentToEL(context, cc); // This makes #{cc} available.
        try {
            faceletContext.includeFacelet(implementation, resource.getURL());
        } catch (IOException e) {
            LOG.error(e);
        } finally {
            parentComponent.popComponentFromEL(context);
        }
        return cc; */
        throw  new UnsupportedOperationException();
    }

    /**
     * @return the fullIdPath
     */
    public String getFullIdPath() {
        if (fullIdPath == null) {
            fullIdPath = buildFullIdPath();
        }
        return fullIdPath;
    }

    /**
     * Getter for initializedFlag
     *
     * @return the initializedFlag
     */
    protected Boolean getInitializedFlag() {
        return initializedFlag;
    }

    /**
     * Get an attribute as method expression
     *
     * @param attributeName
     * @return
     */
    protected Object invokeMethod(String attributeName, Object[] params) {
        MethodExpression method = (MethodExpression) getAttributes().get(attributeName);
        if (method == null) {
            return null;
        }

        try {
            return method.invoke(FacesContext.getCurrentInstance().getELContext(), params);
        } catch (ELException e) {
            // if fails in the faces context then try in the Facelets context
            final Application application = FacesContext.getCurrentInstance().getApplication();
            FaceletContext faceletElContext = (FaceletContext) FacesContext.getCurrentInstance().getAttributes().get(
                    FaceletContext.FACELET_CONTEXT_KEY);
            ExpressionFactory ef = application.getExpressionFactory();

            Class<?>[] classes = new Class[params.length];
            for (int j = 0; j < params.length; j++) {
                classes[j] = params[j].getClass();
            }
            MethodExpression me = ef.createMethodExpression(faceletElContext, method.getExpressionString(), null,
                    classes);

            return me.invoke(faceletElContext, params);
        }
    }

}
