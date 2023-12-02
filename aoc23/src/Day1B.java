import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Day1B {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:/dev/leetcode/aoc/inputs/day1.txt"));
        int sum = 0;
        while(sc.hasNextLine()){
            sum += solve(sc.nextLine());
        }
        System.out.println(sum);
    }
    static int solve(String str){
        int first = -1, last = -1;
        String[] set = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(Character.isDigit(c)){
                if(first == -1)
                    first = c - '0';
                last = c - '0';
            }
            else
                for(int s = 0; s < set.length; s++)
                    if(str.indexOf(set[s], i) == i){
                        if(first == -1)
                            first = s + 1;
                        last = s + 1;
                        break;
                    }
        }
        return first * 10 + last;
    }
}
