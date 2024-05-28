// Test Cases/generateListOfKeywordMethodHTML

import com.kazurayam.ks.AUTType
import com.kazurayam.ks.KeywordMethod
import com.kazurayam.ks.KeywordUtil as KU

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

void listKeywords(AUTType autType, StringBuilder sb) {
	 List<KeywordMethod> methods = KU.getKeywordMethods(autType.getKeywordClass())
	 methods.stream()
		 .each({ KeywordMethod km ->
			 sb.append("<li>")
			 sb.append(km.autType().toString());
			 sb.append(" ")
			 sb.append(km.keywordGroup())
			 sb.append(" ")
			 sb.append(km.methodName())
			 sb.append(" ")
			 sb.append("<a href=\"")
			 sb.append(km.javadocUrl())
			 sb.append("\">javadoc</a>")
			 sb.append("</li>")
			 sb.append("\n")
			 })
}

StringBuilder sb = new StringBuilder()
sb.append("<!doctype html>\n")
sb.append("<html>\n")
sb.append("<head>\n")
sb.append("<meta charset=\"utf-8\">\n")
sb.append("<title>Katalon Studio Built-in Keywords</title>\n")
sb.append("</head>\n")
sb.append("<body>\n")
sb.append("<div id=\"wrapper\">\n")
sb.append("<h1>Katalon Studio Built-in Keywords</h1>\n")

sb.append("<h3>WebUI</h3>")
sb.append("<ul>\n")
listKeywords(AUTType.WebUI, sb)
sb.append("</ul>\n")

sb.append("<h3>WS</h3>")
sb.append("<ul>\n")
listKeywords(AUTType.WS, sb)
sb.append("</ul>\n")

sb.append("<h3>Mobile</h3>")
sb.append("<ul>\n")
listKeywords(AUTType.Mobile, sb)
sb.append("</ul>\n")

sb.append("<h3>Windows</h3>")
sb.append("<ul>\n")
listKeywords(AUTType.Windows, sb)
sb.append("</ul>\n")

sb.append("</div>\n")
sb.append("</body>\n")
sb.append("</html>\n")

//println sb.toString()

Path dir = Paths.get("./build")
Files.createDirectories(dir)
Path file = dir.resolve("keywordList.html")

Files.writeString(file, sb.toString())



