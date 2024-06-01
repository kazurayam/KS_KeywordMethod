package com.kazurayam.ks

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.module.SimpleModule

import java.nio.file.Path
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords

public class KeywordBook {

	private Map<AUTType, List<KeywordMethod>> collection

	static KeywordBook createKeywordBook() {
		KeywordBook kb = new KeywordBook()
		kb.setKeywordMethods(AUTType.WebUI, KeywordUtils.getKeywordMethods(WebUiBuiltInKeywords.class))
		kb.setKeywordMethods(AUTType.WS, KeywordUtils.getKeywordMethods(WSBuiltInKeywords.class))
		kb.setKeywordMethods(AUTType.Mobile, KeywordUtils.getKeywordMethods(MobileBuiltInKeywords.class))
		kb.setKeywordMethods(AUTType.Windows, KeywordUtils.getKeywordMethods(WindowsBuiltinKeywords.class))
		return kb
	}

	public KeywordBook() {
		this.collection = new HashMap<>()
	}

	public void setKeywordMethods(AUTType autType, List<KeywordMethod> list) {
		collection.put(autType, list)
	}

	public void addKeywordMethod(KeywordMethod keywordMethod) {
		if (collection.keySet().contains(keywordMethod.autType())) {
			// the key AUTType is found, add the KM into the existing list
			collection.get(keywordMethod.autType()).add(keywordMethod)
		} else {
			// new AUTType, create a new list and add the KM
			List<KeywordMethod> list = new ArrayList<>()
			list.add(keywordMethod)
			collection.put(keywordMethod.autType(), list)
		}
	}

	public Set<AUTType> keySet() {
		return collection.keySet()
	}

	public List<KeywordMethod> getKeywordMethods(AUTType autType) {
		return collection.get(autType)
	}

	public void serializeInto(Path jsonFile) throws IOException {
		File f = jsonFile.toFile()
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(f), "utf-8")
		this.serializeInto(osw)
	}

	public String serializeAsText() throws IOException {
		StringWriter sw = new StringWriter()
		this.serializeInto(sw)
		return sw.toString()
	}

	public void serializeInto(Writer writer) throws IOException {
		ObjectMapper mapper = new ObjectMapper()
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true)
		SimpleModule module = new SimpleModule()
		module.addSerializer(KeywordBook.class, new KeywordBookSerializer())
		module.addSerializer(KeywordMethod.class, new KeywordMethodSerializer())
		mapper.registerModule(module)
		mapper.writeValue(writer, collection)
	}

	public static KeywordBook deserializeFrom(Path jsonFile) {
		File f = jsonFile.toFile()
		InputStreamReader isr = new InputStreamReader(new FileInputStream(f), "utf-8")
		return KeywordBook.deserializeFrom(isr)
	}

	public static KeywordBook deserializeFrom(StringReader sr) {
		return KeywordBook.deserializeFrom(sr)
	}

	public static KeywordBook deserializeFrom(Reader reader) {
		ObjectMapper mapper = new ObjectMapper()
		SimpleModule module = new SimpleModule()
		module.addDeserializer(KeywordBook.class, new KeywordBookDeserializer())
		module.addDeserializer(KeywordMethod.class, new KeywordMethodDeserializer())
		mapper.registerModule(module)
		return mapper.readValue(reader, KeywordBook.class)
	}

	@Override
	public String toString() {
		StringWriter sw = new StringWriter()
		this.serializeInto(sw)
		return sw.toString()
	}
}
