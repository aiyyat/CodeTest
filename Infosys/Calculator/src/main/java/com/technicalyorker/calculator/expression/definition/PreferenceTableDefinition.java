package com.technicalyorker.calculator.expression.definition;

import java.util.Hashtable;
import java.util.Map;

public class PreferenceTableDefinition {
	static final Map<String, Integer> preferenceTable = new Hashtable<>();
	static {
		preferenceTable.put("+", 1);
		preferenceTable.put("-", 1);
		preferenceTable.put("*", 2);
		preferenceTable.put("/", 2);
		preferenceTable.put("Sine", 3);
		preferenceTable.put("Tan", 3);
	}

	public static Map<String, Integer> getPreferenceDefinition() {
		return preferenceTable;
	}
}
