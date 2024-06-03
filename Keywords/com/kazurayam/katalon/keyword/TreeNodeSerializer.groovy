package com.kazurayam.katalon.keyword

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class TreeNodeSerializer extends StdSerializer<TreeNode> {

	public TreeNodeSerializer() {
		this(null)
	}

	public TreeNodeSerializer(Class<TreeNode> t) {
		super(t)
	}

	@Override
	public void serialize(TreeNode node,
			JsonGenerator jgen,
			SerializerProvider provider) throws IOException {
		jgen.writeStartObject()
		jgen.writeStringField("icon", node.icon().value())
		jgen.writeStringField("text", node.text())
		if (node.hasNodes() && node.size()> 0) {
			jgen.writeFieldName("nodes")
			jgen.writeStartArray()
			for (TreeNode tn : node.nodes()) {
				jgen.writeObject(tn)
			}
			jgen.writeEndArray()
		}
		jgen.writeEndObject()
	}
}
