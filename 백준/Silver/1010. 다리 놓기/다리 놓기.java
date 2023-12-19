import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static final int size = 30;
    static int[][] dp;
    static int n, m;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        dp = new int[size][size];
        while (t --> 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            sb.append(combi(m, n)).append("\n");
        }
        System.out.println(sb);
    }

    private static int combi(int n, int r) {
        if (dp[n][r] > 0) { // 이미 계산된 값이라면
            return dp[n][r];
        }

        if (n == r || r == 0) {
            return dp[n][r] = 1;
        }

        return dp[n][r] = combi(n-1, r-1) + combi(n-1, r);
    }
}