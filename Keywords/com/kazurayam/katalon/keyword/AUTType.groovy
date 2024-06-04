package com.kazurayam.katalon.keyword

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords

/**
 * Application-Under-Test Type
 */
public enum AUTType {

	WebUI(WebUiBuiltInKeywords.class,
	"com.kms.katalon.core.webui",
	"com/kms/katalon/core/webui/keyword/WebUiBuiltInKeywords.html"),
	WS(WSBuiltInKeywords.class,
	"com.kms.katalon.core.webservice",
	"com/kms/katalon/core/webservice/keyword/WSBuiltInKeywords.html"),
	Mobile(MobileBuiltInKeywords.class,
	"com.kms.katalon.core.mobile",
	"com/kms/katalon/core/mobile/keyword/MobileBuiltInKeywords.html"),
	Windows(WindowsBuiltinKeywords.class,
	"com.kms.katalon.core.windows",
	"com/kms/katalon/core/windows/keyword/WindowsBuiltinKeywords.html");

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

	private final String httpHost = "https://api-docs.katalon.com/"

	private int order
	private Class clazz
	private String javadocUrlComponent0
	private String javadocUrlComponent1

	AUTType(Class clazz, String javadocUrlComponent0, String javadocUrlComponent1) {
		this.clazz = clazz
		this.javadocUrlComponent0 = javadocUrlComponent0
		this.javadocUrlComponent1 = javadocUrlComponent1
	}

	Class getKeywordsClass() {
		return clazz
	}

	String getJavadocUrlComponent0() {
		return javadocUrlComponent0
	}

	String getJavadocUrlComponent1() {
		return javadocUrlComponent1
	}

	String getJavadocUrl() {
		return httpHost + javadocUrlComponent1
	}

	String getClassName() {
		return clazz.getName()
	}

	String getShortClassName() {
		String longName = getClassName()
		return longName.substring(longName.lastIndexOf(".") + 1)
	}
}
