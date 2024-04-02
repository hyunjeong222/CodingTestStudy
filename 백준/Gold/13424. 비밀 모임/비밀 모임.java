import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] dist;
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        while (t --> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 방의 개수
            m = Integer.parseInt(st.nextToken()); // 비밀통로의 개수
            dist = new int[n+1][n+1];
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == j) continue;
                    dist[i][j] = INF;
                }
            }
            int u, v, w;
            for (int i = 0; i < m ;i++) {
                st = new StringTokenizer(br.readLine());
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());
                w = Integer.parseInt(st.nextToken());

                dist[u][v] = w;
                dist[v][u] = w;
            }
            floyd();
            int[] ansArr = new int[n+1];
            int k = Integer.parseInt(br.readLine()); // 모임에 참여하는 친구 수
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= k; i++) {
                int num = Integer.parseInt(st.nextToken());
                for (int j = 1; j <= n; j++) {
                    ansArr[j] += dist[num][j];
                }
            }
            int min = 1;
            for (int i = 2; i <= n; i++) {
                if (ansArr[min] > ansArr[i]) min = i;
            }
            sb.append(min).append("\n");
        }
        System.out.println(sb);
    }

    private static void floyd() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == j || j == k || k == i) continue;
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
    }
}