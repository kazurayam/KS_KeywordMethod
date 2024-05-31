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

	/*
	 {
	 "autType" : "WebUI",
	 "group" : "Alert",
	 "methodName" : "acceptAlert",
	 "parameters" : "(com.kms.katalon.core.model.FailureHandling)",
	 "description" : "mayday"
	 }
	 */
	@Override
	public KeywordMethod deserialize(JsonParser jp, DeserializationContext ctxt)
	throws IOException, JsonProcessingException {
		JsonNode node = jp.getCodec().readTree(jp);
		println node
		String autType = node.get("autType").asText()
		String group = node.get("group").asText()
		String methodName = node.get("methodName").asText()
		String parameters = node.get("parameters").asText()
		String description = node.get("description").asText()
		return new KeywordMethod(autType, group, methodName, parameters, description)
	}
}
