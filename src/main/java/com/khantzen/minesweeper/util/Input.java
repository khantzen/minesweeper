package com.khantzen.minesweeper.util;

import java.util.Scanner;

public class Input {
    private final Scanner scanner;

    public Input() {
        this.scanner = new Scanner(System.in);
    }

    public String askQuestion(String question) {
        System.out.println(question);
        return this.scanner.next();
    }
}
