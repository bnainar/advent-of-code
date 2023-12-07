package aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Day7A {
    enum Type {
        HIGH_CARD, ONE_PAIR, TWO_PAIR, THREE, FULL, FOUR, FIVE,
    }

    record Node(String hand, int bid, Type type) {
    }

    static ArrayList<Character> ranks = new ArrayList<>(List.of(
            'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2'));

    public static void main(String[] args) throws IOException {
        String path = "C:/dev/advent-of-code/inputs/t";
        var lines = Files.readAllLines(Paths.get(path));
        var list = new ArrayList<Node>();
        for (String line : lines) {
            String hand = line.split(" ")[0];
            int bid = Integer.parseInt(line.split(" ")[1]);
            Type t = getType1(hand);
            list.add(new Node(hand, bid, t));
        }
        list.sort((a, b) -> {
            if (!a.type.equals(b.type)) {
                return a.type.compareTo(b.type);
            }
            for (int i = 0; i < a.hand.length(); i++) {
                if (a.hand.charAt(i) != b.hand.charAt(i)) {
                    return ranks.indexOf(b.hand.charAt(i)) - ranks.indexOf(a.hand.charAt(i));
                }
            }
            return 0;
        });
        int ans = 0;
        for (int i = 0; i < list.size(); i++) {
            ans += (i + 1) * list.get(i).bid;
        }
        System.out.println(ans);
    }

    private static Type getType1(String hand) {
        var map = new HashMap<Character, Integer>();
        for (char c : hand.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        List<Integer> vals = new ArrayList<>(map.values());
        vals.sort(Collections.reverseOrder());
        if (map.size() == 1) return Type.FIVE;
        if (vals.contains(4)) return Type.FOUR;
        if (vals.contains(3))
            if (vals.contains(2)) return Type.FULL;
            else return Type.THREE;

        if (vals.equals(List.of(2, 2, 1))) return Type.TWO_PAIR;
        if (vals.equals(List.of(2, 1, 1, 1))) return Type.ONE_PAIR;
        return Type.HIGH_CARD;
    }

}
