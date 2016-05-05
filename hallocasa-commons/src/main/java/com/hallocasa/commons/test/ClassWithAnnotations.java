package com.hallocasa.commons.test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hallocasa.commons.annotations.PropertyFieldValueParser;

public class ClassWithAnnotations {

	@PropertyFieldValueParser(id = 1, methodToExecute="sayHello")
	private String myField1;
	
	@PropertyFieldValueParser(id = 2, methodToExecute="sayHelloTwo")
	private Integer myField2;
	
	
	
	public String sayHello(String myValue){
		return "hi";
	}
	
	public Integer sayHelloTwo(String myValue){
		return 0;
	}
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		String text = "my text";
		ClassWithAnnotations ob = new ClassWithAnnotations();
		ob.test4();
		Class<? extends ClassWithAnnotations> c = ob.getClass();
		Field[] fields = c.getDeclaredFields();
		for(Field field : fields){
			PropertyFieldValueParser ma = (PropertyFieldValueParser)field.getAnnotations()[0];
			String methodName = ma.methodToExecute();
			Method method = c.getMethod(methodName, String.class);
			field.set(ob, method.invoke(ob, text));
		}
		System.out.println(ob.getMyField1());
		System.out.println(ob.getMyField2());
	}
	
	public void test2(){
		 Gson gson = new GsonBuilder().create();
		 String json = "{'id':1,'name':'hi, this is my name'}";
		 TestVO vo = gson.fromJson(json, TestVO.class);
		 System.out.println(vo.getId());
		 System.out.println(vo.getName());
	}
	
	public void test3(){
		 Gson gson = new GsonBuilder().create();
		 String json = "{'id':1,'objectList':[{'id':5, name:'great'},{'id':6, name:'great2'}], 'name':'hi, this is my name'}";
		 TestVOWithNested vo = gson.fromJson(json, TestVOWithNested.class);
		 System.out.println(vo.getId());
		 System.out.println(vo.getName());
		 System.out.println(vo.getObjectList().get(0).getName());
	}
	
	public void test4(){
		 Gson gson = new GsonBuilder().create();
		 String json = "['A','B','C']";
		 Type listType = new TypeToken<ArrayList<EnumTest>>() {}.getType();
		 List<EnumTest> enumTestList = gson.fromJson(json, listType);
		 System.out.println(enumTestList.get(2).getName());
	}
	

	public String getMyField1() {
		return myField1;
	}

	public void setMyField1(String myField1) {
		this.myField1 = myField1;
	}

	public Integer getMyField2() {
		return myField2;
	}

	public void setMyField2(Integer myField2) {
		this.myField2 = myField2;
	}
	
	
	
}
