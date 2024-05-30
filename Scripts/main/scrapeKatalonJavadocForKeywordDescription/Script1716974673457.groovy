import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.module.SimpleModule
import com.kazurayam.ks.AUTType
import com.kazurayam.ks.KeywordMethod
import com.kazurayam.ks.KeywordMethodSerializer
import com.kazurayam.ks.KeywordUtils as KU


List<KeywordMethod> list = new ArrayList<>()

list.add(KU.makeListOfKeywordMethodsWithDescription(AUTType.WebUI))
list.add(KU.makeListOfKeywordMethodsWithDescription(AUTType.WS))
list.add(KU.makeListOfKeywordMethodsWithDescription(AUTType.Mobile))
list.add(KU.makeListOfKeywordMethodsWithDescription(AUTType.Windows))

Path javadocFile = Paths.get("docs/javadoc.json")
Files.createDirectories(javadocFile.getParent())

ObjectMapper mapper = new ObjectMapper()
mapper.configure(SerializationFeature.INDENT_OUTPUT, true)
SimpleModule module = new SimpleModule()
module.addSerializer(KeywordMethod.class, new KeywordMethodSerializer())
mapper.registerModule(module)
mapper.writeValue(javadocFile.toFile(), list)
