package aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static utils.Utils.getLongList;

public class Day5B {
    static List<List<List<Long>>> maps = new ArrayList<>();

    /**
     * Start is inclusive, but End is exclusive
     * @param start
     * @param end
     */
    record Range(long start, long end){}
    public static void main(String[] args) throws IOException {
        String path = "C:/dev/advent-of-code/inputs/t";
        var arr = Files.readAllLines(Paths.get(path));
        var inputs = getLongList(arr.get(0).split(": ")[1]);
        var seeds = new ArrayDeque<Range>();
        for(int i = 0; i + 1 < inputs.size(); i += 2){
            seeds.offer(new Range(inputs.get(i), inputs.get(i) + inputs.get(i + 1)));
        }
        for(int i = 2; i < arr.size(); i += 2){
            List<List<Long>> map = new ArrayList<>();
            while(i + 1 < arr.size() && !arr.get(i + 1).isBlank()) {
                map.add(getLongList(arr.get(i + 1)));
                i++;
            }
            maps.add(map);
        }

        for (var map: maps) {
            var newSeeds = new HashSet<Range>();
            o:while(!seeds.isEmpty()) {
                Range currSeed = seeds.poll();
                long sStart = currSeed.start, sEnd = currSeed.end;
                for (var mapRange : map) {
                    long rStart = mapRange.get(1), rEnd = rStart + mapRange.get(2);
                    long diff = mapRange.get(1) - mapRange.get(0);
                    // gets the intersection between 2 intervals
                    long[] intersection = new long[]{Math.max(sStart, rStart), Math.min(sEnd, rEnd)};
                    if (intersection[0] < intersection[1]) {
                        if (sStart < rStart) { // re-process the left segment not covered by the range
                            seeds.offer(new Range(sStart, rStart));
                        }
                        if (rEnd < sEnd) { // re-process the right segment not covered by the range
                            seeds.offer(new Range(rEnd, sEnd));
                        }
                        newSeeds.add(new Range(intersection[0] - diff, intersection[1] - diff));
                        continue o;
                    }
                }
                // No map range found for this seed range
                newSeeds.add(currSeed);
            }
            seeds = new ArrayDeque<>(newSeeds);
        }
        System.out.println(seeds.stream().mapToLong(Range::start).min());
    }
}

