package aoc22;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import static utils.Utils.getIntArray;

public class Day5B {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:/dev/advent-of-code/inputs/t"));
        var stack = new HashMap<Integer, StringBuilder>();
        buildStack(sc, stack);
        while(sc.hasNextLine()){
            int[] ins = getIntArray(sc.nextLine());
            var from = stack.get(ins[1]);
            String pop = from.substring(from.length() - ins[0]);
            from.setLength(from.length() - ins[0]);
            stack.get(ins[2]).append(pop);
        }
        stack.forEach((k, v) -> System.out.print(v.charAt(v.length() - 1)));
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
        sc.nextLine();
    }
}
