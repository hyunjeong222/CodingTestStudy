import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 999999999;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] dist = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for( int j = 1; j <= n; j++) {
                dist[i][j] = INF;
            }
        }
        int a, b;
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            dist[a][b] = 1;
        }
        for (int k = 1; k <= n; k++) { // 중간지점
            for (int i = 1; i <= n; i++) { // 시작지점
                for (int j = 1; j <= n; j++) { // 도착지점
                    dist[i][j] = dist[i][j] <= dist[i][k] + dist[k][j] ? dist[i][j] : dist[i][k] + dist[k][j];
                }
            }
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            int cnt = 0;
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] != INF || dist[j][i] != INF) cnt++;
            }
            if (cnt == n-1) ans++;
        }
        System.out.println(ans);
    }
}