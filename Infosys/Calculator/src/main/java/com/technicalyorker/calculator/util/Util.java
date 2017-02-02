package com.technicalyorker.calculator.util;

/**
 * 
 * @author achuth
 *
 */
public final class Util {
	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(.\\d+)?");
	}
}
