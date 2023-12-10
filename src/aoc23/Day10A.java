package aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Day10A {
    record Node(int x, int y, int dist){}
    public static void main(String[] args) throws IOException {
        String path = "C:/dev/advent-of-code/inputs/t";
        var lines = (ArrayList<String>) Files.readAllLines(Paths.get(path));
        var q = new ArrayDeque<Node>();
        int R = lines.size(), C = lines.get(0).length();
        boolean[][] vis = new boolean[R][C];
        o:for(int i = 0; i < lines.size(); i++){
            for(int j = 0; j < lines.get(0).length(); j++){
                if(lines.get(i).charAt(j) == 'S'){
                    q.offer(new Node(i, j, 0));
                    vis[i][j] = true;
                    break o;
                }
            }
        }
        int ans = Integer.MIN_VALUE;
        while(!q.isEmpty()){
            var curr = q.poll();
            int x = curr.x, y = curr.y, d = curr.dist;
            ans = Math.max(ans, d);
            char c = lines.get(x).charAt(y);
            if(c == 'S'){
                char top = isValid(x - 1, y, R, C) ? lines.get(x - 1).charAt(y) : ' ';
                char bottom = isValid(x + 1, y, R, C) ? lines.get(x + 1).charAt(y) : ' ';
                char left = isValid(x, y - 1, R, C) ? lines.get(x).charAt(y - 1) : ' ';
                char right = isValid(x, y + 1, R, C) ? lines.get(x).charAt(y + 1) : ' ';
                if(right == '-' && bottom == '|') c = 'F';
                // top "|7F"
                // bottom "|LJ"
                // left "-LF"
                // right "-J7"
                else if("|7F".indexOf(top) != -1 && "|LJ".indexOf(bottom) != -1) c = '|';
                // -
                else if("-LF".indexOf(left) != -1 && "-J7".indexOf(right) != -1) c = '-';
                // L
                else if("|7F".indexOf(top) != -1 && "-J7".indexOf(right) != -1) c = 'L';
                // J
                else if("|7F".indexOf(top) != -1 && "-LF".indexOf(left) != -1) c = 'J';
                // 7
                else if("|LJ".indexOf(bottom) != -1 && "-LF".indexOf(left) != -1) c = '7';
                // F
                else if("|LJ".indexOf(bottom) != -1 && "-J7".indexOf(right) != -1) c = 'F';
                else c = '.';
            }
            if(c == '|' || c == 'L' || c == 'J'){
                if(isValid(x - 1, y, R, C) && !vis[x - 1][y]){ // N
                    q.offer(new Node(x - 1, y, d + 1));
                    vis[x - 1][y] = true;
                }
            }
            if(c == '|' || c == '7' || c == 'F'){
                if(isValid(x + 1, y, R, C) && !vis[x + 1][y]){ // S
                    q.offer(new Node(x + 1, y, d + 1));
                    vis[x + 1][y] = true;
                }
            }
            if(c == '-' || c == 'J' || c == '7'){
                if(isValid(x, y - 1, R, C) && !vis[x][y - 1]){ // W
                    q.offer(new Node(x, y - 1, d + 1));
                    vis[x][y - 1] = true;
                }
            }
            if(c == '-' || c == 'L' || c == 'F'){
                if(isValid(x, y + 1, R, C) && !vis[x][y + 1]){ // E
                    q.offer(new Node(x, y + 1, d + 1));
                    vis[x][y + 1] = true;
                }
            }
        }
        System.out.println(ans);
    }

    private static boolean isValid(int x, int y, int r, int c) {
        return x >= 0 && x < r && y >= 0 && y < c;
    }

}
