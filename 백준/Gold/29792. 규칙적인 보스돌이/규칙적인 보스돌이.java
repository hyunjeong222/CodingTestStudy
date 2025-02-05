import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 보유한 캐릭터 수
        int m = Integer.parseInt(st.nextToken()); // 하루에 사용할 캐릭터 개수
        int k = Integer.parseInt(st.nextToken()); // 보스의 가짓수

        // 캐릭터가 1초에 가하는 데미지
        long[] damage = new long[n];
        for (int i = 0; i < n; i++) {
            damage[i] = Long.parseLong(br.readLine());
        }

        long[] boss_w = new long[k];
        int[] meso = new int[k+1];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            boss_w[i] = Long.parseLong(st.nextToken()); // 보스의 체력
            meso[i+1] = Integer.parseInt(st.nextToken()); // 보스를 처치했을 때 드랍하는 메소
        }

        int[] result = new int[n]; // 각 캐릭터가 얻을 수 있는 최대 메소
        for (int i = 0; i < n; i++) {
            long[] player_w = new long[k+1]; // 각 보스를 처치하는 데 필요한 시간
            player_w[0] = 0;
            for (int j = 0; j < k; j++) {
                long hp = boss_w[j];
                long time = hp / damage[i];
                if (hp % damage[i] > 0) time++; // 나머지가 있다면 1초 추가
                player_w[j+1] = time;
            }

            int[][] dp = new int[k+1][901];
            for (int a = 1; a <= k; a++) {
                for (int b = 1; b < 901; b++) {
                    if (b >= player_w[a]) {
                        dp[a][b] = Math.max(dp[a-1][b], dp[a-1][b-(int)player_w[a]]+meso[a]);
                    } else {
                        dp[a][b] = dp[a-1][b];
                    }
                }
            }

            result[i] = dp[k][900]; // 현재 캐릭터가 900초 안에 얻을 수 있는 최대 메소
        }

        Arrays.sort(result);
        int sum = 0;
        // 하루에 사용할 캐릭터는 m개
        for (int i = n-1; i >= n-m; i--) {
            sum += result[i];
        }

        System.out.println(sum);
    }
}