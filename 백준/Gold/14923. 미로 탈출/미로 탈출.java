import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int hx, hy, ex, ey;
    static int[][] map;
    static boolean[][][] checked;
    static PriorityQueue<Pos> pq;
    static public class Pos implements Comparable<Pos> {
        int x; int y; int dist; int flag;
        public Pos (int x, int y, int dist, int flag) {
            this.x = x; this.y = y; this.dist = dist; this.flag = flag;
        }
        @Override
        public int compareTo(Pos o) {
            return this.dist - o.dist;
        }
    }
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        hx = Integer.parseInt(st.nextToken())-1;
        hy = Integer.parseInt(st.nextToken())-1;

        st = new StringTokenizer(br.readLine());
        ex = Integer.parseInt(st.nextToken())-1;
        ey = Integer.parseInt(st.nextToken())-1;

        map = new int[n][m];
        checked = new boolean[n][m][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        pq = new PriorityQueue<>();
        pq.offer(new Pos(hx, hy, 0, 0));
        checked[hx][hy][0] = true;

        bfs();

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    private static void bfs() {
        while (!pq.isEmpty()) {
            Pos now = pq.poll();

            if (now.x == ex && now.y == ey) {
                ans = now.dist;
                return;
            }

            for (int d = 0; d < 4; d++) {
                int nx = dx[d] + now.x;
                int ny = dy[d] + now.y;

                if (rangeCheck(nx, ny)) continue;

                if (map[nx][ny] == 0 && !checked[nx][ny][now.flag]) {
                    checked[nx][ny][now.flag] = true;
                    pq.offer(new Pos(nx, ny, now.dist+1, now.flag));
                } else if (map[nx][ny] == 1 && now.flag == 0) {
                    checked[nx][ny][1] = true;
                    pq.offer(new Pos(nx, ny, now.dist+1, 1));
                }
            }
        }
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }
}