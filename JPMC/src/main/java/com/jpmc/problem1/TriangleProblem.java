package com.jpmc.problem1;

import static com.jpmc.problem1.constants.Constants.ELEMENT_SEPERATOR;
import static com.jpmc.problem1.constants.Constants.EMPTY_STRING;
import static com.jpmc.problem1.constants.Constants.LINE_SEPERATOR;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Definitions of Terms, I used in this problem:
 * 
 * 1) Element: In a Triangle 
 *    1 
 *   2 3 
 *  4 5 6
 * 
 * 1,2,3,4,5,6 are all 'elements'.
 * 
 * 
 * 2) Cell: A Cell Encapsulates an element. It is thus aware of element's value
 * and the Adjacent cells below that are related to it.
 * 
 * 3) Abbreviations: Ele: Element Idx: Index Adj: Adjacent
 * 
 * @author achuth
 *
 */
public class TriangleProblem {
	private Cell[][] cells;
	private Integer max = Integer.MIN_VALUE;

	public TriangleProblem(InputStream is) {
		try {
			int ch = -1;
			final StringBuilder inputStr = new StringBuilder(EMPTY_STRING);
			while (-1 != (ch = is.read())) {
				inputStr.append((char) ch);
			}
			formCellTriangleAsArrays(inputStr.toString());
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	public TriangleProblem(String input) {
		formCellTriangleAsArrays(input);
	}

	private void formCellTriangleAsArrays(String inputText) {
		String[] lines = inputText.split(LINE_SEPERATOR);
		cells = new Cell[lines.length][];
		for (int rowIdx = 0; rowIdx < lines.length; rowIdx++) {
			String[] elements = lines[rowIdx].split(ELEMENT_SEPERATOR);
			cells[rowIdx] = new Cell[elements.length];
			if (elements.length != rowIdx + 1) {
				throw new IllegalArgumentException("Elements in line " + rowIdx
						+ ", Expected: " + (rowIdx + 1) + " Actual: "
						+ elements.length);
			}
			for (Integer cellIdx = 0; cellIdx < elements.length; cellIdx++) {
				try {
					Integer elementVal = new Integer(elements[cellIdx]);
					cells[rowIdx][cellIdx] = new Cell(elementVal);
				} catch (NumberFormatException e) {
					throw new IllegalArgumentException("Illegal Argument '"
							+ elements[cellIdx] + "' found in line number " + (rowIdx + 1)
							+ " of input");
				}
				if (rowIdx > 0) {
					assignParentAssocitationsForCellAt(rowIdx, cellIdx);
				}
			}
		}
	}

	private void assignParentAssocitationsForCellAt(int rowIdx, int cellIdx) {
		for (int adjCellIdx = cellIdx - 1; adjCellIdx < cellIdx + 1; adjCellIdx++) {
			if (adjCellIdx > -1 && adjCellIdx < cells[rowIdx - 1].length) {
				cells[rowIdx - 1][adjCellIdx]
						.addAdjacentCellsBelow(cells[rowIdx][cellIdx]);
			}
		}
	}

	public Integer getMaxSum() {
		getMaxSum(cells[0][0].getValue(), cells[0][0]);
		return max;
	}

	private void getMaxSum(Integer sumTillNow, Cell referencedCell) {
		List<Cell> adjCellsBelow = referencedCell.getAdjacentCellsBelow();
		if (0 == adjCellsBelow.size()) {
			if (max < sumTillNow) {
				max = sumTillNow;
			}
			return;
		}
		for (Cell cell : adjCellsBelow) {
			getMaxSum(sumTillNow + cell.getValue(), cell);
		}
	}

	public Cell[][] getCells() {
		return cells;
	}

	public String toString() {
		StringBuilder str = new StringBuilder(EMPTY_STRING);
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				str.append(TriangleProblemUtil.getSeperatedElementValues(j,
						cells[i][j].getValue()));
			}
			str.append(LINE_SEPERATOR);
		}
		return str.toString();
	}
}
