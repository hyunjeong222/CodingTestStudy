import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[9][9];

        StringTokenizer st;
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);
    }

    private static void dfs(int row, int col) {
        if (col == 9) {
            dfs(row+1, 0);
            return;
        }

        if (row == 9) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(map[i][j] + " ");
                }
                sb.append("\n");
            }
            
            System.out.println(sb);
            System.exit(0);
        }

        if (map[row][col] == 0) {
            for (int i = 1; i <= 9; i++) {
                if (numCheck(row, col, i)) {
                    map[row][col] = i;
                    dfs(row, col+1);
                }
            }
            map[row][col] = 0;
            return;
        } else {
            dfs(row, col+1);
        }
    }

    private static boolean numCheck(int row, int col, int val) {
        for (int i = 0; i < 9; i++) {
            if (map[row][i] == val) return false;
        }

        for (int i = 0; i < 9; i++) {
            if (map[i][col] == val) return false;
        }

        int threeRow = (row/3) * 3;
        int threeCol = (col/3) * 3;
        for (int i = threeRow; i < threeRow+3; i++) {
            for (int j = threeCol; j < threeCol+3; j++) {
                if (map[i][j] == val) return false;
            }
        }
        
        return true;
    }
}