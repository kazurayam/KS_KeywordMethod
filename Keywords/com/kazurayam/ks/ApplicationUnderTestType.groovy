package com.kazurayam.ks

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords

public enum ApplicationUnderTestType {
	
	WebUI(WebUiBuiltInKeywords.class, "webui"),
	WS(WSBuiltInKeywords.class, "webservice"),
	Mobile(MobileBuiltInKeywords.class, "mobile"),
	Windows(WindowsBuiltinKeywords.class, "windows");
	
	private Class clazz
	private String urlComponent
	
	ApplicationUnderTestType(Class clazz, String urlComponent) {
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
		return longName.substring(longName.lastIndexOf("."))
	}
}
