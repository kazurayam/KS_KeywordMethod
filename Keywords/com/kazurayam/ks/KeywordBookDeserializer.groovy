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
		KeywordBook kb = new KeywordBook()
		JsonNode rootNode = jp.getCodec().readTree(jp);
		for (String autTypeId : rootNode.fieldNames()) {
			//println "autTypeId: " + autTypeId
			for (JsonNode arrayKM : rootNode.findValues(autTypeId)) {
				//println "arrayKM.isArray(): " + arrayKM.isArray()
				if (arrayKM.isArray()) {
					for (JsonNode node : arrayKM.iterator()) {
						String autType = node.get("autType").asText()
						String group = node.get("group").asText()
						String methodName = node.get("methodName").asText()
						String parameters = node.get("parameters").asText()
						String description = node.get("description").asText()
						KeywordMethod km =
							new KeywordMethod(autType, group, methodName, parameters, description)
						kb.setKeywordMethod(AUTType.valueOf(autType), km)
					}
				} else {
					throw new JsonProcessingException("expected json array but found a JsonNode of type: " + 
						arrayKM.getNodeType())
				}
			}
		}
		return kb
	}
}
