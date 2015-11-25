package com.jpmc.problem1;

import com.jpmc.problem1.constants.Constants;
import static com.jpmc.problem1.constants.Constants.EMPTY_STRING;

/**
 * 
 * @author achuth
 *
 */
public class TriangleProblemUtil {
	public static String getSeperatedElementValues(int indexPosition, int value) {
		return getSeperatedValue(indexPosition, Constants.ELEMENT_SEPERATOR) + value;
	}

	public static String getSeperatedLineValue(int lineNumber, int value) {
		return getSeperatedValue(lineNumber, Constants.LINE_SEPERATOR) + value;
	}

	private static String getSeperatedValue(int position, String seperatorString) {
		return position > 0 ? seperatorString : EMPTY_STRING;
	}
}
