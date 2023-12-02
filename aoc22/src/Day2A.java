import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2A {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:/dev/advent-of-code/inputs/submit"));
        int sum = 0;
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            char them = line.charAt(0), me = line.charAt(2);
            them += 'X' - 'A';
            if(me == them){
                System.out.println("Draw");
                sum += 4 + (me - 'X');
            }
            else if(me - 1 == them || me + 2 == them){
                System.out.println("Win");
                sum += 7 + (me - 'X');
            }
            else{
                System.out.println("Lose");
                sum += 1 + (me - 'X');
            }
        }
        System.out.println(sum);
    }
}
