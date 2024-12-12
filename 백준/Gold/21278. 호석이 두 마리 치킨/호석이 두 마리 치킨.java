import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] dist;
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dist = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                dist[i][j] = INF;
            }
        }

        int u, v;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());

            dist[u][v] = 1;
            dist[v][u] = 1;
        }

        floyd();

        int ans1 = 0, ans2 = 0;
        int timeSum = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            for (int j = i+1; j <= n; j++) {
                int dist = distance(i, j); // 두 개 지점을 치킨집으로 지정
                if (timeSum > dist) {
                    ans1 = i;
                    ans2 = j;
                    timeSum = dist;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(ans1).append(" ").append(ans2).append(" ").append(timeSum*2).append("\n");

        System.out.println(sb.toString());
    }

    private static int distance(int x, int y) {
        int sum = 0; // 모든 건물에서 가장 가까운 치킨집까지 왕복하는 최단 시간의 총합
        for (int i = 1; i <= n; i++) {
            sum += Math.min(dist[x][i], dist[y][i]);
        }

        return sum;
    }

    private static void floyd() {
        for (int k = 1; k <= n; k++) { // 경유지
            for (int i = 1; i <= n; i++) { // 출발지
                if (k == i) continue;
                for (int j = 1; j <= n; j++) { // 도착지
                    if (k == j || i == j) continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }
}