package com.kazurayam.ks

import java.lang.reflect.Method

import com.kms.katalon.core.annotation.Keyword

class KeywordMethod implements Comparable<KeywordMethod> {

	private ApplicationUnderTestType application
	private Method method
	private Keyword keyword = null

	KeywordMethod(ApplicationUnderTestType application, Method method) {
		Objects.requireNonNull(application)
		Objects.requireNonNull(method)
		this.application = application
		this.method = method
		if (method.getDeclaredAnnotation(Keyword.class) != null) {
			keyword = (Keyword)method.getDeclaredAnnotation(Keyword.class)
		}
	}

	boolean isAnnotatedWithKeyword() {
		return keyword != null
	}

	ApplicationUnderTestType application() {
		return this.application
	}

	String methodName() {
		return method.getName()
	}

	String category() {
		return (isAnnotatedWithKeyword()) ? keyword.keywordObject() : ""
	}

	String javadocUrl() {
		StringBuilder sb = new StringBuilder()
		sb.append("https://api-docs.katalon.com/com/kms/katalon/core/")
		sb.append(application().getUrlComponent())
		sb.append("/keyword/")
		sb.append(application().getClassName())
		sb.append(".html")
		sb.append("#")
		sb.append(methodName())
	}

	@Override
	boolean equals(Object obj) {
		if (!(obj instanceof KeywordMethod)) {
			return false
		}
		KeywordMethod other = (KeywordMethod)obj
		// ignore the difference of method signature
		return this.application() == other.application() &&
				this.methodName() == other.methodName() &&
				this.category() == other.category()
	}

	@Override
	int hashCode() {
		int hash = 7;
		hash = 31 * hash + application().hashCode();
		hash = 31 * hash + methodName().hashCode();
		hash = 31 * hash + category().hashCode();
		return hash
	}

	@Override
	String toString() {
		return application().toString() + "," + category() + "," + methodName()
	}

	@Override
	int compareTo(KeywordMethod other) {
		int applicationComparison = this.application().compareTo(other.application())
		if (applicationComparison == 0) {
			int categoryComparison = this.category().compareTo(other.category())
			if (categoryComparison == 0) {
				return this.methodName().compareTo(other.methodName())
			} else {
				return categoryComparison
			}
		} else {
			return applicationComparison
		}
	}
}
