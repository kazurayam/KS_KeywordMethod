package com.kazurayam.katalon.keyword

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords

/**
 * Application-Under-Test Type
 */
public enum AUTType {

	WebUI(WebUiBuiltInKeywords.class, "https://api-docs.katalon.com/com/kms/katalon/core/webui/keyword/WebUiBuiltInKeywords.html"),
	WS(WSBuiltInKeywords.class, "https://api-docs.katalon.com/com/kms/katalon/core/webservice/keyword/WSBuiltInKeywords.html"),
	Mobile(MobileBuiltInKeywords.class, "https://api-docs.katalon.com/com/kms/katalon/core/mobile/keyword/MobileBuiltInKeywords.html"),
	Windows(WindowsBuiltinKeywords.class, "https://api-docs.katalon.com/com/kms/katalon/core/windows/keyword/WindowsBuiltinKeywords.html");

	static AUTType resolve(Class clazz) {
		if (clazz.getName() == MobileBuiltInKeywords.getName()) {
			return AUTType.Mobile
		} else if (clazz.getName() == WSBuiltInKeywords.getName()) {
			return AUTType.WS
		} else if (clazz.getName() == WebUiBuiltInKeywords.getName()) {
			return AUTType.WebUI
		} else if (clazz.getName() == WindowsBuiltinKeywords.getName()) {
			return AUTType.Windows
		} else {
			throw new IllegalArgumentException("Unable to resolve AUTType of " + clazz.getName())
		}
	}

	private Class clazz
	private String javadocUrl

	AUTType(Class clazz, String javadocUrl) {
		this.clazz = clazz
		this.javadocUrl = javadocUrl
	}

	Class getKeywordsClass() {
		return clazz
	}

	String getJavadocUrl() {
		return javadocUrl
	}

	String getClassName() {
		return clazz.getName()
	}

	String getShortClassName() {
		String longName = getClassName()
		return longName.substring(longName.lastIndexOf(".") + 1)
	}
}
