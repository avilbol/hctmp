package com.mobiera.hallocasa.commons.ip;

/**
 * This class is for comparing two ips, taking into account if they have some
 * joker
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class IPMatcher {

	/* static fields */

	/* instance variables */

	public static final String IP_WILD_CARD_2 = "0.0.0.0";
	public static final String IP_WILD_CARD_1 = "*.*.*.*";

	/* constructors */
	private IPMatcher() {
	}

	/* Methods */

	/**
	 * Check if the first IP (or IP mask) contains/is equal(mask/not mask) ipd2
	 * 
	 * @param ip1 IP address or IP mask
	 * @param ip2 IP address
	 * @return true if ip1 contains/is equal to ip2, false otherwise
	 */
	public static final boolean match(String ip1, String ip2) {
		if (ip1.equals(IP_WILD_CARD_1)) {
			return true;
		}
		if (ip1.equals(IP_WILD_CARD_2)) {
			return true;
		}
		if (ip1.equals(ip2)) {
			return true;
		}

		String[] ip1Parts = ip1.split("\\.");
		String[] ip2Parts = ip2.split("\\.");
		if (ip1Parts.length != ip2Parts.length) {
			return false;
		}

		for (int i = 0; i < ip1Parts.length; i++) {
			if (!compareSections(ip1Parts[i], ip2Parts[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Compare a section of the ip1 with section of ip2. Ip1 section may have
	 * joker, ip2 may not
	 * 
	 * @param ip1Section
	 * @param ip2Section
	 * @return
	 */
	private static final boolean compareSections(String ip1Section,
		String ip2Section) {
		if (ip1Section.equals("*")) {
			return true;
		}
		if (ip2Section.equals(ip1Section)) {
			return true;
		}
		if (ip2Section.matches(ip1Section.replaceFirst("(\\*)+", "(.)*"))) {
			return true;
		}
		return false;
	}
	/* Getters & Setters */
}
