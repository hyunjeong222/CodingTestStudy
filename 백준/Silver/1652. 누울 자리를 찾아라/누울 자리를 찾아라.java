import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(br.readLine());

        char[][] map = new char[n][n];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        int horizontal = 0, vertical = 0; // 가로, 세로
        for (int i = 0; i < n; i++) {
            int tmpHorizontal = 0, tmpVertical = 0;
            for (int j = 0; j < n; j++) {
                // 가로 체크
                if (map[i][j] == '.') tmpHorizontal++;
                if (map[i][j] == 'X' || j == n-1) {
                    if (tmpHorizontal >= 2) horizontal++;
                    tmpHorizontal = 0;
                }

                // 세로 체크
                if (map[j][i] == '.') tmpVertical++;
                if (map[j][i] == 'X' || j == n-1) {
                    if (tmpVertical >= 2) vertical++;
                    tmpVertical = 0;
                }
            }
        }

        sb.append(horizontal).append(" ").append(vertical).append("\n");

        System.out.println(sb.toString());

        br.close();
    }
}