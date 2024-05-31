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
public class KeywordBookDeserializerTest {

	@Test
	void test_deserialize() {
		String json = '''{
  "WebUI": [{
    "autType" : "WebUI",
    "group" : "Alert",
    "methodName" : "acceptAlert",
    "parameters" : "(com.kms.katalon.core.model.FailureHandling)",
    "description" : "mayday"
  }
]}'''
		ObjectMapper mapper = new ObjectMapper()
		SimpleModule module = new SimpleModule()
		module.addDeserializer(KeywordBook.class,
				new KeywordBookDeserializer());
		mapper.registerModule(module)
		// when
		KeywordBook actual = mapper.readValue(json, KeywordBook.class)
		// then
		assertNotNull(actual)
		throw new RuntimeException("TODO")
	}
}
