import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2B {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:/dev/advent-of-code/inputs/submit"));
        int sum = 0;
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            char them = line.charAt(0);
            them += 'X' - 'A';
            char me = switch (line.charAt(2)) {
                case 'X' -> (char) ((them - 'X' - 1 + 3) % 3 + 'X');
                case 'Y' -> them;
                default -> (char) ((them - 'X' + 1 + 3) % 3 + 'X');
            };
            if(me == them)
                sum += 4 + (me - 'X');
            else if(me - 1 == them || me + 2 == them)
                sum += 7 + (me - 'X');
            else
                sum += 1 + (me - 'X');
        }
        System.out.println(sum);
    }
}
