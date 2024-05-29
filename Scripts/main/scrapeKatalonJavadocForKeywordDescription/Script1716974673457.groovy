import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.module.SimpleModule
import com.kazurayam.ks.AUTType
import com.kazurayam.ks.KeywordMethod
import com.kazurayam.ks.KeywordMethodSerializer
import com.kazurayam.ks.KeywordUtil as KU
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

TestObject makeTestObject(String id, String xpath) {
	TestObject tObj = new TestObject(id)
	tObj.addProperty("xpath", ConditionType.EQUALS, xpath)
	return tObj
}

List<KeywordMethod> getKeywordMethodsWithDescription(AUTType autType) {
	WebUI.comment("********** " + autType.toString() + " **********")
	int MAX_DESCRIPTION_LENGTH = 280  // as Twitter
	List<KeywordMethod> result = new ArrayList<>()
	WebUI.openBrowser(autType.getJavadocUrl())
	List<KeywordMethod> list = KU.getKeywordMethods(autType.getKeywordsClass())
	list.forEach({ KeywordMethod km ->
		String anchorName = km.anchorName()
		WebUI.comment("anchorName: " + anchorName)
		TestObject anchorTO = makeTestObject("anchor", "//a[@name=\"${anchorName}\"]")
		boolean b = WebUI.waitForElementPresent(anchorTO, 3, FailureHandling.OPTIONAL)
		if (b) {
			TestObject descriptionTO = makeTestObject("text", "//a[@name=\"${anchorName}\"]/following-sibling::ul[1]/li[1]/p[1]")
			String description =  WebUI.getText(descriptionTO)
			WebUI.comment("description: " + description)
			if (description != null && description.length() > 0) {
				if (description.length() <= MAX_DESCRIPTION_LENGTH) {
					km.setDescription(description)
				} else {
					km.setDescription(description.substring(0, MAX_DESCRIPTION_LENGTH - 4) + " ...")
				}
			}
		}
		result.add(km)
	})	
	WebUI.closeBrowser()
	return list
}

List<KeywordMethod> list = new ArrayList<>()

list.add(getKeywordMethodsWithDescription(AUTType.WebUI))
list.add(getKeywordMethodsWithDescription(AUTType.WS))
list.add(getKeywordMethodsWithDescription(AUTType.Mobile))
list.add(getKeywordMethodsWithDescription(AUTType.Windows))

Path javadocFile = Paths.get("docs/javadoc.json")
Files.createDirectories(javadocFile.getParent())

ObjectMapper mapper = new ObjectMapper()
mapper.configure(SerializationFeature.INDENT_OUTPUT, true)
SimpleModule module = new SimpleModule()
module.addSerializer(KeywordMethod.class, new KeywordMethodSerializer())
mapper.registerModule(module)
mapper.writeValue(javadocFile.toFile(), list)
