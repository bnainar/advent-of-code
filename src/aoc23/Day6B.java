package aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static utils.Utils.getLongArray;


public class Day6B {
    public static void main(String[] args) throws IOException {
        String path = "C:/dev/advent-of-code/inputs/t";
        var arr = Files.readAllLines(Paths.get(path));
        var times = getLongArray(arr.get(0).replace(" ", ""))[0];
        var dist = getLongArray(arr.get(1).replace(" ", ""))[0];

        System.out.println(waysToBeat(times, dist));
    }
    private static int waysToBeat(long time, long d) {
        int count = 0;
        for(long charge = 0; charge <= time; charge++){
            var remain = time - charge;
            var newDist = charge * remain; // speed * distance
            if(newDist > d) count++;
        }
        return count;
    }
}
