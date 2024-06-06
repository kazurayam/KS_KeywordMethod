import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import com.kazurayam.katalon.keyword.KeywordBrowser
import com.kms.katalon.core.configuration.RunConfiguration

/**
 * create the Keyword Browser represented as a web page at "projectdir/docs/keywor-browser/kbr.html"
 */
Path projectDir = Paths.get(RunConfiguration.getProjectDir())
Path targetDir = projectDir.resolve("docs/keyword-browser")
Files.createDirectories(targetDir)

KeywordBrowser kbr = new KeywordBrowser()
Path html = kbr.writeHtml(targetDir)
Path css = kbr.writeCss(targetDir)
Path js = kbr.writeJs(targetDir)

println "output: " + html.toString()