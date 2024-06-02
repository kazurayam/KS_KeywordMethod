package com.kazurayam.katalon.keyword

public class KeywordBrowserNode {
	
	String text = null
	String icon = null
	List<KeywordBrowserNode> nodes = null
	
	KeywordBrowserNode(String text, String icon) {
		Objects.requireNonNull(text)
		Objects.requireNonNull(icon)
	}
	
	void add(KeywordBrowserNode kbn) {
		if (nodes == null) {
			nodes = new ArrayList<>()
		}
		nodes.add(kbn)
	}
	
	int size() {
		if (nodes != null) {
			return nodes.size()
		} else {
			throw new IllegalStateException("nodes is null")
		}
	}
	
	KeywordBrowserNode get(int i) {
		if (nodes != null) {
			return nodes.get(i)
		} else {
			throw new IllegalStateException("nodes is null")
		}
	}
	
	KeywordBrowserNode last() {
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
