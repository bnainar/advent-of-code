package aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;

import static utils.Utils.print;

public class Day4A {
    public static void main(String[] args) throws IOException {
        String path = "C:/dev/advent-of-code/inputs/t";
        int ans = Files.readAllLines(Paths.get(path)).stream().mapToInt(s -> {
            var nums = s.split(": +")[1].split("\\|");
            var set = new HashSet<>(Arrays.asList(nums[0].trim().split(" +")));
            int matches = (int) Arrays.stream(nums[1].trim().split(" +")).filter(set::contains).count();
            return (int) Math.pow(2, matches - 1);
        }).sum();
        print("%d", ans);
    }
}
