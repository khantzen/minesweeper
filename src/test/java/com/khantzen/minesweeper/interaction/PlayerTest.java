package com.khantzen.minesweeper.interaction;

import com.khantzen.minesweeper.model.Coordinate;
import com.khantzen.minesweeper.util.Input;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.text.ParseException;

import static junit.framework.TestCase.fail;

public class PlayerTest {
    private final Input input = Mockito.mock(Input.class);
    private Player player;

    @Before
    public void setUp() {
        this.player = new Player(this.input);
    }

    @Test
    public void validDimensionGivenTest() throws ParseException {
        Mockito.when(this.input.askQuestion("Which dimensions would you like for your MineField ? (width x length)"))
                .thenReturn("15 x 36");

        String fieldSize = this.player.askForFieldSize();

        Assertions.assertThat(fieldSize).isEqualTo("15x36");
    }

    @Test
    public void missingSpacesFieldDimensionGivenTest() throws ParseException {
        Mockito.when(this.input.askQuestion("Which dimensions would you like for your MineField ? (width x length)"))
                .thenReturn("15    x6");

        String fieldSize = this.player.askForFieldSize();

        Assertions.assertThat(fieldSize).isEqualTo("15x6");
    }

    @Test
    public void invalidFieldDimensionGivenTest() {
        Mockito.when(this.input.askQuestion("Which dimensions would you like for your MineField ? (width x length)"))
                .thenReturn("a bx60");

        try {
            this.player.askForFieldSize();
            fail("This method is supposed to throw a parse exception");
        } catch (ParseException ex) {
            Assertions.assertThat(ex.getMessage()).contains("a bx60 is not a valid input for MineField size");
        }
    }

    @Test
    public void zeroWidthDimensionGivenTest() {
        Mockito.when(this.input.askQuestion("Which dimensions would you like for your MineField ? (width x length)"))
                .thenReturn("0x41");

        try {
            this.player.askForFieldSize();
            fail("This method is supposed to throw a parse exception");
        } catch (ParseException ex) {
            Assertions.assertThat(ex.getMessage()).contains("Field width can't be zero.");
        }
    }

    @Test
    public void zeroHeightDimensionGivenTest() {
        Mockito.when(this.input.askQuestion("Which dimensions would you like for your MineField ? (width x length)"))
                .thenReturn("45x00000");

        try {
            this.player.askForFieldSize();
            fail("This method is supposed to throw a parse exception");
        } catch (ParseException ex) {
            Assertions.assertThat(ex.getMessage()).contains("Field height can't be zero.");
        }
    }

    @Test
    public void validMineNumberGivenTest() throws ParseException {
        Mockito.when(this.input.askQuestion("How many mines do you wish to have in your mine field ?"))
                .thenReturn("10");

        int mineCount = this.player.askForMineNumber(35);

        Assertions.assertThat(mineCount).isEqualTo(10);
    }

    @Test
    public void invalidMineNumberGivenTest() {
        Mockito.when(this.input.askQuestion("How many mines do you wish to have in your mine field ?"))
                .thenReturn("Han shots first !");

        try {
            this.player.askForMineNumber(25);
            fail("This method is supposed to throw a parse exception");
        } catch (ParseException ex) {
            Assertions.assertThat(ex.getMessage()).contains("Han shots first ! is not a valid number for mine count");
        }
    }

    @Test
    public void givenMineNumberGreaterThanFieldArea() {
        Mockito.when(this.input.askQuestion("How many mines do you wish to have in your mine field ?"))
                .thenReturn("800");

        try {
            this.player.askForMineNumber(25);
            fail("This method is supposed to throw a parse exception");
        } catch (ParseException ex) {
            Assertions.assertThat(ex.getMessage()).contains("You can't have more mine than case in your mine field (ie 25) !");
        }
    }

    @Test
    public void givenValidCoordinatesTest() throws ParseException {
        Mockito.when(this.input.askQuestion("Which case do you want to reveal ? (x,y)"))
                .thenReturn("(15,36)");

        Coordinate coord = this.player.askForCoordinates(20, 85);

        Assertions.assertThat(coord.getX()).isEqualTo(15);
        Assertions.assertThat(coord.getY()).isEqualTo(36);
    }

    @Test
    public void givenInvalidCoordinatesFormatTest() {
        Mockito.when(this.input.askQuestion("Which case do you want to reveal ? (x,y)"))
                .thenReturn("One does not simply walk into the Mordor");

        try {
            this.player.askForCoordinates(20, 85);
            fail("This method is supposed to throw a ParseException");
        } catch (ParseException ex) {
            Assertions.assertThat(ex.getMessage()).contains("Invalid coordinates format");
        }
    }

    @Test
    public void givenInvalidCoordinatesValueTest() {
        Mockito.when(this.input.askQuestion("Which case do you want to reveal ? (x,y)"))
                .thenReturn("(100,85)");

        try {
            this.player.askForCoordinates(20, 85);
            fail("This method is supposed to throw a ParseException");
        } catch (ParseException ex) {
            Assertions.assertThat(ex.getMessage()).contains("Chosen case is out of field");
        }
    }
}
