package aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Day15 {
    record Lens(String label, int focalLength) {
    }
    public static void main(String[] args) throws IOException {
        String path = "C:/dev/advent-of-code/inputs/t";
        var lines = Files.readString(Paths.get(path)).split(",");
        part1(lines);
        part2(lines);
    }

    private static void part1(String[] lines) {
        System.out.println("Part 1: " + Arrays.stream(lines).mapToInt(Day15::getHash).sum());
    }
    private static void part2(String[] lines) {
        var boxes = new HashMap<Integer, ArrayList<Lens>>(256);
        o:
        for (String line : lines) {
            int op = line.indexOf('-');
            if (op == -1) op = line.indexOf('=');
            String currLabel = line.substring(0, op);
            int h = getHash(currLabel);
            boxes.computeIfAbsent(h, k -> new ArrayList<>());
            if (line.charAt(op) == '-') {
                boxes.get(h).removeIf(l -> l.label.equals(currLabel));
            } else {
                Lens newLens = new Lens(currLabel, line.charAt(op + 1) - '0');
                for (int i = 0; i < boxes.get(h).size(); i++) {
                    if (boxes.get(h).get(i).label.equals(currLabel)) {
                        boxes.get(h).set(i, newLens);
                        continue o;
                    }
                }
                boxes.get(h).add(newLens);
            }
        }
        int ans = 0;
        for (int i: boxes.keySet())
            for (int j = 0; j < boxes.get(i).size(); j++)
                ans += (i + 1) * (j + 1) * boxes.get(i).get(j).focalLength;

        System.out.println("Part 2: " + ans);
    }
    private static int getHash(String l) {
        return l.chars().reduce(0, (acc, c) -> (acc + c) * 17 % 256);
    }
}
