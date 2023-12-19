import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static final int size = 30;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        int[][] dp = new int[size][size];
        for (int i = 0; i < size; i++) {
            dp[i][i] = 1;
            dp[i][0] = 1;
        }
        for (int i = 2; i < size; i++) {
            for (int j = 1; j < size; j++) {
                dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
            }
        }
        while (t --> 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            sb.append(dp[m][n]).append("\n");
        }
        System.out.println(sb);
    }
}