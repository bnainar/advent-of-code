package aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day7B {
    enum Type {
        FIVE,
        FOUR,
        FULL,
        THREE,
        TWO_PAIR,
        ONE_PAIR,
        HIGH_CARD
    }

    record Node(String hand, int bid, Type type) {
    }
    // 250680510 too big
    public static void main(String[] args) throws IOException {
        String path = "C:/dev/advent-of-code/inputs/t";
        var lines = (ArrayList<String>) Files.readAllLines(Paths.get(path));

        var list = new ArrayList<Node>();
        for (int i = 0; i < lines.size(); i++) {
            String hand = lines.get(i).split(" ")[0];
            int bid = Integer.parseInt(lines.get(i).split(" ")[1]);
            System.out.println(hand);
            temp.clear();
            generateAllCombinations(new StringBuilder(hand), 0);
            System.out.println(temp);
            var newList = new ArrayList<Node>();
            for (String h : temp) {
                var res = getType(h);
                if (res != null) {
                    newList.add(new Node(h, 0, res));
                }
            }
            newList.sort((a, b) -> {
                if (a.type != b.type) {
                    return b.type.compareTo(a.type);
                } else {
                    for (int j = 0; j < a.hand.length(); j++) {
                        if (a.hand.charAt(j) != b.hand.charAt(j)) {
                            return getIndex(b.hand.charAt(j)) - getIndex(a.hand.charAt(j));
                        }
                    }
                    throw new IllegalArgumentException();
                }
            });
            Type t = getType(hand);
            int n = newList.size() - 1;
            list.add(new Node(
                    hand,
                    bid,
                    newList.get(n).type));
        }
        list.sort((a, b) -> {
            if (a.type != b.type) {
                return b.type.compareTo(a.type);
            } else {
                for (int i = 0; i < a.hand.length(); i++) {
                    if (a.hand.charAt(i) != b.hand.charAt(i)) {
                        return getIndex(b.hand.charAt(i)) - getIndex(a.hand.charAt(i));
                    }
                }
                return 0;
            }
        });
        int ans = 0;
        for (int i = 0; i < list.size(); i++) {
            int rank = i + 1;
            ans += rank * list.get(i).bid;
        }
        System.out.println(ans);
    }

    static char[] ranks = {'A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'J'};
    static ArrayList<String> temp = new ArrayList<>();

    private static void generateAllCombinations(StringBuilder hand, int i) {
        if (i == hand.length()) {
            temp.add(hand.toString());
            return;
        }
        if (hand.charAt(i) == 'J') {
            for (char r : ranks) {
                if (r == 'J') continue;
                var sb = new StringBuilder(hand);
                sb.setCharAt(i, r);
                generateAllCombinations(sb, i + 1);
            }
        } else {
            generateAllCombinations(hand, i + 1);
        }
    }

    private static int getIndex(char c) {
        for (int i = 0; i < ranks.length; i++) {
            if (c == ranks[i])
                return i;
        }
        throw new IllegalArgumentException();
    }

    private static Type getType(String hand) {
        var arr = hand.toCharArray();
        Arrays.sort(arr);
        var map = new HashMap<Character, Integer>();
        int jokers = 0;
        for (char c : arr) {
            if (c == 'J') jokers++;
            else map.put(c, map.getOrDefault(c, 0) + 1);
        }
        Collection<Integer> values = map.values();
        List<Integer> vals = new ArrayList<>(values);
        vals.sort(Collections.reverseOrder());
        if (map.size() == 1)
            return Type.FIVE;
        else if (vals.equals(List.of(4, 1)))
            return Type.FOUR;
        else if (vals.equals(List.of(3, 2)))
            return Type.FULL;
        else if (vals.equals(List.of(3, 1, 1)))
            return Type.THREE;
        else if (vals.equals(List.of(2, 2, 1)))
            return Type.TWO_PAIR;
        else if (vals.equals(List.of(2, 1, 1, 1)))
            return Type.ONE_PAIR;
        else if (map.size() == 5)
            return Type.HIGH_CARD;
        return null;
    }

}
