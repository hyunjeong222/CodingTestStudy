import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] map;
    static int[] dx = {1, 0};
    static int[] dy = {0, 1};
    static PriorityQueue<Pos> pq;
    static int[][] dist;
    static public class Pos implements Comparable<Pos> {
        int x; int y; int dist;
        public Pos (int x, int y, int dist) {
            this.x = x; this.y = y; this.dist = dist;
        }
        @Override
        public int compareTo(Pos o) {
            return this.dist - o.dist;
        }
    }
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        init();
        dijkstra();

        System.out.println(dist[n-1][n-1]);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void dijkstra() {
        pq = new PriorityQueue<>();
        pq.offer(new Pos(0, 0, 0));
        dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
        }
        dist[0][0] = 0;

        while (!pq.isEmpty()) {
            Pos now = pq.poll();

            if (dist[now.x][now.y] < now.dist) continue;

            for (int d = 0; d < 2; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if (rangeCheck(nx, ny)) continue;

                int nd = map[nx][ny] - map[now.x][now.y] + 1;
                if (nd <= 0) nd = 0;

                if (dist[nx][ny] > now.dist + nd) {
                    dist[nx][ny] = now.dist + nd;
                    pq.offer(new Pos(nx, ny, dist[nx][ny]));
                }
            }
        }
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= n;
    }
}