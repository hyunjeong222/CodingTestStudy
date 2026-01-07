import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        // r번째 줄 c번째 수
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        // 한 변이 포함하는 수의 개수
        int w = Integer.parseInt(st.nextToken());

        int[][] dp = new int[r+w+1][c+w+1];
        dp[1][1] = 1;
        // 파스칼 삼각형
        for (int i = 2; i <= r+w; i++) {
            for (int j = 1; j <= c+w; j++) {
                dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
            }
        }
        // System.out.println(Arrays.deepToString(dp));

        int sum = 0;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j <= i; j++) {
                sum += dp[i+r][j+c];
            }
        }

        System.out.println(sum);

        br.close();
    }
}