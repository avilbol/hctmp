/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.pagination;

import java.io.Serializable;

/**
 *
 * @author David Mantilla
 */
public class PageIndexInfo implements Serializable {

    /**
     * ************************************************************************
     * Constanst
     * **************************************************************************
     */
    private static final long serialVersionUID = 1L;
    /**
     * *************************************************************************
     * Instance variable
     * **************************************************************************
     */
    private final long starItemIndex;
    private final long endItemIndex;
    private final long pageTotalItems;

    /**
     * *************************************************************************
     * Constructor
     * **************************************************************************
     */
    /**
     *
     * @param starItemIndex
     * @param endItemIndex
     */
    public PageIndexInfo(long starItemIndex, long endItemIndex) {
        super();
        this.starItemIndex = starItemIndex;
        this.endItemIndex = endItemIndex;
        this.pageTotalItems = endItemIndex - starItemIndex;
    }

    /**
     * *************************************************************************
     * Methods
     * *************************************************************************
     */
    /**
     * Hascode
     *
     * @return
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (endItemIndex ^ (endItemIndex >>> 32));
        result = prime * result + (int) (pageTotalItems ^ (pageTotalItems >>> 32));
        result = prime * result + (int) (starItemIndex ^ (starItemIndex >>> 32));
        return result;
    }

    /**
     * Eguals
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PageIndexInfo other = (PageIndexInfo) obj;
        if (endItemIndex != other.endItemIndex) {
            return false;
        }
        if (pageTotalItems != other.pageTotalItems) {
            return false;
        }
        return starItemIndex == other.starItemIndex;
    }

    /**
     * *************************************************************************
     * Getters y Setters
     * *************************************************************************
     */
    /**
     * @return the starItemIndex
     */
    public long getStarItemIndex() {
        return starItemIndex;
    }

    /**
     * @return the finalItemIndex
     */
    public long getEndItemIndex() {
        return endItemIndex;
    }

    /**
     * @return the pageTotalItems
     */
    public long getPageTotalItems() {
        return pageTotalItems;
    }
}
