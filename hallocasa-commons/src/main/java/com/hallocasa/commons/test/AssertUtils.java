package com.hallocasa.commons.test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Assert;

/**
 * Assert utils for junit testing
 * 
 * @author David Mantilla
 *
 * @since 1.7
 */
public class AssertUtils {

	/* static fields */
	private static final Logger LOG = Logger.getLogger(AssertUtils.class
		.getName());

	/* instance variables */

	/* constructors */

	/* Methods */

	/* Getters & Setters */

	/**
	 * Assert on exception thrown
	 * 
	 * @param <T>
	 * @param message
	 * @param bean
	 * @param method
	 * @param params
	 * @param paramClasses
	 * @param exceptionClass
	 * @return the called method's return value
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Exception> Object failOnException(String message,
		Object bean, String method, Object[] params, Class<?>[] paramClasses,
		Class<T> exceptionClass) {
		try {
			Object result = MethodUtils.invokeExactMethod(bean, method, params,
				paramClasses);
			return result;
		} catch (NoSuchMethodException | IllegalAccessException iea) {
			LOG.log(Level.SEVERE, "", iea);
			throw new RuntimeException(iea);
		} catch (InvocationTargetException e) {
			if (exceptionClass.isAssignableFrom(e.getCause().getClass())) {
				T castedE = (T) e.getCause();
				LOG.log(Level.INFO, "", castedE);
				Assert
					.fail(message == null ? castedE.getClass().toString() : message);
				return null;
			} else {
				// The exception is not of the expected type
				LOG.log(Level.SEVERE, "", e);
				return null;
			}
		}
	}

	/**
	 * @param <T>
	 * @param bean
	 * @param method
	 * @param params
	 * @param paramClasses
	 * @param exceptionClass
	 * @return the called method's return value
	 */
	public static <T extends Exception> Object failOnException(Object bean,
		String method, Object[] params, Class<?>[] paramClasses,
		Class<T> exceptionClass) {
		return failOnException(null, bean, method, params, paramClasses,
			exceptionClass);
	}

	/**
	 * Assert on exception thrown
	 * 
	 * @param <T>
	 * @param bean
	 * @param method
	 * @param params
	 * @param paramClasses
	 * @param exceptionClass
	 * @return the thrown exception
	 */
	public static <T extends Exception> T assertOnException(Object bean,
		String method, Object[] params, Class<?>[] paramClasses,
		Class<T> exceptionClass) {
		return assertOnException("", bean, method, params, paramClasses,
			exceptionClass);
	}

	/**
	 * Assert on exception thrown
	 * 
	 * @param <T>
	 * @param message
	 * @param bean
	 * @param method
	 * @param params
	 * @param paramClasses
	 * @param exceptionClass
	 * @return the thrown exception
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Exception> T assertOnException(String message,
		Object bean, String method, Object[] params, Class<?>[] paramClasses,
		Class<T> exceptionClass) {
		try {
			MethodUtils.invokeExactMethod(bean, method, params, paramClasses);
			Assert.fail(message);
			return null;
		} catch (NoSuchMethodException | IllegalAccessException iea) {
			throw new RuntimeException(iea);
		} catch (InvocationTargetException e) {
			if (exceptionClass.isAssignableFrom(e.getCause().getClass())) {
				T castedE = (T) e.getCause();
				LOG.log(Level.INFO, "", castedE);
				return castedE;
			} else {
				// The exception is not of the expected type
				LOG.log(Level.SEVERE, "", e);
				Assert.fail(message);
				return null;
			}
		}
	}

	/**
	 * Assert if the content of two arrays is equal, no matters order
	 * 
	 * @param array1
	 * @param array2
	 */
	public static void assertArrayContentEquals(Object[] array1, Object[] array2) {
		Assert.assertEquals(array1.length, array2.length);
		for (Object element : array1) {
			boolean founded = false;
			for (Object element2 : array2) {
				if (element2.equals(element)) {
					founded = true;
					break;
				}
			}
			Assert.assertTrue(founded);
		}
	}

	/**
	 * Assert if the content of the array is equal to the ids of the list of
	 * objects (No matters order)
	 * 
	 * @param array1
	 * @param list List of objects, each of them with id property
	 */
	@SuppressWarnings("rawtypes")
	public static void assertArrayEqualsListWithId(Object[] array1, List list) {
		Assert.assertEquals(array1.length, list.size());
		for (Object element : array1) {
			boolean founded = false;
			for (Object element2 : list) {
				Object id = null;
				try {
					id = PropertyUtils.getProperty(element2, "id");
				} catch (IllegalAccessException | InvocationTargetException
					| NoSuchMethodException e) {
					LOG.log(Level.SEVERE, "Error getting id property", e);
					throw new RuntimeException(e);
				}
				if (element.equals(id)) {
					founded = true;
					break;
				}
			}
			Assert.assertTrue(founded);
		}
	}
}
