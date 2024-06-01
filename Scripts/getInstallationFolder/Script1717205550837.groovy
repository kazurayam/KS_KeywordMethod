// getInstallationFolder

import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;

import com.kms.katalon.core.configuration.RunConfiguration

public Path getCodeSourcePathOf(Class<?> clazz) {
	CodeSource codeSource = clazz.getProtectionDomain().getCodeSource();
	URL url = codeSource.getLocation();
	try {
		return Paths.get(url.toURI());
	} catch (URISyntaxException e) {
		throw new RuntimeException(e);
	}
}

Path jarPath = getCodeSourcePathOf(RunConfiguration.class)
println jarPath.toString()

Path pluginsPath = jarPath.getParent()
Path eclipsePath = pluginsPath.getParent()
Path contentsPath = eclipsePath.getParent()
Path installationFolderPath = contentsPath.getParent()

println installationFolderPath   // on Mac, "/Applications/Katalon Studio.app"
