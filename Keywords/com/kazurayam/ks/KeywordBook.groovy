package com.kazurayam.ks

import java.nio.file.Path

public class KeywordBook {

	private Map<AUTType, List<KeywordMethod>> collection

	public KeywordBook() {
		this.collection = new HashMap<>()
	}

	public void setKeywordMethods(AUTType autType, List<KeywordMethod> list) {
		collection.put(autType, list)
	}

	public List<KeywordMethod> getKeywordMethods(AUTType autType) {
		return collection.get(autType)
	}

	public void serialize(Path jsonFile) {
		throw new RuntimeException("TODO")
	}

	public static KeywordBook deserialize(Path jsonFile) {
		throw new RuntimeException("TODO")
	}
}
