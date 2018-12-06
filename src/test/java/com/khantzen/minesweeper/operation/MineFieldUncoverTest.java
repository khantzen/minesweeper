package com.khantzen.minesweeper.operation;

import com.khantzen.minesweeper.model.MineCell;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class MineFieldUncoverTest {

    @Test
    public void uncoverNumberedCaseTest() {
        String testField =
                "111;" +
                "151;" +
                "111";

        MineField mineField = this.convertStringToMineFieldGrid(testField);

        mineField.uncoverCell(1, 1);

        Assertions.assertThat(mineField.getCell(1,1).isHidden()).isFalse();
        Assertions.assertThat(mineField.getCell(2,2).isHidden()).isTrue();
    }

    @Test
    public void uncoverZeroCaseTest() {
        String testField =
                "0000;" +
                "1101;" +
                "0*10";

        MineField mineField = this.convertStringToMineFieldGrid(testField);

        mineField.uncoverCell(1, 2);

        Assertions.assertThat(mineField.getCell(2, 3).isHidden()).isTrue();
        Assertions.assertThat(mineField.getCell(2, 2).isHidden()).isFalse();
        Assertions.assertThat(mineField.getCell(0, 3).isHidden()).isFalse();
        Assertions.assertThat(mineField.getCell(0, 0).isHidden()).isFalse();
    }

    private MineField convertStringToMineFieldGrid(String mineField) {
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
