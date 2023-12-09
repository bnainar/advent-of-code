package aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static utils.Utils.getLongList;
import static utils.Utils.print;

public class Day9 {
    public static void main(String[] args) throws IOException {
        var path = Paths.get("C:/dev/advent-of-code/inputs/t");
        var lines = Files.readAllLines(path);
        long ans1 = 0, ans2 = 0;
        for (String line : lines) {
            ArrayList<ArrayList<Long>> list = new ArrayList<>();
            var nums= getLongList(line);
            list.add(nums);
            while (!allZero(nums)) {
                var newNums = new ArrayList<Long>();
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

    private static long part1(ArrayList<ArrayList<Long>> list) {
        int n = list.size() - 1;
        list.get(n).add(0L);
        while (n > 0) {
            int x = list.get(n).size() - 1;
            list.get(n - 1).add(list.get(n).get(x) + list.get(n - 1).get(x));
            n--;
        }
        return list.get(0).get(list.get(0).size() - 1);
    }
    private static long part2(ArrayList<ArrayList<Long>> list) {
        int n = list.size() - 1;
        list.get(n).add(0L);
        while (n > 0) {
            list.get(n - 1).add(0, list.get(n - 1).get(0) - list.get(n).get(0));
            n--;
        }
        return list.get(0).get(0);
    }

    static boolean allZero(ArrayList<Long> array){
        for(long x: array)
            if(x != 0)
                return false;
        return true;
    }
}
