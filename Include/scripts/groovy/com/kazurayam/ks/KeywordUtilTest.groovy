package com.kazurayam.ks

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import java.lang.reflect.Method

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

@RunWith(JUnit4.class)
public class KeywordUtilTest {

	@Test
	public void test_getAccessibleMethods() {
		List<Method> allMethods = KeywordUtil.getAccessibleMethods(WebUiBuiltInKeywords.class)
		println "allMethods.size()=" + allMethods.size()   // 370
		assertTrue(allMethods.size() > 0)
	}

	@Test
	public void test_getKeywordMethods() {
		List<KeywordMethod> kms = KeywordUtil.getKeywordMethods(WebUiBuiltInKeywords.class)
		println "kms.size()=" + kms.size()   //
		assertTrue(kms.size() > 0)
	}
	
	@Test
	public void test_getKeywordMethod() {
		KeywordMethod km = 
			KeywordUtil.getKeywordMethod(WebUiBuiltInKeywords.class,
				AUTType.WebUI, "Attribute", "getAttribute",
				new MethodParameters(Arrays.asList(TestObject.class, String.class))
				)
		assertNotNull(km)
	}
}
