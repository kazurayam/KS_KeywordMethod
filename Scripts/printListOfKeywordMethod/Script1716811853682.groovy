
import java.lang.reflect.Method

import com.kazurayam.ks.KeywordMethod
import com.kazurayam.ks.ApplicationUnderTestType
import com.kazurayam.ks.KeywordUtil as KU
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

void printKeywords(ApplicationUnderTestType autType) {
	 List<Method> methods = KU.getAccessibleMethods(autType.getKeywordClass())
	 methods.stream()
			 .map({ Method m-> new KeywordMethod(autType, m) })
			 .filter({ KeywordMethod km -> km.isAnnotatedWithKeyword() })
			.distinct()
			.sorted()
			.each({ KeywordMethod km -> println km.toString() })
}
 
printKeywords(ApplicationUnderTestType.WebUI)
printKeywords(ApplicationUnderTestType.WS)
printKeywords(ApplicationUnderTestType.Mobile)
printKeywords(ApplicationUnderTestType.Windows)
