package com.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 {
    public static void main(String[] args) {
        try {
            List<String> sample = Files.readAllLines(Paths.get("src/main/resources/day4/sample.txt"));
            List<String> input = Files.readAllLines(Paths.get("src/main/resources/day4/input.txt"));

            System.out.println();
            System.out.println("Part 1 Answer (example): " + solvePart1(sample));
            System.out.println("Part 1 Answer: " + solvePart1(input));
            System.out.println();
            System.out.println("Part 2 Answer (example): " + solvePart2(sample));
            System.out.println("Part 2 Answer: " + solvePart2(input));
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        }
    }

    private static long solvePart1(List<String> data) {
        int count = 0;
        int numLines = data.size();
        int lineLength = data.get(0).length();
        char[][] input = new char[numLines][];
        for (int i = 0; i < numLines; i++) {
            String lineStr = data.get(i);
            char[] line = new char[lineLength];
            for (int j = 0; j < lineLength; j++) {
                line[j] = lineStr.charAt(j);
            }
            input[i] = line;
        }
        // System.out.println(Arrays.deepToString(input));
        for (int i = 0; i < numLines; i++) {
            for (int j = 0; j < lineLength; j++) {
                count += getNumOfOccurrence(input, i, j);
            }
        }
        return count;
    }

    private static int getNumOfOccurrence(char[][] input, int i, int j) {
        if (input[i][j] != 'X') {
            return 0;
        }
        // System.out.println("X found at i: " + i + " j: " + j);
        int count = 0;
        for (int k = -1; k < 2; k++) {
            for (int k2 = -1; k2 < 2; k2++) {
                // if (k == 0 && k2 ==0) {
                // continue;
                // }
                count += searchAlong(input, i, j, k, k2, 'M');
            }
        }
        return count;
    }

    private static int searchAlong(char[][] input, int i, int j, int dirX, int dirY, char toSearch) {
        // System.out.println("Searching >" + toSearch + "< at i: >" + i + "< j: >" + j
        // + "<");
        int numLines = input.length;
        int lineLength = input[0].length;
        if (i + dirX < 0 || i + dirX >= lineLength) {
            return 0;
        }
        if (j + dirY < 0 || j + dirY >= numLines) {
            return 0;
        }
        int currI = i + dirX;
        int currJ = j + dirY;
        if (input[currI][currJ] == toSearch) {
            if (toSearch == 'M') {
                return searchAlong(input, currI, currJ, dirX, dirY, 'A');
            } else if (toSearch == 'A') {
                return searchAlong(input, currI, currJ, dirX, dirY, 'S');
            } else if (toSearch == 'S') {
                return 1;
            }
        }
        return 0;
    }

    private static long solvePart2(List<String> data) {
        int count = 0;
        int numLines = data.size();
        int lineLength = data.get(0).length();
        char[][] input = new char[numLines][];
        for (int i = 0; i < numLines; i++) {
            String lineStr = data.get(i);
            char[] line = new char[lineLength];
            for (int j = 0; j < lineLength; j++) {
                line[j] = lineStr.charAt(j);
            }
            input[i] = line;
        }
        // System.out.println(Arrays.deepToString(input));
        for (int i = 0; i < numLines; i++) {
            for (int j = 0; j < lineLength; j++) {
                if (input[i][j] == 'A') {
                    if (validX(input, i, j)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private static boolean validX(char[][] input, int i, int j) {
        if (i == 0 || i == input[0].length - 1) {
            return false;
        }
        if (j == 0 || j == input.length - 1) {
            return false;
        }
        return (input[i-1][j-1] == 'M' && input[i+1][j-1] == 'M' && input[i-1][j+1] == 'S' && input[i+1][j+1] =='S') 
                || input[i-1][j-1] == 'M' && input[i-1][j+1] == 'M' && input[i+1][j-1] == 'S' && input[i+1][j+1] =='S'
                || input[i+1][j+1] == 'M' && input[i+1][j-1] == 'M' && input[i-1][j+1] == 'S' && input[i-1][j-1] =='S'
                || input[i+1][j+1] == 'M' && input[i-1][j+1] == 'M' && input[i+1][j-1] == 'S' && input[i-1][j-1] =='S';
    }
}
