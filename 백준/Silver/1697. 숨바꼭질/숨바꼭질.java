import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static boolean[] checked;
    static Queue<Pos> que;
    static public class Pos {
        int x; int time;
        public Pos(int x, int time) {
            this.x = x; this.time = time;
        }
    }
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 수빈이 위치
        k = Integer.parseInt(st.nextToken()); // 동생의 위치
        checked = new boolean[100001];
        que = new LinkedList<>();
        que.offer(new Pos(n, 0));
        bfs();
        System.out.println(ans);
    }

    private static void bfs() {
        while (!que.isEmpty()) {
            Pos now = que.poll();
            int cur = now.x;
            int time = now.time;

            if (cur == k) {
                ans = time;
                return;
            }

            checked[cur] = true;

            if (check(cur+1) && !checked[cur+1]) {
                checked[cur+1] = true;
                que.offer(new Pos(cur+1, time+1));
            }

            if (check(cur-1) && !checked[cur-1]) {
                checked[cur-1] = true;
                que.offer(new Pos(cur-1, time+1));
            }

            if (check(cur*2) && !checked[cur*2]) {
                checked[cur*2] = true;
                que.offer(new Pos(cur*2, time+1));
            }
        }
    }

    private static boolean check(int x) {
        return x >= 0 && x < 100001;
    }
}