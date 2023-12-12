package aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static utils.Utils.getIntArray;

public class Day6A {
    public static void main(String[] args) throws IOException {
        String path = "C:/dev/advent-of-code/inputs/t";
        var arr = Files.readAllLines(Paths.get(path));
        var times = getIntArray(arr.get(0), " ");
        var dist = getIntArray(arr.get(1), " ");
        int ans = 1;
        for (int i = 0; i < times.length; i++) {
            ans *= getDist(times[i], dist[i]);
        }
        System.out.println(ans);
    }

    private static int getDist(int time, int d) {
        int count = 0;
        for (int charge = 0; charge <= time; charge++) {
            int remain = time - charge;
            int newDist = charge * remain;
            if (newDist > d) count++;
        }
        return count;
    }
}
