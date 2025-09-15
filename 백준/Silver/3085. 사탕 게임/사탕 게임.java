import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[][] map = new char[n][n];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        // System.out.println(Arrays.deepToString(map));

        int max = 0; // 먹을 수 있는 사탕의 최대 개수
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j < n-1) {
                    swap(map, i, j, i, j+1);
                    max = Math.max(max, maxCandy(map, n));
                    swap(map, i, j, i, j+1); // 제자리로
                }

                if (i < n-1) {
                    swap(map, i, j, i+1, j);
                    max = Math.max(max, maxCandy(map, n));
                    swap(map, i, j, i+1, j); // 제자리로
                }
            }
        }

        System.out.println(max);

        br.close();
    }

    private static int maxCandy(char[][] map, int n) {
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            int row = 1;
            int col = 1;
            for (int j = 1; j < n; j++) {
                if (map[i][j-1] == map[i][j]) {
                    row++;
                } else {
                    maxLen = Math.max(maxLen, row);
                    row = 1;
                }

                if (map[j-1][i] == map[j][i]) {
                    col++;
                } else {
                    maxLen = Math.max(maxLen, col);
                    col = 1;
                }
            }

            maxLen = Math.max(maxLen, row);
            maxLen = Math.max(maxLen, col);
        }

        return maxLen;
    }

    private static void swap(char[][] map, int x1, int y1, int x2, int y2) {
        char tmp = map[x1][y1];
        map[x1][y1] = map[x2][y2];
        map[x2][y2] = tmp;
    }
}