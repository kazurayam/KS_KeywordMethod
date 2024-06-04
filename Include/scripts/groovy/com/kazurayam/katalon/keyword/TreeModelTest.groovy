package com.kazurayam.katalon.keyword

import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4.class)
public class TreeModelTest {

	@Test
	void test_serialize() {
		// given
		TreeModel treeModel = new TreeModel()
		treeModel.addTreeNode(TreeNodeTest.getFixture())
		// when
		String json = treeModel.serialize()
		// then
		assertNotNull(json)
		println json
	}
}
