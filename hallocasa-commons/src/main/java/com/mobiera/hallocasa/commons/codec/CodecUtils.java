package com.mobiera.hallocasa.commons.codec;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Codec utilities
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class CodecUtils {
	/* static fields */
	private static final long HASH_MAX = Long.MAX_VALUE;

	/*
	 * Password token
	 */
	private static final String PASSWORD_TOKEN = "32KPjjB%^%LACQ4V7h36M-6SNeLApZSgCG#4nGJ?SB8NZ$wMypuG6qST7=EYeRp?rCpESjFsCN9j-F4b$YzYK*_FGrMejRJE37WN@XW*5Hc@?TxdKq5U5ZQzfc#gx%_=";

	/* instance variables */

	/* constructors */

	/* Methods */

	/**
	 * Get the md5 has of a string
	 * 
	 * @param message
	 * @return md5 hash
	 */
	public static String md5(String message) {
		String digest = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hash = md.digest(message.getBytes("UTF-8"));

			// converting byte array to Hexadecimal String
			StringBuilder sb = new StringBuilder(2 * hash.length);
			for (byte b : hash) {
				sb.append(String.format("%02x", b & 0xff));
			}
			digest = sb.toString();
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException(ex);
		} catch (NoSuchAlgorithmException ex) {
			throw new RuntimeException(ex);
		}
		return digest;
	}

	/**
	 * Return an unique commutative value for pair(i,j) so pair(i,j) = pair(j,i)
	 * 
	 * @param i
	 * @param j
	 * @return the pair function value
	 */
	public static long pairFunction(long i, long j) {
		if (i <= 0) {
			throw new IllegalArgumentException(
				"value must be greater than 0: i sent was " + i);
		}
		if (j <= 0) {
			throw new IllegalArgumentException(
				"value must be greater than 0: i sent was " + j);
		}

		return (i * j + (i * i) * (j * j) + (i * i * i) * (j * j * j))
			% HASH_MAX;
	}

	/**
	 * Encrypt password
	 * 
	 * @param password
	 * @return The encrypted password
	 */
	public static String encryptPassword(String password) {
		return CodecUtils.md5(password + PASSWORD_TOKEN);
	}

	/* Getters & Setters */
}
