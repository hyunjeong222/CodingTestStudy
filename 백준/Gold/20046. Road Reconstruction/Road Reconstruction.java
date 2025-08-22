import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] map;
    static int[][] dist;
    static PriorityQueue<Pos> pq;
    static public class Pos implements Comparable<Pos> {
        int x; int y; int cost;
        public Pos (int x, int y, int cost) {
            this.x = x; this.y = y; this.cost = cost;
        }
        @Override
        public int compareTo(Pos o) {
            return this.cost-o.cost;
        }
    }
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static final int INF = 1000*1000+1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //
        if (map[0][0] == -1 || map[n-1][m-1] == -1) {
            System.out.println(-1);
            return;
        }

        int ans = dijkstra(0, 0);

        System.out.println(ans);

        br.close();
    }

    private static int dijkstra(int x, int y) {
        pq = new PriorityQueue<>();
        dist = new int[n][m];
        for (int[] row : dist) {
            Arrays.fill(row, INF);
        }
        dist[x][y] = map[x][y] == 0 ? 0 : map[x][y];
        pq.offer(new Pos(x, y, map[x][y]));

        while (!pq.isEmpty()) {
            Pos now = pq.poll();

            if (now.x == n-1 && now.y == m-1) {
                return dist[now.x][now.y];
            }

            if (now.cost > dist[now.x][now.y]) continue;

            for (int d = 0; d < 4; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if (rangeCheck(nx, ny)) continue;
                if (map[nx][ny] == -1) continue;

                if (dist[nx][ny] > map[nx][ny]+now.cost) {
                    dist[nx][ny] = map[nx][ny] + now.cost;
                    pq.offer(new Pos(nx, ny, dist[nx][ny]));
                }
            }
        }

        return -1;
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }
}