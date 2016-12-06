package com.hallocasa.services.user;

import com.hallocasa.services.messaging.exceptions.MailServicesErrorException;
import com.hallocasa.vo.PasswordRecoveryRequest;

public interface PasswordRecoveryService {

	/**
	 * Validate the password recovery token, supplied by the page who attempts
	 * to retype a forgotten password
	 * @param passwordRecoveryToken
	 * @return
	 */
	PasswordRecoveryRequest validatePasswordRecoveryToken(String passwordRecoveryToken);

	/**
	 * Update the password of user who forgot it
	 * @param passwordRecoveryRequest
	 */
	void confirmPasswordRecovery(PasswordRecoveryRequest passwordRecoveryRequest);

	/**
	 * Send an email with password recovery instructions
	 * @param email
	 * 		Email to send info
	 * @throws MailServicesErrorException
	 */
	void sendPasswordRecovery(String email);

}
