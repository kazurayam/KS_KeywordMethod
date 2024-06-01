import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import com.kazurayam.katalon.keyword.AUTType
import com.kazurayam.katalon.keyword.KeywordBook
import com.kms.katalon.core.configuration.RunConfiguration

Path projectDir = Paths.get(RunConfiguration.getProjectDir())
Path json = projectDir.resolve("docs/keywordbook.json")
Files.createDirectories(json.getParent())
if (Files.exists(json)) {
	Files.delete(json)
}

// create a KeywordBook object, serialize it into a JSON file
KeywordBook kb1 = KeywordBook.createKeywordBook()
kb1.serializeInto(json)
assert Files.exists(json)

// deserialize a JSON file to create a KeywordBook object
KeywordBook kb2 = KeywordBook.deserializeFrom(json)
assert kb2.getKeywordMethods(AUTType.Mobile).size() > 0
assert kb2.getKeywordMethods(AUTType.WebUI).size() > 0
assert kb2.getKeywordMethods(AUTType.WS).size() > 0
assert kb2.getKeywordMethods(AUTType.Windows).size() > 0
