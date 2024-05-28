package com.kazurayam.ks

import java.lang.reflect.Method
import java.lang.reflect.Modifier

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
	
}
