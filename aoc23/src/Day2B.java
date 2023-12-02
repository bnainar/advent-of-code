import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day2B {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:/dev/leetcode/aoc/inputs/Bsub"));
        int ans = 0;
        while (sc.hasNextLine()) {
            var map = new HashMap<>(Map.of("red", 0, "green", 0, "blue", 0));
            String line = sc.nextLine().split(":")[1];
            for(String set: line.split(";"))
                for(String e: set.split(",")){
                    var x = e.trim().split(" ");
                    int num = Integer.parseInt(x[0]);
                    map.put(x[1], Math.max(map.get(x[1]), num));
                }
            ans += map.values().stream().reduce(1, (a, c) -> a * c);
        }
        System.out.println(ans);
    }
}