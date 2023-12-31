import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map;
    static boolean[][] checked;
    static int[] dx = {0, -1, 0, 1, -1, -1, 1, 1};
    static int[] dy = {1, 0, -1, 0, 1, -1, 1, -1};
    static Queue<Pos> que;
    static public class Pos {
        int x; int y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        checked = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 && !checked[i][j]) {
                    bfs(i, j);
                    ans++;
                }
            }
        }
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs(int x, int y) {
        que = new LinkedList<>();
        que.offer(new Pos(x, y));
        checked[x][y] = true;

        while (!que.isEmpty()) {
            Pos now = que.poll();

            for (int i = 0; i < 8; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !checked[nx][ny]) {
                    if (map[nx][ny] == 1) {
                        que.offer(new Pos(nx, ny));
                        checked[nx][ny] = true;
                    }
                }
            }
        }
    }
}