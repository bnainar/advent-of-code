package aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static utils.Utils.*;

public class Day9 {
    public static void main(String[] args) throws IOException {
        var path = Paths.get("C:/dev/advent-of-code/inputs/t");
        var lines = Files.readAllLines(path);
        int ans1 = 0, ans2 = 0;
        for (String line : lines) {
            ArrayList<ArrayList<Integer>> list = new ArrayList<>();
            var nums= getIntList(line);
            list.add(nums);
            while (nums.stream().anyMatch(n -> n != 0)) {
                var newNums = new ArrayList<Integer>();
                for (int j = 0; j + 1 < nums.size(); j++) {
                    newNums.add(nums.get(j + 1) - nums.get(j));
                }
                list.add(nums = newNums);
            }
            ans1 += part1(list);
            ans2 += part2(list);
        }
        print("Part 1: %d\nPart 2: %d", ans1, ans2);
    }

    private static int part1(ArrayList<ArrayList<Integer>> list) {
        int prev = 0;
        for (int n = list.size() - 2; n >= 0; n--) {
            prev += list.get(n).get(list.get(n).size() - 1);
        }
        return prev;
    }
    private static int part2(ArrayList<ArrayList<Integer>> list) {
        int prev = 0;
        for (int n = list.size() - 2; n >= 0; n--) {
            prev = list.get(n).get(0) - prev;
        }
        return prev;
    }
}
