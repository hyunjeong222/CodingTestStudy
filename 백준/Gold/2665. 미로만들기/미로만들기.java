import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int n;
    static int[][] map;
    static int[][] dist;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static public class Pos {
        int x; int y;
        public Pos(int x, int y) {
            this.x = x; this.y = y;
        }
    }
    static Queue<Pos> que;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j) - '0';
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        bfs(0, 0);
        System.out.println(dist[n-1][n-1]); 
    }

    private static void bfs(int x, int y) {
        que = new LinkedList<>();
        que.offer(new Pos(x, y));
        dist[x][y] = 0;

        while (!que.isEmpty()) {
            Pos now = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                if (dist[nx][ny] > dist[now.x][now.y]) {
                    if (map[nx][ny] == 1) dist[nx][ny] = dist[now.x][now.y];
                    else dist[nx][ny] = dist[now.x][now.y] + 1;
                    que.offer(new Pos(nx, ny));
                }
            }
        }
    }
}