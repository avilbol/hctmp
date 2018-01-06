package com.hallocasa.services.generalities;

import java.util.List;

/**
 * This interface specifies that the services which implement manages dependencies
 * with other services in order to list theirs items
 * @author avilbol
 */
public interface Listable<T> {

	/**
	 * Find the items with the specified parent element id
	 * @param parentId
	 * @return
	 * 		the items that match the specified parent element id
	 */
	public List<T> findByParentId(Integer parentId);
	
	/**
	 * Find the items with the specified parent element id list
	 * @param parentIdList
	 * @return
	 * 		the items that match any of elements in parent id list
	 */
	public List<T> findByParentIdList(List<Integer> parentIdList);
	
}
