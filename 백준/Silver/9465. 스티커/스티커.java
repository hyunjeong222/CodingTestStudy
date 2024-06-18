import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (t --> 0) {
            n = Integer.parseInt(br.readLine());
            arr = new int[2][n+1];
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int score = getMax();
            sb.append(score).append("\n");
        }
        System.out.println(sb);
    }

    private static int getMax() {
        int[][] dp = new int[2][n+1]; // 현재 스티커 점수에서 얻을 수 있는 최대값 저장
        dp[0][0] = 0; dp[1][0] = 0;
        dp[0][1] = arr[0][1]; dp[1][1] = arr[1][1];

        for (int i = 2; i <= n; i++) {
            dp[0][i] = Math.max(dp[1][i-1], dp[1][i-2]) + arr[0][i];
            dp[1][i] = Math.max(dp[0][i-1], dp[0][i-2]) + arr[1][i];
        }

        return Math.max(dp[0][n], dp[1][n]);
    }
}