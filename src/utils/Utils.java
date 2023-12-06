package utils;

import java.util.Arrays;
import java.util.stream.Stream;

public class Utils {
    public static void print(String format, Object... args) {
        System.out.printf((format) + "%n", args);
    }
    public static int[] getIntArray(String line) {
        return getNumberStream(line)
                .mapToInt(Integer::parseInt)
                .toArray();
    }
    public static long[] getLongArray(String line) {
        return getNumberStream(line)
                .mapToLong(Long::parseLong)
                .toArray();
    }

    private static Stream<String> getNumberStream(String line) {
        return Arrays.stream(line.split("[^0-9]+| +"))
                .filter(s -> !s.isBlank());
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }
    public static void main(String[] args){
        System.out.println(Arrays.toString(getIntArray("  23    45 2 1 32 44")));
        System.out.println(Arrays.toString(getIntArray(" Range 2: 23 . hi as  45 2 1 32 44")));
    }
}
