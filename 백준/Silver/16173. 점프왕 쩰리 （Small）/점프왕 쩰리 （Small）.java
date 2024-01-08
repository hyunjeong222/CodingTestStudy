import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] map;
    static boolean[][] checked;
    static int[] dx = {0, 1};
    static int[] dy = {1, 0};
    static Queue<Pos> que;
    static public class Pos {
        int x; int y;
        public Pos(int x, int y) {
            this.x = x; this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        checked = new boolean[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bfs(0, 0);
    }

    private static void bfs(int x, int y) {
        que = new LinkedList<>();
        checked[x][y] = true;
        que.offer(new Pos(x, y));

        while (!que.isEmpty()) {
            Pos now = que.poll();

            if (map[now.x][now.y] == -1) {
                System.out.println("HaruHaru");
                return;
            }

            for (int i = 0; i < 2; i++) {
                int nx = now.x + dx[i] * map[now.x][now.y];
                int ny = now.y + dy[i] * map[now.x][now.y];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n || checked[nx][ny]) continue;

                checked[nx][ny] = true;
                que.offer(new Pos(nx, ny));
            }
        }
        System.out.println("Hing");
    }
}