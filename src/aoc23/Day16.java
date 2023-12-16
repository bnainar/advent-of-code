package aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static utils.Utils.print;

public class Day16 {
    enum Dir {LEFT, RIGHT, UP, DOWN}

    static final Map<Dir, int[]> map = Map.of(
            Dir.LEFT, new int[]{0, -1},
            Dir.RIGHT, new int[]{0, 1},
            Dir.UP, new int[]{-1, 0},
            Dir.DOWN, new int[]{1, 0});

    record Beam(int x, int y, Dir dir) {
    }

    static int R = 0;
    static int C = 0;

    public static void main(String[] args) throws IOException {
        String path = "C:/dev/advent-of-code/inputs/t";
        var input = (ArrayList<String>) Files.readAllLines(Paths.get(path));
        R = input.size();
        C = input.get(0).length();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < R; i++) {
            max = Math.max(max, f(new Beam(i, 0, Dir.RIGHT), input));
            max = Math.max(max, f(new Beam(i, C - 1, Dir.LEFT), input));
        }
        for (int j = 0; j < C; j++) {
            max = Math.max(max, f(new Beam(0, j, Dir.DOWN), input));
            max = Math.max(max, f(new Beam(R - 1, j, Dir.UP), input));
        }
        print("Part 1: %d", f(new Beam(0, 0, Dir.RIGHT), input));
        print("Part 2: %d", max);
    }

    private static int f(Beam seed, ArrayList<String> input) {
        var vis = new boolean[R][C];
        var q = new ArrayDeque<Beam>();
        var set = new HashSet<Beam>();
        q.offer(seed);
        while (!q.isEmpty()) {
            var curr = q.poll();
            int x = curr.x, y = curr.y;
            if (!set.add(curr)) continue;
            vis[x][y] = true;
            char tile = input.get(x).charAt(y);
            switch (tile) {
                case '.' -> redirectBeam(q, x, y, curr.dir);
                case '|' -> {
                    switch (curr.dir) {
                        case UP, DOWN -> redirectBeam(q, x, y, curr.dir);
                        default -> {
                            redirectBeam(q, x, y, Dir.UP);
                            redirectBeam(q, x, y, Dir.DOWN);
                        }
                    }
                }
                case '-' -> {
                    switch (curr.dir) {
                        case LEFT, RIGHT -> redirectBeam(q, x, y, curr.dir);
                        default -> {
                            redirectBeam(q, x, y, Dir.LEFT);
                            redirectBeam(q, x, y, Dir.RIGHT);
                        }
                    }
                }
                default -> {
                    switch (curr.dir) {
                        case LEFT -> redirectBeam(q, x, y, tile == '/' ? Dir.DOWN : Dir.UP);
                        case RIGHT -> redirectBeam(q, x, y, tile == '/' ? Dir.UP : Dir.DOWN);
                        case UP -> redirectBeam(q, x, y, tile == '/' ? Dir.RIGHT : Dir.LEFT);
                        case DOWN -> redirectBeam(q, x, y, tile == '/' ? Dir.LEFT : Dir.RIGHT);
                    }
                }
            }
        }
        int ans = 0;
        for (var row : vis)
            for (var t : row)
                if (t) ans++;
        return ans;
    }

    private static void redirectBeam(ArrayDeque<Beam> q, int x, int y, Dir d) {
        var delta = map.get(d);
        int nx = x + delta[0], ny = y + delta[1];
        if (nx < 0 || nx >= R || ny < 0 || ny >= C) return;
        q.offer(new Beam(nx, ny, d));
    }

}
