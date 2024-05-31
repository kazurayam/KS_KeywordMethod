package com.kazurayam.ks


import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule

import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling

@RunWith(JUnit4.class)
public class KeywordMethodSerializerTest {

	@Test
	void test_serialize() {
		// given
		KeywordMethod km = KeywordUtils.getKeywordMethod(WebUiBuiltInKeywords.class,
				AUTType.WebUI, "Alert", "acceptAlert",
				new MethodParameters(Arrays.asList(FailureHandling.class))
				)
		ObjectMapper mapper = new ObjectMapper()
		SimpleModule module = new SimpleModule()
		module.addSerializer(KeywordMethod.class, new KeywordMethodSerializer())
		mapper.registerModule(module);
		// when
		String json = mapper.writeValueAsString(km)
		//String json = '''{
		//    "autType" : "WebUI",
		//    "group" : "Alert",
		//    "methodName" : "acceptAlert",
		//    "parameters" : "(com.kms.katalon.core.model.FailureHandling)",
		//    "description" : ""
		//  }'''
		println json
		assertTrue(json.contains("autType") && json.contains("WebUI"))
		assertTrue(json.contains("group") && json.contains("Alert"))
		assertTrue(json.contains("methodName") && json.contains("acceptAlert"))
		assertTrue(json.contains("parameters") && json.contains("(com.kms.katalon.core.model.FailureHandling)"))
		assertTrue(json.contains("description"))
	}
}
