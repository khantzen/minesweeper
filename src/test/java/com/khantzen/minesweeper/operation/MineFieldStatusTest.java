package com.khantzen.minesweeper.operation;

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

        MineField mineField = MockBuilder.convertStringToMineField(testField);

        String mineFieldStatus = mineField.getStatus();

        Assertions.assertThat(mineFieldStatus).isEqualTo("ON_GOING");
    }

    @Test
    public void terminatedMineFieldTest() {
        String testField = "" +
                "0000;" +
                "0000;" +
                "0000";

        MineField mineField = MockBuilder.convertStringToMineField(testField);

        mineField.uncoverCell(0, 0);

        String mineFieldStatus = mineField.getStatus();

        Assertions.assertThat(mineFieldStatus).isEqualTo("TERMINATED");
    }

    @Test
    public void doomedMineFieldTest() {
        String testField = "" +
                "0000;" +
                "1101;" +
                "1*10";

        MineField mineField = MockBuilder.convertStringToMineField(testField);

        mineField.uncoverCell(2, 1);

        String mineFieldStatus = mineField.getStatus();

        Assertions.assertThat(mineFieldStatus).isEqualTo("DOOMED");
    }

}
