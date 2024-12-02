package com.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Day1 {
    public static void main(String[] args) {
        try {
            List<String> sample = Files.readAllLines(Paths.get("src/main/resources/day1/sample.txt"));
            List<String> input = Files.readAllLines(Paths.get("src/main/resources/day1/input.txt"));

            System.out.println("Part 1 Answer (for example): " + solvePart1(sample));
            System.out.println("Part 1 Answer (for example): " + solvePart1(input));
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        }
    }

    private static long solvePart1(List<String> data) {
        PriorityQueue<Integer> list1 = new PriorityQueue<>();
        PriorityQueue<Integer> list2 = new PriorityQueue<>();
        for (String line : data) {
            String[] inputs = line.split("   ");
            list1.add(Integer.parseInt(inputs[0]));
            list2.add(Integer.parseInt(inputs[1]));
        }
        long sum = 0;
        while (!list1.isEmpty()) {
            sum += Math.abs(list1.poll() - list2.poll());
        }
        return sum;
    }
}
