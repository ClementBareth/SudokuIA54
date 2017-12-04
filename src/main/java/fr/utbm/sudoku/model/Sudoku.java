package fr.utbm.sudoku.model;

import org.dizitart.no2.objects.Id;

public class Sudoku {

	private int[][] matrix = new int[9][9];
	private Difficulty difficulty;
	
	@Id
	private String name;
	
	public int getValue(int x, int y) {
		return matrix[x][y];
	}
	
	public void setValue(int x, int y, int value) {
		matrix[x][y] = value;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
