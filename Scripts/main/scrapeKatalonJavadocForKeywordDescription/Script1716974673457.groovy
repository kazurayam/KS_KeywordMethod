import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import com.kazurayam.katalon.keyword.AUTType
import com.kazurayam.katalon.keyword.KeywordBook
import com.kms.katalon.core.configuration.RunConfiguration

Path projectDir = Paths.get(RunConfiguration.getProjectDir())
Path json = projectDir.resolve("docs/keywordbook-with-javadoc.json")
Files.createDirectories(json.getParent())
if (Files.exists(json)) {
	Files.delete(json)
}

// create a KeywordBook object, serialize it into a JSON file
KeywordBook kb1 = KeywordBook.createKeywordBook()

// inject Javadoc infomration
// Caution; this will take 15 minutes to finish
kb1.injectJavadoc()

// 
kb1.serializeInto(json)
assert Files.exists(json)
