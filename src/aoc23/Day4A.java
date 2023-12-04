package aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static utils.Utils.print;

public class Day4A {
    public static void main(String[] args) throws IOException {
        String path = "C:/dev/advent-of-code/inputs/t";
//        Scanner sc = new Scanner(new File(path));
        var arr = (ArrayList<String>) Files.readAllLines(Paths.get(path));
        int ans = 0;
        for (String s : arr) {
            var nums = s.split(":")[1].split("\\|");
            var set = new HashSet<>(Arrays.asList(nums[0].trim().split(" +")));
            int m = (int) Arrays.stream(nums[1].trim().split(" +")).filter(set::contains).count();
            ans += (int) Math.pow(2, m - 1);
        }
        print("%d", ans);
    }
}
