package com.kazurayam.katalon.keyword

import static org.junit.Assert.*

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import com.kazurayam.unittest.TestOutputOrganizer
import com.kms.katalon.core.configuration.RunConfiguration

@RunWith(JUnit4.class)
public class KeywordBrowserTest {

	private static Path projDir = Paths.get(RunConfiguration.getProjectDir())

	private static TestOutputOrganizer too = new TestOutputOrganizer.Builder(KeywordBrowserTest.class)
	.projectDirectory(projDir)
	.subOutputDirectory(KeywordBrowserTest.class)
	.build()

	private KeywordBrowser keywordBrowser

	@Before
	public void setup() {
		keywordBrowser = new KeywordBrowser()
	}

	@Test
	public void test_writeCss() {
		Path dir = too.cleanMethodOutputDirectory("test_writeCss")
		Path file = keywordBrowser.writeCss(dir)
		assertTrue(Files.exists(file))
		assertTrue(file.getFileName().toString().endsWith(".css"))
	}

	@Test
	public void test_writeJs() {
		Path dir = too.cleanMethodOutputDirectory("test_writeJs")
		Path file = keywordBrowser.writeJs(dir)
		assertTrue(Files.exists(file))
		assertTrue(file.getFileName().toString().endsWith(".js"))
	}

	@Test
	public void test_writeHtml() {
		Path dir = too.cleanMethodOutputDirectory("test_writeHtml")
		Path file = keywordBrowser.writeHtml(dir)
		assertTrue(Files.exists(file))
		assertTrue(file.getFileName().toString().endsWith(".html"))
		// additive
		Path css = keywordBrowser.writeCss(dir)
		Path js = keywordBrowser.writeJs(dir)
	}

	@Test
	public void test_toModel() {
		// given
		KeywordBook kb = KeywordBook.createKeywordBook()
		println kb.serializeAsText()
		kb.injectJavadoc()
		println kb.serializeAsText()
		// when
		TreeModel tm = KeywordBrowser.toModel(kb)
		println tm.serialize()
		// then
		assertNotNull(tm)
		assertTrue(tm.size() > 0)
		TreeNode webuiTreeNode = tm.getTreeNode(0)
		assertEquals("WebUI", webuiTreeNode.text())
		assertTrue("webuiTreeNode has no child nodes", webuiTreeNode.size() > 0)
		List<TreeNode> groupNodes = webuiTreeNode.nodes()
		assertNotNull(groupNodes)
		assertTrue("groupNodes has no child nodes", groupNodes.size( ) > 0)
	}
}
