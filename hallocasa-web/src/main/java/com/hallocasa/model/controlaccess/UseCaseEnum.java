package com.mobiera.social.model.controlaccess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Enumeration for all the social's uses cases
 * 
 * @author David Mantilla
 *
 */
@SuppressWarnings("javadoc")
public enum UseCaseEnum {

	FULL_ACCESS("/social/admin/main"),

	SEE_EDIT_MY_ACCOUNT("/social/user/my account", FULL_ACCESS),
	EDIT_MY_COMPANY("/social/admin/company profile/edit", FULL_ACCESS),
	SEE_MY_COMPANY("/social/admin/company profile/view", EDIT_MY_COMPANY),
	EDIT_USERS("/social/admin/users/edit users", FULL_ACCESS),
	SEE_USERS("/social/admin/users/view users", EDIT_USERS),
	EDIT_SUPERVISION("/social/admin/apps/supervision access/edit", FULL_ACCESS),
	SEE_SUPERVISION_CONFIG("/social/admin/apps/supervision access/view",
		EDIT_SUPERVISION),
	CONFIGURE_APP_ACCESS("/social/admin/company profile/view", FULL_ACCESS),
	EDIT_APP_ACCESS_DEFINITION("/social/admin/apps/features/edit", FULL_ACCESS),
	SEE_APP_ACCESS_DEFINITION("/social/admin/apps/features/view",
		EDIT_APP_ACCESS_DEFINITION),
	EDIT_APP_ACCESS("/social/admin/apps/configure access/edit", FULL_ACCESS),
	SEE_APP_ACCESS("/social/admin/apps/configure access/view", EDIT_APP_ACCESS);

	private static HashMap<String, UseCaseEnum> findMap;
	private final String useCaseName;
	private final UseCaseEnum[] containedBy; // list of use cases which
												// contains

	// the current use-case. For example
	// a .../main use-case contains
	// /edit and /list

	/**
	 * @param name
	 */
	private UseCaseEnum(String useCaseName, UseCaseEnum... containedBy) {
		this.useCaseName = useCaseName;
		List<UseCaseEnum> plainContainedList = new ArrayList<UseCaseEnum>();

		// add all parents to a plain list
		for (UseCaseEnum u : containedBy) {
			addParentToList(plainContainedList, u);
		}
		this.containedBy = plainContainedList.toArray(new UseCaseEnum[0]);
	}

	/**
	 * Adds the parent use-cases of a given use-case to the list recursively
	 * 
	 * @param list
	 * @param useCaseEnum
	 */
	private void addParentToList(List<UseCaseEnum> list, UseCaseEnum useCaseEnum) {
		list.add(useCaseEnum);
		for (UseCaseEnum u : useCaseEnum.getContainedBy()) {
			addParentToList(list, u);
		}
	}

	/**
	 * Getter for useCaseName
	 * 
	 * @return the useCaseName
	 */
	public String getUseCaseName() {
		return useCaseName;
	}

	static {
		findMap = new HashMap<String, UseCaseEnum>();
		for (UseCaseEnum e : UseCaseEnum.values()) {
			findMap.put(e.useCaseName, e);
		}
	}

	/**
	 * Finds a use-case enum
	 * 
	 * @param id
	 * @return
	 */
	public static UseCaseEnum findByName(String useCaseName) {
		UseCaseEnum e = findMap.get(useCaseName);
		return e;
	}

	/**
	 * Return a plain list of all use-cases which contains directly or
	 * indirectly this use-case
	 * 
	 * @return
	 */
	public UseCaseEnum[] getContainedBy() {
		return containedBy;
	}

}
