package com.kazurayam.ks

import static org.junit.Assert.*

import org.junit.BeforeClass
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import com.kazurayam.ks.KeywordUtils as KU
import com.kazurayam.unittest.TestOutputOrganizer
import com.kms.katalon.core.configuration.RunConfiguration

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

	@BeforeClass
	public static void beforeClass() throws IOException {
		too.cleanOutputDirectory()
	}

	@Test
	public void test_constructor() {
		KeywordBook kb = new KeywordBook()
		assertNotNull(kb)
	}

	@Test
	public void test_set_and_get_KeywordMethods() {
		// given
		List<KeywordMethod> list1 = KU.getKeywordMethods(AUTType.WebUI.getKeywordsClass())
		KeywordBook kb = new KeywordBook()
		kb.setKeywordMethods(AUTType.WebUI, list1)
		// when
		List<KeywordMethod> list2 = kb.getKeywordMethods(AUTType.WebUI)
		// then
		assertNotNull(list2)
		assertEquals(list1.size(), list2.size())
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
		kb.serialize(file)
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
		kb1.serialize(file)
		// when
		KeywordBook kb2 = KeywordBook.deserialize(file)
		List<KeywordMethod> list2 = kb2.getKeywordMethods(AUTType.WebUI)
		// then
		assertNotNull(list2)
		assertTrue(list2.size() > 0)
		
		
	}
}
