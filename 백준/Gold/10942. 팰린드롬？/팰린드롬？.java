import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n+1];
        int[][] dp = new int[n+1][n+1]; // i에서 j까지 팰린드롬인지 확인

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            for (int j = 1; j <= i; j++) {
                if (j == i) dp[j][i] = 1; // 자기 자신 하나
                // 길이가 2면 앞뒤만 비교
                else if (i - j == 1) dp[j][i] = (arr[j] == arr[i] ? 1 : 0);
                // 길이가 3이상이면 앞뒤 비교 + 안쪽이 팰린드롬인지 체크
                else {
                    dp[j][i] = (arr[j] == arr[i] && dp[j+1][i-1] == 1 ? 1 : 0);
                }
            }
        }

        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int s, e;
        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            sb.append(dp[s][e]).append("\n");
        }

        System.out.println(sb.toString());

    }
}