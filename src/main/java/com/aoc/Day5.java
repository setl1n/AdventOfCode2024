package com.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Day5 {
    public static void main(String[] args) {
        try {
            String sample = Files.readString(Paths.get("src/main/resources/day5/sample.txt"));
            String input = Files.readString(Paths.get("src/main/resources/day5/input.txt"));

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

    private static void processInput(String input, HashMap<Integer,HashSet<Integer>> rules, List<String> lines) {
        String[] splittedInput = input.split("\n\n");
        for (String rule : splittedInput[0].split("\n")) {
            int before = Integer.parseInt(rule.split("\\|")[0]);
            int after = Integer.parseInt(rule.split("\\|")[1]);
            HashSet<Integer> mapOfBefore = rules.getOrDefault(before, new HashSet<Integer>());
            mapOfBefore.add(after);
            rules.put(before, mapOfBefore);
        }
        for (String string : splittedInput[1].split("\n")) {
            lines.add(string);
        }
    }
    
    private static boolean isValidOrder(String line, HashMap<Integer,HashSet<Integer>> rules) {
        List<Integer> occurredBefore = new ArrayList<Integer>();
        for (String num : line.split(",")) {
            int curr = Integer.parseInt(num);
            for (Integer prevInt : occurredBefore) {
                if (rules.getOrDefault(curr, new HashSet<>()).contains(prevInt)) {
                    return false;
                }
            }
            occurredBefore.add(curr);
        }
        return true;
    }

    private static int getMiddlePage(String string) {
        String[] pages = string.split(",");
        return Integer.parseInt(pages[pages.length / 2]);
    }

    private static long solvePart1(String data) {
        int sum = 0;
        HashMap<Integer,HashSet<Integer>> rules = new HashMap<>();
        List<String> lines = new ArrayList<>();
        processInput(data, rules, lines);
        for (String string : lines) {
            if (isValidOrder(string, rules)) {
                sum += getMiddlePage(string);
            }
        }
        return sum;
    }

    private static long solvePart2(List<String> data) {
        int count = 0;
        return count;
    }

}
