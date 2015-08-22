/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.components.datalist;

import com.hallocasa.commons.beans.Identificable;
import com.hallocasa.services.pagination.PageIndexInfo;
import com.hallocasa.services.pagination.PageList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

/**
 * @author David Mantilla
 * @param <T>
 */
public abstract class LazyPageListDataModel<T extends Identificable<?>> extends
		LazyDataModel<T> implements Serializable {

	private static final long serialVersionUID = 5192732276125344290L;

	private final boolean alwaysRefresh;
	private PageIndexInfo lastPageIndexInfo;
	private PageList<T> lastPageList;

	public LazyPageListDataModel() {
		super();
		this.alwaysRefresh = true;
		setPageSize(10);
	}

	public LazyPageListDataModel(boolean alwaysRefresh) {
		super();
		this.alwaysRefresh = alwaysRefresh;
		setPageSize(10);
	}

	/**
	 * @param first
	 * @param pageSize
	 * @param multiSortMeta
	 * @param filters
	 * @return
	 */
	@Override
	public List<T> load(int first, int pageSize, List<SortMeta> multiSortMeta,
			Map<String, Object> filters) {
		PageIndexInfo indexInfo = new PageIndexInfo((long) first,
				(long) (first + pageSize));

		// If always refresh is false, then only reload the page if the page
		// index info has changed from the last
		// request
		if (!alwaysRefresh) {
			if (lastPageIndexInfo == null ? false : indexInfo
					.equals(lastPageIndexInfo)) {
				// skip refresh returning the page of the last request
				return lastPageList == null ? null : lastPageList.getItems();
			}
		}

		// reload the page
		PageList<T> page = loadPage(indexInfo, filters);
		lastPageList = page;
		lastPageIndexInfo = indexInfo;

		if (page != null) {
			List<T> list = page.getItems();
			setRowCount((int) page.getTotalItemsCount());
			return list;
		} else {
			setRowCount(0);
			List<T> list = new ArrayList<>();
			return list;
		}
	}

	@Override
	public List<T> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		PageIndexInfo indexInfo = new PageIndexInfo((long) first,
				(long) (first + pageSize));

		// request
		if (!alwaysRefresh) {
			if (lastPageIndexInfo == null ? false : indexInfo
					.equals(lastPageIndexInfo)) {
				// skip refresh returning the page of the last request
				return lastPageList == null ? null : lastPageList.getItems();
			}
		}

		// reload the page
		PageList<T> page = loadPage(indexInfo, filters);
		lastPageList = page;
		lastPageIndexInfo = indexInfo;

		if (page != null) {
			List<T> list = page.getItems();
			setRowCount((int) page.getTotalItemsCount());
			return list;
		} else {
			setRowCount(0);
			List<T> list = new ArrayList<>();
			return list;
		}
	}

	/**
	 * Creates an empty page
	 *
	 * @return
	 */
	public PageList<T> createEmptyPage() {
		return new PageList<>(new ArrayList<T>(), 0, 10, 0);
	}

	/**
	 * Loads a page
	 *
	 * @param indexInfo
	 * @param filters
	 * @return
	 */
	protected abstract PageList<T> loadPage(PageIndexInfo indexInfo,
			Map<String, Object> filters);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.primefaces.model.SelectableDataModel#getRowData(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T getRowData(String rowKey) {
		List<Identificable<?>> objects = (List<Identificable<?>>) getWrappedData();
		for (Identificable<?> actual : objects) {
			if (actual.getId().toString().equals(rowKey)) {
				return (T) actual;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.primefaces.model.SelectableDataModel#getRowKey(java.lang.Object)
	 */
	@Override
	public Object getRowKey(T object) {
		return ((Identificable<?>) object).getId();
	}

	/**
     *
     */
	public void forceRefreshNextTime() {
		lastPageIndexInfo = null;
	}
	/**
	 * *************************************************************************
	 * Getters y Setters
	 * *************************************************************************
	 */
}
