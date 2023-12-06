package aoc23;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class Day2A {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:/dev/advent-of-code/inputs/t"));
        int ans = 0;
        Map<String, Integer> map = Map.of("red", 12, "green", 13, "blue", 14);
        l: while (sc.hasNextLine()) {
            String line = sc.nextLine();
            int gameNo = Integer.parseInt(line.substring(5, line.indexOf(":")));
            line = line.substring(line.indexOf(":") + 2);
            for(String e: line.split(", +|; +")){
                var x = e.split(" ");
                if(Integer.parseInt(x[0]) > map.get(x[1])) continue l;
            }
            ans += gameNo;
        }
        System.out.println(ans);
    }
}
