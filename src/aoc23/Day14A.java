package aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Day14A {
    public static void main(String[] args) throws IOException {
        String path = "C:/dev/advent-of-code/inputs/t";
        var lines = (ArrayList<String>) Files.readAllLines(Paths.get(path));

        for (String line : lines) {
            System.out.println(line);
        }
        int ans = tillNorth(lines);
        System.out.println(ans);
    }

    private static int tillNorth(ArrayList<String> lines) {
        int R = lines.size(), C = lines.get(0).length();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                char c = lines.get(i).charAt(j);
                if (c == 'O') {
                    int newRow = i;
                    while (newRow - 1 >= 0 && lines.get(newRow - 1).charAt(j) == '.') {
                        newRow--;
                    }
                    var sb = new StringBuilder(lines.get(i));
                    sb.setCharAt(j, '.');
                    lines.set(i, sb.toString());
                    sb = new StringBuilder(lines.get(newRow));
                    sb.setCharAt(j, 'O');
                    lines.set(newRow, sb.toString());
                }
            }
        }
        System.out.println("new");
        for (String line : lines) {
            System.out.println(line);
        }
        int ans = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                char c = lines.get(i).charAt(j);
                if (c == 'O') {
                    if (lines.get(i).charAt(j) == 'O') ans += R - i;
                }
            }
            System.out.println(ans);
        }
        return ans;
    }

}
