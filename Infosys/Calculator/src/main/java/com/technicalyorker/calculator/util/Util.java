package com.technicalyorker.calculator.util;

public final class Util {
	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(.\\d+)?");
	}
}
