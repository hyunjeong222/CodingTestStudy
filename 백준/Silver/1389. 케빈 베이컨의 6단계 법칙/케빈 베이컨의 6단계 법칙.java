import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 유저의 수
        int m = Integer.parseInt(st.nextToken()); // 친구 관계의 수

        int[][] dist = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) dist[i][j] = INF;
            }
        }

        int u, v;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());

            // 양방향
            dist[u][v] = 1;
            dist[v][u] = 1;
        }

        for (int k = 1; k <= n; k++) { // 중간
            for (int i = 1; i <= n; i++) { // 출발
                for (int j = 1; j <= n ; j++) { // 도착
                    dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
                }
            }
        }

        int ans = 0; // 번호가 가장 작은 사람
        int min = INF;
        for (int i = 1; i <= n; i++) {
            int total = 0;
            for (int j = 1; j <= n; j++) {
                total += dist[i][j];
            }

            if (total < min) {
                min = total; // 작은 번호로 갱신
                ans = i;
            }
        }

        System.out.println(ans);

        br.close();
    }
}