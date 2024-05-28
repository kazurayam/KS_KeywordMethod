// Test Cases/printListOfKeywordMethod

import com.kazurayam.ks.AUTType
import com.kazurayam.ks.KeywordMethod
import com.kazurayam.ks.KeywordUtil as KU

void printKeywords(AUTType autType) {
	 List<KeywordMethod> methods = KU.getKeywordMethods(autType.getKeywordClass())
	 methods.stream()
	 	.each({ KeywordMethod km -> println km.toString() })
}
 
printKeywords(AUTType.WebUI)
printKeywords(AUTType.WS)
printKeywords(AUTType.Mobile)
printKeywords(AUTType.Windows)
