import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map;
    static boolean[][][] checked;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<Pos> que;
    static public class Pos {
        int x; int y; int cnt; boolean flag;
        public Pos(int x, int y, int cnt, boolean flag) {
            this.x = x; this.y = y; this.cnt = cnt; this.flag = flag;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        checked = new boolean[n][m][2];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }
        int ans = bfs(0, 0);
        System.out.println(ans);
    }

    private static int bfs(int x, int y) {
        que = new LinkedList<>();
        que.offer(new Pos(x, y, 1, false));
        checked[x][y][0] = true;
        checked[x][y][1] = true;

        while (!que.isEmpty()) {
            Pos now = que.poll();

            if (now.x == n-1 && now.y == m-1) return now.cnt;

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                if (map[nx][ny] == 0) {
                    if (!now.flag && !checked[nx][ny][0]) {
                        que.offer(new Pos(nx, ny, now.cnt+1, false));
                        checked[nx][ny][0] = true;
                    } else if (now.flag && !checked[nx][ny][1]) {
                        que.offer(new Pos(nx, ny, now.cnt+1, true));
                        checked[nx][ny][1] = true;
                    }
                } else {
                    if (!now.flag) {
                        que.offer(new Pos(nx, ny, now.cnt+1, true));
                        checked[nx][ny][1] = true;
                    }
                }
            }
        }
        return -1;
    }
}