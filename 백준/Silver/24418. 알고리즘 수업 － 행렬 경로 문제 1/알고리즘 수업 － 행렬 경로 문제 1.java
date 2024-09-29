import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int ans1, ans2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n+1][n+1];
        StringTokenizer st;
        for (int i = 1 ; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        matrix_path(n, map);
        matrix_path_dp(n, map);

        StringBuilder sb = new StringBuilder();
        sb.append(ans1).append(" ").append(ans2);
        System.out.println(sb.toString());
    }

    private static int matrix_path_dp(int n, int[][] map) {
        int[][] dp = new int[n+1][n+1];
        for (int i = 0; i <= n; i++) dp[i][0] = 0;
        for (int j = 1; j <= n; j++) dp[0][j] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                ans2++;
                dp[i][j] = map[i][j] + (Math.max(dp[i-1][j], dp[i][j-1]));
            }
        }
        return dp[n][n];
    }

    private static int matrix_path(int n, int[][] map) {
        return matrix_path_rec(map, n, n);
    }

    private static int matrix_path_rec(int[][] map, int i, int j) {
        if (i == 0 || j == 0) {
            ans1++;
            return 0;
        } else {
            return (map[i][j] + (Math.max(matrix_path_rec(map, i-1, j), matrix_path_rec(map, i, j-1))));
        }
    }
}