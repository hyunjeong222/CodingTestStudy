import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, s, m;
    static int[] volume;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 곡의 개수
        s = Integer.parseInt(st.nextToken()); // 시작 볼륨
        m = Integer.parseInt(st.nextToken()); // 0보다 작은 값, M보다 큰 값으로 볼륨을 바꿀 수 없음

        volume = new int[n+1]; // 곡이 시작하기 전에 바꿀 수 있는 볼륨의 리스트
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            volume[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[m+1][n];
        for (int[] arr : dp) {
            Arrays.fill(arr, -2);
        }

        System.out.println(recur(s, 0));

        br.close();
    }

    private static int recur(int diffVolume, int idx) {
        if (diffVolume > m || diffVolume < 0) return -1;

        if (idx == n) return diffVolume;

        if (dp[diffVolume][idx] != -2) return dp[diffVolume][idx];

        return dp[diffVolume][idx] = Math.max(dp[diffVolume][idx],
                Math.max(recur(diffVolume+volume[idx], idx+1), recur(diffVolume-volume[idx], idx+1)));
    }
}