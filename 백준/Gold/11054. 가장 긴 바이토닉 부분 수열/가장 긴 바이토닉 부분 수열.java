import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 수열 A의 크기
        int[] A = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int[] dpL = new int[n]; // 왼 -> 오 수열
        int[] dpR = new int[n]; // 오 -> 왼 수열
        int[] dp = new int[n]; // 겹치는 인덱스를 제외한 값 저장

        dpL[0] = 1;
        dpR[n-1] = 1;

        for (int i = 1; i < n; i++) {
            int max = 0;
            for (int j = i-1; j >= 0; j--) {
                if (A[j] < A[i]) {
                    if (dpL[j] > max) max = dpL[j];
                }
            }
            dpL[i] = max+1;
        }

        for (int i = n-2; i >= 0; i--) {
            int max = 0;
            for (int j = i+1; j < n; j++) {
                if (A[j] < A[i]) {
                    if (dpR[j] > max) max = dpR[j];
                }
            }
            dpR[i] = max+1;
        }

        for (int i = 0; i < n; i++) {
            dp[i] = dpL[i] + dpR[i] - 1;
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] > max) max = dp[i];
        }

        System.out.println(max);

        br.close();
    }
}