import aoc23.Day16;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import static utils.Utils.*;

public class DayX {
    enum Dir {LEFT, RIGHT, UP, DOWN}

    static final Map<Dir, int[]> map = Map.of(
            Dir.LEFT, new int[]{0, -1},
            Dir.RIGHT, new int[]{0, 1},
            Dir.UP, new int[]{-1, 0},
            Dir.DOWN, new int[]{1, 0});
    public static void main(String[] args) throws IOException {
        String path = "C:/dev/advent-of-code/inputs/d";
        Scanner sc = new Scanner(new File(path));
        var input = (ArrayList<String>) Files.readAllLines(Paths.get(path));
        int R = input.size(), C = input.get(0).length();
        long ans = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {

            }
        }
        System.out.println(ans);
    }
}
