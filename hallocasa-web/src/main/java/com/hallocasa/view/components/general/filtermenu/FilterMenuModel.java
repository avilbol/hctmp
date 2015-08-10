/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.components.general.filtermenu;

import java.util.List;

/**
 *
 * @author David Mantilla
 */
public interface FilterMenuModel {

    /**
     * Finds a menu item by its id
     *
     * @param id
     * @return
     */
    public FilterMenuItem<?> findFilterMenuItem(String id);

    /**
     * List of filter menu items
     *
     * @return
     */
    public List<FilterMenuItem<?>> getFilterMenuItems();
    
    /**
     * List of all conditions that are active in menu items
     * @return 
     */
    public FilterMenuItemCondition<?>[] getActiveMenuItemConditions();

}
