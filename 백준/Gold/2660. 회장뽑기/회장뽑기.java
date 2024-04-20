import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] dist = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                dist[i][j] = INF;
            }
        }
        StringTokenizer st;
        int u, v;
        while (true) {
             st = new StringTokenizer(br.readLine());
             u = Integer.parseInt(st.nextToken());
             v = Integer.parseInt(st.nextToken());

             if (u == -1 && v == -1) break;

             dist[u][v] = 1; dist[v][u] = 1;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == j) continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
                }
            }
        }

        int readerScore = INF; // 리더 점수
        int[] scoreArr = new int[n+1]; // 후보 번호
        for (int i = 1; i <= n; i++) {
            int score = 0;
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] != INF) {
                    score = Math.max(score, dist[i][j]);
                }
            }
            scoreArr[i] = score;
            readerScore = Math.min(readerScore, score);
        }

        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        sb.append(readerScore).append(" ");
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (readerScore == scoreArr[i]) {
                cnt++;
                sb2.append(i).append(" ");
            }
        }
        sb.append(cnt);

        System.out.println(sb);
        System.out.println(sb2);
    }
}