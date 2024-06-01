// Test Cases/printListOfKeywordMethod

import com.kazurayam.katalon.keyword.AUTType
import com.kazurayam.katalon.keyword.KeywordMethod
import com.kazurayam.katalon.keyword.KeywordMethodFactory as KMF
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

void printKeywords(AUTType autType, BufferedWriter bw) {
	 List<KeywordMethod> methods = KMF.getKeywordMethods(autType.getKeywordsClass())
	 methods.stream()
	 	.each({ KeywordMethod km -> bw.println(km.toString()) })
}

Path output = Paths.get("docs/keywordsList.txt")
Files.createDirectories(output.getParent())
BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output.toFile()), "utf-8"))

printKeywords(AUTType.WebUI, bw)
printKeywords(AUTType.WS, bw)
printKeywords(AUTType.Mobile, bw)
printKeywords(AUTType.Windows, bw)

bw.flush()
bw.close()

Files.readAllLines(output).each { println it; }