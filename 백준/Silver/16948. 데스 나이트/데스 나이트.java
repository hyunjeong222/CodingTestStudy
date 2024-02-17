import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int r1, c1, r2, c2;
    static boolean[][] map;
    static int[] dx = {-2, -2, 0, 0, 2, 2};
    static int[] dy = {-1, 1, -2, 2, -1, 1};
    static Queue<Pos> que;
    static public class Pos {
        int x; int y; int cnt;
        public Pos(int x, int y, int cnt) {
            this.x = x; this.y = y; this.cnt = cnt;
        }
    }
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new boolean[n+1][n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        r1 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());
        bfs(r1, c1);
        System.out.println(ans != Integer.MAX_VALUE ? ans : -1);
    }

    private static void bfs(int r1, int c1) {
        que = new LinkedList<>();
        que.offer(new Pos(r1, c1, 0));
        map[r1][c1] = true;
        while (!que.isEmpty()) {
            Pos now = que.poll();

            if (now.x == r2 && now.y == c2) {
                ans = Math.min(ans, now.cnt);
                return;
            }

            for (int i = 0; i < 6; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;

                if (nx < 0 || nx >= n || ny < 0 || ny >= n || map[nx][ny]) continue;

                map[nx][ny] = true;
                que.offer(new Pos(nx, ny, now.cnt+1));
            }
        }
    }
}