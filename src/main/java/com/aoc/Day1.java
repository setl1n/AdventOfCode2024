package com.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day1 {
    public static void main(String[] args) {
        try {
            List<String> input = Files.readAllLines(Paths.get("src/main/resources/day1/input.txt"));
            List<String> sample = Files.readAllLines(Paths.get("src/main/resources/day1/sample.txt"));

            System.out.println("Sample Output: " + solve(sample));
            System.out.println("Actual Output: " + solve(input));
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        }
    }

    private static int solve(List<String> data) {
        // Example solution: Sum integers in the input
        return data.stream().mapToInt(Integer::parseInt).sum();
    }
}
