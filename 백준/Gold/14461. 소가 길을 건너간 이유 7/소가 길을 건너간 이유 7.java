import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, t;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static public class Pos implements Comparable<Pos> {
        int x; int y; int move; int time;
        public Pos (int x, int y, int move, int time) {
            this.x = x; this.y = y; this.move = move; this.time = time;
        }
        @Override
        public int compareTo(Pos o) {
            return this.time - o.time;
        }
    }
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 목초지 n*n
        t = Integer.parseInt(st.nextToken()); // 길을 건너는데 걸리는 시간

        map = new int[n][n]; // 풀을 먹는데 걸리는 시간
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 북서쪽 끝 -> 남동쪽 끝
        dijkstra(0, 0);

        System.out.println(ans);
    }

    private static void dijkstra(int x, int y) {
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(new Pos(x, y, 0, 0));

        // 위치에 몇 번째로 도착했는지에 따라 최소 시간이 달라지므로 방문체크 필요
        boolean[][][] checked = new boolean[n][n][3];

        while (!pq.isEmpty()) {
            Pos now = pq.poll();

            if (checked[now.x][now.y][now.move]) continue;
            checked[now.x][now.y][now.move] = true;

            if (now.x == n-1 && now.y == n-1) {
                ans = now.time;
                return;
            }

            for (int d = 0; d < 4; d++) {
                int nx = dx[d] + now.x;
                int ny = dy[d] + now.y;

                if (rangeCheck(nx, ny) || checked[nx][ny][(now.move+1)%3]) continue;

                // 길을 세 번 건널 때마다 풀을 먹어야 함
                if (now.move == 2) pq.offer(new Pos(nx, ny, 0, now.time+map[nx][ny]+t));
                else pq.offer(new Pos(nx, ny, now.move+1, now.time+t));
            }
        }
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= n;
    }
}