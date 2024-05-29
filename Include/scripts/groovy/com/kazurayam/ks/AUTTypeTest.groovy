package com.kazurayam.ks

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

		String urlComponent = AUTType.WebUI.getUrlComponent()
		assertEquals("webui", urlComponent)

		String className = AUTType.WebUI.getClassName()
		assertEquals("com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords", className)

		String shortName = AUTType.WebUI.getShortClassName()
		assertEquals("WebUiBuiltInKeywords", shortName)
	}

	@Test
	void test_resolve() {
		assertEquals("webui", AUTType.resolve(WebUiBuiltInKeywords).getUrlComponent())
		assertEquals("webservice", AUTType.resolve(WSBuiltInKeywords).getUrlComponent())
		assertEquals("mobile", AUTType.resolve(MobileBuiltInKeywords).getUrlComponent())
		assertEquals("windows", AUTType.resolve(WindowsBuiltinKeywords).getUrlComponent())
	}
}
