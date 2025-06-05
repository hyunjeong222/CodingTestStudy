import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, sx, sy;
    static int[] len;
    static boolean[] checked;
    static public class Pos {
        int x; int cnt;
        public Pos (int x, int cnt) {
            this.x = x; this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        len = new int[n+1];
        checked = new boolean[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            len[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken());
        sy = Integer.parseInt(st.nextToken());

        int ans = bfs(sx);

        System.out.println(ans);

        br.close();
    }

    private static int bfs(int x) {
        Queue<Pos> que = new LinkedList<>();
        que.offer(new Pos(x, 0));
        checked[x] = true;

        while (!que.isEmpty()) {
            Pos now = que.poll();

            if (now.x == sy) return now.cnt;

            int move = len[now.x];
            // a>b일지, a<b일지 모름
            // 순방향, 역방향 다 고려해야 함
            for (int i = now.x+move; i <= n; i+=move) {
                if (!checked[i]) {
                    checked[i] = true;
                    que.offer(new Pos(i, now.cnt+1));
                }
            }
            for (int i = now.x-move; i >= 1; i-=move) {
                if (!checked[i]) {
                    checked[i] = true;
                    que.offer(new Pos(i, now.cnt+1));
                }
            }
        }

        return -1;
    }
}