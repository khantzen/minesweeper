package com.khantzen.minesweeper.operation;

import com.khantzen.minesweeper.model.Coordinate;
import com.khantzen.minesweeper.util.RandomGenerator;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class MineFieldBuildGridTest {

    private RandomGenerator randomGenerator = Mockito.mock(RandomGenerator.class);
    private MineField mineField;

    @Before
    public void setUp() {
        this.mineField = new MineField(randomGenerator);
    }

    @Test
    public void mineField3x3WithoutMinesTest() {
        mineField.init(3, 3, 0);
        Assertions.assertThat(mineField.getCell(0, 0).getCellValue()).isEqualTo(0);
    }

    @Test
    public void mineField3x3WithOneMineInTheMiddle() {
        Mockito.when(this.randomGenerator.getRandomCoordinate(3, 3))
                .thenReturn(new Coordinate(1, 1));

        mineField.init(3, 3, 1);

        Assertions.assertThat(mineField.getCell(1, 1).isMined()).isTrue();

        Assertions.assertThat(mineField.getCell(0, 0).getCellValue()).isEqualTo(1);
        Assertions.assertThat(mineField.getCell(0, 1).getCellValue()).isEqualTo(1);
        Assertions.assertThat(mineField.getCell(0, 2).getCellValue()).isEqualTo(1);

        Assertions.assertThat(mineField.getCell(1, 0).getCellValue()).isEqualTo(1);
        Assertions.assertThat(mineField.getCell(1, 2).getCellValue()).isEqualTo(1);

        Assertions.assertThat(mineField.getCell(2, 0).getCellValue()).isEqualTo(1);
        Assertions.assertThat(mineField.getCell(2, 1).getCellValue()).isEqualTo(1);
        Assertions.assertThat(mineField.getCell(2, 2).getCellValue()).isEqualTo(1);
    }

    @Test
    public void mineField5x7With3MineTest() {
        Mockito.when(this.randomGenerator.getRandomCoordinate(5, 7))
                .thenReturn(new Coordinate(3, 2))
                .thenReturn(new Coordinate(4, 4))
                .thenReturn(new Coordinate(4, 5));

        this.mineField.init(5,7, 3);

        Assertions.assertThat(this.mineField.getCell(2, 3).isMined()).isTrue();
        Assertions.assertThat(this.mineField.getCell(5, 4).isMined()).isTrue();
        Assertions.assertThat(this.mineField.getCell(4, 4).isMined()).isTrue();

        Assertions.assertThat(this.mineField.getCell(3, 3).getCellValue()).isEqualTo(2);
    }

    @Test
    public void mineField5x9With3MineAndScrewedRNGTest() {
        Mockito.when(this.randomGenerator.getRandomCoordinate(5, 7))
                .thenReturn(new Coordinate(3, 2))
                .thenReturn(new Coordinate(4, 5))
                .thenReturn(new Coordinate(3, 2))
                .thenReturn(new Coordinate(3, 2))
                .thenReturn(new Coordinate(4, 5))
                .thenReturn(new Coordinate(4, 4))
                .thenReturn(new Coordinate(4, 5));

        this.mineField.init(5,7, 3);

        this.mineField.revealAllGrid();
        this.mineField.printGrid();

        Assertions.assertThat(this.mineField.getCell(2, 3).isMined()).isTrue();
        Assertions.assertThat(this.mineField.getCell(5, 4).isMined()).isTrue();
        Assertions.assertThat(this.mineField.getCell(4, 4).isMined()).isTrue();

        Assertions.assertThat(this.mineField.getCell(3, 3).getCellValue()).isEqualTo(2);
    }
}
