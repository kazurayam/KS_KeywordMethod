package com.kazurayam.ks

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;


/**
 * Custom Jackson Serializer that serializes a KeywordBook class into JSON
 *
 */
public class KeywordBookSerializer extends StdSerializer<KeywordBook> {

	public KeywordBookSerializer() {
		this(null);
	}

	public KeywordBookSerializer(Class<KeywordBook> t) {
		super(t)
	}

	@Override
	public void serialize(KeywordBook kb,
			JsonGenerator jgen,
			SerializerProvider provider) throws IOException {
		jgen.writeStartObject()
		for (AUTType autType : kb.keySet()) {
			jgen.writeFieldName(autType.toString())
			jgen.writeStartArray()
			List<KeywordMethod> listKM = kb.getKeywordMethods(autType)
			listKM.forEach({ km ->
				jgen.writeObject(km)
			})
			jgen.writeEndArray()
		}
		jgen.writeEndObject()
	}
}

