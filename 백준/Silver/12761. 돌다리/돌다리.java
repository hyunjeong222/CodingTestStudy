import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int a, b, n, m;
    static int[] ans;
    static boolean[] checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 스카이 콩콩 힘
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken()); // 동규 현재 위치
        m = Integer.parseInt(st.nextToken()); // 주미 현재 위치

        ans = new int[100001];
        checked = new boolean[100001];

        bfs(n);

        System.out.println(ans[m]);
    }

    private static void bfs(int start) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(start);
        checked[start] = true;

        while (!que.isEmpty()) {
            int now = que.poll();
            int[] direction = {now-1, now+1, now+a, now+b, now-a, now-b, now*a, now*b};
            for (int i = 0; i < 8; i++) {
                if (rangeCheck(direction[i]) || checked[direction[i]]) continue;
                que.offer(direction[i]);
                checked[direction[i]] = true;
                ans[direction[i]] = ans[now]+1;
            }
            if (ans[m] > 0) break;
        }
    }

    private static boolean rangeCheck(int x) {
        return x < 0 || x >= 100001;
    }
}