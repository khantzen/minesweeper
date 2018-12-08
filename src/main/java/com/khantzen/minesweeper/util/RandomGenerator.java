package com.khantzen.minesweeper.util;

import com.khantzen.minesweeper.model.Coordinate;

import java.util.Random;

public class RandomGenerator {
    private static Random random = new Random();

    public Coordinate getRandomCoordinate(int width, int height) {
        int randomX = random.nextInt(width  -1);
        int randomY = random.nextInt(height -1);
        return new Coordinate(randomX, randomY);
    }
}
