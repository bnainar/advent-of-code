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
            var s = lines.get(i).split("[^A-Z]+");
            adj.put(s[0], new String[]{s[1], s[2]});
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
