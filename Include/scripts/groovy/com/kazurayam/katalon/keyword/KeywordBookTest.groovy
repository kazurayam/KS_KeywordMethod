package com.kazurayam.katalon.keyword

import static org.junit.Assert.*

import org.junit.Before
import org.junit.BeforeClass
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import com.kazurayam.katalon.keyword.KeywordUtils as KU
import com.kazurayam.unittest.TestOutputOrganizer
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

@RunWith(JUnit4.class)
public class KeywordBookTest {

	private static Path projDir = Paths.get(RunConfiguration.getProjectDir())

	private static TestOutputOrganizer too =
	new TestOutputOrganizer.Builder(KeywordBookTest.class)
	.projectDirectory(projDir)
	.subOutputDirectory(KeywordBookTest.class)
	.build()

	private KeywordBook kb

	@BeforeClass
	public static void beforeClass() throws IOException {
		too.cleanOutputDirectory()
	}

	@Before
	public void setup() {
		kb = new KeywordBook()
		List<KeywordMethod> list = KU.getKeywordMethods(AUTType.WebUI.getKeywordsClass())
		kb.setKeywordMethods(AUTType.WebUI, list)
	}

	@Test
	public void test_constructor() {
		KeywordBook kb0 = new KeywordBook()
		assertNotNull(kb0)
	}

	@Test
	public void test_setKeywordMethod_getKeywordMethod() {
		// when
		KeywordMethod km = kb.getKeywordMethod(AUTType.WebUI, 1)
		km.setDescription("mayday")
		kb.setKeywordMethod(AUTType.WebUI, km)
		// then
		KeywordMethod km2 = kb.getKeywordMethod(AUTType.WebUI, 1)
		assertNotNull(km2)
		assertEquals("mayday", km2.description())
	}

	@Test
	public void test_getKeywordMethod_with_base() {
		// when
		KeywordMethod base = KU.getKeywordMethod(WebUiBuiltInKeywords.class, AUTType.WebUI, "Alert", "acceptAlert", new MethodParameters(Arrays.asList(FailureHandling.class)))
		KeywordMethod found = kb.getKeywordMethod(AUTType.WebUI, base)
		assertNotNull(found)
	}

	@Test
	public void test_setKeywordMethods_getKeywordMethods_sizeOfKeywordMethods() {
		// given
		List<KeywordMethod> list2 = KU.getKeywordMethods(AUTType.WS.getKeywordsClass())
		// when
		kb.setKeywordMethods(AUTType.WS, list2)
		// then
		assertTrue(kb.getKeywordMethods(AUTType.WS).size() > 0)
	}

	@Test
	public void test_getKeywordMethod() {
		KeywordMethod km = kb.getKeywordMethod(AUTType.WebUI, 0)
		assertNotNull(km)
		assertEquals("acceptAlert", km.methodName())
	}

	@Test
	public void test_serialize() {
		// given
		List<KeywordMethod> list1 = KU.getKeywordMethods(AUTType.WebUI.getKeywordsClass())
		KeywordBook kb = new KeywordBook()
		kb.setKeywordMethods(AUTType.WebUI, list1)
		// when
		Path dir = too.cleanMethodOutputDirectory("test_serialize")
		Path file = dir.resolve("KeywordBook.json")
		kb.serializeInto(file)
		// then
		assertTrue(Files.exists(file))
		assertTrue(file.toFile().length() > 100)  // has some content
	}

	@Test
	public void test_deserialize() {
		// given
		List<KeywordMethod> list1 = KU.getKeywordMethods(AUTType.WebUI.getKeywordsClass())
		KeywordBook kb1 = new KeywordBook()
		kb1.setKeywordMethods(AUTType.WebUI, list1)
		Path dir = too.cleanMethodOutputDirectory("test_deserialize")
		Path file = dir.resolve("KeywordBook.json")
		kb1.serializeInto(file)
		// when
		KeywordBook kb2 = KeywordBook.deserializeFrom(file)
		List<KeywordMethod> list2 = kb2.getKeywordMethods(AUTType.WebUI)
		// then
		assertNotNull(list2)
		assertTrue(list2.size() > 0)
	}

	@Test
	public void test_createKeywordBook() {
		KeywordBook kb = KeywordBook.createKeywordBook()
		assertTrue(kb.getKeywordMethods(AUTType.WebUI).size() > 0)
		assertTrue(kb.getKeywordMethods(AUTType.WS).size() > 0)
		assertTrue(kb.getKeywordMethods(AUTType.Mobile).size() > 0)
		assertTrue(kb.getKeywordMethods(AUTType.Windows).size() > 0)
	}
}
