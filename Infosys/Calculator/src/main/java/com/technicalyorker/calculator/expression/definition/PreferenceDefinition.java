package com.technicalyorker.calculator.expression.definition;

import java.util.Hashtable;
import java.util.Map;

public class PreferenceDefinition {
	static final Map<String, Integer> preferenceTable = new Hashtable<>();
	static {
		preferenceTable.put("+", 1);
		preferenceTable.put("-", 1);
		preferenceTable.put("*", 2);
		preferenceTable.put("/", 2);
		preferenceTable.put("Sine", 3);
		preferenceTable.put("Tan", 3);
	}

	public static Map<String, Integer> getDefinition() {
		return preferenceTable;
	}

	public static boolean higerPref(String ch1, String ch2) {
		return getDefinition().containsKey(ch2)
				&& (getDefinition().get(ch2).compareTo(getDefinition().get(ch1)) >= 0);
	}
}
