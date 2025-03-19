import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 도로의 가로
        int m = Integer.parseInt(st.nextToken()); // 세로

        int[][][] map = new int[101][101][2]; // map[i][j][k] : (i, j)에서 k 방향으로 갈 수 있는지
        long[][] dp = new long[101][101];

        int k = Integer.parseInt(br.readLine()); // 공사중인 도로의 개수

        int x1, y1, x2, y2;
        // -1이라면 이동 불가능
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());

            if (y1 == y2) {
                if (x1 > x2) {
                    int tmp = x1;
                    x1 = x2;
                    x2 = tmp;
                }
                map[x2][y1][0] = -1; // 아래로 이동 불가능 / 왼쪽에서 오른쪽 이동
            }

            if (x1 == x2) {
                if (y1 > y2) {
                    int tmp = y1;
                    y1 = y2;
                    y2 = tmp;
                }
                map[x1][y2][1] = -1; // 왼쪽 이동 불가능 / 위에서 아래로 이동
            }
        }

        dp[0][0] = 1;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (map[i][j][0] != -1 && i-1 >= 0) { // x 방향 이동 가능
                    dp[i][j] += dp[i-1][j];
                }
                if (map[i][j][1] != -1 && j-1 >= 0) { // y 방향 이동 가능
                    dp[i][j] += dp[i][j-1];
                }
            }
        }

        System.out.println(dp[n][m]);
        br.close();
    }
}