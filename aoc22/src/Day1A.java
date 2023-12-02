import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1A {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:/dev/leetcode/aoc/inputs/ca.txt"));
        int max = Integer.MIN_VALUE;
        int sum = 0;
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            if(line.isEmpty()){
               max = Math.max(max, sum);
               sum = 0;
            }
            else{
                sum += Integer.parseInt(line);
            }
        }
        System.out.println(max);
    }
}
