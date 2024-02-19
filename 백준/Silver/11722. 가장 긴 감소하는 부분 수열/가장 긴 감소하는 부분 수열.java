import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(br.readLine()); // 수열 a의 크기
        int[] arr = new int[a+1]; // 수열
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= a; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[a+1];
        int ans = 0;
        for (int i = 1; i <= a; i++) {
            dp[i] = 1;
            for (int j = 1; j <= i; j++) {
                if (arr[i] < arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
    }
}