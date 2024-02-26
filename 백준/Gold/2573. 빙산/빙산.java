import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map;
    static boolean[][] checked;
    static int[][] zero;
    static Queue<Pos> que;
    static public class Pos {
        int x; int y;
        public Pos(int x, int y) {
            this.x = x; this.y = y;
        }
    }
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        que = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int year = 0;
        while (true) {
            int cnt = countIsland();
            if (cnt >= 2) break;
            else if (cnt == 0) {
                year = 0; break;
            }
            bfs();
            year++;
        }
        System.out.println(year);
    }

    private static int countIsland() {
        checked = new boolean[n][m];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 0 && !checked[i][j]) {
                    dfs(i, j, checked);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static void dfs(int x, int y, boolean[][] checked) {
        checked[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (nx >= n || nx < 0 || ny >= m || ny < 0) continue;

            if (map[nx][ny] != 0 && !checked[nx][ny]) {
                dfs(nx, ny, checked);
            }
        }
    }

    private static void bfs() {
        checked = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 0) {
                    que.offer(new Pos(i, j));
                    checked[i][j] = true;
                }
            }
        }
        zero = new int[n][m];
        while (!que.isEmpty()) {
            Pos now = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;

                if (nx >= n || nx < 0 || ny >= m || ny < 0) continue;

                if (map[nx][ny] == 0) zero[now.x][now.y]++;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (zero[i][j] != 0) {
                    map[i][j] -= zero[i][j];
                    if (map[i][j] < 0) map[i][j] = 0;
                }
            }
        }
    }
}