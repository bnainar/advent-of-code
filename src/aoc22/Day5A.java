package aoc22;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import static utils.Utils.print;

public class Day5A {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:/dev/advent-of-code/inputs/t"));
        var stack = new HashMap<Integer, StringBuilder>();
        buildStack(sc, stack);
        stack.forEach((k, v) -> print("%d -> %s", k, v));
        while(sc.hasNextLine()){
            int[] ins = parseInstructions(sc);
            while(ins[0]--> 0){
                var from = stack.get(ins[1]);
                char pop = from.charAt(from.length() - 1);
                from.setLength(from.length() - 1);
                stack.get(ins[2]).append(pop);
            }
        }
//        stack.forEach((k, v) -> print("%d -> %s", k, v));
        stack.forEach((k, v) -> System.out.print(v.charAt(v.length() - 1)));
    }

    private static int[] parseInstructions(Scanner sc) {
        int[] arr = new int[3];
        for(int i = 0; i < 3; i++){
            sc.next();
            arr[i] = sc.nextInt();
        }
        return arr;
    }

    private static void buildStack(Scanner sc, HashMap<Integer, StringBuilder> stack) {
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            if(line.charAt(1) == '1') break;
            for(int i = 1; i < line.length(); i += 4) {
                char c = line.charAt(i);
                if(c != ' ')
                    stack.computeIfAbsent(((i - 1) / 4) + 1, k -> new StringBuilder())
                        .insert(0, c);
            }
        }
    }
}
