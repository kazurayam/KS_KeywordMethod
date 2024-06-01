package com.kazurayam.katalon.keyword

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
public class KeywordMethodDeserializerTest {

	@Test
	void test_deserialize() {
		String json = '''{
    "autType" : "WebUI",
    "group" : "Alert",
    "methodName" : "acceptAlert",
    "parameters" : "(com.kms.katalon.core.model.FailureHandling)",
    "description" : "mayday"
  }'''
		ObjectMapper mapper = new ObjectMapper()
		SimpleModule module = new SimpleModule()
		module.addDeserializer(KeywordMethod.class,
				new KeywordMethodDeserializer());
		mapper.registerModule(module)
		// when
		KeywordMethod actual = mapper.readValue(json, KeywordMethod.class)
		// then
		assertNotNull(actual)
		assertEquals("mayday", actual.description())
		println actual.toString()
	}
}
