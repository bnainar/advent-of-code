package aoc22;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

import static utils.Utils.*;

public class Day6 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:/dev/advent-of-code/inputs/t"));
        var line = sc.nextLine();
        print("%d", solve1(line));
        print("%d", solve2(line));
        System.out.println("test");
    }

    private static int solve1(String line) {
        var set = new HashSet<Character>();
        for(int i = 0; i < line.length(); i++){
            if(!set.add(line.charAt(i))){
                set.clear(); // restart
                set.add(line.charAt(i));
            }
            else if(set.size() == 4)
                return i + 1;
        }
        return -1;

    }
    private static int solve2(String s) {
        int i = 0, j = 0;
        var freq = new int[128];
        while(j < s.length()){
            char c = s.charAt(j);
            if(++freq[c] == 2)
                while(freq[c] == 2)
                    freq[s.charAt(i++)]--; // shrink the window
            if(++j - i == 14)
                return j;
        }
        return -1;
    }
}
