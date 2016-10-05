package com.hallocasa.laboratory;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtils;

public class RunnerTest {

	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		Child child = new Child(1, "my desc", "my prof");
		Parent parent = new Parent(1, "my desc", "my prof", child);
		ParentTransform parentTransform = new ParentTransform();
		BeanUtilsBean bub = new BeanUtilsBean();
		bub.setProperty(parentTransform, "description", parent.getDescription());
		
		Map<String, Object> voProperties = PropertyUtils.describe(parent);
		Map<String, Object> entityProperties = PropertyUtils.getMappedPropertyDescriptors(parent);

		for (String propertyName : voProperties.keySet()) {
			System.out.println(voProperties.get(propertyName).getClass());
			System.out.println(entityProperties.get(propertyName).getClass());
			if(!voProperties.get(propertyName).getClass().equals(entityProperties.get(propertyName).getClass())){
				System.out.println("diff!!");
			}
		}
	}
	
}
