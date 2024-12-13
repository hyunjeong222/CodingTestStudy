import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 주문의 수
        int m = Integer.parseInt(st.nextToken()); // 치즈버거 개수
        int K = Integer.parseInt(st.nextToken()); // 감자튀김 개수

        int[][] dp = new int[m+1][K+1];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int buger = Integer.parseInt(st.nextToken()); // 치즈버거
            int fries = Integer.parseInt(st.nextToken()); // 감자튀김

            for (int j = m; j > 0; j--) {
                for (int k = K; k > 0; k--) {
                    if (buger <= j && fries <= k) {
                        dp[j][k] = Math.max(dp[j][k], dp[j-buger][k-fries]+1);
                    }
                }
            }
        }

        System.out.println(dp[m][K]);
    }
}