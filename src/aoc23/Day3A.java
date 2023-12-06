package aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Day3A {
    public static void main(String[] args) throws IOException {
        int sum = 0;
        var arr = (ArrayList<String>) Files.readAllLines(Paths.get("C:/dev/advent-of-code/inputs/t"));
        for(int i = 0; i < arr.size(); i++){
            String line = arr.get(i);
            for(int j = 0; j < line.length(); j++)
                if(Character.isDigit(line.charAt(j))){
                    int start = j, num = line.charAt(j) - '0';
                    while(j + 1 < line.length() && Character.isDigit(line.charAt(j + 1)))
                        num = (num * 10) + (line.charAt(++j) - '0');
                    if(canInclude(i, j++, arr, start))  sum += num;
                }
        }
        System.out.println(sum);
    }

    private static boolean canInclude(int i, int j, ArrayList<String> arr, int start) {
        int minCol = Math.max(0, start - 1), minRow = Math.max(i - 1, 0);
        int maxCol = Math.min(j + 1, arr.get(i).length() - 1), maxRow = Math.min(i + 1, arr.size() - 1);
        for(int x = minRow; x <= maxRow; x++)
            for(int y = minCol; y <= maxCol; y++){
                char c = arr.get(x).charAt(y);
                if(!Character.isDigit(c) && c != '.')
                        return true;
            }
        return false;
    }

}
