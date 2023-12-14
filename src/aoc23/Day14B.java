package aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static utils.Utils.print;

public class Day14B {
    static ArrayList<String> lines = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        String path = "C:/dev/advent-of-code/inputs/t";
        lines = (ArrayList<String>) Files.readAllLines(Paths.get(path));

        var set = new HashSet<>(Set.of(getHash()));
        var arr = new ArrayList<>(List.of(getHash()));
        int i = 0;
        while (true) {
            tiltNorth();
            tiltWest();
            tiltSouth();
            tiltEast();
            System.out.println(++i);

            if (!set.add(getHash())) break;
            arr.add(getHash());
        }
        int entrance = arr.indexOf(getHash());
        print("Cycle entrance: %d, Detected at iteration %d", entrance, i);
        int limit = 1000000000;
        var finalGrid = arr.get((limit - entrance) % (i - entrance) + entrance).split(",");
        for (var s : finalGrid)
            System.out.println(s);
        System.out.println(calculateLoad(finalGrid));
    }

    private static int calculateLoad(String[] grid) {
        int R = grid.length, C = grid[0].length(), ans = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                char c = grid[i].charAt(j);
                if (c == 'O') {
                    ans += R - i;
                }
            }
        }
        return ans;
    }

    static String getHash() {
        var hash = new StringBuilder();
        for (var l : lines)
            hash.append(l).append(',');
        return hash.toString();
    }

    private static void tiltNorth() {
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
    }

    private static void tiltSouth() {
        int R = lines.size(), C = lines.get(0).length();
        for (int i = R - 1; i >= 0; i--) {
            for (int j = 0; j < C; j++) {
                char c = lines.get(i).charAt(j);
                if (c == 'O') {
                    int newRow = i;
                    while (newRow + 1 < R && lines.get(newRow + 1).charAt(j) == '.') {
                        newRow++;
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
    }

    private static void tiltWest() {
        int R = lines.size(), C = lines.get(0).length();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                char c = lines.get(i).charAt(j);
                if (c == 'O') {
                    int newCol = j;
                    while (newCol - 1 >= 0 && lines.get(i).charAt(newCol - 1) == '.') {
                        newCol--;
                    }
                    var sb = new StringBuilder(lines.get(i));
                    sb.setCharAt(j, '.');
                    sb.setCharAt(newCol, 'O');
                    lines.set(i, sb.toString());
                }
            }
        }
    }

    private static void tiltEast() {
        int R = lines.size(), C = lines.get(0).length();
        for (int i = 0; i < R; i++) {
            for (int j = C - 1; j >= 0; j--) {
                char c = lines.get(i).charAt(j);
                if (c == 'O') {
                    int newCol = j;
                    while (newCol + 1 < C && lines.get(i).charAt(newCol + 1) == '.') {
                        newCol++;
                    }
                    var sb = new StringBuilder(lines.get(i));
                    sb.setCharAt(j, '.');
                    sb.setCharAt(newCol, 'O');
                    lines.set(i, sb.toString());
                }
            }
        }
    }
}
