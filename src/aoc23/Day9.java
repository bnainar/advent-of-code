package aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import static utils.Utils.*;

public class Day9 {
    public static void main(String[] args) throws IOException {
        var path = Paths.get("C:/dev/advent-of-code/inputs/t");
        var lines = Files.readAllLines(path);
        int ans1 = 0, ans2 = 0;
        for (String line : lines) {
            var list = new ArrayList<int[]>();
            var nums = getIntArray(line);
            list.add(nums);
            while (Arrays.stream(nums).anyMatch(n -> n != 0)) {
                var newNums = new int[nums.length - 1];
                for (int j = 0; j + 1 < nums.length; j++) {
                    newNums[j] = nums[j + 1] - nums[j];
                }
                list.add(nums = newNums);
            }
            ans1 += part1(list);
            ans2 += part2(list);
        }
        print("Part 1: %d\nPart 2: %d", ans1, ans2);
    }

    private static int part1(ArrayList<int[]> list) {
        int prev = 0;
        for (int n = list.size() - 2; n >= 0; n--) {
            prev += list.get(n)[list.get(n).length - 1];
        }
        return prev;
    }
    private static int part2(ArrayList<int[]> list) {
        int prev = 0;
        for (int n = list.size() - 2; n >= 0; n--) {
            prev = list.get(n)[0] - prev;
        }
        return prev;
    }
}
