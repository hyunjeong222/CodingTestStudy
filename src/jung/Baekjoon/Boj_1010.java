package src.jung.Baekjoon;

import java.io.*;
import java.util.*;

/**
 * 문제 이름(난이도) : 다리 놓기(SIL5)
 * 시간 : ms
 * 메모리 : KB
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/1010
 * */
public class Boj_1010 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int dp[][] = new int[30][30];

        // 2번 성질 (n == r, r == 0)
        for (int i = 0; i < 30; i++) {
            dp[i][i] = 1;
            dp[i][0] = 1;
        }

        for (int i = 2; i < 30; i++) {
            for (int j = 1; j < 30; j++) {
                // 1번 성질
                dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
            }
        }

        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());

            // M개중 N개를 뽑는 경우이므로 nCr 에서 n = M, r = N
            int n = Integer.parseInt(st.nextToken()); // r
            int m = Integer.parseInt(st.nextToken()); // n

            bw.append(dp[m][n]+ "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
// https://st-lab.tistory.com/194
// https://st-lab.tistory.com/159#%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98
