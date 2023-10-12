import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {-1, 0, 1, 0, 0, 0};
    static int[] dy = {0, -1, 0, 1, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};
    static public class pos {
        int z; int x; int y;
        public pos(int z, int x, int y) {
            this.z = z; this.x = x; this.y = y;
        }
    }
    static int m, n, h;
    static int[][][] map;
    static int day;
    static Queue<pos> que;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken()); // 가로
        n = Integer.parseInt(st.nextToken()); // 세로
        h = Integer.parseInt(st.nextToken()); // 높이
        map = new int[h][n][m];
        que = new LinkedList<>();
        for (int k = 0; k < h; k++) {
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    map[k][i][j] = Integer.parseInt(st.nextToken());
                    if (map[k][i][j] == 1) que.offer(new pos(k, i, j));
                }
            }
        }
        bfs();
        bw.close();
        br.close();
    }

    private static void bfs() throws IOException {
        while (!que.isEmpty()) {
            pos now = que.poll();

            for (int d = 0; d < 6; d++) {
                int nz = now.z + dz[d];
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if (nz >= 0 && nz < h && nx >= 0 && nx < n && ny >= 0 && ny < m && map[nz][nx][ny] == 0) {
                    map[nz][nx][ny] = map[now.z][now.x][now.y] + 1;
                    que.offer(new pos(nz, nx, ny));
                }
            }
        }

        for (int k = 0; k < h; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[k][i][j] == 0) {
                        bw.append(-1 + "\n");
                        return;
                    }
                    day = Math.max(day, map[k][i][j]);
                }
            }
        }
        bw.append(day-1 + "\n");
        bw.flush();
    }
}