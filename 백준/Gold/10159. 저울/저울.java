import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] graph;
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        graph = new int[n+1][n+1];

        int a, b;
        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            graph[a][b] = 1;
            graph[b][a] = -1;
        }

        for (int i = 1; i <= n; i++) { // 경유지
            for (int j = 1; j <= n; j++) { // 출발
                if (i == j) continue;
                for (int k = 1; k <= n; k++) { // 도착
                    if (i == k || j == k) continue;
                    if (graph[j][i] != 0 && graph[j][i] == graph[i][k]) {
                        graph[j][k] = graph[j][i];
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            int cnt = 0;
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;

                if (graph[i][j] == 0 && graph[j][i] == 0) cnt++;
            }
            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }
}