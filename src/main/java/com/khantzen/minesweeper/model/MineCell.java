package com.khantzen.minesweeper.model;

public class MineCell {
    private final int cellValue;
    private boolean hidden;

    public MineCell(int cellValue) {
        this.cellValue = cellValue;
        this.hidden = true;
    }

    public int getCellValue() {
        return cellValue;
    }

    public boolean isMined() {
        return this.cellValue == -1;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void reveal() {
        this.hidden = false;
    }
}
