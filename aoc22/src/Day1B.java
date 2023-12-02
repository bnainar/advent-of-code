import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day1B {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:/dev/leetcode/aoc/inputs/ca.txt"));
        int sum = 0;
        var list = new ArrayList<Integer>();
        while(sc.hasNextLine() || sum != 0){
            String line = sc.hasNextLine() ? sc.nextLine() : "";
            if(line.isEmpty()){
                list.add(sum);
                sum = 0;
            }
            else{
                sum += Integer.parseInt(line);
            }
        }
        list.sort(Collections.reverseOrder());
        System.out.println(list.get(0) + list.get(1) + list.get(2));
    }
}
