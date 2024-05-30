package com.kazurayam.ks

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer

/**
 * Custom Jackson Deserializer that instantiates a KeywordMethod class from JSON
 *
 */
public class KeywordMethodDeserializer extends StdDeserializer<KeywordMethod> {

	public KeywordMethodDeserializer() {
		this(null)
	}

	public KeywordMethodDeserializer(Class<?> vc) {
		super(vc)
	}

	@Override
	public KeywordMethod deserialize(JsonParser jp, DeserializationContext ctxt)
	throws IOException, JsonProcessingException {
		JsonNode node = jp.getCodec().readTree(jp);
		throw new RuntimeException("TODO")
		//return new KeywordMethod();
	}
}
