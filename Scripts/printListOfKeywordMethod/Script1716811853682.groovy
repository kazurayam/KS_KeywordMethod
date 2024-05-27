
import java.lang.reflect.Method
import java.lang.reflect.Modifier

import com.kazurayam.ks.KeywordMethod
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

List<Method> getAccessibleMethods(Class clazz) {
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

void printKeywords(String application, Class clazz) {
	 List<Method> methods = getAccessibleMethods(clazz)
	 methods.stream()
			 .map({ Method m-> new KeywordMethod(application, m) })
			 .filter({ KeywordMethod km -> km.isAnnotatedWithKeyword() })
			.distinct()
			.sorted()
			.each({ KeywordMethod km -> println km.toString() })
}
 
printKeywords("WebUI", WebUI.class)
 
printKeywords("WS", WS.class)
 
printKeywords("Mobile", Mobile.class)
 
printKeywords("Windows", Windows.class)
