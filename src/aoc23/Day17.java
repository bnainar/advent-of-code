package aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day17 {
    enum Dir {WEST, EAST, NORTH, SOUTH}

    static final Map<Dir, int[]> dirMap = Map.of(
            Dir.WEST, new int[]{0, -1},
            Dir.EAST, new int[]{0, 1},
            Dir.NORTH, new int[]{-1, 0},
            Dir.SOUTH, new int[]{1, 0});
    static final Map<Dir, Dir> opposite = Map.of(
            Dir.EAST, Dir.WEST,
            Dir.WEST, Dir.EAST,
            Dir.NORTH, Dir.SOUTH,
            Dir.SOUTH, Dir.NORTH);

    record Node(int x, int y, int streak, Dir dir) {
    }


    public static void main(String[] args) throws IOException {
        String path = "C:/dev/advent-of-code/inputs/t";
        var input = (ArrayList<String>) Files.readAllLines(Paths.get(path));
        System.out.println("Part 1: " + getMinPathCost(input, 0, 3));
        System.out.println("Part 2: " + getMinPathCost(input, 4, 10));
    }

    private static int getMinPathCost(ArrayList<String> input, int minSteps, int maxSteps) {
        int R = input.size(), C = input.get(0).length();
        final int INF = (int) 1e9;
        Node start1 = new Node(0, 0, 1, Dir.EAST);
        Node start2 = new Node(0, 0, 1, Dir.SOUTH);
        Node END = new Node(R - 1, C - 1, 0, Dir.EAST); // only the end's x and y matter

        var minCosts = new HashMap<Node, Integer>();
        var q = new PriorityQueue<Node>(Comparator.comparingInt(a -> minCosts.getOrDefault(a, INF)));
        minCosts.put(start1, 0);
        minCosts.put(start2, 0);
        q.offer(start1);
        q.offer(start2);

        while (!q.isEmpty()) {
            var from = q.poll();
            for (var d : Dir.values()) {
                if (opposite.get(from.dir).equals(d)) continue; // no 180
                if (d.equals(from.dir) && from.streak == maxSteps)
                    continue;
                if (!d.equals(from.dir) && from.streak < minSteps)
                    continue;

                var res = applyDir(from, d, R, C);
                if (res.isEmpty()) continue;
                var next = res.get();

                int fromCost = minCosts.get(from), toCost = minCosts.getOrDefault(next, INF);
                int gridVal = input.get(next.x).charAt(next.y) - '0';
                int newCost = fromCost + gridVal;

                if (next.x == END.x && next.y == END.y) {
                    return newCost;
                }
                if (newCost < toCost) {
                    q.offer(next);
                    minCosts.put(next, newCost);
                }
            }
        }
        return INF;
    }

    private static Optional<Node> applyDir(Node from, Dir d, int R, int C) {
        var delta = dirMap.get(d);
        int nx = from.x + delta[0], ny = from.y + delta[1];
        if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
            int streak = from.dir.equals(d) ? from.streak + 1 : 1;
            return Optional.of(new Node(nx, ny, streak, d));
        }
        return Optional.empty();
    }
    /*
    Part 1: 1128
    Part 2: 1268
     */
}
