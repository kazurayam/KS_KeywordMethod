package com.kazurayam.katalon.keyword

import java.nio.file.Path

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.module.SimpleModule
import com.kazurayam.katalon.keyword.KeywordMethodFactory as KU
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords

public class KeywordBook {

	private Map<AUTType, List<KeywordMethod>> collection

	static KeywordBook createKeywordBook() {
		KeywordBook kb = new KeywordBook()
		kb.setKeywordMethods(AUTType.WebUI, KeywordMethodFactory.getKeywordMethods(WebUiBuiltInKeywords.class))
		kb.setKeywordMethods(AUTType.WS, KeywordMethodFactory.getKeywordMethods(WSBuiltInKeywords.class))
		kb.setKeywordMethods(AUTType.Mobile, KeywordMethodFactory.getKeywordMethods(MobileBuiltInKeywords.class))
		kb.setKeywordMethods(AUTType.Windows, KeywordMethodFactory.getKeywordMethods(WindowsBuiltinKeywords.class))
		return kb
	}

	public KeywordBook() {
		this.collection = new HashMap<>()
	}

	public void setKeywordMethods(AUTType autType, List<KeywordMethod> list) {
		collection.put(autType, list)
	}

	public void setKeywordMethod(AUTType autType, KeywordMethod keywordMethod) {
		if (collection.keySet().contains(autType)) {
			// the key AUTType is found
			List<KeywordMethod> list = collection.getAt(autType)
			int index = list.indexOf(keywordMethod)
			if (index >= 0) {
				// the given KeywordMethod object is found in the collection,
				// so replace the entry with the given one
				list.set(index, keywordMethod)
			} else {
				// the given KeywordMethod object is a new one,
				// so append it into the list
				list.add(keywordMethod)
			}
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

	public int sizeOfKeywordMethods(AUTType autType) {
		return collection.get(autType).size()
	}

	public KeywordMethod getKeywordMethod(AUTType autType, int index) {
		List<KeywordBook> list = collection.get(autType)
		return list.get(index)
	}

	public KeywordMethod getKeywordMethod(AUTType autType, KeywordMethod base) {
		List<KeywordBook> list = collection.get(autType)
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == base) {
				return list.get(i)
			}
		}
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

	/**
	 * Read the Katalon Javadoc outside the project, extract the information to
	 * inject into the KeywordMethod objects.
	 * Caution: this method will take rather long time: possible 10 minutes to finish.
	 */
	public void injectJavadoc() {
		JavadocRetriever jr = new JavadocRetriever()
		this.usingRetrieverInjectJavadocIntoKeywordMethodsOf(jr, AUTType.WebUI)
		this.usingRetrieverInjectJavadocIntoKeywordMethodsOf(jr, AUTType.WS)
		this.usingRetrieverInjectJavadocIntoKeywordMethodsOf(jr, AUTType.Mobile)
		this.usingRetrieverInjectJavadocIntoKeywordMethodsOf(jr, AUTType.Windows)
	}
	private void usingRetrieverInjectJavadocIntoKeywordMethodsOf(JavadocRetriever jr, AUTType autType) {
		//WebUiBuiltInKeywords.comment("********** " + autType.toString() + " **********")
		List<KeywordMethod> result = new ArrayList<>()
		List<KeywordMethod> list = KU.getKeywordMethods(autType.getKeywordsClass())
		list.forEach({ KeywordMethod km ->
			//WebUiBuiltInKeywords.comment("anchorName: " + km.anchorName())
			String description = jr.selectDescriptionOf(km) ?: ""
			//WebUiBuiltInKeywords.comment("description: " + description)
			if (description != null && description.length() > 0) {
				KeywordMethod target = this.getKeywordMethod(autType, km)
				if (target.description() == null || target.description().length() == 0) {
					target.setDescription(description)
				} else {
					if (target.description().length() < description) {
						target.setDescription(description)
					}
					// else --- the description in the javadoc is empty, ignore it
				}
			}
			result.add(km)
		})
	}

	@Override
	public String toString() {
		StringWriter sw = new StringWriter()
		this.serializeInto(sw)
		return sw.toString()
	}
}
