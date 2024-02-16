import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] t = new int[n+1];
        int[] p = new int[n+1];
        int[] dp = new int[n+2];
        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = n; i > 0; i--) {
            int next = t[i] + i;
            if (next > n+1) {
                dp[i] = dp[i+1];
            } else {
                dp[i] = Math.max(dp[i+1], p[i] + dp[next]);
            }
        }
        System.out.println(dp[1]);
    }
}