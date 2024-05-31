package com.kazurayam.ks

import java.lang.reflect.Method
import java.lang.reflect.Modifier
import java.util.stream.Collectors

import com.kazurayam.ks.TestObjectUtils as TOU
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class KeywordUtils {

	static KeywordMethod getKeywordMethod(Class<?> clazz,
			AUTType autType,
			String keywordGroup,
			String methodName,
			MethodParameters parameters) {
		List<KeywordMethod> list = KeywordUtils.getKeywordMethods(clazz)
		for (KeywordMethod km : list) {
			if (km.autType() == autType &&
					km.keywordGroup() == keywordGroup &&
					km.methodName() == methodName &&
					km.getMethodParameters() == parameters) {
				return km
			}
		}
		return null
	}

	static List<Method> getAccessibleMethods(Class<?> clazz) {
		List<Method> result = new ArrayList<Method>()
		while (clazz != null) {
			for (Method method : clazz.getDeclaredMethods()) {
				int modifiers = method.getModifiers()
				if (Modifier.isPublic(modifiers) ||
						Modifier.isProtected(modifiers)) {
					result.add(method)
				}
			}
			clazz = clazz.getSuperclass()
		}
		return result;
	}

	static List<KeywordMethod> getKeywordMethods(Class<?> clazz) {
		List<Method> allMethods = KeywordUtils.getAccessibleMethods(clazz)
		AUTType autType = AUTType.resolve(clazz)
		if (autType == null) {
			throw new RuntimeException("Unable to resolve AUTType of " +
			clazz.getName())
		}
		List<KeywordMethod> keywordMethods = allMethods.stream()
				.map({ Method m -> new KeywordMethod(autType, m) })
				.filter({ KeywordMethod km -> km.isAnnotatedWithKeyword() })
				.distinct()
				.sorted()
				.collect(Collectors.toList())
		return keywordMethods
	}


	/**
	 * Using the Selenium WebDriver, opens the JavaDoc URL of Katalon API, 
	 * scrape it to extract the Keyword description,
	 * returns a list of KeywordMethod objects injected 
	 * with the description text.
	 * 
	 * This will take long minutes to finish.
	 * 
	 * @param autType
	 * @return List of KeywordMethd objects that belongs to the AUTType
	 */
	static List<KeywordMethod> makeListOfKeywordMethodsWithDescription(AUTType autType) {
		WebUI.comment("********** " + autType.toString() + " **********")
		int MAX_DESCRIPTION_LENGTH = 280  // as Twitter
		List<KeywordMethod> result = new ArrayList<>()
		WebUI.openBrowser(autType.getJavadocUrl())
		List<KeywordMethod> list = KeywordUtils.getKeywordMethods(autType.getKeywordsClass())
		list.forEach({ KeywordMethod km ->
			String anchorName = km.anchorName()
			WebUI.comment("anchorName: " + anchorName)
			TestObject anchorTO = TOU.makeTestObject("anchor", "//a[@name=\"${anchorName}\"]")
			boolean b = WebUI.waitForElementPresent(anchorTO, 3, FailureHandling.OPTIONAL)
			if (b) {
				TestObject descriptionTO = TOU.makeTestObject("text", "//a[@name=\"${anchorName}\"]/following-sibling::ul[1]/li[1]/p[1]")
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
}
