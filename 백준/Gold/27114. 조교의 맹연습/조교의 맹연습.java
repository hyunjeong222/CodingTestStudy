import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] cost = new int[3]; // 좌우뒤
        for (int i = 0; i < 3; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }
        int k = Integer.parseInt(st.nextToken()); // 사용하고자 하는 총 에너지 양

        int[] dir = {3, 1, 2};
        int[][] dp = new int[k+1][4]; // 에너지, 방향(제자리, 좌, 위, 뒤)
        for (int i = 0; i <= k; i++) {
            Arrays.fill(dp[i], 1000001);
        }
        dp[0][0] = 0;
        for (int i = 0; i <= k; i++) {
            for (int j = 0; j < 4; j++) {
                if (dp[i][j] == 1000001) continue;
                for (int o = 0; o < 3; o++) {
                    if (i+cost[o] > k) continue;
                    dp[i+cost[o]][(j+dir[o])%4] = Math.min(dp[i+cost[o]][(j+dir[o])%4], dp[i][j]+1);
                }
            }
        }

        System.out.println(dp[k][0] == 1000001 ? -1 : dp[k][0]);
    }
}