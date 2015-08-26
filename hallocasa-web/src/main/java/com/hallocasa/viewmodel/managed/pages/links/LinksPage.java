package com.hallocasa.viewmodel.managed.pages.links;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.hallocasa.dataentities.Link;
import com.hallocasa.dataentities.LinkType;
import com.hallocasa.model.controlaccess.HallocasaViewNames;
import com.hallocasa.services.interfaces.RelatedLinkServicesInterface;
import com.hallocasa.viewmodel.managed.base.BaseManagedBean;
import com.hallocasa.viewmodel.viewfacade.AbstractViewFacade;

@ManagedBean(name = HallocasaViewNames.LINKS )
@ViewScoped
public class LinksPage extends BaseManagedBean {

    /* Static variables */
    private static final long serialVersionUID = 6677832641482281707L;
    private static final String PATH = "/pages/links/";
    private static final String SECTION_LINKS = "links.xhtml";
    private static final String QUERY_STRING_OPTION = "option";

    public enum MenuOption {

        BLOGS(SECTION_LINKS, true, 2),
        STATE(SECTION_LINKS, true, 4),
        TRAVEL(SECTION_LINKS, true, 6),
        NEWS(SECTION_LINKS, true, 7);

        private final String sectionPath;
        private final boolean linkTypeFlag;
        private final Integer lintTypeId;

        private MenuOption(String sectionPath, boolean linkTypeFlag,
                Integer lintTypeId) {
            this.sectionPath = sectionPath;
            this.lintTypeId = lintTypeId;
            this.linkTypeFlag = linkTypeFlag;
        }
    }

    /* Dependencies */
    @EJB
    private RelatedLinkServicesInterface relatedLinkServices;
    private AbstractViewFacade abstractViewFacade;

    /* Instance variables */
    private String activeSection;
    private int activeSectionIndex;
    private List<Link> lstLinks;
    private List<LinkType> lstLinksType;

    /**
     * Initialize
     */
    @PostConstruct
    public void initialize() {
        // TODO: replace by CDI injection
        abstractViewFacade = AbstractViewFacade.getCurrentInstance();

        // select section
        String requestedOptionStr
                = abstractViewFacade.getViewParams().get(QUERY_STRING_OPTION);
        requestedOptionStr = requestedOptionStr == null
                ? MenuOption.BLOGS.name() : requestedOptionStr.toUpperCase();

        // identify requested option
        MenuOption requestedMenuOption;
        try {
            requestedMenuOption = MenuOption.valueOf(requestedOptionStr);
        } catch (IllegalArgumentException e) {
            requestedMenuOption = MenuOption.BLOGS;
        }

        // Get the section parameter
        activeSection = PATH + requestedMenuOption.sectionPath;
        activeSectionIndex = requestedMenuOption.ordinal();
        onMenuItemClick(requestedMenuOption);

    }

    /* Methods */
    /**
     * Method to query all links belonging to a LinkType.
     *
     * @param idLinkType . Unique identifier of the type of parameter.
     *
     * @author: Jhon Fredy Martínez Realpe
     */
    private void searchRelatedLinks(int idLinkType) {
        lstLinks = relatedLinkServices.searchRelatedLinks(idLinkType);
    }

    /* Listeners */
    public void onMenuItemClick(MenuOption menuOption) {
        activeSection = menuOption.sectionPath;
        activeSectionIndex = menuOption.ordinal();
        if (menuOption.linkTypeFlag) {
            searchRelatedLinks(menuOption.lintTypeId);
        }
    }

    /* Getter & Setters */
    /**
     * @return the activeSection
     */
    public String getActiveSection() {
        return activeSection;
    }

    /**
     * @return the activeSectionIndex
     */
    public int getActiveSectionIndex() {
        return activeSectionIndex;
    }

    // Getters y Setters
    public List<Link> getLstLinks() {
        return lstLinks;
    }

    /**
     * @return
     */
    public List<LinkType> getLstLinksType() {
        return lstLinksType;
    }

}
