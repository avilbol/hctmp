package com.hallocasa.model.controlaccess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Enumeration for all the hallocasa's uses cases
 *
 * @author David Mantilla
 *
 */
@SuppressWarnings("javadoc")
public enum UseCaseEnum {

    FULL_ACCESS("/hallocasa/admin/all"),
    SEE_MY_PROFILE("/hallocasa/user/my profile/view", FULL_ACCESS);

    private static final HashMap<String, UseCaseEnum> findMap;
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
        List<UseCaseEnum> plainContainedList = new ArrayList<>();

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
        findMap = new HashMap<>();
        for (UseCaseEnum e : UseCaseEnum.values()) {
            UseCaseEnum.findMap.put(e.useCaseName, e);
        }
    }

    /**
     * Finds a use-case enum
     *
     * @param useCaseName
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
