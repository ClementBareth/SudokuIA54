package fr.utbm.sudoku.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dizitart.no2.objects.Id;

public class Sudoku {

	private Integer[][] matrix = new Integer[9][9];
	private Difficulty difficulty;

	@Id
	private String name;

	public Integer getValue(int x, int y) {
		return this.matrix[x][y];
	}

	public void setValue(int x, int y, Integer value) {
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
			Integer v = this.matrix[i][rowNum];
			row.add(v);
		}
		return row;
	}

	public List<Integer> getColumn(int colNum) {
		ArrayList<Integer> col = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			Integer v = this.matrix[colNum][i];
			col.add(v);
		}
		return col;
	}

	public List<Integer> getBloc(int rowStart, int rowEnd, int colStart, int colEnd) {
		ArrayList<Integer> region = new ArrayList<>();
		for (int i = rowStart; i <= rowEnd; i++) {
			for (int j = colStart; j <= colEnd; j++) {
				Integer v = this.matrix[j][i];
				region.add(v);
			}
		}
		return region;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.difficulty == null) ? 0 : this.difficulty.hashCode());
		result = prime * result + Arrays.deepHashCode(this.matrix);
		result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sudoku other = (Sudoku) obj;
		if (this.difficulty != other.difficulty)
			return false;
		if (!Arrays.deepEquals(this.matrix, other.matrix))
			return false;
		if (this.name == null) {
			if (other.name != null)
				return false;
		} else if (!this.name.equals(other.name))
			return false;
		return true;
	}
	
	
}
