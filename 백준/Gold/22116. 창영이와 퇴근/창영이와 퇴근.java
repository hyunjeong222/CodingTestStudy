import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] map;
    static int[][] dist;
    static PriorityQueue<Pos> pq;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static public class Pos implements Comparable<Pos> {
        int x; int y; int dist;
        public Pos(int x, int y, int dist) {
            this.x = x; this.y = y; this.dist = dist;
        }
        @Override
        public int compareTo(Pos o) {
            return this.dist - o.dist;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        dist = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        Dijkstra(0, 0);
        System.out.println(dist[n-1][n-1]);
    }

    private static void Dijkstra(int x, int y) {
        pq = new PriorityQueue<>();
        pq.offer(new Pos(x, y, 0));
        dist[x][y] = 0;

        while (!pq.isEmpty()) {
            Pos now = pq.poll();

            if (now.x == n-1 && now.y == n-1) {
                return;
            }

            if (dist[now.x][now.y] < now.dist) continue;

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;

                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    int slope = Math.max(now.dist, Math.abs(map[now.x][now.y]-map[nx][ny]));
                    if (dist[nx][ny] > slope) {
                        dist[nx][ny] = slope;
                        pq.offer(new Pos(nx, ny, slope));
                    }
                }
            }
        }
    }
}