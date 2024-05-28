package com.kazurayam.ks

import java.lang.reflect.Method
import java.lang.reflect.Modifier
import java.util.stream.Collectors

public class KeywordUtil {

	static List<Method> getAccessibleMethods(Class clazz) {
		List<Method> result = new ArrayList<Method>()
		while (clazz != null) {
			for (Method method : clazz.getDeclaredMethods()) {
				int modifiers = method.getModifiers()
				if (Modifier.isPublic(modifiers) || Modifier.isProtected(modifiers)) {
					result.add(method)
				}
			}
			clazz = clazz.getSuperclass()
		}
		return result;
	}
	
	static List<KeywordMethod> getKeywordMethods(Class clazz) {
		List<Method> allMethods = KeywordUtil.getAccessibleMethods(clazz)
		AUTType autType = AUTType.resolve(clazz)
		if (autType == null) {
			throw new RuntimeException("Unable to resolve AUTType of " + clazz.getName())
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
