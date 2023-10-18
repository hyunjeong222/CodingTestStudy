import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int sx, sy;
    static int[][] map;
    static boolean[][] checked;
    static int[][] distance;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static public class pos {
        int x, y;
        public pos(int x, int y) {
            this.x = x; this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());;

        map = new int[n][m];
        checked = new boolean[n][m];
        distance = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 2) {
                    sx = i;
                    sy = j;
                } else if (map[i][j] == 1) {
                    map[i][j] = -1;
                }
            }
        }

        bfs(sx, sy);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }
        bw.append(sb + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs(int sx, int sy) {
        Queue<pos> que = new LinkedList<>();
        que.offer(new pos(sx, sy));
        checked[sx][sy] = true;
        map[sx][sy] = 0;

        while (!que.isEmpty()) {
            pos now = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !checked[nx][ny] && map[nx][ny] != 0) {
                    map[nx][ny] = map[now.x][now.y] + 1;
                    checked[nx][ny] = true;
                    que.offer(new pos(nx, ny));
                }
            }
        }
    }
}