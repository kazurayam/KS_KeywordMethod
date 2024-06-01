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

}
