import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+1];
        int[] before = new int[n+1];

        // 1을 만들기 위한 연산의 수 저장
        dp[0] = 0; dp[1] = 0;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1]+1;
            before[i] = i-1; // dp[n]을 구할 때 n의 다음 수

            if (i % 2 == 0 && dp[i] > dp[i/2]+1) {
                dp[i] = dp[i/2]+1;
                before[i] = i/2;
            }
            if (i % 3 == 0 && dp[i] > dp[i/3]+1) {
                dp[i] = dp[i/3]+1;
                before[i] = i/3;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dp[n]).append("\n");

        int point = n;
        sb.append(point).append(" ");
        for (int i = 0; i < dp[n]; i++) {
            sb.append(before[point]).append(" ");
            point = before[point];
        }

        System.out.println(sb);
    }
}