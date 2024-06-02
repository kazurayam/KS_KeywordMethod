package com.kazurayam.katalon.keyword

import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

import com.kms.katalon.core.configuration.RunConfiguration

public class JavadocRetriever {

	private Map<AUTType, Document> cache = new HashMap<>()

	public JavadocRetriever() throws IOException {
		cache.put(AUTType.WebUI, loadJavadoc(AUTType.WebUI))
		cache.put(AUTType.WS, loadJavadoc(AUTType.WS))
		cache.put(AUTType.Mobile, loadJavadoc(AUTType.Mobile))
		cache.put(AUTType.Windows, loadJavadoc(AUTType.Windows))
	}

	public static final Path getKatalonInstallationDirectory() {
		Path coreJar = getCodeSourcePathOf(RunConfiguration.class)
		// /Applications/Katalon Studio.app/Contents/Eclipse/plugins/com.kms.katalon.core_1.0.0.202310232352.jar
		Path plugins = coreJar.getParent()
		Path eclipse = plugins.getParent()
		Path contents = eclipse.getParent()
		Path installation = contents.getParent()
		// /Applications/Katalon Studio.app
		return installation
	}

	public static final Path getCodeSourcePathOf(Class<?> clazz) {
		CodeSource codeSource = clazz.getProtectionDomain().getCodeSource();
		URL url = codeSource.getLocation();
		try {
			return Paths.get(url.toURI());
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	public static final String subPathApiDocs = "Contents/Eclipse/configuration/resources/apidocs"

	public static final Path resolveBundledJavadoc(AUTType autType) {
		return getKatalonInstallationDirectory()					// /Applications/Katalon Studio.app/
				.resolve(subPathApiDocs)						// Contents/Eclipse/configuration/resources/apidocs/
				.resolve(autType.getJavadocUrlComponent0())		// apidocs/com.kms.katalon.core.webui/
				.resolve(autType.getJavadocUrlComponent1())		// com/kms/katalon/core/webui/keyword/WebUiBuiltInKeywords.html
	}

	public static final Document loadJavadoc(AUTType autType) throws IOException {
		return Jsoup.parse(resolveBundledJavadoc(autType).toFile(), "utf-8")
	}

	public String selectDescriptionOf(KeywordMethod km) {
		Document doc = cache.get(km.autType())
		Elements elements = doc.selectXpath(makeXPath(km))
		if (elements != null && elements.first() != null) {
			return elements.first().text()
		} else {
			return ""
		}
	}

	static String makeXPath(KeywordMethod km) {
		return "//a[@name=\"${km.anchorName()}\"]/following-sibling::ul[1]/li[1]/p[1]"
	}
}
