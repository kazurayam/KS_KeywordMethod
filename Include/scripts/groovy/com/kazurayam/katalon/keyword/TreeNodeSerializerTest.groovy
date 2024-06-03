package com.kazurayam.katalon.keyword


import static org.junit.Assert.*

import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule



@RunWith(JUnit4.class)
public class TreeNodeSerializerTest {

	private TreeNode treeNode = TreeNodeTest.getFixture()

	@Test
	public void test_serialize() {
		// given
		TreeNode autTypeNode = TreeNodeTest.getFixture()
		ObjectMapper mapper = new ObjectMapper()
		SimpleModule module = new SimpleModule()
		module.addSerializer(TreeNode.class, new TreeNodeSerializer())
		mapper.registerModule(module)
		// when
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(treeNode)
		// then
		println json
		assertNotNull(json)
	}
}
