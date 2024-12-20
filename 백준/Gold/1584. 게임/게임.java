import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int WARNING = 1;
    static final int DEATH = 2;
    static final int INF = 987654321;
    static int[][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static public class Pos implements Comparable<Pos> {
        int x; int y; int dist;
        public Pos (int x, int y, int dist) {
            this.x = x; this.y = y; this.dist = dist;
        }
        @Override
        public int compareTo (Pos o) {
            return this.dist - o.dist;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        map = new int[501][501];

        int n = Integer.parseInt(br.readLine()); // 구역의 수
        int x1, y1, x2, y2;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());

            marking(x1, y1, x2, y2, WARNING);
        }

        int m = Integer.parseInt(br.readLine()); // 구역의 수
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());

            marking(x1, y1, x2, y2, DEATH);
        }

        int ans = bfs();

        System.out.println(ans);
    }

    private static void marking(int x1, int y1, int x2, int y2, int mark) {
        for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++) {
            for (int j = Math.min(y1, y2); j <= Math.max(y1, y2); j++) {
                map[i][j] = mark;
            }
        }
    }

    private static int bfs() {
        PriorityQueue<Pos> dq = new PriorityQueue<>();
        dq.offer(new Pos(0, 0, 0));
        int[][] dist = new int[501][501];
        for (int[] row : dist) Arrays.fill(row, INF);
        dist[0][0] = 0;

        while (!dq.isEmpty()) {
            Pos cur = dq.poll();
            int curDist = cur.dist;

            for (int d = 0; d < 4; d++) {
                int nr = cur.x + dx[d];
                int nc = cur.y + dy[d];

                if (rangeCheck(nr, nc) || map[nr][nc] == DEATH) continue;
                if (dist[nr][nc] <= curDist + map[nr][nc]) continue;

                dist[nr][nc] = curDist + map[nr][nc];
                if (map[nr][nc] == 0) dq.offer(new Pos(nr, nc, curDist));
                else dq.offer(new Pos(nr, nc, curDist+1));
            }
        }

        return dist[500][500] == INF ? -1 : dist[500][500];
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x > 500 || y < 0 || y > 500;
    }
}