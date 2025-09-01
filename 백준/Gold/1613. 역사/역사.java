import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 사건의 개수
        int m = Integer.parseInt(st.nextToken()); // 사건의 전후 관계의 개수

        int[][] map = new int[n+1][n+1];
        int a, b;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            map[a][b] = -1;
            map[b][a] = 1; // 사건의 역방향
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (map[i][k] == 1 && map[k][j] == 1) {
                        map[i][j] = 1;
                    } else if (map[i][k] == -1 && map[k][j] == -1) {
                        map[i][j] = -1;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int s = Integer.parseInt(br.readLine()); // 사건의 전후 관계를 알고 싶은 사건 쌍의 수
        for (int i = 0; i < s; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            sb.append(map[a][b]).append("\n");
        }

        System.out.println(sb.toString());
        
        br.close();
    }
}