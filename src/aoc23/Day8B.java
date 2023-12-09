package aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import static utils.Utils.print;

public class Day8B {
    public static void main(String[] args) throws IOException {
        String path = "C:/dev/advent-of-code/inputs/t";
        var lines = (ArrayList<String>) Files.readAllLines(Paths.get(path));
        String d = lines.get(0).trim();
        var adj = new HashMap<String, String[]>();
        var list = new ArrayList<String>();
        for(int i = 2; i < lines.size(); i++){
            var s = lines.get(i).split("[^A-Z]+");
            if(s[0].endsWith("A")) list.add(s[0]);
            adj.put(s[0], new String[]{s[1], s[2]});
        }

        list.forEach(curr -> {
            int i = -1, steps = 0;
            while(!curr.endsWith("Z")){
                steps++;
                i = (i + 1) % d.length();
                curr = adj.get(curr)[d.charAt(i) == 'L' ? 0 : 1];
            }
            print("%d", steps);
        });
        // the input has assumptions like there won't be a self-loop (like EEE->EEE, EEE) after **Z so,
        // and that always get back to **Z, whose cycle length is same as start to cycle entrance. Hence, the LCM
        // 9858474970153
    }
}
