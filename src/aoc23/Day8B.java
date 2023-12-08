package aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class Day8B {
    public static void main(String[] args) throws IOException {
        String path = "C:/dev/advent-of-code/inputs/t";
        var lines = (ArrayList<String>) Files.readAllLines(Paths.get(path));
        String dirs = lines.get(0).trim();
        var adj = new HashMap<String, String[]>();
        var list = new ArrayList<String>();
        for(int i = 2; i < lines.size(); i++){
            String x = lines.get(i);
            String from = x.substring(0, x.indexOf(" ="));
            if(from.charAt(from.length() - 1) == 'A') list.add(from);
            String left = x.substring(x.indexOf("(") + 1, x.indexOf(","));
            String right = x.substring(x.indexOf(",") + 2, x.indexOf(")"));
            adj.put(from, new String[]{left, right});
        }

        list.stream().mapToInt(curr -> {
            int i = -1, steps = 0;
            while(curr.charAt(curr.length() - 1) != 'Z'){
                steps++;
                i = (i + 1) % dirs.length();
                char d = dirs.charAt(i);
                curr = adj.get(curr)[d == 'L' ? 0 : 1];
            }
            return steps;
        }).forEach(i -> System.out.print(i + ", "));
        // the question implicitly assumes there won't be self-loop (like EEE->EEE, EEE) after **Z so,
        // and that always get back to **Z. Hence, the LCM
        // 9858474970153
    }
}
