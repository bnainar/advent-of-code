import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import static utils.Utils.*;

public class DayX {
    public static void main(String[] args) throws IOException {
        String path = "C:/dev/advent-of-code/inputs/d";
        Scanner sc = new Scanner(new File(path));
        var lines = Files.readAllLines(Paths.get(path));

        int ans = 0;
        System.out.println(ans);
    }

}
