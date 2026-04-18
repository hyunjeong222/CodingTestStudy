import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[] coins;
    static int[] checked;
    static public class Pos {
        int cnt, sum;
        Pos(int cnt, int sum) {
            this.cnt = cnt;
            this.sum = sum;
        }
    }
    static final int MAX_M = 20001;
    static int OFFSET = 10000;
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 가짓수
        m = Integer.parseInt(st.nextToken()); // 지불할 금액

        coins = new int[n];
        if (n > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }
        }

        // 이 금액에 도달하는 최소 동전 개수
        checked = new int[MAX_M];
        Arrays.fill(checked, INF);

        int ans = bfs();
        System.out.println(ans);

        br.close();
    }

    private static int bfs() {
        Queue<Pos> que = new LinkedList<>();

        que.offer(new Pos(0, 0));
        checked[0+OFFSET] = 0;

        while (!que.isEmpty()) {
            Pos now = que.poll();

            if (now.sum == m) {
                return now.cnt;
            }

            for (int coin : coins) {
                int nextSum = now.sum+coin;
                int nextCnt = now.cnt+1;

                if (nextSum < -10000 || nextSum > 10000) continue;

                if (checked[nextSum+OFFSET] > nextCnt) {
                    checked[nextSum+OFFSET] = nextCnt;
                    que.offer(new Pos(nextCnt, nextSum));
                }
            }
        }
        return -1;
    }
}