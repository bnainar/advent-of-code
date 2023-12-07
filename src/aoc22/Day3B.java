package aoc22;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static utils.Utils.print;

public class Day3B {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:/dev/advent-of-code/inputs/t"));
        int sum = 0;
        while(sc.hasNextLine()){
            int[] freq = new int[128];
            for(int i = 0; i < 3; i++)
                for(char c : sc.nextLine().toCharArray())
                    freq[c] |= 1 << i;
            char badge = ' ';
            for(int i = 0; i < 128 && badge == ' '; i++)
                if(freq[i] == 7)
                    badge = (char) i;
            if(badge <= 'Z'){
                sum += (badge - 'A') + 27;
            }
            else{
                sum += (badge - 'a') + 1;
            }
        }
        print("%d", sum);
    }
}
