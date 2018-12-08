package com.khantzen.minesweeper.operation;

import com.khantzen.minesweeper.model.MineCell;
import com.khantzen.minesweeper.util.MockBuilder;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class MineFieldUncoverTest {

    @Test
    public void uncoverNumberedCaseTest() {
        String testField = "" +
                "111;" +
                "151;" +
                "111";

        MineField mineField = getMineField(testField);

        mineField.uncoverCell(1, 1);

        Assertions.assertThat(mineField.getCell(1, 1).isHidden()).isFalse();
        Assertions.assertThat(mineField.getCell(2, 2).isHidden()).isTrue();
    }

    @Test
    public void uncoverZeroCaseTest() {
        String testField = "" +
                "0000;" +
                "1101;" +
                "0*10";

        MineField mineField = getMineField(testField);

        mineField.uncoverCell(1, 2);

        Assertions.assertThat(mineField.getCell(2, 3).isHidden()).isTrue();
        Assertions.assertThat(mineField.getCell(2, 2).isHidden()).isFalse();
        Assertions.assertThat(mineField.getCell(0, 3).isHidden()).isFalse();
        Assertions.assertThat(mineField.getCell(0, 0).isHidden()).isFalse();
    }

    private MineField getMineField(String testField) {
        MineCell[][] grid = MockBuilder.convertStringToGrid(testField);
        return new MineField(grid);
    }

}
