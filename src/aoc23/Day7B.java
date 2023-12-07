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

    record Node(String hand, int bid, Type type) { }
    static Comparator<Node> customComparator = (a, b) -> {
        if (!a.type.equals(b.type)) {
            return b.type.compareTo(a.type);
        }
        for (int i = 0; i < a.hand.length(); i++) {
            if (a.hand.charAt(i) != b.hand.charAt(i)) {
                return cardStrength(b.hand.charAt(i)) - cardStrength(a.hand.charAt(i));
            }
        }
        throw new IllegalArgumentException();
    };
    public static void main(String[] args) throws IOException {
        String path = "C:/dev/advent-of-code/inputs/t";
        var lines = Files.readAllLines(Paths.get(path));
        var list = new ArrayList<Node>();
        for (String line : lines) {
            String hand = line.split(" ")[0];
            int bid = Integer.parseInt(line.split(" ")[1]);
            ArrayList<String> temp = new ArrayList<>();
            generateAllCombinations(new StringBuilder(hand), 0, temp);
            var newList = new ArrayList<Node>();
            for (String h : temp) {
                try {
                    newList.add(new Node(h, 0, getType(h)));
                } catch (IllegalArgumentException ignored) {}
            }
            newList.sort(customComparator);
            int n = newList.size() - 1;
            list.add(new Node(hand, bid, newList.get(n).type));
        }
        list.sort(customComparator);
        int ans = 0;
        for (int i = 0; i < list.size(); i++)
            ans += (i + 1) * list.get(i).bid;

        System.out.println(ans);
    }

    static char[] ranks = {'A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'J'};


    private static void generateAllCombinations(StringBuilder hand, int i, ArrayList<String> temp) {
        if (i == hand.length()) {
            temp.add(hand.toString());
            return;
        }
        if (hand.charAt(i) == 'J') {
            for (char r : ranks) {
                if (r == 'J') continue;
                var sb = new StringBuilder(hand);
                sb.setCharAt(i, r);
                generateAllCombinations(sb, i + 1, temp);
            }
        } else {
            generateAllCombinations(hand, i + 1, temp);
        }
    }

    private static int cardStrength(char c) {
        for (int i = 0; i < ranks.length; i++) {
            if (c == ranks[i])
                return i;
        }
        throw new IllegalArgumentException();
    }

    private static Type getType(String hand) {
        var arr = hand.toCharArray();
        var map = new HashMap<Character, Integer>();
        for (char c : arr) {
            if (c != 'J')
                map.put(c, map.getOrDefault(c, 0) + 1);
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
        throw new IllegalArgumentException();
    }

}
