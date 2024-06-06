package com.kazurayam.katalon.keyword

import java.lang.reflect.Method
import java.util.stream.Collectors

import com.kms.katalon.core.annotation.Keyword


class KeywordMethod implements Comparable<KeywordMethod> {

	private AUTType autType
	private Method method
	private Keyword keyword = null
	private String description

	KeywordMethod(AUTType autType, Method method) {
		Objects.requireNonNull(autType)
		Objects.requireNonNull(method)
		this.autType = autType
		this.method = method
		if (method.getDeclaredAnnotation(Keyword.class) != null) {
			this.keyword = (Keyword)method.getDeclaredAnnotation(Keyword.class)
		}
		this.description = ""
	}

	KeywordMethod(String autTypeStr, String groupStr, String methodNameStr, String methodParametersStr, String descriptionStr) {
		List<KeywordMethod> kmList = KeywordMethodFactory.getKeywordMethods(AUTType.valueOf(autTypeStr).getKeywordsClass())
		List<KeywordMethod> filtered =
				kmList.stream()
				.filter { km -> km.keywordGroup() == groupStr }
				.filter { km -> km.methodName() == methodNameStr }
				.filter { km -> km.getMethodParameters().toString() == methodParametersStr }
				.collect(Collectors.toList())
		if (filtered.size() == 1) {
			KeywordMethod km = filtered.get(0)
			this.autType = km.autType()
			this.method = km.method()
			if (method.getDeclaredAnnotation(Keyword.class) != null) {
				this.keyword = (Keyword)method.getDeclaredAnnotation(Keyword.class)
			}
			this.description = descriptionStr
		} else {
			throw new IllegalArgumentException(
			"autType=\"${autTypeStr}\", group=\"${groupStr}\", methodName=\"${methodNameStr}\", methodParameters=\"${methodParametersStr}\", description=\"${descriptionStr}\"")
		}
	}

	boolean isAnnotatedWithKeyword() {
		return keyword != null
	}

	AUTType autType() {
		return this.autType
	}

	String keywordGroup() {
		return (isAnnotatedWithKeyword()) ? keyword.keywordObject() : ""
	}

	Method method() {
		return this.method
	}

	String methodName() {
		return method.getName()
	}

	MethodParameters getMethodParameters() {
		Class<?>[] parameterTypes = method.getParameterTypes()
		List<Class<?>> list = new ArrayList<>()
		for (Class<?> cls : parameterTypes) {
			list.add(cls)
		}
		return new MethodParameters(list)
	}

	/**
	 * 
	 * @return e.g. "authenticate(java.lang.String, java.lang.String, java.lang.String, int, com.kms.katalon.core.model.FailureHandling)"
	 */
	String fragment() {
		return methodName() + getMethodParameters().toString().replaceAll("\\s", "%20")
	}

	String anchorName() {
		return methodName() + getMethodParameters().toString()
	}

	String javadocUrl() {
		StringBuilder sb = new StringBuilder()
		sb.append(autType().getHttpJavadocUrl())
		sb.append("#")
		sb.append(fragment())
	}

	String description() {
		return this.description
	}

	void setDescription(String description) {
		this.description = description
	}

	@Override
	boolean equals(Object obj) {
		if (!(obj instanceof KeywordMethod)) {
			return false
		}
		KeywordMethod other = (KeywordMethod)obj
		// ignore the difference of method signature
		return  this.autType() == other.autType() &&
				this.keywordGroup() == other.keywordGroup() &&
				this.methodName() == other.methodName() &&
				this.getMethodParameters() == other.getMethodParameters()
	}

	@Override
	int hashCode() {
		int hash = 7;
		hash = 31 * hash + autType().hashCode();
		hash = 31 * hash + keywordGroup().hashCode();
		hash = 31 * hash + methodName().hashCode();
		hash = 31 * hash + getMethodParameters().hashCode();
		return hash
	}

	@Override
	String toString() {
		String s = "${autType().toString()}, ${keywordGroup()}, ${methodName()}${getMethodParameters().toString()}"
		if (description() != null && description().length() > 0) {
			s += ", \"${description()}\""
		}
		return s
	}

	@Override
	int compareTo(KeywordMethod other) {
		int autTypeComparison = this.autType().compareTo(other.autType())
		if (autTypeComparison == 0) {
			int keywordGroupComparison = this.keywordGroup().compareTo(other.keywordGroup())
			if (keywordGroupComparison == 0) {
				int methodNameComparison = this.methodName().compareTo(other.methodName())
				if (methodNameComparison == 0) {
					int signatureComparison = this.getMethodParameters().compareTo(other.getMethodParameters())
					return signatureComparison
				} else {
					return methodNameComparison
				}
			} else {
				return keywordGroupComparison
			}
		} else {
			return autTypeComparison
		}
	}
}
