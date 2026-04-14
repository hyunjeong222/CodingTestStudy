import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, k, ans;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 먹이 개수
        k = Integer.parseInt(st.nextToken()); // 최소 만족도

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long[] dp = new long[n+1]; // dp[i] = i 까지의 최대 탈피 에너지
        long sum = arr[0]; int left = 0; int right = 1;
        while (right <= n) {
            if (sum >= k) {
                dp[right] = Math.max(dp[right], dp[left]+sum-k);
                sum -= arr[left++];
            } else {
                dp[right] = Math.max(dp[right], dp[right-1]);

                if (right == n) break;

                sum += arr[right++];
            }
        }

        System.out.println(dp[n]);

        br.close();
    }
}