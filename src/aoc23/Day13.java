package aoc23;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static utils.Utils.print;

public class Day13 {
    public static void main(String[] args) throws IOException {
        String path = "C:/dev/advent-of-code/inputs/t";
        Scanner sc = new Scanner(new File(path));
        var lines = new ArrayList<String>();
        int ans1 = 0, ans2 = 0;
        while (sc.hasNext() || !lines.isEmpty()) {
            var l = sc.hasNextLine() ? sc.nextLine() : "";
            if (l.isBlank()) {
                ans1 += part1(lines);
                ans2 += part2(lines);
                lines.clear();
            } else {
                lines.add(l);
            }
        }

        print("Part 1: %d\nPart 2: %d", ans1, ans2);
    }

    private static int part1(ArrayList<String> matrix) {
        int R = matrix.size(), C = matrix.get(0).length();
        o:
        for (int row = 0; row < R - 1; row++) {
            for (int i = row, j = i + 1; i >= 0 && j < R; i--, j++)
                for (int c = 0; c < C; c++)
                    if (matrix.get(i).charAt(c) != matrix.get(j).charAt(c))
                        continue o;

            return 100 * (row + 1);
        }
        o:
        for (int col = 0; col < C - 1; col++) {
            for (int i = col, j = i + 1; i >= 0 && j < C; i--, j++)
                for (String line : matrix)
                    if (line.charAt(i) != line.charAt(j))
                        continue o;

            return col + 1;
        }
        return -1;
    }

    private static int part2(ArrayList<String> matrix) {
        int R = matrix.size(), C = matrix.get(0).length();

        for (int row = 0; row < R - 1; row++) {
            int mismatches = 0;
            for (int i = row, j = i + 1; i >= 0 && j < R; i--, j++)
                for (int c = 0; c < C; c++)
                    if (matrix.get(i).charAt(c) != matrix.get(j).charAt(c))
                        ++mismatches;

            if (mismatches == 1) return 100 * (row + 1);
        }

        for (int col = 0; col < C - 1; col++) {
            int mismatches = 0;
            for (int i = col, j = i + 1; i >= 0 && j < C; i--, j++)
                for (String line : matrix)
                    if (line.charAt(i) != line.charAt(j))
                        ++mismatches;

            if (mismatches == 1) return col + 1;
        }
        return -1;
    }
}
