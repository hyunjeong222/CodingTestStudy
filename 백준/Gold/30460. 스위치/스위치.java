import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 점수를 얻는 횟수
        int[] score = new int[n+3];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            score[i] = Integer.parseInt(st.nextToken());
        }
        // System.out.println(Arrays.toString(score));
        int[] dp = new int[n+3]; // dp[i] : i초까지 얻을 수 있는 점수의 최댓값
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        dp[1] = score[1]; // 스위치를 안누르면 score[1]만 얻음
        dp[2] = score[1] + score[2]; // 스위치를 안누르면 score[1] + score[2]만 얻음

        // 스위치를 누르려면 최소 3초가 필요
        for (int i = 3; i <= n+2; i++) {
            // 두배로 받는 경우
            dp[i] = Math.max(dp[i], dp[i-3]+(score[i-2]+score[i-1]+score[i])*2);
            // 두배로 안받는 경우
            dp[i] = Math.max(dp[i], dp[i-1]+score[i]);
        }

        System.out.println(dp[n+2]);

        br.close();
    }
}