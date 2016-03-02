package com.hallocasa.commons.codec;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import com.hallocasa.commons.codec.CodecUtils;

public class CodecUtilsTest {


	/* static fields */

	/* instance variables */

	/* constructors */

	/* Methods */
	@Test
	public void testUniquePair() {

		HashMap<Long, Integer> hashMap = new HashMap<Long, Integer>();

		for (long i = 1; i < 10000000L; i++) {
			for (long j = 1; i < 10000000L; i++) {
				Long pairFunction = CodecUtils.pairFunction(i, j);
				Integer count = hashMap.get(pairFunction);
				count = count == null ? 0 : count.intValue();
				count++;
				Assert.assertTrue("Pair function for " + i + ", " + j,
					count <= 2);
				hashMap.put( pairFunction, count);
			}
		}
	}

	/* Getters & Setters */
}
