import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine()); // 집의 수
        int[][] dp = new int[n+1][3];
        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            dp[i][0] = r + Math.min(dp[i-1][1], dp[i-1][2]);
            dp[i][1] = g + Math.min(dp[i-1][0], dp[i-1][2]);
            dp[i][2] = b + Math.min(dp[i-1][0], dp[i-1][1]);
        }
        int ans = Math.min(dp[n][0], Math.min(dp[n][1], dp[n][2]));
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}