import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int k = Integer.parseInt(br.readLine()); // 버튼을 K번 눌렀을 때

        /*
        A  B  BA  BAB  BABBA  BABBABAB  BABBABABBABBA  BABBABABBABBABABBABAB
        BABBABABBABBABABBABABBABBABABBABBA ...
            1      2     3     4     5     6      7       8      9      10
        A   0      1     1     2     3     5      8      13      21     34
        B   1      1     2     3     5     8      13     21      34     55
        */
        int[][] dp = new int[2][46];
        dp[0][2] = 1;
        dp[1][1] = 1;
        dp[1][2] = 1;
        for (int i = 3; i <= k; i++) {
            dp[0][i] = dp[0][i-2]+dp[0][i-1];
            dp[1][i] = dp[1][i-2]+dp[1][i-1];
        }

        sb.append(dp[0][k]).append(" ").append(dp[1][k]).append("\n");

        System.out.println(sb.toString());

        br.close();
    }
}