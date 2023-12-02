package aoc22;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static utils.Utils.print;

public class Day3A {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:/dev/advent-of-code/inputs/t"));
        int sum = 0;
        while(sc.hasNextLine()){
            char res = solve(sc.nextLine());
            if(res <= 'Z')
                sum += (res - 'A') + 27;
            else
                sum += (res - 'a') + 1;
        }
        print("%d", sum);
    }
    static char solve(String str){
        int len = str.length();
        boolean[] freq = new boolean[128];
        for(int i = 0; i < len / 2; i++)
            freq[str.charAt(i)] = true;

        for(int i = len / 2; i < len; i++)
            if(freq[str.charAt(i)])
                return str.charAt(i);

        return ' ';
    }
}
