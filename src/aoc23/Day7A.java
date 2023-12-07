package aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day7A {
    enum Type{
        FIVE,
        FOUR,
        FULL,
        THREE,
        TWO_PAIR,
        ONE_PAIR,
        HIGH_CARD
    }
    record Node(String hand, int bid, Type type){}
    public static void main(String[] args) throws IOException {
        String path = "C:/dev/advent-of-code/inputs/t";
        var lines =  Files.readAllLines(Paths.get(path));
        var list = new ArrayList<Node>();
        for (String line : lines) {
            String hand = line.split(" ")[0];
            int bid = Integer.parseInt(line.split(" ")[1]);
            Type t = getType1(hand);
            list.add(new Node(hand, bid, t));
        }
        list.sort((a, b) -> {
            if (a.type != b.type) {
                return b.type.compareTo(a.type);
            } else {
                for (int i = 0; i < a.hand.length(); i++) {
                    if (a.hand.charAt(i) != b.hand.charAt(i)) {
                        return cardStrength(b.hand.charAt(i)) - cardStrength(a.hand.charAt(i));
                    }
                }
                throw new IllegalArgumentException();
            }
        });
        int ans = 0;
        for(int i = 0; i < list.size(); i++){
            int rank = i + 1;
            ans += rank * list.get(i).bid;
        }
        System.out.println(ans);
    }

    private static int cardStrength(char c) {
        char[] ranks = {'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2'};
        for(int i = 0; i < ranks.length; i++){
            if(c == ranks[i])
                return i;
        }
        throw new IllegalArgumentException();
    }

    private static Type getType1(String hand) {
        var map = new HashMap<Character, Integer>();
        for(char c: hand.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        Collection<Integer> values = map.values();
        List<Integer> vals = new ArrayList<>(values);
        vals.sort(Collections.reverseOrder());
        if(map.size() == 5) return Type.HIGH_CARD;
        if(map.size() == 1) return Type.FIVE;
        else if(vals.equals(List.of(4, 1))) return Type.FOUR;
        else if(vals.equals(List.of(3, 2))) return Type.FULL;
        else if(vals.equals(List.of(3, 1, 1))) return Type.THREE;
        else if(vals.equals(List.of(2, 2, 1))) return Type.TWO_PAIR;
        else if(vals.equals(List.of(2, 1, 1, 1))) return Type.ONE_PAIR;
        throw new IllegalArgumentException();
    }

}
