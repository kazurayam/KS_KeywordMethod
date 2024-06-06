package com.kazurayam.katalon.keyword

import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords

@RunWith(JUnit4.class)
public class AUTTypeTest {

	@Test
	void test_WebUI() {
		String className = AUTType.WebUI.getClassName()
		assertEquals("com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords", className)

		String shortName = AUTType.WebUI.getShortClassName()
		assertEquals("WebUiBuiltInKeywords", shortName)

		String javadocUrl = AUTType.WebUI.getHttpJavadocUrl()
		assertEquals("https://api-docs.katalon.com/com/kms/katalon/core/webui/keyword/WebUiBuiltInKeywords.html", javadocUrl)

		String javadocUrlComponent0 = AUTType.WebUI.getJavadocUrlComponent0()
		assertEquals("com.kms.katalon.core.webui", javadocUrlComponent0)

		String javadocUrlComponent1 = AUTType.WebUI.getJavadocUrlComponent1()
		assertEquals("com/kms/katalon/core/webui/keyword/WebUiBuiltInKeywords.html", javadocUrlComponent1);
	}

	@Test
	void test_resolve() {
		assertEquals("WebUiBuiltInKeywords", AUTType.resolve(WebUiBuiltInKeywords).getShortClassName())
		assertEquals("WSBuiltInKeywords", AUTType.resolve(WSBuiltInKeywords).getShortClassName())
		assertEquals("MobileBuiltInKeywords", AUTType.resolve(MobileBuiltInKeywords).getShortClassName())
		assertEquals("WindowsBuiltinKeywords", AUTType.resolve(WindowsBuiltinKeywords).getShortClassName())
	}
	
	@Test
	void test_getHttpJavadocUrl() {
		assertEquals("https://api-docs.katalon.com/com/kms/katalon/core/webui/keyword/WebUiBuiltInKeywords.html", AUTType.WebUI.getHttpJavadocUrl())
		
	}
}
