package com.khantzen.minesweeper.operation;

import com.khantzen.minesweeper.model.MineCell;
import com.khantzen.minesweeper.util.MockBuilder;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class MineFieldStatusTest {

    @Test
    public void onGoingMineFieldTest() {
        String testField = "" +
                "0000;" +
                "1101;" +
                "1*10";

        MineField mineField = getMineField(testField);

        String mineFieldStatus = mineField.getStatus();

        Assertions.assertThat(mineFieldStatus).isEqualTo("ON_GOING");
    }

    @Test
    public void terminatedMineFieldWithNoMineTest() {
        String testField = "" +
                "0000;" +
                "0000;" +
                "0000";

        MineField mineField = getMineField(testField);

        mineField.uncoverCell(0, 0);

        String mineFieldStatus = mineField.getStatus();

        Assertions.assertThat(mineFieldStatus).isEqualTo("TERMINATED");
    }

    @Test
    public void terminatedMineFieldWithTwoMinesTest() {
        String testField = "" +
                "11000;" +
                "*1011;" +
                "1101*";

        MineField mineField = getMineField(testField);

        mineField.uncoverCell(0, 4);
        mineField.uncoverCell(0, 0);
        mineField.uncoverCell(2, 0);

        String mineFieldStatus = mineField.getStatus();

        Assertions.assertThat(mineFieldStatus).isEqualTo("TERMINATED");
    }

    @Test
    public void doomedMineFieldTest() {
        String testField = "" +
                "0000;" +
                "1101;" +
                "1*10";

        MineField mineField = getMineField(testField);

        mineField.uncoverCell(2, 1);

        String mineFieldStatus = mineField.getStatus();

        Assertions.assertThat(mineFieldStatus).isEqualTo("DOOMED");
    }

    private MineField getMineField(String testField) {
        MineCell[][] grid = MockBuilder.convertStringToGrid(testField);
        return new MineField(grid);
    }

}
