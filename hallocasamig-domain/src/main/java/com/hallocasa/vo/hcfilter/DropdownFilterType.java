package com.hallocasa.vo.hcfilter;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

/**
 * VO representing a dropdown filter type
 * @author avillamil
 */
public class DropdownFilterType extends HcFilterType implements Serializable, ValueObject {

	private static final long serialVersionUID = 7171194327323161403L;
	private boolean allowMultiple;
	private boolean useSearch;
	private boolean useSort;
	private boolean useSelectAll;
	private boolean useRemoteList;
	
	public boolean isAllowMultiple() {
		return allowMultiple;
	}
	public void setAllowMultiple(boolean allowMultiple) {
		this.allowMultiple = allowMultiple;
	}
	public boolean isUseSearch() {
		return useSearch;
	}
	public void setUseSearch(boolean useSearch) {
		this.useSearch = useSearch;
	}
	public boolean isUseSort() {
		return useSort;
	}
	public void setUseSort(boolean useSort) {
		this.useSort = useSort;
	}
	public boolean isUseSelectAll() {
		return useSelectAll;
	}
	public void setUseSelectAll(boolean useSelectAll) {
		this.useSelectAll = useSelectAll;
	}
	public boolean isUseRemoteList() {
		return useRemoteList;
	}
	public void setUseRemoteList(boolean useRemoteList) {
		this.useRemoteList = useRemoteList;
	}
}
