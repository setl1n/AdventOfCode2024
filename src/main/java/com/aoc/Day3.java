package com.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) {
        try {
            List<String> sample = Files.readAllLines(Paths.get("src/main/resources/day3/sample.txt"));
            List<String> input = Files.readAllLines(Paths.get("src/main/resources/day3/input.txt"));

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
        long sum = 0;
        String inputLine = data.get(0);
        Pattern pattern = Pattern.compile("mul\\(([0-9]{1,3}),([0-9]{1,3})\\)");
        Matcher matcher = pattern.matcher(inputLine);
        while (matcher.find()) {
            System.out.println(matcher.group());
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            System.out.println("before:" + sum);
            sum += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
            System.out.println("after:" + sum);
        }
        return sum;
    }

    private static long solvePart2(List<String> data) {
        int count = 0;
        return count;
    }
}
