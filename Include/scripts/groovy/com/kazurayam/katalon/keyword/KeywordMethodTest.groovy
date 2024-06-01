package com.kazurayam.katalon.keyword

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling

@RunWith(JUnit4.class)
public class KeywordMethodTest {

	private KeywordMethod km

	@Before
	public void setup() {
		km = KeywordUtils.getKeywordMethod(WebUiBuiltInKeywords.class,
				AUTType.WebUI, "Attribute", "getAttribute",
				new MethodParameters(Arrays.asList(TestObject.class, String.class))
				)
	}

	@Test
	public void test_autType() {
		assertEquals(AUTType.WebUI, km.autType())
	}

	@Test
	public void test_keywordGroup() {
		assertEquals("Attribute", km.keywordGroup())
	}

	@Test
	public void test_methodName() {
		assertEquals("getAttribute", km.methodName())
	}

	@Test
	public void test_fragment() {
		assertEquals("getAttribute(com.kms.katalon.core.testobject.TestObject,%20java.lang.String)", km.fragment())
	}

	@Test
	public void test_anchorName() {
		assertEquals("getAttribute(com.kms.katalon.core.testobject.TestObject, java.lang.String)", km.anchorName())
	}

	@Test
	public void test_javadocUrl() {
		String protocol = "https"
		String host = "api-docs.katalon.com"
		String file = "/com/kms/katalon/core/webui/keyword/WebUiBuiltInKeywords.html"
		String fragment = "getAttribute(com.kms.katalon.core.testobject.TestObject,%20java.lang.String)"
		String expected = protocol + "://" + host + file + "#" + fragment
		assertEquals(expected, km.javadocUrl())
	}

	@Test
	public void test_toString() {
		String str = km.toString()
		println str
		String expected = "WebUI, Attribute, getAttribute(com.kms.katalon.core.testobject.TestObject, java.lang.String)"
		assertEquals(expected, str)
	}

	@Test
	public void test_constructor_with_strings() {
		// given
		KeywordMethod expected =
				KeywordUtils.getKeywordMethod(WebUiBuiltInKeywords.class,
				AUTType.WebUI, "Alert", "acceptAlert",
				new MethodParameters(Arrays.asList(FailureHandling.class)))
		expected.setDescription("mayday")
		// when
		String autType = "WebUI"
		String group = "Alert"
		String methodName = "acceptAlert"
		String parameters = "(com.kms.katalon.core.model.FailureHandling)"
		String description = "mayday"
		KeywordMethod actual = new KeywordMethod(autType, group, methodName, parameters, description)
		// then
		assertEquals(expected, actual)
	}
}
