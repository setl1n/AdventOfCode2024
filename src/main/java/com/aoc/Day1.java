package com.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Day1 {
    public static void main(String[] args) {
        try {
            List<String> sample = Files.readAllLines(Paths.get("src/main/resources/day1/sample.txt"));
            List<String> input = Files.readAllLines(Paths.get("src/main/resources/day1/input.txt"));

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

    private static long solvePart2(List<String> data) {
        List<Integer> leftList = new ArrayList<>();
        HashMap<Integer,Integer> rightList = new HashMap<>();
        for (String line : data) {
            String[] inputs = line.split("   ");
            leftList.add(Integer.parseInt(inputs[0]));
            int numOnRightList = Integer.parseInt(inputs[1]);
            // increment count of numbers on right list
            rightList.put(numOnRightList, rightList.getOrDefault(numOnRightList, 0) + 1);
        }
        long sum = 0;
        for (Integer integer : leftList) {
            sum += integer * rightList.getOrDefault(integer, 0);
        }
        return sum;
    }
}
