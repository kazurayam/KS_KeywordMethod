// Test Cases/generateDocLinkedToJavadoc

import com.kazurayam.katalon.keyword.AUTType
import com.kazurayam.katalon.keyword.KeywordMethod
import com.kazurayam.katalon.keyword.KeywordMethodFactory as KMF

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import static org.apache.commons.lang.StringEscapeUtils.escapeHtml

void listKeywords(AUTType autType, StringBuilder sb) {
	 List<KeywordMethod> methods = KMF.getKeywordMethods(autType.getKeywordsClass())
	 methods.stream()
		 .each({ KeywordMethod km ->
			 sb.append("<li><span class=\"autType\">")
			 sb.append(km.autType().toString());
			 sb.append("</span><span class=\"keywordGroup\">")
			 sb.append(km.keywordGroup())
			 sb.append("</span><span class=\"keywordName\">")
			 sb.append(km.methodName())
			 sb.append("<span><span class=\"description\">")
			 sb.append(escapeHtml(km.description()))
			 sb.append("</span>")
			 sb.append("</li>")
			 sb.append("\n")
		})
}

StringBuilder sb = new StringBuilder()
sb.append("<!doctype html>\n")
sb.append("<html>\n")
sb.append("<head>\n")
sb.append("<meta charset=\"utf-8\">\n")
sb.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n")
sb.append("<title>Katalon Studio Built-in Keywords</title>\n")
sb.append("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM\" crossorigin=\"anonymous\">\n")
sb.append('''<style type=\"text/css\">
.autType, .keywordGroup, keywordName {
    margin-right: 1em;
}
</style>\n''')
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
sb.append("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz\" crossorigin=\"anonymous\"></script>\n")
sb.append("</body>\n")
sb.append("</html>\n")

println sb.toString()

Path dir = Paths.get("./docs")
Files.createDirectories(dir)
Path file = dir.resolve("keywords-with-description.html")

Files.writeString(file, sb.toString())




