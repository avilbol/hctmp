package com.hallocasa.model.controlaccess;

/**
 * Interface for the access validator
 *
 * @author David Mantilla
 * @since 1.7
 */
public interface AccessValidator {

    /**
     * Validate if the current session has access to use case. All profiles
     * assigned to the user (in social) are used to determine if the user has
     * access to the view.
     *
     *
     * @param hallocasaView View for validating access to
     * @return true if the session has access to the view, false otherwise
     */
    public boolean validateAccessToView(HallocasaViewEnum hallocasaView);

    /**
     * Validate if the current session has access to use case. All profiles
     * assigned to the user (in social) are used to determine if the user has
     * access to the view.
     *
     *
     * @param useCaseEnum Use case for validation access to
     * @return true if the session has access to the use case, false otherwise
     */
    public boolean validateAccessToUseCase(UseCaseEnum useCaseEnum);

    /**
     * Validates if the current session has access to at least one of the
     * use-cases given in the variables arguments. As the same in the
     * validateAccessToUseCase, the supervision mode is taken into account.
     *
     * @param useCaseEnums
     * @return true if the session has access to at least one of the given use
     * cases, false otherwise
     */
    public boolean validateAccessToUseCases(UseCaseEnum... useCaseEnums);

    /**
     * Validates if the current session has access to All of the use-cases given
     * in the variables arguments. As the same in the validateAccessToUseCase,
     * the supervision mode is taken into account.
     *
     * @param useCaseEnums
     * @return true if the session has access to All of the given use cases,
     * false otherwise
     */
    public boolean validateAccessToUseCasesAll(UseCaseEnum... useCaseEnums);

    /**
     * Clear the saved data about accessing. After clearing cache, all access
     * permissions are going to be evaluate again
     */
    public void clear();

}
