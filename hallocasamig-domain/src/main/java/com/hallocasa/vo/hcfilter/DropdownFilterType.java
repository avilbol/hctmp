package com.hallocasa.vo.hcfilter;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

/**
 * VO representing a dropdown filter type
 * @author avillamil
 */
public class DropdownFilterType extends HcFilterType implements Serializable, ValueObject {

	private static final long serialVersionUID = 7171194327323161403L;
	private Boolean allowMultiple;
	private Boolean useSearch;
	private Boolean useSort;
	private Boolean useSelectAll;
	private Boolean useRemoteList;
	private Boolean useLinks;
	
	public Boolean isAllowMultiple() {
		return allowMultiple;
	}
	public void setAllowMultiple(Boolean allowMultiple) {
		this.allowMultiple = allowMultiple;
	}
	public Boolean isUseSearch() {
		return useSearch;
	}
	public void setUseSearch(Boolean useSearch) {
		this.useSearch = useSearch;
	}
	public Boolean isUseSort() {
		return useSort;
	}
	public void setUseSort(Boolean useSort) {
		this.useSort = useSort;
	}
	public Boolean isUseSelectAll() {
		return useSelectAll;
	}
	public void setUseSelectAll(Boolean useSelectAll) {
		this.useSelectAll = useSelectAll;
	}
	public Boolean isUseRemoteList() {
		return useRemoteList;
	}
	public void setUseRemoteList(Boolean useRemoteList) {
		this.useRemoteList = useRemoteList;
	}
	public Boolean isUseLinks() {
		return useLinks;
	}
	public void setUseLinks(Boolean useLinks) {
		this.useLinks = useLinks;
	}
}
