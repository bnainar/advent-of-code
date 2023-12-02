package aoc23;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Day1A {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:/dev/leetcode/aoc/inputs/day1.txt"));
        int sum = 0;
        while (sc.hasNextLine()) {
            sum += solve(sc.nextLine());
        }
        System.out.println(sum);
    }

    static int solve(String str) {
        int first = -1, last = -1;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isDigit(c)) {
                if (first == -1) {
                    first = last = c - '0';
                } else {
                    last = c - '0';
                }
            }
        }
        return first * 10 + last;
    }
}
