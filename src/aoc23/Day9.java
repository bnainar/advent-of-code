package aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.IntStream;

import static utils.Utils.getIntArray;
import static utils.Utils.print;

public class Day9 {
    public static void main(String[] args) throws IOException {
        var path = Paths.get("C:/dev/advent-of-code/inputs/t");
        var lines = Files.readAllLines(path);
        int ans1 = 0, ans2 = 0;
        for (String line : lines) {
            var list = new ArrayList<int[]>();
            int[] nums = getIntArray(line, " ");
            list.add(nums);
            while (IntStream.of(nums).anyMatch(n -> n != 0)) {
                var diff = new int[nums.length - 1];
                for (int i = 0; i + 1 < nums.length; i++)
                    diff[i] = nums[i + 1] - nums[i];

                list.add(nums = diff);
            }

            ans1 += list.stream().mapToInt(a -> a[a.length - 1])
                    .sum();
            ans2 += IntStream.range(2 - list.size(), 1)
                    .reduce(0, (prev, n) -> list.get(-n)[0] - prev);
        }
        print("Part 1: %d\nPart 2: %d", ans1, ans2);
    }
}
