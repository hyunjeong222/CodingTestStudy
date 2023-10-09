import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map;
    static boolean[][] checked;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static public class pos {
        int x;
        int y;
        public pos(int x, int y) {
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
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        bfs(0, 0);
        bw.append(map[n-1][m-1] + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs(int x, int y) {
        Queue<pos> que = new LinkedList<>();
        que.offer(new pos(x, y));
        checked[x][y] = true;

        while (!que.isEmpty()) {
            pos now = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !checked[nx][ny] && map[nx][ny] == 1) {
                    checked[nx][ny] = true;
                    que.offer(new pos(nx, ny));
                    map[nx][ny] = map[now.x][now.y] + 1;
                }
            }
        }
    }
}