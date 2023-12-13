package aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import static utils.Utils.getLongArray;

public class Day12A {
    public static void main(String[] args) throws IOException {
        String path = "C:/dev/advent-of-code/inputs/t";
        var lines =  Files.readAllLines(Paths.get(path));

        long ans = 0;
        for(var line: lines){
            var x = line.split(" ")[0];
            var nums = getLongArray(line.split(" ")[1], ",");
            ans += solve(x, nums);
        }
        System.out.println(ans);
    }

    private static long solve(String line, long[] nums) {
        var list = new ArrayList<String>();
        generateComb(0, line, new StringBuilder(), list, nums);
        long count = 0;
        o:
        for(var l: list){
            var res = f(l);
            if(nums.length != res.length) continue;
            for(int d = 0; d < nums.length; d++){
                if(nums[d] != res[d]) continue o;
            }
            count++;
        }
        return count;
    }
    private static long[] f(String input) {
        return Arrays.stream(input.split("[.]+")).filter(s -> !s.isBlank()).mapToLong(String::length).toArray();

    }
    // break early like Word Search, dont need to generate all possibilites
    // a combination is true if it reaches the end
    private static void generateComb(int i, String line, StringBuilder sb, ArrayList<String> list, long[] nums) {
        if(i == line.length()){
            list.add(sb.toString());
            return;
        }
        var res = f(sb.toString());
        if(res.length > nums.length) return;
        for(int j = 0; j < res.length; j++){
            if(res[j] > nums[j]) {
                return;
            }
        }

        char c = line.charAt(i);
        if(c != '?'){
            sb.append(c);
        }
        else{
            sb.append('#');
            generateComb(i +1 , line, sb, list, nums);
            sb.deleteCharAt(sb.length() - 1);
            sb.append('.');
        }
        generateComb(i + 1, line, sb, list, nums);
        sb.deleteCharAt(sb.length() - 1);
    }
}
