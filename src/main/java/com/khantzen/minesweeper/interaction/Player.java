package com.khantzen.minesweeper.interaction;

import com.khantzen.minesweeper.model.Coordinates;
import com.khantzen.minesweeper.util.Input;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Player {
    private final Input playerInput;

    public Player(Input playerInput) {
        this.playerInput = playerInput;
    }

    public String askForFieldSize() throws ParseException {
        String wishedFieldDimensions =
                this.playerInput.askQuestion("Which dimensions would you like for your MineField ? (width x length)");

        String spaceCleanedInput = wishedFieldDimensions.replace(" ", "");

        Pattern getFieldDimensionsPattern = Pattern.compile("^(?<width>\\d+?)x(?<height>\\d+?)$");
        Matcher fieldDimensionsMatcher = getFieldDimensionsPattern.matcher(spaceCleanedInput);

        if (!fieldDimensionsMatcher.find()) {
            throw new ParseException(wishedFieldDimensions + " is not a valid input for MineField size", 0);
        }

        this.checkFieldDimensions(fieldDimensionsMatcher);


        return spaceCleanedInput;
    }

    private void checkFieldDimensions(Matcher fieldDimensionsMatcher) throws ParseException {
        int width = Integer.parseInt(fieldDimensionsMatcher.group("width"));
        if (width == 0) {
            throw new ParseException("Field width can't be zero.", 0);
        }

        int height = Integer.parseInt(fieldDimensionsMatcher.group("height"));
        if (height  == 0) {
            throw new ParseException("Field height can't be zero.", 0);
        }
    }

    public int askForMineNumber(int fieldCaseCount) throws ParseException {
        String wishedMineCount =
                this.playerInput.askQuestion("How many mines do you wish to have in your mine field ?");

        if (!wishedMineCount.matches("^\\d+$")) {
            throw new ParseException(wishedMineCount + " is not a valid number for mine count", 0);
        }

        int mineCount = Integer.parseInt(wishedMineCount);

        if (mineCount > fieldCaseCount) {
            throw new ParseException("You can't have more mine than case in your mine field (ie " + fieldCaseCount + ") !", 0);
        }

        return mineCount;
    }

    public Coordinates askForCoordinates(int width, int height) throws ParseException {
        String wishedCellCoord =
                this.playerInput.askQuestion("Which case do you want to reveal ? (x,y)");

        Pattern coordinatesMatchingPattern = Pattern.compile("^\\((?<coordX>\\d+),(?<coordY>\\d+)\\)$");
        Matcher coordinatesMatcher = coordinatesMatchingPattern.matcher(wishedCellCoord);

        if (!coordinatesMatcher.find()) {
            throw new ParseException("Invalid coordinates format", 0);
        }

        int coordX = Integer.parseInt(coordinatesMatcher.group("coordX"));
        int coordY = Integer.parseInt(coordinatesMatcher.group("coordY"));

        if (coordX >= width || coordY >= height) {
            throw new ParseException("Chosen case is out of field", 0);
        }

        return new Coordinates(coordX, coordY);
    }
}
