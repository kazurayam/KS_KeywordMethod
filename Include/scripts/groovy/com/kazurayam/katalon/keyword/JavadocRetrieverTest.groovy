package com.kazurayam.katalon.keyword

import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords
import com.kms.katalon.core.model.FailureHandling
import java.nio.file.Files
import java.nio.file.Path
import org.jsoup.nodes.Document


@RunWith(JUnit4.class)
public class JavadocRetrieverTest {

	private JavadocRetriever jr = new JavadocRetriever()

	@Test
	public void test_resolveBundledJavadocHTML_WebUI() {
		String expected = "/Applications/Katalon Studio.app/Contents/Eclipse/configuration/resources/apidocs/com.kms.katalon.core.webui/com/kms/katalon/core/webui/keyword/WebUiBuiltInKeywords.html"
		Path actual = JavadocRetriever.resolveBundledJavadoc(AUTType.WebUI)
		assertEquals(expected, actual.toString())
		assertTrue(Files.exists(actual))
	}
	
	@Test
	public void test_loadJavadoc() {
		Document javadoc = JavadocRetriever.loadJavadoc(AUTType.WebUI)
		assertNotNull(javadoc)
	}
	
	@Test
	public void test_selectDescriptionOf() {
		KeywordMethod openBrowserKeyword =
				KeywordMethodFactory.getKeywordMethod(WebUiBuiltInKeywords.class, AUTType.WebUI, "Browser", "openBrowser",
						new MethodParameters(Arrays.asList(String.class, FailureHandling.class)))
		String desc = jr.selectDescriptionOf(openBrowserKeyword)
		assertEquals("Open browser and navigate to the specified url; if url is left empty then just open browser", desc)
	}

}
