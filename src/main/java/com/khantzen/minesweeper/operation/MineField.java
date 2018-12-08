package com.khantzen.minesweeper.operation;

import com.khantzen.minesweeper.model.Coordinate;
import com.khantzen.minesweeper.model.MineCell;
import com.khantzen.minesweeper.util.RandomGenerator;

public class MineField {
    private RandomGenerator randomGenerator;

    private MineCell[][] grid;

    public MineField() {
        this.randomGenerator = new RandomGenerator();
    }

    MineField(MineCell[][] board) {
        this.grid = board;
    }

    MineField(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    public void uncoverCell(int line, int column) {
        MineCell mineCell;

        try {
            mineCell = this.grid[line][column];
            if (!mineCell.isHidden())
                return;
        } catch (ArrayIndexOutOfBoundsException ex) {
            return;
        }

        mineCell.reveal();

        int cellValue = mineCell.getCellValue();

        if (cellValue == 0) {
            uncoverCell(line - 1, column);
            uncoverCell(line + 1, column);
            uncoverCell(line, column - 1);
            uncoverCell(line, column + 1);
        }

    }

    public MineCell getCell(int line, int column) {
        return this.grid[line][column];
    }

    public String getStatus() {
        int uncoveredCell = 0;

        for (MineCell[] cellLine : this.grid) {
            for (MineCell cell : cellLine) {
                if (!cell.isHidden()) {
                    if (cell.isMined()) {
                        return "DOOMED";
                    } else {
                        uncoveredCell++;
                    }
                }
            }
        }

        int totalCells = getTotalCells();

        return uncoveredCell == totalCells ? "TERMINATED" : "ON_GOING";
    }

    private int getTotalCells() {
        return this.grid.length * this.grid[0].length;
    }

    public void init(int width, int height, int mineCount) {
        this.grid = new MineCell[height][width];

        for (int line = 0; line < this.grid.length; line++) {
            for (int col = 0; col < this.grid[line].length; col++) {
                this.grid[line][col] = new MineCell(0);
            }
        }

        for (int i = 0; i < mineCount; i++) {
            Coordinate mineCoordinate = getNextMineCoordinates(width, height);
            this.setUpMine(mineCoordinate);
        }
    }

    private Coordinate getNextMineCoordinates(int width, int height) {
        Coordinate mineCoordinate = this.randomGenerator.getRandomCoordinate(width, height);

        if (this.grid[mineCoordinate.getY()][mineCoordinate.getX()].isMined()) {
            return getNextMineCoordinates(width, height);
        }

        return mineCoordinate;
    }

    private void setUpMine(Coordinate coord) {
        int line = coord.getY();
        int col = coord.getX();

        this.grid[line][col].setUpMine();

        int aboveLine = line - 1;
        int afterLine = line + 1;

        int aboveColumn = col - 1;
        int afterColumn = col + 1;

        int lineCount = this.grid.length;
        int columnCount = this.grid[0].length;

        if (aboveLine >= 0) {
            this.grid[aboveLine][col].incrementCellValue();

            if (aboveColumn >= 0) {
                this.grid[aboveLine][aboveColumn].incrementCellValue();
            }

            if (afterColumn < columnCount) {
                this.grid[aboveLine][afterColumn].incrementCellValue();
            }
        }

        if (aboveColumn >= 0) {
            this.grid[line][aboveColumn].incrementCellValue();

            if (afterLine < lineCount) {
                this.grid[afterLine][aboveColumn].incrementCellValue();
            }
        }

        if (afterColumn < columnCount) {
            this.grid[line][afterColumn].incrementCellValue();
        }

        if (afterLine < lineCount) {
            this.grid[afterLine][col].incrementCellValue();

            if (afterColumn < columnCount) {
                this.grid[afterLine][afterColumn].incrementCellValue();
            }
        }
    }

    public void printGrid() {
        for (int line = 0; line < this.grid.length; line++) {
            // System.out.print(line + "|");
            for (int col = 0; col < this.grid[line].length; col++) {
                System.out.print(this.grid[line][col].toString() + " |");
            }
            System.out.println();
        }
    }

    public void revealAllGrid() {
        for (int line = 0; line < this.grid.length; line++) {
            for (int col = 0; col < this.grid[line].length; col++) {
                this.grid[line][col].reveal();
            }
        }
    }

    MineCell[][] getGrid() {
        return grid;
    }
}
