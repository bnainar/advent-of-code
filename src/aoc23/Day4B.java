package aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static utils.Utils.print;

public class Day4B {
    public static void main(String[] args) throws IOException {
        var arr = (ArrayList<String>) Files.readAllLines(Paths.get("C:/dev/advent-of-code/inputs/t"));
        var cardCount = new int[arr.size() + 1];
        Arrays.fill(cardCount, 1);
        int ans = 0;
        for (int i = 0; i < arr.size(); i++) {
            ans += cardCount[i];
            var nums = arr.get(i).split(":")[1].split("\\|");
            var set = new HashSet<Integer>();
            for (var n : nums[0].trim().split(" +"))
                set.add(Integer.parseInt(n));
            int m = 0;
            for (var n : nums[1].trim().split(" +"))
                if (set.contains(Integer.parseInt(n)))
                    m++;
            for (int x = 1; x <= m; x++)
                cardCount[i + x] += cardCount[i];
        }
        print("%d", ans);
    }
}
// 5329815