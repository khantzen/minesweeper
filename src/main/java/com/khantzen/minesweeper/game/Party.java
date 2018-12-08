package com.khantzen.minesweeper.game;

import com.khantzen.minesweeper.interaction.Player;
import com.khantzen.minesweeper.model.Coordinate;
import com.khantzen.minesweeper.operation.MineField;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Party {
    private Player player;
    private MineField mineField;

    public Party() {
        this.player = new Player();
        this.mineField = new MineField();
    }

    public void run() throws ParseException {
        String fieldSize = getFieldSize();

        Pattern fieldSizeMatchingPattern = Pattern.compile("^(?<width>\\d+)x(?<height>\\d+)$");
        Matcher fieldSizeMatcher = fieldSizeMatchingPattern.matcher(fieldSize);

        fieldSizeMatcher.find();

        int width = Integer.parseInt(fieldSizeMatcher.group("width"));
        int height = Integer.parseInt(fieldSizeMatcher.group("height"));

        int fieldArea = width * height;

        int mineCount = this.player.askForMineNumber(fieldArea);

        mineField.init(width, height, mineCount);

        String fieldStatus = mineField.getStatus();

        while (fieldStatus.equals("ON_GOING")) {
            mineField.printGrid();
            Coordinate coordinate = getCoordinate(width, height);
            mineField.uncoverCell(coordinate.getY(), coordinate.getX());
            fieldStatus = mineField.getStatus();
        }

        if (fieldStatus.equals("DOOMED")) {
            mineField.revealAllGrid();
            mineField.printGrid();
            System.out.println("YOU LOOSE");
        } else {
            System.out.println("YOU WIN");
        }


    }

    private Coordinate getCoordinate(int width, int height) {
        try {
            return this.player.askForCoordinates(width, height);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            return this.getCoordinate(width, height);
        }
    }

    private String getFieldSize() {
        try {
            return this.player.askForFieldSize();
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            return this.getFieldSize();
        }
    }
}
