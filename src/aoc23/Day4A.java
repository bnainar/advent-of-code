package aoc23;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

import static utils.Utils.print;

public class Day4A {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("C:/dev/advent-of-code/inputs/t"));
        int ans = 0;
        while(sc.hasNextLine()){
            var line = sc.nextLine();
            var sp = line.split(":");
            var win = sp[1].substring(0, sp[1].indexOf("|"));
            var set  = new HashSet<Integer>();
            for(var n: win.trim().split(" ")){

               if(!n.isBlank()) set.add(Integer.parseInt(n));
            }
            var me = sp[1].substring(sp[1].indexOf("|") + 1);
            int count = 0;
            for(var n: me.trim().split(" "))
                if(!n.isBlank())
                    if(set.contains(Integer.parseInt(n)))
                        if(count == 0) count = 1;
                        else count *= 2;
            ans += count;
        }
        print("%d hi", ans);
    }


}
