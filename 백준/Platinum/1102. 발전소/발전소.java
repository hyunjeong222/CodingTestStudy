import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, p;
    static int[][] cost;
    static int[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());

        // 발전소 i를 이용해서 발전소 j를 재시작할 때 드는 비용
        cost = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[1 << n]; // mask 상태에서 p개 이상의 발전소를 가동하는 최소 비용
        Arrays.fill(dp, -1);

        int pos = 0; // 현재 발전소 작동 상태
        int cnt = 0; // 현재 작동하고 있는 발전소 개수
        String[] status = br.readLine().split("");
        for (int i = 0; i < n; i++) {
            if (status[i].equals("Y")) {
                pos |= (1 << i); // pos에 값 추가
                cnt++;
            }
        }

        p = Integer.parseInt(br.readLine());
        
        int ans = dfs(pos, cnt);

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);

        br.close();
    }

    private static int dfs(int pos, int cnt) {
        if (cnt >= p) return 0;
        if (dp[pos] != -1) return dp[pos];

        dp[pos] = Integer.MAX_VALUE;

        // 발전소를 고치는 방법 : 고장나지 않은 발전소를 이용해서 고장난 발전소를 재시작
        for (int i = 0; i < n; i++) {
            if ((pos & (1 << i)) != 0) { // i번째 발전소가 정상 작동하는 경우,  pos와 같은 번호를 반환 (포함 의미)
                for (int j = 0; j < n; j++) {
                    if ((pos & (1 << j)) == 0) { // j번째 발전소가 고장난 경우
                        dp[pos] = Math.min(dp[pos], dfs(pos | (1 << j), cnt+1)+cost[i][j]);
                    }
                }
            }
        }

        return dp[pos];
    }
}