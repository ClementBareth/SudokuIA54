package fr.utbm.sudoku.model;

import java.util.ArrayList;
import java.util.List;

import org.dizitart.no2.objects.Id;

public class Sudoku {

	private Integer[][] matrix = new Integer[9][9];
	private Difficulty difficulty;

	@Id
	private String name;

	public int getValue(int x, int y) {
		return this.matrix[x][y];
	}

	public void setValue(int x, int y, int value) {
		this.matrix[x][y] = value;
	}

	public Difficulty getDifficulty() {
		return this.difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getRow(int rowNum) {
		ArrayList<Integer> row = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			row.add(this.matrix[i][rowNum]);
		}
		return row;
	}

	public List<Integer> getColumn(int colNum) {
		ArrayList<Integer> col = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			col.add(this.matrix[colNum][i]);
		}
		return col;
	}

	public List<Integer> getRegion(int rowStart, int rowEnd, int colStart, int colEnd) {
		ArrayList<Integer> region = new ArrayList<>();
		for (int i = rowStart; i < rowEnd; i++) {
			for (int j = colStart; j < colEnd; j++) {
				region.add(this.matrix[i][j]);
			}
		}
		return region;
	}
}
