import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine()); // 상자의 개수

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] size = new int[n]; // 각 상자의 크기
        for (int i = 0; i < n; i++) {
            size[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (size[i] > size[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }

            ans = Math.max(ans, dp[i]);
        }

        System.out.println(ans);

        br.close();
    }
}