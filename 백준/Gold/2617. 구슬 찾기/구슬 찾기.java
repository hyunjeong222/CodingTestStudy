import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] dist = new int[n+1][n+1];
        int a, b;
        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            // a > b
            dist[a][b] = 1; // a > b : a <- b
            dist[b][a] = -1; // a < b : b -> a
        }

        for (int i = 1; i <= n; i++) { // 거쳐가는 구슬
            for (int j = 1; j <= n; j++) { // 출발 구슬
                if (i == j) continue; // 자기 자신인 경우 탐색 X
                for (int k = 1; k <= n; k++) { // 도착 구슬
                    // 같은 경우 탐색 X
                    if (i == k || j == k) continue;
                    if (dist[k][i] != 0 && dist[j][i] == dist[i][k]) {
                        dist[j][k] = dist[j][i];
                    }
                }
            }
        }

        int[] big = new int[n+1];
        int[] small = new int[n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] == 1) big[i]++;
                else if (dist[i][j] == -1) small[i]++;
            }
        }

        int half = (n/2)+1;
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (big[i] >= half || small[i] >= half) ans++;
        }

        System.out.println(ans);
    }
}