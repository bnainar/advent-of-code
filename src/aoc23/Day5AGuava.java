package aoc23;

import com.google.common.collect.Range;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static utils.Utils.getLongList;


public class Day5AGuava {
    static List<List<List<Long>>> transformations = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        String path = "C:/dev/advent-of-code/inputs/t";
        var arr = Files.readAllLines(Paths.get(path));
        List<Long> seeds = getLongList(arr.get(0).split(": ")[1]);

        for (int i = 2; i < arr.size(); i += 2) {
            List<List<Long>> map = new ArrayList<>();
            while (i + 1 < arr.size() && !arr.get(i + 1).isBlank()) {
                map.add(getLongList(arr.get(i + 1)));
                i++;
            }
            transformations.add(map);
        }

        for (var ranges : transformations)
            seeds = seeds.stream().mapToLong(s -> {
                for (var range : ranges) {
                    long source = range.get(1), d = range.get(0), r = range.get(2);
                    // this range covers the curr seed
                    if (Range.closedOpen(source, source + r).contains(s)) {
                        return s - source + d;
                    }
                }
                return s; // no ranges found
            }).boxed().collect(Collectors.toList());
        System.out.println(seeds.stream().min(Long::compareTo));
    }
}
