/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.pagination;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author David Mantilla
 * @param <T>
 */
public class PageList<T> implements Serializable {

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
    private final List<T> items;
    private final PageIndexInfo pageIndexInfo;
    private final long totalItemsCount;

    /**
     * *************************************************************************
     * Constructor
     * **************************************************************************
     */
    /**
     * @param items
     * @param pageIndexInfo
     * @param totalItemsCount
     */
    public PageList(List<T> items, PageIndexInfo pageIndexInfo, long totalItemsCount) {
        super();
        this.items = items;
        this.pageIndexInfo = pageIndexInfo;
        this.totalItemsCount = totalItemsCount;
    }

    /**
     * Constructor for PageList.java
     *
     * @param items
     * @param startIndex
     * @param endIndex
     * @param totalItemsCount
     */
    public PageList(List<T> items, long startIndex, long endIndex, long totalItemsCount) {
        super();
        this.items = items;
        this.pageIndexInfo = new PageIndexInfo(startIndex, endIndex);
        this.totalItemsCount = totalItemsCount;
    }

    /**
     * *************************************************************************
     * Methods
     * *************************************************************************
     */
    /**
     * *************************************************************************
     * Getters y Setters
     * *************************************************************************
     */
    /**
     * @return the items
     */
    public List<T> getItems() {
        return items;
    }

    /**
     * @return the pageIndexInfo
     */
    public PageIndexInfo getPageIndexInfo() {
        return pageIndexInfo;
    }

    /**
     * @return the totalItemsCount
     */
    public long getTotalItemsCount() {
        return totalItemsCount;
    }
}
