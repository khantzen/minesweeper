package com.khantzen.minesweeper.operation;

import com.khantzen.minesweeper.model.MineCell;

public class MineField {
    private final MineCell[][] board;

    public MineField(MineCell[][] board) {
        this.board = board;
    }

    public void uncoverCell(int line, int column) {
        MineCell mineCell;

        try {
            mineCell = this.board[line][column];
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
        return this.board[line][column];
    }
}
