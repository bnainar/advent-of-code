import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class Day2A {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:/dev/leetcode/aoc/inputs/Asub"));
        int ans = 0;
        Map<String, Integer> map = Map.of("red", 12, "green", 13, "blue", 14);
        l: while (sc.hasNextLine()) {
            String line = sc.nextLine();
            int gameNo = Integer.parseInt(line.substring(5, line.indexOf(":")));
            line = line.substring(line.indexOf(":") + 1);
            for(String set: line.split(";"))
                for(String e: set.split(",")){
                    var x = e.trim().split(" ");
                    if(Integer.parseInt(x[0]) > map.get(x[1])) continue l;
                }
            ans += gameNo;
        }
        System.out.println(ans);
    }
}
