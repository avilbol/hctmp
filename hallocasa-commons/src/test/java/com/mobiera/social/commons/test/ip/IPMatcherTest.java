package com.mobiera.social.commons.test.ip;

import org.junit.Assert;
import org.junit.Test;

import com.hallocasa.commons.ip.IPMatcher;

public class IPMatcherTest {

	/* Methods */
	@Test
	public void testfullComodin1Match() {
		String ip1 = "0.0.0.0";
		String ip2 = "126.891.902.123";
		boolean result = IPMatcher.match(ip1, ip2);
		Assert.assertTrue(result);
	}

	@Test
	public void testFullComodin2Match() {
		String ip1 = "*.*.*.*";
		String ip2 = "126.891.902.123";
		boolean result = IPMatcher.match(ip1, ip2);
		Assert.assertTrue(result);
	}

	@Test
	public void testPartialComodinMatch() {
		String ip1 = "126.891.902.*";
		String ip2 = "126.891.902.123";
		boolean result = IPMatcher.match(ip1, ip2);
		Assert.assertTrue(result);

		ip1 = "126.891.*.123";
		ip2 = "126.891.902.123";
		result = IPMatcher.match(ip1, ip2);
		Assert.assertTrue(result);

		ip1 = "126.*.902.123";
		ip2 = "126.891.902.123";
		result = IPMatcher.match(ip1, ip2);
		Assert.assertTrue(result);

		ip1 = "*.891.902.123";
		ip2 = "126.891.902.123";
		result = IPMatcher.match(ip1, ip2);
		Assert.assertTrue(result);

		ip1 = "126.891.*.*";
		ip2 = "126.891.902.123";
		result = IPMatcher.match(ip1, ip2);
		Assert.assertTrue(result);

		ip1 = "126.*.*.*";
		ip2 = "126.891.902.123";
		result = IPMatcher.match(ip1, ip2);
		Assert.assertTrue(result);

		ip1 = "126.891.902.*23";
		ip2 = "126.891.902.123";
		result = IPMatcher.match(ip1, ip2);
		Assert.assertTrue(result);

		ip1 = "126.891.902.12*";
		ip2 = "126.891.902.123";
		result = IPMatcher.match(ip1, ip2);
		Assert.assertTrue(result);

		ip1 = "126.891.9*.123";
		ip2 = "126.891.902.123";
		result = IPMatcher.match(ip1, ip2);
		Assert.assertTrue(result);

		ip1 = "126.8*1.902.123";
		ip2 = "126.891.902.123";
		result = IPMatcher.match(ip1, ip2);
		Assert.assertTrue(result);

		ip1 = "**6.891.902.123";
		ip2 = "6.891.902.123";
		result = IPMatcher.match(ip1, ip2);
		Assert.assertTrue(result);
	}

	@Test
	public void testPartialComodinNotMatch() {
		String ip1 = "126.891.902.*";
		String ip2 = "126.891.901.123";
		boolean result = IPMatcher.match(ip1, ip2);
		Assert.assertFalse(result);

		ip1 = "126.891.*.123";
		ip2 = "126.891.902.122";
		result = IPMatcher.match(ip1, ip2);
		Assert.assertFalse(result);

		ip1 = "126.*.902.123";
		ip2 = "123.891.902.123";
		result = IPMatcher.match(ip1, ip2);
		Assert.assertFalse(result);

		ip1 = "*.891.902.123";
		ip2 = "126.891.902.124";
		result = IPMatcher.match(ip1, ip2);
		Assert.assertFalse(result);

		ip1 = "126.891.*.*";
		ip2 = "127.892.902.123";
		result = IPMatcher.match(ip1, ip2);
		Assert.assertFalse(result);

		ip1 = "126.*.*.*";
		ip2 = "127.891.902.123";
		result = IPMatcher.match(ip1, ip2);
		Assert.assertFalse(result);

		ip1 = "126.891.902.*23";
		ip2 = "126.891.902.124";
		result = IPMatcher.match(ip1, ip2);
		Assert.assertFalse(result);

		ip1 = "126.891.902.12*";
		ip2 = "126.891.902.132";
		result = IPMatcher.match(ip1, ip2);
		Assert.assertFalse(result);

		ip1 = "126.891.9*.123";
		ip2 = "126.791.902.123";
		result = IPMatcher.match(ip1, ip2);
		Assert.assertFalse(result);

		ip1 = "126.8*1.902.123";
		ip2 = "6.891.902.123";
		result = IPMatcher.match(ip1, ip2);
		Assert.assertFalse(result);

		ip1 = "**6.891.902.123";
		ip2 = "126.891.902.124";
		result = IPMatcher.match(ip1, ip2);
		Assert.assertFalse(result);
	}

	@Test
	public void exactMatchTest() {
		String ip1 = "126.891.902.123";
		String ip2 = "126.891.902.123";
		boolean result = IPMatcher.match(ip1, ip2);
		Assert.assertTrue(result);

		ip1 = "6.891.902.123";
		ip2 = "6.891.902.123";
		result = IPMatcher.match(ip1, ip2);
		Assert.assertTrue(result);

		ip1 = "6.891.902.12";
		ip2 = "6.891.902.12";
		result = IPMatcher.match(ip1, ip2);
		Assert.assertTrue(result);

		ip1 = "6.891.902.12";
		ip2 = "6.891.902.12";
		result = IPMatcher.match(ip1, ip2);
		Assert.assertTrue(result);
	}

	@Test
	public void notMatchTest() {
		String ip1 = "126.891.902.123";
		String ip2 = "126.891.901.124";
		boolean result = IPMatcher.match(ip1, ip2);
		Assert.assertFalse(result);
	}

	public void notExceptionInCrazyValues() {
		String ip1 = "crazy.valu.e";
		String ip2 = "126.mad.ness.124";
		try {
			IPMatcher.match(ip1, ip2);
		} catch (Exception e) {
			Assert.fail();
		}
	}
}
