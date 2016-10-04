package com.hallocasa.utils.security;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

/**
 * Utilities in order to add encryption capabilities
 */
public class EncryptionUtils {

	/**
	 * Encrypt a string text using md5
	 * @param text
	 * 		The text to encrypt
	 * @return
	 * 		The text encrypted
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 */
	public static String hashMd5(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.reset();
		messageDigest.update(text.getBytes(Charset.forName("UTF8")));
		final byte[] resultByte = messageDigest.digest();
		final String result = new String(Hex.encodeHex(resultByte));
		return result;
	}
}
