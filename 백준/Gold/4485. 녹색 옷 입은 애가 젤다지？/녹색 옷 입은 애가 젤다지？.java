import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] map;
    static int[][] dist;
    static boolean[][] checked;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static PriorityQueue<Pos> pq;
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
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int idx = 1;
        while (true) {
            n = Integer.parseInt(br.readLine());
            if (n == 0) break; // 종료조건
            map = new int[n][n];
            dist = new int[n][n];
            checked = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
            int ans = dijkstra(0, 0);
            sb.append("Problem ").append(idx).append(": ").append(ans).append("\n");
            idx++;
        }
        System.out.println(sb);
    }

    private static int dijkstra(int x, int y) {
        pq = new PriorityQueue<>();
        pq.offer(new Pos(x, y, map[x][y]));
        checked[x][y] = true;
        dist[x][y] = 0;

        while (!pq.isEmpty()) {
            Pos now = pq.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;

                if (nx < 0 || ny < 0 || nx >= n || ny >= n || checked[nx][ny]) continue;

                if (dist[nx][ny] > map[nx][ny] + now.dist) {
                    dist[nx][ny] = map[nx][ny] + now.dist;
                    pq.offer(new Pos(nx, ny, dist[nx][ny]));
                    checked[nx][ny] = true;
                }
            }
        }
        return dist[n-1][n-1];
    }
}