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
		this.matrix[y][x] = value;
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
		newSudoku.matrix = new Integer[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Integer value = this.matrix[i][j];
				if (value == null) {
					newSudoku.matrix[i][j] = null;
				} else {
					newSudoku.matrix[i][j] = new Integer(this.matrix[i][j].intValue());
				}
			}
		}
		newSudoku.difficulty = this.difficulty;
		return newSudoku;
	}

	public Position getBlocWithMostDoublons() {
		Position nullValue = getNullValue();
		int row = 0;
		int col = 0;
		if (nullValue == null) {
			HashMap<Position, Integer> blocsDoublons = new HashMap<>();
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					int startI = i - 2;
					int startJ = j - 2;
					if (startI % 3 == 0 && startJ % 3 == 0) {
						blocsDoublons.put(new Position(startI, startJ),
								new Integer(evaluateDoublons(startI, i, startJ, j)));
					}
				}
			}
			if (!blocsDoublons.isEmpty()) {
				Integer maxInBlocs = Collections.max(blocsDoublons.values());
				List<Position> blocs = new ArrayList<>();
				for (Entry<Position, Integer> e : blocsDoublons.entrySet()) {
					if (e.getValue().equals(maxInBlocs)) {
						blocs.add(e.getKey());
					}
				}
				Collections.shuffle(blocs);
				return blocs.get(0);
			} else {
				HashMap<Integer, Integer> rowsDoublons = new HashMap<>();
				HashMap<Integer, Integer> colsDoublons = new HashMap<>();
				for (int i = 0; i < 9; i++) {
					rowsDoublons.put(new Integer(i), new Integer(evaluateDoublons(RegionTypeEnum.LIGNE, i)));
					colsDoublons.put(new Integer(i), new Integer(evaluateDoublons(RegionTypeEnum.COLONNE, i)));
				}
				Integer maxInRows = Collections.max(rowsDoublons.values());
				Integer maxInCols = Collections.max(colsDoublons.values());
				List<Integer> xs = new ArrayList<>();
				List<Integer> ys = new ArrayList<>();

				for (Entry<Integer, Integer> e : rowsDoublons.entrySet()) {
					if (e.getValue().equals(maxInRows)) {
						xs.add(new Integer(e.getKey().intValue()));
					}
				}
				for (Entry<Integer, Integer> e : colsDoublons.entrySet()) {
					if (e.getValue().equals(maxInCols)) {
						ys.add(new Integer(e.getKey().intValue()));
					}
				}
				
				// TODO traiter cas où une des deux listes est vide
				Collections.shuffle(xs);
				row = xs.get(0).intValue() / 3;
				Collections.shuffle(ys);
				col = ys.get(0).intValue() / 3;
			}
		} else {
			row = nullValue.getX() / 3;
			col = nullValue.getY() / 3;
		}
		return new Position(row * 3, col * 3);
	}

	public Position getNullValue() {
		for (int i = 0; i < 9; i++) {
			List<Integer> row = getRow(i);
			int col = 0;
			for (Integer value : row) {
				if (value == null) {
					return new Position(i, col);
				} else {
					col++;
				}
			}
		}
		return null;
	}

	private int evaluateDoublons(int startI, int i, int startJ, int j) {
		List<Integer> values = getBloc(startI, i, startJ, j);
		return getDoublons(values);
	}

	private int getDoublons(List<Integer> values) {
		int occurrences = 0;
		for (int i = 1; i <= 9; i++) {
			int freq = Collections.frequency(values, new Integer(i));
			if (freq > 1)
				occurrences = occurrences + freq - 1;
		}
		return occurrences;
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
		return getDoublons(values);
	}

	@Override
	public String toString() {
		String str = "\n";
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				str += matrix[j][i] + " ";
			}
			str += "\n";
		}
		return str;
	}
}
