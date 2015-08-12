package com.mobiera.hallocasa.commons.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * utilities for unit testing
 * 
 * @author David Mantilla
 *
 * @since 1.7
 */
public class TestUtils {
	/* static fields */
	private static List<String> invalidSizeEmails;
	private static List<String> validEmails;
	private static List<String> invalidPatternEmails;
	private static List<String> invalidIps;
	private static List<String> validIps;
	private static List<String> invalidPatternUrls;
	private static List<String> validUrls;
	private static List<String> invalidPatternPasswords;
	private static List<String> validPatternPasswords;

	/* instance variables */

	/* constructors */

	/* Methods */

	/* Getters & Setters */

	/**
	 * Getter for invalidPatternEmails
	 * 
	 * @return the invalidPatternEmails
	 */
	public static List<String> getInvalidPatternEmails() {
		if (invalidPatternEmails == null) {
			invalidPatternEmails = new ArrayList<String>();
			// wrong format
			invalidPatternEmails.add("correoinvalido");
			invalidPatternEmails.add("correoinvalido.com");
			invalidPatternEmails.add("correo@invalido");
			invalidPatternEmails.add("correo@invalido.co1.co");
			invalidPatternEmails.add("@correoinvalido.co1.co");
			invalidPatternEmails.add("correo invalido@.co1.co");
			invalidPatternEmails.add("correoinvalido@c m.co");
			invalidPatternEmails = Collections
				.unmodifiableList(invalidPatternEmails);
		}
		return invalidPatternEmails;
	}

	/**
	 * Creates and return a list of invalid emails to test
	 * 
	 * @return the created list
	 */
	public static List<String> getInvalidSizeEmails() {
		if (invalidSizeEmails == null) {
			invalidSizeEmails = new ArrayList<String>();
			// wrong size
			invalidSizeEmails
				.add("correodemasiadolargocorreodemasiadolargocorreodemasiadolargocorreolarg@correo.com");
			invalidSizeEmails = Collections.unmodifiableList(invalidSizeEmails);
		}
		return invalidSizeEmails;
	}

	/**
	 * Creates and return a list of valid emails to test
	 * 
	 * @return the created list
	 */
	public static List<String> getValidEmails() {
		if (validEmails == null) {
			validEmails = new ArrayList<String>();

			// wrong format
			validEmails.add("correo.valido@si.com");
			validEmails.add("correo@valido.com.co");
			validEmails.add("correo@valido.com");
			validEmails.add("correo.valido@si.com.co");
			validEmails = Collections.unmodifiableList(validEmails);
		}
		return validEmails;
	}

	/**
	 * Creates and return a list of valid emails to test
	 * 
	 * @return the created list
	 */
	public static List<String> getValidUrls() {
		if (validUrls == null) {
			validUrls = new ArrayList<String>();

			// wrong format
			validUrls.add("http://www.valid.com/this/is/valid/url");
			validUrls.add("https://www.valid.com");
			validUrls.add("http://www.valid.com?this=is&valid=url");
			validUrls.add("http://www.valid.com/vaadin/#!valid/in/vaadin");
			validUrls
				.add("http://localhost:10080/SocialWeb/#!passrequest/token/1215ce7cb9b776edee61bb58cec6be73/tokentime/25022015083217");
			validUrls = Collections.unmodifiableList(validUrls);
		}
		return validUrls;
	}

	/**
	 * Getter for invalidIps
	 * 
	 * @return the invalidIps
	 */
	public static List<String> getInvalidIps() {
		if (invalidIps == null) {
			invalidIps = new ArrayList<String>();
			// wrong format
			invalidIps.add("120.156.68.891");
			invalidIps.add("891.120.156.68");
			invalidIps.add("891.120.156.68");
			invalidIps.add("120.891.156.68");
			invalidIps.add("120.156.891.68");
			invalidIps.add("-1.156.891.68");
			invalidIps.add("*.156.891.68");
			invalidIps.add(".1.156.891.68");
			invalidIps.add("115689168");
			invalidIps.add("a1.120.120.120");
			invalidIps.add("120..120.120");
			invalidIps.add("10.120.120.120.");
			invalidIps.add("120.%.120.120");

			invalidIps = Collections.unmodifiableList(invalidIps);
		}
		return invalidIps;
	}

	/**
	 * Getter for validIps
	 * 
	 * @return the validIps
	 */
	public static List<String> getValidIps() {
		if (validIps == null) {
			validIps = new ArrayList<String>();

			validIps.add("190.156.78.120");
			validIps.add("1.1.1.1");
			validIps.add("0.0.0.0");
			validIps.add("127.0.0.1");
			validIps.add("255.255.255.255");

			validIps = Collections.unmodifiableList(validIps);
		}
		return validIps;
	}

	/**
	 * Join arrays in one array result avoiding repeat any element
	 * 
	 * @param <T>
	 * @param emptyArray
	 * @param arrays
	 * @return resulting array
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] joinArrays(T[] emptyArray, T[]... arrays) {
		List<T> result = new ArrayList<T>();
		for (T[] array : arrays) {
			for (T element : array) {
				if (!result.contains(element)) {
					result.add(element);
				}
			}
		}
		return result.toArray(emptyArray);
	}

	/**
	 * 
	 * @return a list of invalid URLs
	 */
	public static List<String> getInvalidPatternUrls() {
		if (invalidPatternUrls == null) {
			invalidPatternUrls = new ArrayList<String>();
			// wrong format
			invalidPatternUrls.add("htp://invalid.url.com");
			invalidPatternUrls.add("http: //invlida.com");
			invalidPatternUrls.add("http:/invalid.com");
			invalidPatternUrls.add("https://vali d");
			invalidPatternUrls.add("www.invalid.com");
			invalidPatternUrls = Collections
				.unmodifiableList(invalidPatternUrls);
		}
		return invalidPatternUrls;
	}

	/**
	 * 
	 * @return a list of invalid passwords
	 */
	public static List<String> getInvalidPasswords() {
		if (invalidPatternPasswords == null) {
			invalidPatternPasswords = new ArrayList<String>();
			// wrong format
			invalidPatternPasswords.add("12345678");
			invalidPatternPasswords.add("nouppercase1");
			invalidPatternPasswords.add("atoolongpasswordbeleivemeistoolong");
			invalidPatternPasswords.add("With space 1");
			invalidPatternPasswords.add("NOLOWERCASE1");
			invalidPatternPasswords.add("NoNumbers");
			invalidPatternPasswords.add("2Short");
			invalidPatternPasswords = Collections
				.unmodifiableList(invalidPatternPasswords);
		}
		return invalidPatternPasswords;
	}

	/**
	 * 
	 * @return a list of invalid passwords
	 */
	public static List<String> getValidPasswords() {
		if (validPatternPasswords == null) {
			validPatternPasswords = new ArrayList<String>();
			// wrong format
			validPatternPasswords.add("ValidPassword1");
			validPatternPasswords.add("My10123@Asd");
			validPatternPasswords.add("4+18C9nsl!i^~JP");
			validPatternPasswords.add("UD7ISb2Z2:hy18W");
			validPatternPasswords.add("3e96~AZ1k+*0=Xd");
			validPatternPasswords.add("Ju@ad%OPAS1");
			validPatternPasswords = Collections
				.unmodifiableList(validPatternPasswords);
		}
		return validPatternPasswords;
	}
}
