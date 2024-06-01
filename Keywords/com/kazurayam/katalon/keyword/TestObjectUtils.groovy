package com.kazurayam.katalon.keyword

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject


public class TestObjectUtils {

	private TestObjectUtils() {}

	static TestObject makeTestObject(String id, String xpath) {
		TestObject tObj = new TestObject(id)
		tObj.addProperty("xpath", ConditionType.EQUALS, xpath)
		return tObj
	}
}
