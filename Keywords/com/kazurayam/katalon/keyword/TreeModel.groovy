package com.kazurayam.katalon.keyword

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule

public class TreeModel {

	List<TreeNode> root

	TreeModel() {
		root = new ArrayList<>()
	}

	void addTreeNode(TreeNode treeNode) {
		root.add(treeNode)
	}

	int size() {
		return root.size()
	}

	TreeNode getTreeNode(int index) {
		return root.get(index)
	}

	String serialize() throws IOException {
		ObjectMapper mapper = new ObjectMapper()
		SimpleModule module = new SimpleModule()
		module.addSerializer(TreeNode.class, new TreeNodeSerializer())
		mapper.registerModule(module)
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root)
	}

	/*
	 public String serialize() {
	 return """
	 [
	 {
	 text: "WebUI",
	 icon: "fa fa-folder fa-fw",
	 nodes: [
	 {
	 text: "Alert",
	 icon: "fa fa-folder fa-fw",
	 nodes: [
	 {
	 icon: "fa fa-key fa-fw",
	 text: "acceptAlert",
	 nodes: [
	 {
	 icon: "fa fa-comment-o fa-fw",
	 text: "Fires the Close event of the running application to the Windows System. This action is similar to pressing 'ALT + F4' and also does not force close the application."
	 }
	 ]
	 },
	 ]
	 }
	 ]
	 }
	 ]
	 """
	 }
	 */
}

