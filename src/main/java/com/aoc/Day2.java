package com.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
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
            System.out.println("Part 2 Answer (example): " + solvePart2(sample));
            System.out.println("Part 2 Answer: " + solvePart2(input));
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
        int count = 0;
        for (String line : data) {
            String[] levels = line.split(" ");
            if (levels.length == 1) {
                count++;
                continue;
            }
            String[] reverseLevels = Arrays.copyOf(levels, levels.length);
            Collections.reverse(Arrays.asList(reverseLevels));
            if (isSafeWithDampener(levels, false) || isSafeWithDampener(reverseLevels, false)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isSafeWithDampener(String[] levels, boolean hasDampened) {
        boolean isIncreasing = Integer.parseInt(levels[1]) - Integer.parseInt(levels[0]) > 0;
        for (int i = 1; i < levels.length; i++) {
            int currLevel = Integer.parseInt(levels[i]);
            int prevLevel = Integer.parseInt(levels[i - 1]);
            if (currLevel == prevLevel || Math.abs(currLevel-prevLevel) > 3) {
                if (hasDampened) return false;
                String[] levelsWithoutProblemChild = makeArrayWithoutI(levels, i);
                return isSafeWithDampener(levelsWithoutProblemChild, !hasDampened);
            }
            if (isIncreasing) {
                if (currLevel < prevLevel) {
                    if (hasDampened) return false;
                    else {
                        String[] levelsWithoutProblemChild = makeArrayWithoutI(levels, i);
                        return isSafeWithDampener(levelsWithoutProblemChild, !hasDampened);
                    }
                }
            } else {
                if (currLevel > prevLevel) {
                    if (hasDampened) return false;
                    else {
                        String[] levelsWithoutProblemChild = makeArrayWithoutI(levels, i);
                        return isSafeWithDampener(levelsWithoutProblemChild, !hasDampened);
                    }
                }
            }
        }
        return true;
    }

    private static String[] makeArrayWithoutI(String[] levels, int i) {
        String[] ret = new String[levels.length - 1];
        for (int j = 0, k = 0; j < levels.length; j++) {
            if (j == i) continue;
            ret[k++] = levels[j];
        }
        return ret;
    }
}
