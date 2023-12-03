package aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static utils.Utils.print;

public class Day3B {
    public static void main(String[] args) throws IOException {
        var arr = (ArrayList<String>) Files.readAllLines(Paths.get("C:/dev/advent-of-code/inputs/t"));
        int C = arr.get(0).length(), ans = 0;
        var map = new HashMap<String, List<Integer>>();
        for(int i = 0; i < arr.size(); i++) {
            String line = arr.get(i);
            for (int j = 0; j < C; j++)
                if (Character.isDigit(line.charAt(j))) {
                    int start = j, n = 0;
                    while (j < C && Character.isDigit(line.charAt(j)))
                        n = (n * 10) + (line.charAt(j++) - '0');
                    String pos = findStar(i, --j, arr, start);
                    if (pos != null)
                        map.computeIfAbsent(pos, k -> new ArrayList<>()).add(n);
                }
        }
        for(var v: map.values())
            if(v.size() == 2)
                ans += v.get(0) * v.get(1);
        print("%d", ans);
    }
    private static String findStar(int i, int j, ArrayList<String> arr, int start) {
        int minCol = Math.max(0, start - 1), minRow = Math.max(i - 1, 0);
        int maxCol = Math.min(j + 1, arr.get(i).length() - 1), maxRow = Math.min(i + 1, arr.size() - 1);
        for(int x = minRow; x <= maxRow; x++)
            for(int y = minCol; y <= maxCol; y++)
                if(arr.get(x).charAt(y) == '*')
                        return x + " " + y;
        return null;
    }

}
