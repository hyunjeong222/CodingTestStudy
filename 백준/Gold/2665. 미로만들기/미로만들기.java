import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    static int n;
    static char[][] map;
    static boolean[][] checked;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static PriorityQueue<Pos> pq;
    static public class Pos implements Comparable<Pos> {
        int x; int y; int cnt;
        public Pos(int x, int y, int cnt) {
            this.x = x; this.y = y; this.cnt = cnt;
        }
        @Override
        public int compareTo(Pos o) {
            return this.cnt - o.cnt;
        }
    }
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        // System.out.println(Arrays.deepToString(map));

        System.out.println(bfs());
        br.close();
    }

    private static int bfs() {
        checked = new boolean[n][n];
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
        }
        dist[0][0] = 0;

        pq = new PriorityQueue<>();
        pq.offer(new Pos(0, 0, 0));

        int cnt = 0;

        while (!pq.isEmpty()) {
            Pos now = pq.poll();

            if (checked[now.x][now.y]) continue;
            checked[now.x][now.y] = true;

            if (now.x == n-1 && now.y == n-1) return now.cnt;

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                if (map[nx][ny] == '0') { // 검은방
                    if (dist[nx][ny] > now.cnt+1) {
                        dist[nx][ny] = now.cnt+1;
                        pq.offer(new Pos(nx, ny, dist[nx][ny]));
                    }
                } else {
                    if (dist[nx][ny] > now.cnt) {
                        dist[nx][ny] = now.cnt;
                        pq.offer(new Pos(nx, ny, dist[nx][ny]));
                    }
                }
            }
        }

        return cnt;
    }
}