package com.kazurayam.ks

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords

/**
 * Application-Under-Test Type
 */
public enum AUTType {

	WebUI(WebUiBuiltInKeywords.class, "webui"),
	WS(WSBuiltInKeywords.class, "webservice"),
	Mobile(MobileBuiltInKeywords.class, "mobile"),
	Windows(WindowsBuiltinKeywords.class, "windows");

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
	private String urlComponent

	AUTType(Class clazz, String urlComponent) {
		this.clazz = clazz
		this.urlComponent = urlComponent
	}

	Class getKeywordClass() {
		return clazz
	}

	String getUrlComponent() {
		return urlComponent
	}

	String getClassName() {
		return clazz.getName()
	}

	String getShortClassName() {
		String longName = getClassName()
		return longName.substring(longName.lastIndexOf(".") + 1)
	}
}
