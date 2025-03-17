import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] map;
    static int[][] dp;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 대나무 숲의 크기
        map = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[n][n];
        int ans = 0; // 판다가 이동할 수 있는 칸의 수의 최댓값
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, dfs(i, j));
            }
        }

        System.out.println(ans);
    }

    private static int dfs(int x, int y) {
        if (dp[x][y] != 0) { // 이미 경로가 있음
            return dp[x][y];
        }

        // 판다는 최소 한 군데에서 대나무를 먹을 수 있음
        dp[x][y] = 1;

        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            // 범위 체크
            if (rangeCheck(nx, ny)) continue;

            // 대나무를 먹고 자리를 옮기면 그 옮긴 지역에 그 전 지역보다 대나무가 많이 있어야 함
            if (map[x][y] < map[nx][ny]) {
                // 상하좌우 중 가장 많이 먹을 수 있은 경로 탐색
                dp[x][y] = Math.max(dp[x][y], dfs(nx, ny)+1);
                // dfs(nx, ny);
            }
        }

        return dp[x][y];
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= n;
    }
}