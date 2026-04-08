import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine()); // 컴퓨터 개수

        ArrayList<Integer>[] rank = new ArrayList[n + 2];
        for (int i = 0; i < n + 2; i++) {
            rank[i] = new ArrayList<>();
        }
        int[] speed = new int[n+1]; // 수행 시간
        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            rank[c].add(i); // 계급 c인 컴퓨터들의 인덱스 리스트
            speed[i] = t;
        }

        int[] dp = new int[n+1];
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            // 낮은 계급부터 처리 → 다음 계급으로 값 전달
            for (int j : rank[i]) {
                dp[j] += speed[j];

                for (int k : rank[i+1]) {
                    // 컴퓨터 간의 전송 시간은 (i - j)2
                    int transfer = (k - j) * (k - j);
                    if (dp[k] < dp[j] + transfer) {
                        dp[k] = dp[j] + transfer;
                    }
                }

                if (dp[j] > ans) {
                    ans = dp[j];
                }
            }
        }

        System.out.println(ans);

        br.close();
    }
}