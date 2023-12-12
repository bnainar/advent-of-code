package aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Day11 {
    record Galaxy(int x, int y) {
    }

    public static void main(String[] args) throws IOException {
        String path = "C:/dev/advent-of-code/inputs/t";
        var input = (ArrayList<String>) Files.readAllLines(Paths.get(path));
        int R = input.size(), C = input.get(0).length();
        boolean[] rowHasG = new boolean[R], colHasG = new boolean[C];
        var galaxies = new ArrayList<Galaxy>();
        for (int i = 0; i < R; i++)
            for (int j = 0; j < C; j++)
                if (input.get(i).charAt(j) == '#') {
                    rowHasG[i] = colHasG[j] = true;
                    galaxies.add(new Galaxy(i, j));
                }

        int[] rowPrefix = new int[R], colPrefix = new int[C]; // stores the actual row, col values

        int EXPANSION = 1000000;
        for (int i = 0; i < R; i++) {
            int prev = i == 0 ? 0 : rowPrefix[i - 1];
            rowPrefix[i] = prev + (rowHasG[i] ? 1 : EXPANSION);
        }
        for (int j = 0; j < C; j++) {
            int prev = j == 0 ? 0 : colPrefix[j - 1];
            colPrefix[j] = prev + (colHasG[j] ? 1 : EXPANSION);
        }

        long ans = 0; // change #2 for Part 2 :-D
        for (var n : galaxies)
            for (var m : galaxies) {
                int x1 = rowPrefix[n.x], y1 = colPrefix[n.y];
                int x2 = rowPrefix[m.x], y2 = colPrefix[m.y];
                ans += Math.abs(x1 - x2) + Math.abs(y1 - y2);
            }

        System.out.println(ans / 2); // 519939907614
    }
}
