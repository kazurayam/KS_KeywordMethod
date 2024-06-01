package com.kazurayam.ks

import static org.junit.Assert.*

import org.junit.Before
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
public class KeywordBookSerializerTest {

	private static Path projDir = Paths.get(RunConfiguration.getProjectDir())

	private static TestOutputOrganizer too =
	new TestOutputOrganizer.Builder(KeywordBookSerializerTest.class)
	.projectDirectory(projDir)
	.subOutputDirectory(KeywordBookSerializerTest.class)
	.build()

	private KeywordBook kb

	@BeforeClass
	public static void beforeClass() throws IOException {
		too.cleanOutputDirectory()
	}

	@Before
	public void setup() {
		List<KeywordMethod> list1 = KU.getKeywordMethods(AUTType.WebUI.getKeywordsClass())
		kb = new KeywordBook()
		kb.setKeywordMethods(AUTType.WebUI, list1)
	}

	@Test
	public void test_serializeInto() {
		// given
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName()
		too.cleanMethodOutputDirectory(methodName)
		Path json = too.resolveMethodOutputDirectory(methodName).resolve("KeywordBook.json")
		// when
		kb.serializeInto(json)
		// then
		assertTrue(Files.exists(json))
		assertTrue(json.toFile().length() > 0)
	}
}
