package com.jpmc.problem1;

import java.util.ArrayList;
import java.util.List;
import static com.jpmc.problem1.constants.Constants.EMPTY_STRING;

/**
 * 
 * @author achuth
 *
 */
public class Cell {
	private int value;
	private List<Cell> adjacentCellsBelow = new ArrayList<Cell>();

	public Cell(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public List<Cell> getAdjacentCellsBelow() {
		return adjacentCellsBelow;
	}

	public void addAdjacentCellsBelow(Cell cell) {
		adjacentCellsBelow.add(cell);
	}

	@Override
	public String toString() {
		return " " + value;
	}

	public String getAdjacentCellsBelowInPrettyPrint() {
		StringBuilder str = new StringBuilder(EMPTY_STRING);
		for (int i = 0; i < adjacentCellsBelow.size(); i++) {
			str.append(TriangleProblemUtil.getSeperatedElementValues(i,
					adjacentCellsBelow.get(i).getValue()));
		}
		return str.toString();
	}
}