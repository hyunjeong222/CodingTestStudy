import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine()); // 수열의 길이

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 인덱스 i에서 끝나는 길이
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            if (A[i-1] < A[i]) {
                dp[i] = dp[i-1]+1;
            }
        }

        long sum = 0;
        for (int val : dp) {
            sum += val;
        }

        // System.out.println(Arrays.toString(dp));
        // +n : 각 원소 자신 하나짜리 구간 (i,i)
        System.out.println(sum+n);

        br.close();
    }
}