import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 보도 블럭 수
        int k = Integer.parseInt(st.nextToken()); // 보폭

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 1; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i][j] = 점프를 i번 뛴 상태로 j에 도달했을 때 연속으로 밟은 블록의 최댓값
        int[][] dp = new int[2][n];
        int ans = 0;
        dp[0][0] = dp[0][1] = 1;
        for (int j = 1; j < n; j++) {
            if (arr[j] <= k) { // i 블럭에서 i+1 블럭으로 이동할 수 있다면
                dp[0][j] = dp[0][j-1]+1;
                dp[1][j] = dp[1][j-1]+1;
            } else { // 이동 불가
                dp[0][j] = 1; // 다시 시작
                dp[1][j] = dp[0][j-1]+1; // 점프 사용
            }

            ans = Math.max(ans, Math.max(dp[0][j], dp[1][j]));
        }

        System.out.println(ans);

        br.close();
    }
}