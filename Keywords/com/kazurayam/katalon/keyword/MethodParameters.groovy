package com.kazurayam.katalon.keyword

/**
 * method signature, a sequence of Class<?> as parameters to a method
 */
public class MethodParameters implements Iterable<Class<?>>, Comparable<MethodParameters> {

	List<Class<?>> parameterTypes

	public MethodParameters(List<Class<?>> parameterTypes) {
		this.parameterTypes = parameterTypes
	}

	@Override
	boolean equals(Object obj) {
		if (!(obj instanceof MethodParameters)) {
			return false
		}
		MethodParameters other = (MethodParameters)obj
		for (int i = 0; i < this.parameterTypes.size(); i++) {
			if (this.parameterTypes.get(i).getName() != other.parameterTypes.get(i).getName()) {
				return false
			}
		}
		return true
	}

	@Override
	int hashCode() {
		int hash = 7;
		this.parameterTypes.each { Class<?> cls->
			hash = 31 * hash + cls.hashCode();
		}
		return hash
	}

	/**
	 * @returns e.g. "(java.lang.String, java.lang.Interger)"
	 */
	@Override
	String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(")
		int count = 0
		this.parameterTypes.each { Class<?> cls ->
			if (count > 0) {
				sb.append(", ")
			}
			sb.append(cls.getName())
			count += 1
		}
		sb.append(")")
		return sb.toString()
	}

	@Override
	Iterator<Class<?>> iterator() {
		return parameterTypes.iterator()
	}

	@Override
	int compareTo(MethodParameters other) {
		int sizeDelta = other.parameterTypes.size() - this.parameterTypes.size()
		if (sizeDelta == 0) {
			for (int i = 0; i < this.parameterTypes.size(); i++) {
				Class<?> cls1 = this.parameterTypes.get(i)
				Class<?> cls2 = other.parameterTypes.get(i)
				int clsComparison = cls1.getName().compareTo(cls2.getName())
				if (clsComparison != 0) {
					return clsComparison
				}
			}
			return 0
		} else {
			return sizeDelta
		}
	}
}