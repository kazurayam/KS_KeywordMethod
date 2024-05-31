package com.kazurayam.ks

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer

/**
 * Custom Jackson Deserializer that instantiates a KeywordBook class from JSON
 *
 */
public class KeywordBookDeserializer extends StdDeserializer<KeywordBook> {

	public KeywordBookDeserializer() {
		this(null)
	}

	public KeywordBookDeserializer(Class<?> vc) {
		super(vc)
	}

	/*
	 {
	 "WebUI" : [ {
	 "autType" : "WebUI",
	 "group" : "Alert",
	 "methodName" : "acceptAlert",
	 "parameters" : "(com.kms.katalon.core.model.FailureHandling)",
	 "description" : ""
	 }, {
	 ...
	 */
	@Override
	public KeywordBook deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
	}
}
