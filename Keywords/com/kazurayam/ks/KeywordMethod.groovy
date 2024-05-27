package com.kazurayam.ks

import java.lang.reflect.Method

import com.kms.katalon.core.annotation.Keyword

class KeywordMethod implements Comparable<KeywordMethod> {

	private String application
	private Method method
	private Keyword keyword = null

	KeywordMethod(String application, Method method) {
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

	String application() {
		return this.application
	}

	String methodName() {
		return method.getName()
	}

	String category() {
		return (isAnnotatedWithKeyword()) ? keyword.keywordObject() : ""
	}

	@Override
	boolean equals(Object obj) {
		if (!(obj instanceof KeywordMethod)) {
			return false
		}
		KeywordMethod other = (KeywordMethod)obj
		// ignore the difference of method signagure
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
		return application() + "," + category() + "," + methodName()
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
