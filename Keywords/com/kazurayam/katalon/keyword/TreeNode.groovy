package com.kazurayam.katalon.keyword


public class TreeNode {

	static enum Icon {
		Folder("fa fa-folder fa-fw"),
		Key("fa fa-key fa-fw"),
		Comment("fa fa-comment-o fa-fw")
		;

		private String value

		Icon(String value) {
			this.value = value
		}
		String value() {
			return this.value
		}
		String toString() {
			return this.value
		}
	}

	String text = null
	Icon icon = null
	List<TreeNode> nodes = null

	TreeNode(String text, Icon icon) {
		Objects.requireNonNull(text)
		Objects.requireNonNull(icon)
		this.text = text
		this.icon = icon
	}

	String text() {
		return this.text
	}

	Icon icon() {
		return this.icon
	}

	void addNode(TreeNode kbn) {
		if (nodes == null) {
			nodes = new ArrayList<>()
		}
		nodes.add(kbn)
	}

	boolean hasNodes() {
		return (nodes != null)
	}

	int size() {
		if (nodes != null) {
			return nodes.size()
		} else {
			throw new IllegalStateException("nodes is null")
		}
	}

	List<TreeNode> nodes() {
		return this.nodes
	}

	TreeNode getNode(int i) {
		if (nodes != null) {
			return this.nodes.get(i)
		} else {
			return null
		}
	}

	TreeNode last() {
		if (nodes != null) {
			if (nodes.size() > 0) {
				return nodes.last()
			} else {
				return null
			}
		} else {
			throw new IllegalStateException("nodes is null")
		}
	}
}
