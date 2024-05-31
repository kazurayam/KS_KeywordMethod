package com.kazurayam.ks

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.module.SimpleModule

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

	public void serialize(Path jsonFile) throws IOException {
		ObjectMapper mapper = new ObjectMapper()
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true)
		SimpleModule module = new SimpleModule()
		module.addSerializer(KeywordMethod.class, new KeywordMethodSerializer())
		mapper.registerModule(module)
		mapper.writeValue(jsonFile.toFile(), collection)
	}

	public static KeywordBook deserialize(Path jsonFile) {
		ObjectMapper mapper = new ObjectMapper()
		SimpleModule module = new SimpleModule()
		module.addDeserializer(KeywordMethod.class, new KeywordMethodDeserializer())
		mapper.registerModule(module)
		return mapper.readValue(jsonFile.toFile(), KeywordMethod.class)
	}
}
