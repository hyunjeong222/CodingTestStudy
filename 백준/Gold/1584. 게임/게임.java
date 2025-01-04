import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 987654321;
    static final int WARNING = 1;
    static final int DEATH = 2;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static PriorityQueue<Pos> pq;
    static public class Pos implements Comparable<Pos> {
        int x; int y;
        int dist;
        public Pos (int x, int y, int dist) {
            this.x = x; this.y = y;
            this.dist = dist;
        }
        @Override
        public int compareTo(Pos o) {
            return this.dist - o.dist;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[501][501];
        int n = Integer.parseInt(br.readLine()); // 위험한 구역의 수
        StringTokenizer st;
        // 위험한 구역의 정보
        int x1, y1, x2, y2;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());

            marking(x1, y1, x2, y2, WARNING);
        }

        int m = Integer.parseInt(br.readLine()); // 죽음의 구역의 수
        // 죽음의 구역의 정보
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());

            marking(x1, y1, x2, y2, DEATH);
        }

        int ans = dijkstra();

        System.out.println(ans);
    }

    private static void marking(int x1, int y1, int x2, int y2, int mark) {
        for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++) {
            for (int j = Math.min(y1, y2); j <= Math.max(y1, y2); j++) {
                map[i][j] = mark;
            }
        }
    }

    private static int dijkstra() {
        int[][] dist = new int[501][501];
        for (int[] row : dist) Arrays.fill(row, INF);

        pq = new PriorityQueue<>();
        pq.offer(new Pos(0, 0, 0));
        dist[0][0] = 0;

        while (!pq.isEmpty()) {
            Pos now = pq.poll();
            int nowDist = now.dist;

            for (int d = 0; d < 4; d++) {
                int nx = dx[d] + now.x;
                int ny = dy[d] + now.y;

                if (rangeCheck(nx, ny) || map[nx][ny] == DEATH) continue;

                if (dist[nx][ny] <= nowDist + map[nx][ny]) continue;

                dist[nx][ny] = nowDist + map[nx][ny];
                if (map[nx][ny] == 0) pq.offer(new Pos(nx, ny, nowDist));
                else pq.offer(new Pos(nx, ny, nowDist+1));
            }
        }

        return dist[500][500] == INF ? -1 : dist[500][500];
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= 501 || y < 0 || y >= 501;
    }
}