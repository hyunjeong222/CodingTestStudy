import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, size;
    static int[] attack, people, dp;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 몬스터 수
        int k = Integer.parseInt(st.nextToken()); // 초기 체력

        attack = new int[n]; // 몬스터 공격력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            attack[i] = Integer.parseInt(st.nextToken());
        }

        people = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        size = (1<<n)-1;
        dp = new int[size+1];
        Arrays.fill(dp, -1);

        dfs(k, 0, 0, 0);

        System.out.println(ans);

        br.close();
    }

    private static void dfs(int hp, int save, int damage, int checked) {
        ans = Math.max(ans, save); // 최대 구한 주민

        // 모든 마을을 방문 || 누적된 공격량이 내 체력보다 많은 경우
        if (checked == size || hp <= damage) return;

        for (int i = 0; i < n; i++) {
            int bit = (1<<i);
            // 이미 방문
            if ((checked&bit) > 0) continue;
            // 해당 마을 방문 정보
            int totalDamage = damage+attack[i];
            int nextHp = hp-totalDamage;
            int nextChecked = checked | bit;
            // 몬스터를 물리치지 못하거나 || 메모이제이션 최댓값 X
            if (nextHp < 0 || dp[nextChecked] >= nextHp) continue;
            // 다음 마을 방문 진행
            dp[nextChecked] = nextHp;
            dfs(nextHp, save+people[i], totalDamage, nextChecked);
        }
    }
}