package aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Day5A {
    static List<List<List<Long>>> transformations = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        String path = "C:/dev/advent-of-code/inputs/t";
//        Scanner sc = new Scanner(new File(path));
        var arr = Files.readAllLines(Paths.get(path));
        var seeds = getLongList(arr.get(0).split(": ")[1]);

        for(int i = 2; i < arr.size(); i++){
            int j = i;
            while(j + 1 < arr.size() && !arr.get(j + 1).isBlank()) j++;
            buildMap(i, j, arr);
            i = j + 1;
        }

        for (var ranges: transformations) {
            var newSeeds = new ArrayList<Long>();
            o:for(var s: seeds){
                for (var range : ranges) {
                    long source = range.get(1), d = range.get(0), r = range.get(2);
                    if (s >= source && s < source + r) {
                        newSeeds.add(s - source + d);
                        continue o;
                    }
                }
                newSeeds.add(s);
            }
            seeds = newSeeds;
        }
        System.out.println(seeds.stream().min(Long::compareTo));
    }

    private static ArrayList<Long> getLongList(String line) {
        return (ArrayList<Long>) Arrays.stream(line.split(" ")).mapToLong(Long::parseLong).boxed().collect(Collectors.toList());
    }

    private static void buildMap(int i, int j, List<String> arr) {
        List<List<Long>> temp = new ArrayList<>();
        for(int x = i + 1; x <= j; x++){
            temp.add(getLongList(arr.get(x)));
        }
        transformations.add(temp);
    }

}
