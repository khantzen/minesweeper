package com.khantzen.minesweeper;

import com.khantzen.minesweeper.game.Party;
import com.khantzen.minesweeper.util.RandomGenerator;

import java.util.Random;

public class MineSweeperMain {

    public static void main(String[] args) {
        try {
            setRngSeed(args);
            Party party = new Party();
            party.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void setRngSeed(String[] args) {
        if (args.length > 0) {
            long seed = Long.parseLong(args[0]);
            RandomGenerator.setRngSeed(seed);
        } else {
            Random random = new Random();
            RandomGenerator.setRngSeed(random.nextLong());
        }
    }
}
