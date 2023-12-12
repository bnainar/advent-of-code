package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {
    public static void print(String format, Object... args) {
        System.out.printf((format) + "%n", args);
    }

    public static int[] getIntArray(String line, String regex) {
        return getNumberStream(line, regex)
                .mapToInt(Integer::parseInt)
                .toArray();
    }
    public static ArrayList<Integer> getIntList(String line, String regex) {
        return (ArrayList<Integer>) getNumberStream(line, regex)
                .mapToInt(Integer::parseInt)
                .boxed().collect(Collectors.toList());
    }

    public static long[] getLongArray(String line, String regex) {
        return getNumberStream(line, regex)
                .mapToLong(Long::parseLong)
                .toArray();
    }

    // " +|[^0-9-]+"
    public static Stream<String> getNumberStream(String line, String regex) {
        return Arrays.stream(line.split(regex))
                .filter(s -> !s.isBlank());
    }

    public static ArrayList<Long> getLongList(String line, String regex) {
        return (ArrayList<Long>) getNumberStream(line, regex)
                .mapToLong(Long::parseLong)
                .boxed()
                .collect(Collectors.toList());
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(getLongList("  23    45 2 1 32 44", " +"));
        System.out.println(Arrays.toString(getLongArray(" Range 2 : 23 . hi as  45 2 1 32 44", " +|[^0-9-]+")));
        System.out.println(Arrays.toString(getIntArray("test3 4. -5 23 ..... -32", " +|[^0-9-]+")));
        // TODO double arrays
    }
}
