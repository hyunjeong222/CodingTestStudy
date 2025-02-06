import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 파이프 종류 수
        int x = Integer.parseInt(st.nextToken()); // 만들고 싶은 파이프 길이

        int[] l = new int[n+1]; // 파이프 길이
        int[] c = new int[n+1]; // 파이프 수량
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            l[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[n+1][x+1]; // i번 파이프까지 봤을 때 길이가 j가 되는 경우의 수
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) { // 파이프 종류
            for (int j = 0; j <= x; j++) { // 길이
                for (int k = 0; k <= c[i]; k++) {
                    int len = l[i] * k;

                    if (j >= len) { // j를 만들기 위해 len 만큼의 파이프를 사용할 수 있는 경우 체크
                        dp[i][j] += dp[i-1][j-len]; // 이전 파이프 조합에서 만들 수 있는 경우 추가
                    }
                }
            }
        }

        System.out.println(dp[n][x]);
    }
}