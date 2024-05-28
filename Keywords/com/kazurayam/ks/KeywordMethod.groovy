package com.kazurayam.ks

import java.lang.reflect.Method

import com.kms.katalon.core.annotation.Keyword

class KeywordMethod implements Comparable<KeywordMethod> {

	private AUTType autType
	private Method method
	private Keyword keyword = null

	KeywordMethod(AUTType autType, Method method) {
		Objects.requireNonNull(autType)
		Objects.requireNonNull(method)
		this.autType = autType
		this.method = method
		if (method.getDeclaredAnnotation(Keyword.class) != null) {
			keyword = (Keyword)method.getDeclaredAnnotation(Keyword.class)
		}
	}

	boolean isAnnotatedWithKeyword() {
		return keyword != null
	}

	AUTType autType() {
		return this.autType
	}

	String methodName() {
		return method.getName()
	}

	String keywordGroup() {
		return (isAnnotatedWithKeyword()) ? keyword.keywordObject() : ""
	}

	String javadocUrl() {
		StringBuilder sb = new StringBuilder()
		sb.append("https://api-docs.katalon.com/com/kms/katalon/core/")
		sb.append(autType().getUrlComponent())
		sb.append("/keyword/")
		sb.append(autType().getShortClassName())
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
		return this.autType() == other.autType() &&
				this.keywordGroup() == other.keywordGroup() &&
				this.methodName() == other.methodName()
	}

	@Override
	int hashCode() {
		int hash = 7;
		hash = 31 * hash + autType().hashCode();
		hash = 31 * hash + methodName().hashCode();
		hash = 31 * hash + keywordGroup().hashCode();
		return hash
	}

	@Override
	String toString() {
		return autType().toString() + "," + keywordGroup() + "," + methodName()
	}

	@Override
	int compareTo(KeywordMethod other) {
		int autTypeComparison = this.autType().compareTo(other.autType())
		if (autTypeComparison == 0) {
			int keywordGroupComparison = this.keywordGroup().compareTo(other.keywordGroup())
			if (keywordGroupComparison == 0) {
				return this.methodName().compareTo(other.methodName())
			} else {
				return keywordGroupComparison
			}
		} else {
			return autTypeComparison
		}
	}
}
