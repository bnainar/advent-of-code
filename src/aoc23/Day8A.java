package aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class Day8A {

    public static void main(String[] args) throws IOException {
        String path = "C:/dev/advent-of-code/inputs/t";
        var lines = (ArrayList<String>) Files.readAllLines(Paths.get(path));
        String dirs = lines.get(0).trim();
        var adj = new HashMap<String, String[]>();
        for(int i = 2; i < lines.size(); i++){
            String x = lines.get(i);
            String from = x.substring(0, x.indexOf(" ="));
            String left = x.substring(x.indexOf("(") + 1, x.indexOf(","));
            String right = x.substring(x.indexOf(",") + 2, x.indexOf(")"));
            adj.put(from, new String[]{left, right});
        }
        int i = -1, steps = 0;
        String curr = "AAA";
        while(!curr.equals("ZZZ")){
            steps++;
            i = (i + 1) % dirs.length();
            char d = dirs.charAt(i);
            curr = adj.get(curr)[d == 'L' ? 0 : 1];
        }
        System.out.println(steps);
    }
}
