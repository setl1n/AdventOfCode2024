package com.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Day2 {
    public static void main(String[] args) {
        try {
            List<String> sample = Files.readAllLines(Paths.get("src/main/resources/day2/sample.txt"));
            List<String> input = Files.readAllLines(Paths.get("src/main/resources/day2/input.txt"));

            System.out.println();
            System.out.println("Part 1 Answer (example): " + solvePart1(sample));
            System.out.println("Part 1 Answer: " + solvePart1(input));
            System.out.println();
            // System.out.println("Part 2 Answer (example): " + solvePart2(sample));
            // System.out.println("Part 2 Answer: " + solvePart2(input));
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        }
    }

    private static long solvePart1(List<String> data) {
        int count = 0;
        for (String line : data) {
            String[] levels = line.split(" ");
            if (levels.length == 1) {
                count++;
                continue;
            }
            boolean isIncreasing = Integer.parseInt(levels[1]) - Integer.parseInt(levels[0]) > 0;
            boolean isSafe = true;
            for (int i = 1; i < levels.length; i++) {
                int currLevel = Integer.parseInt(levels[i]);
                int prevLevel = Integer.parseInt(levels[i - 1]);
                if (isIncreasing) {
                    if (currLevel - prevLevel <= 0 || currLevel - prevLevel > 3) {
                        isSafe = false;
                        break;
                    }
                } else {
                    if (currLevel - prevLevel >= 0 || prevLevel - currLevel > 3) {
                        isSafe = false;
                        break;
                    }
                }
            }
            if (isSafe) {
                count++;
            }
        }
        return count;
    }

    private static long solvePart2(List<String> data) {
        return 0;
    }
}
