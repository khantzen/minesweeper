package com.khantzen.minesweeper.model;

public class MineCell {
    private int cellValue;
    private boolean hidden;

    public MineCell(int cellValue) {
        this.cellValue = cellValue;
        this.hidden = true;
    }

    public int getCellValue() {
        return cellValue;
    }

    public void incrementCellValue() {
        if (this.cellValue != -1) {
            this.cellValue++;
        }
    }

    public boolean isMined() {
        return this.cellValue == -1;
    }

    public void setUpMine() {
        this.cellValue = -1;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void reveal() {
        this.hidden = false;
    }

    @Override
    public String toString() {
        if (this.isHidden()) {
            return "+";
        } else if (this.cellValue == -1) {
            return "*";
        } else {
            return this.cellValue + "";
        }
    }
}
