import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] map, dp;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        int ans = dfs(0, 0);

        System.out.println(ans);

        br.close();
    }

    private static int dfs(int x, int y) {
        if (x == n-1 && y == m-1) return 1;
        if (dp[x][y] != -1) return dp[x][y];

        int now = map[x][y];
        dp[x][y] = 0;
        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (nx <0 || nx > n-1 || ny < 0 || ny > m-1) continue;

            int next = map[nx][ny];
            if (now > next) {
                dp[x][y] += dfs(nx, ny);
            }
        }

        return dp[x][y];
    }
}