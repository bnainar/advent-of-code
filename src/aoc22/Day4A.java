package aoc22;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static utils.Utils.print;

public class Day4A {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:/dev/advent-of-code/inputs/t"));
        int ans = 0;
        while(sc.hasNextLine())
            if(solve(sc.nextLine()))
                ans += 1;
        print("%d", ans);
    }
    static boolean solve(String str){
        int[][] idx = new int[2][2];
        int num = 0, i = 0;
        for(char c: str.toCharArray())
            if(c == ',' || c == '-'){
                idx[i / 2][i & 1] = num;
                num = 0;
                i++;
            }
            else
                num = (num * 10) + (c - '0');
        idx[1][1] = num;
        return isContained(idx[0], idx[1]) || isContained(idx[1], idx[0]);
    }

    /**
     * @param a range 1
     * @param b range 2
     * @return true if a fully contains b
     */
    static boolean isContained(int[] a, int[] b){
        return a[0] <= b[0] && a[1] >= b[1];
    }
}
