package fr.utbm.sudoku.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

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

	public Sudoku copy() {
		Sudoku newSudoku = new Sudoku();
		newSudoku.matrix = this.matrix;
		newSudoku.difficulty = this.difficulty;
		return newSudoku;
	}

	public Position getBlocWithMostDoublons() {
		HashMap<Integer, Integer> rowsDoublons = new HashMap<>();
		HashMap<Integer, Integer> colsDoublons = new HashMap<>();
		for (int i = 0; i < 9; i++) {
			rowsDoublons.put(new Integer(i), new Integer(evaluateDoublons(RegionTypeEnum.LIGNE, i)));
			colsDoublons.put(new Integer(i), new Integer(evaluateDoublons(RegionTypeEnum.COLONNE, i)));
		}
		Integer maxInRows = Collections.max(rowsDoublons.values());
		Integer maxInCols = Collections.max(colsDoublons.values());
		int x = 0;
		int y = 0;
		for (Entry<Integer, Integer> e : rowsDoublons.entrySet()) {
			if (e.getValue().equals(maxInRows)) {
				x = e.getKey().intValue();
			}
		}
		for (Entry<Integer, Integer> e : colsDoublons.entrySet()) {
			if (e.getValue().equals(maxInCols)) {
				y = e.getKey().intValue();
			}
		}
		return new Position(x / 3, y / 3);
	}

	private int evaluateDoublons(RegionTypeEnum type, int number) {
		List<Integer> values = null;
		switch (type) {
		case LIGNE:
			values = getRow(number);
			break;
		case COLONNE:
			values = getColumn(number);
			break;
		default:
			break;
		}
		int occurrences = 0;
		for (int i = 1; i <= 9; i++) {
			int freq = Collections.frequency(values, new Integer(i));
			if (freq > 1)
				occurrences = occurrences + freq - 1;
		}
		return occurrences;
	}
}
