package aoc23;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day3A {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:/dev/advent-of-code/inputs/t"));
        int sum = 0, C = 0;
        ArrayList<String> arr = new ArrayList<>();
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            C = line.length();
            arr.add(line);
        }
        for(int i = 0; i < arr.size(); i++){
            for(int j = 0; j < arr.get(i).length(); j++){
                if(Character.isDigit(arr.get(i).charAt(j))){

                    int start = j;
                    int num = 0;
                    while(j < arr.get(i).length() && Character.isDigit(arr.get(i).charAt(j))){
                        num = (num * 10) + (arr.get(i).charAt(j) - '0');
                        j++;
                    }
                    j--;
                    boolean res = canInclude(i, j, arr, C, start);

                    if(res) {


                        sum += num;
                        System.out.println(arr.get(i).substring(start, j + 1) + (res) + (num));
                    }
                }
            }
        }
        System.out.println(sum);
    }

    private static boolean canInclude(int i, int j, ArrayList<String> arr, int C, int start) {

        int minCol = Math.max(0, start - 1), minRow = Math.max(i - 1, 0);
        int maxCol = Math.min(j + 1, C - 1), maxRow = Math.min(i + 1, arr.size() - 1);
        for(int x = minRow; x <= maxRow; x++){
            for(int y = minCol; y <= maxCol; y++){
                char c = arr.get(x).charAt(y);
                if(!Character.isDigit(c) && c != '.')
                        return true;
            }
        }
        return false;
    }

}
