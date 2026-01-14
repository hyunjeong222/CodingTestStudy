import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, sx, sy, ex, ey;
    static int[][] map;
    static int[][][] dist;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static PriorityQueue<Pos> que;
    static public class Pos implements Comparable<Pos> {
        int x; int y; int cnt; int dist;
        public Pos (int x, int y, int cnt, int dist) {
            this.x = x; this.y = y; this.cnt = cnt; this.dist = dist;
        }
        @Override
        public int compareTo(Pos o) {
            return this.dist - o.dist;
        }
    }
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken())-1;
        sy = Integer.parseInt(st.nextToken())-1;
        ex = Integer.parseInt(st.nextToken())-1;
        ey = Integer.parseInt(st.nextToken())-1;

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = dijkstra();

        System.out.println(cnt == -1 ? -1 : dist[ex][ey][cnt]);

        br.close();
    }

    private static int dijkstra() {
        dist = new int[n][m][3];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < 3; k++) {
                    dist[i][j][k] = INF;
                }
            }
        }

        que = new PriorityQueue<>();
        que.offer(new Pos(sx, sy, 1, 0));
        dist[sx][sy][1] = 0;

        while (!que.isEmpty()) {
            Pos now = que.poll();

            if (dist[now.x][now.y][now.cnt%3] < now.dist) continue;

            if (now.x == ex && now.y == ey) return now.cnt%3;

            if (now.cnt % 3 == 0) { // 상하좌우
                for (int i = 0; i < 4; i++) {
                    calDist(i, now);
                }
            } else if (now.cnt % 3 == 1) { // 상하
                for (int i = 0; i < 2; i++) {
                    calDist(i, now);
                }
            } else if (now.cnt % 3 == 2) { // 좌우
                for (int i = 2; i < 4; i++) {
                    calDist(i, now);
                }
            }
        }


        return -1;
    }

    private static void calDist(int i, Pos now) {
        int nx = now.x + dx[i];
        int ny = now.y + dy[i];

        if (nx < 0 || nx >= n || ny < 0 || ny >= m) return;
        if (map[nx][ny] == -1) return; // 벽

        if (dist[nx][ny][(now.cnt+1)%3] > map[nx][ny]+now.dist) {
            dist[nx][ny][(now.cnt+1)%3] = map[nx][ny]+now.dist;
            que.offer(new Pos(nx, ny, now.cnt +1, dist[nx][ny][(now.cnt+1)%3]));
        }
    }
}