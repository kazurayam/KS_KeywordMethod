package com.kazurayam.katalon.keyword

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * Custom Jackson Serializer that serializes a KeywordMethod class into JSON
 * 
 */
public class KeywordMethodSerializer extends StdSerializer<KeywordMethod> {

	public KeywordMethodSerializer() {
		this(null);
	}

	public KeywordMethodSerializer(Class<KeywordMethod> t) {
		super(t)
	}

	@Override
	public void serialize(KeywordMethod km,
			JsonGenerator jgen,
			SerializerProvider provider) throws IOException {
		jgen.writeStartObject()
		jgen.writeStringField("autType", km.autType().toString())
		jgen.writeStringField("group", km.keywordGroup())
		jgen.writeStringField("methodName", km.methodName())
		jgen.writeStringField("parameters", km.getMethodParameters().toString())
		jgen.writeStringField("description", km.description())
		jgen.writeEndObject()
	}
}
