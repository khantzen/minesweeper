package com.khantzen.minesweeper.util;

import com.khantzen.minesweeper.model.MineCell;
import com.khantzen.minesweeper.operation.MineField;

public class MockBuilder {
    public static MineField convertStringToMineField(String mineField) {
        String[] fieldLine = mineField.split(";");

        int length = fieldLine[0].length();
        int height = fieldLine.length;

        MineCell[][] grid = new MineCell[height][length];

        for (int lineIndex = 0; lineIndex < fieldLine.length; lineIndex++) {
            String line = fieldLine[lineIndex];

            for (int colIndex = 0; colIndex < line.length(); colIndex++){
                char value = line.charAt(colIndex);

                if (value == '*') { // Mine
                    grid[lineIndex][colIndex] = new MineCell(-1);
                } else {
                    grid[lineIndex][colIndex] = new MineCell(Integer.parseInt(value + ""));
                }
            }
        }

        return new MineField(grid);
    }
}
