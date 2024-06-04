package com.kazurayam.katalon.keyword

import static org.junit.Assert.*

import org.junit.Before
import org.junit.BeforeClass
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import com.kazurayam.katalon.keyword.KeywordMethodFactory as KU
import com.kazurayam.unittest.TestOutputOrganizer
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

@RunWith(JUnit4.class)
public class TreeNodeTest {

	private static TreeNode autTypeNode
	private static TreeNode groupNode
	private static TreeNode keywordNode
	private static TreeNode commentNode
	public static String commentText = "Fires the Close event of the running application to the Windows System. This action is similar to pressing 'ALT + F4' and also does not force close the application."

	static {
		commentNode = new TreeNode(commentText, TreeNode.Icon.Comment)
		//
		keywordNode = new TreeNode("acceptAlert", TreeNode.Icon.Key)
		keywordNode.addNode(commentNode)
		//
		groupNode = new TreeNode("Alert", TreeNode.Icon.Folder)
		groupNode.addNode(keywordNode)
		//
		autTypeNode = new TreeNode("WebUI", TreeNode.Icon.Folder)
		autTypeNode.addNode(groupNode)
	}

	/**
	 * to help other Tests
	 * @return
	 */
	public static TreeNode getFixture() {
		return autTypeNode
	}

	@Test
	void test_autTypeNode() {
		assertNotNull(autTypeNode)
		assertEquals(TreeNode.Icon.Folder, autTypeNode.icon())
		assertEquals("WebUI", autTypeNode.text())
		assertEquals(1, autTypeNode.size())
	}

	@Test
	void test_groupNode() {
		TreeNode g = autTypeNode.getNode(0)
		assertNotNull(g)
		assertEquals(TreeNode.Icon.Folder, g.icon())
		assertEquals("Alert", g.text())
		assertEquals(1, g.size())
	}

	@Test
	void test_keywordNode() {
		TreeNode k = autTypeNode.getNode(0).getNode(0)
		assertNotNull(k)
		assertEquals(TreeNode.Icon.Key, k.icon())
		assertEquals("acceptAlert", k.text())
		assertEquals(1, k.size())
	}

	@Test
	void test_comment_node() {
		TreeNode c = autTypeNode.getNode(0).getNode(0).getNode(0)
		assertNotNull(c)
		assertEquals(TreeNode.Icon.Comment, c.icon())
		assertEquals(commentText, c.text())
		assertNull(c.nodes())
	}
}
