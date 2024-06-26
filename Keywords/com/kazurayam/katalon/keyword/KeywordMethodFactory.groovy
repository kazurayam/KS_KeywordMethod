package com.kazurayam.katalon.keyword

import java.lang.reflect.Method
import java.lang.reflect.Modifier
import java.util.stream.Collectors

import com.kazurayam.katalon.keyword.TestObjectUtils as TOU
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class KeywordMethodFactory {

	static KeywordMethod getKeywordMethod(Class<?> keywordsClass,
			AUTType autType,
			String keywordGroup,
			String methodName,
			MethodParameters parameters) {
		List<KeywordMethod> list = KeywordMethodFactory.getKeywordMethods(keywordsClass)
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

	/*
	 * generic method that finds all the methods of any class instance
	 */
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

	/**
	 * filter out the Methods which are associated with @Keyword annotation
	 * @param clazz
	 * @return
	 */
	static List<KeywordMethod> getKeywordMethods(Class<?> clazz) {
		List<Method> allMethods = KeywordMethodFactory.getAccessibleMethods(clazz)
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
}
