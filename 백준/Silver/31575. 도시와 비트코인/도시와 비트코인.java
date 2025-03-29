import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] map;
    static boolean[][] checked;
    static int[] dx = {0, 1};
    static int[] dy = {1, 0};
    static public class Pos {
        int x; int y;
        public Pos (int x, int y) {
            this.x = x; this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        checked = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean flag = bfs(0, 0);

        System.out.println(flag ? "Yes" : "No");
    }

    private static boolean bfs(int x, int y) {
        Queue<Pos> que = new LinkedList<>();
        que.offer(new Pos(x, y));
        checked[x][y] = true;

        while (!que.isEmpty()) {
            Pos now = que.poll();

            if (now.x == n-1 && now.y == m-1) return true;

            for (int i = 0; i < 2; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;

                if (rangeCheck(nx, ny)) continue;
                if (!checked[nx][ny] && map[nx][ny] != 0) {
                    que.offer(new Pos(nx, ny));
                    checked[nx][ny] = true;
                }

            }
        }
        return false;
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }
}